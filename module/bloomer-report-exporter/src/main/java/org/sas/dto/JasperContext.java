package org.sas.dto;

import lombok.*;
import org.sas.writer.jasper.datasource.MutableDataSource;

import org.sas.report.jasper.DynamicReportDto;

/**
 * @author Vivek Tiwari
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class JasperContext<T> {

	private DynamicReportDto dynamicReport;
	private MutableDataSource<T> dataSource;

}
