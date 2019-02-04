package org.sas.dto;

import lombok.Getter;

/**
 * @author Vivek Tiwari
 */
@Getter
public enum ReportType {
	CLASSROOM_REPORT("classroom-report");

	private final String reportName;

	ReportType(String reportName) {
		this.reportName = reportName;
	}
}
