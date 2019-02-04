package org.sas.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vivek Tiwari
 */
@RequiredArgsConstructor
@RestController("/classroom/batch")
public class ClassroomBatchJobController {

  private final Job classroomReportJob;
  private final JobLauncher jobLauncher;

  @GetMapping("/launch")
  public void launchBatch() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
    jobLauncher.run(classroomReportJob, new JobParameters());
  }
}
