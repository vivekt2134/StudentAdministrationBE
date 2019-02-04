package org.sas.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

/**
 * Inject jasper context from job execution context to step execution context
 * for each partition context
 * @author Vivek Tiwari
 */
@Component
@RequiredArgsConstructor
public class ReportContextSlaveInjectorStepListener implements StepExecutionListener {

  @Override
  public void beforeStep(StepExecution stepExecution) {
    stepExecution.getExecutionContext().put(ReportContextInjectorJobListener.JASPER_CONTEXT,
        stepExecution.getJobExecution().getExecutionContext()
            .get(ReportContextInjectorJobListener.JASPER_CONTEXT));
  }

  @Override
  public ExitStatus afterStep(StepExecution stepExecution) {
    return stepExecution.getExitStatus();
  }
}
