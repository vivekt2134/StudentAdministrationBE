package org.sas.writer.jasper;

import org.sas.dto.JasperContext;
import org.sas.dto.ReportType;

/**
 * @author Vivek Tiwari
 */
public interface JasperContextHolder {

  <T> JasperContext<T> getJasperContext(Long stepExecutionId, ReportType reportType);

}
