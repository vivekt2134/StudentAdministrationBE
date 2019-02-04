package org.sas.listener;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.sas.dto.JasperContext;
import org.sas.dto.ReportType;
import org.sas.writer.jasper.JasperContextHolder;
import org.sas.writer.jasper.mapper.DynamicReportMapper;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

/**
 * @author Vivek Tiwari
 */
@Component
@RequiredArgsConstructor
public class ReportContextInjectorJobListener implements JobExecutionListener {

  public static final String JASPER_CONTEXT = "jasper-context";
  public static final String REPORT_TYPE = "report-type";
  private final JasperContextHolder jasperContextHolder;
  private final DynamicReportMapper dynamicReportMapper;


  @Override
  public void beforeJob(JobExecution jobExecution) {
    jobExecution.getExecutionContext().put(JASPER_CONTEXT,
        jasperContextHolder.getJasperContext(jobExecution.getId(), getReportType(jobExecution)));
  }

  @Override
  public void afterJob(JobExecution jobExecution) {
    JasperContext jasperContext = (JasperContext) jobExecution.getExecutionContext()
        .get(JASPER_CONTEXT);

    try {
      if (jasperContext.getDataSource() != null && jasperContext.getDataSource().size() > 0) {
        JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(
            dynamicReportMapper.dynamicReportDtoToDynamicReport(jasperContext.getDynamicReport()),
            new ClassicLayoutManager(),
            jasperContext.getDataSource());

        jasperContext.getDataSource().clear();
      }

    } catch (JRException e) {
      e.printStackTrace();
    }
  }

  private ReportType getReportType(JobExecution jobExecution) {
    return ReportType.valueOf(jobExecution.getJobParameters().getString(
        ReportContextInjectorJobListener.REPORT_TYPE));
  }
}
