package org.sas;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sas.dto.ReportType;
import org.sas.listener.ReportContextInjectorJobListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author Vivek Tiwari
 */
@Getter
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SASApplicationTest
@Sql(scripts = {"classpath:db/h2/seed/classroom.sql"})
public class BloomerBatchApplicationTest {

  private final MockMvc mockMvc;
  private final Job job;
  private final JobLauncher jobLauncher;

  @Test
  @DisplayName("Classroom report generation test.")
  public void classroomReport()
      throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
    jobLauncher.run(job, new JobParametersBuilder()
        .addString(ReportContextInjectorJobListener.REPORT_TYPE, ReportType.CLASSROOM_REPORT.name())
        .toJobParameters());
  }
}
