package org.sas.configuration;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.sas.Classroom;
import org.sas.entity.ClassroomEntity;
import org.sas.partitioner.RowPartitioner;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.HibernateCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author Vivek Tiwari
 */
@Configuration
@RequiredArgsConstructor
public class ClassroomBatchJobConfiguration {

  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;
  private final JdbcTemplate jdbcTemplate;

  private final static String CLASSROOM_JOB_NAME = "bloomer.classroom.report.job";
  private final static String CLASSROOM_MASTER_STEP_NAME = "bloomer.classroom.report-step.master";
  private final static String CLASSROOM_SLAVE_STEP_NAME = "bloomer.classroom.report-step.slave";
  private final static String CLASSROOM_REPORT_READER = "bloomer.classroom.report.reader";

  @Bean
  public Job classroomReportJob(Step classroomReportStep,
      JobExecutionListener reportContextInjectorJobListener) {
    return jobBuilderFactory.get(CLASSROOM_JOB_NAME)
        .incrementer(new RunIdIncrementer())
        .start(classroomReportStep)
        .listener(reportContextInjectorJobListener)
        .build();
  }

  @Bean
  public Step classroomReportStep(
      @Value("${bloomer.report.classroom.batch.threads}") int noOfThreads,
      @Value("${bloomer.report.classroom.batch.chunk.size:10}") int chunkSize,
      ItemStreamReader<ClassroomEntity> classroomReportReader,
      ItemProcessor<ClassroomEntity, Classroom> classroomReportProcessor,
      ItemWriter<Classroom> classroomReportWriter,
      StepExecutionListener reportContextSlaveInjectorStepListener,
      TaskExecutor taskExecutor) {

    return stepBuilderFactory.get(CLASSROOM_MASTER_STEP_NAME)
        .partitioner(stepBuilderFactory.get(CLASSROOM_SLAVE_STEP_NAME)
            .<ClassroomEntity, Classroom>chunk(chunkSize)
            .reader(classroomReportReader)
            .processor(classroomReportProcessor)
            .writer(classroomReportWriter)
            .listener(reportContextSlaveInjectorStepListener)
            .allowStartIfComplete(true)
            .taskExecutor(taskExecutor)
            .build())
        .partitioner(CLASSROOM_SLAVE_STEP_NAME, RowPartitioner.builder()
            .jdbcTemplate(jdbcTemplate)
            .column("id")
            .table("classroom")
            .build())
        .gridSize(noOfThreads)
        .allowStartIfComplete(true)
        .build();
  }

  @Bean
  @StepScope
  public ItemStreamReader<ClassroomEntity> classroomReportReader(
      EntityManagerFactory entityManagerFactory,
      @Value("#{stepExecutionContext[minId]}") Long minId,
      @Value("#{stepExecutionContext[maxId]}") Long maxId,
      @Value("#{stepExecutionContext[query]}") String query,
      @Value("${bloomer.report.fetch-size:100}") int fetchSize) {

    Map<String, Object> parameters = new HashMap<>();
    parameters.put("minId", minId);
    parameters.put("maxId", maxId);

    return new HibernateCursorItemReaderBuilder<ClassroomEntity>()
        .name(CLASSROOM_REPORT_READER)
        .fetchSize(fetchSize)
        .sessionFactory(entityManagerFactory.unwrap(SessionFactory.class))
        .queryString(query)
        .parameterValues(parameters)
        .build();
  }

  @Bean
  public TaskExecutor taskExecutor(
      @Value("${spring.batch.thread-executor.core-pool-size:10}") int corePoolSize,
      @Value("${spring.batch.thread-executor.max-pool-size:15}") int maxPoolSize,
      @Value("${spring.batch.thread-executor.queue-size:40}") int queueCapacity) {

    ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
    threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
    threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
    threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
    return threadPoolTaskExecutor;
  }


}
