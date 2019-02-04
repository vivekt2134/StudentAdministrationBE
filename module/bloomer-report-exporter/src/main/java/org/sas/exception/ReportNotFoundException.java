package org.sas.exception;

import static java.lang.String.format;

/**
 * @author Vivek Tiwari
 */
public class ReportNotFoundException extends RuntimeException {

	private String reportName;
	private static final String MESSAGE = "Report not found. Report name - %s";

	public ReportNotFoundException(String reportName) {
		super();
		this.reportName = reportName;
	}

	public ReportNotFoundException(String reportName, Throwable throwable) {
		super(throwable);
		this.reportName = reportName;
	}

	public ReportNotFoundException(String reportName, String customMessage) {
		super(customMessage);
		this.reportName = reportName;
	}

	public ReportNotFoundException(String reportName, String customMessage, Throwable throwable) {
		super(customMessage, throwable);
		this.reportName = reportName;
	}


	@Override
	public String getMessage() {
		return format(MESSAGE, reportName).concat(super.getMessage());
	}
}
