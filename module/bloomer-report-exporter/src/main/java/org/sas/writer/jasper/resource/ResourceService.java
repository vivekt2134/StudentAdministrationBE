package org.sas.writer.jasper.resource;

import lombok.Getter;
import org.apache.commons.io.IOUtils;
import org.sas.dto.ReportType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Optional;

import static org.sas.writer.jasper.resource.ResourceService.ResourceType.JSON;

/**
 * @author Vivek Tiwari
 */
@Service
public class ResourceService {

	private Resource getResource(String reportName) {
		return new ClassPathResource(new StringBuilder("report")
				.append("/")
				.append(reportName + JSON.getFileExtension())
				.toString());
	}

	public Optional<String> getResourceAsText(ReportType reportType) {
		Resource resource = getResource(reportType.getReportName());
		Optional<String> output = Optional.empty();
		try {
			if (resource.exists()) {
				output = Optional.of(IOUtils.toString(resource.getInputStream(), Charset.defaultCharset()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return output;
	}

	@Getter
	enum ResourceType {
		JSON(".json"), XML(".xml");

		String fileExtension;

		ResourceType(String extension) {
			this.fileExtension = extension;
		}
	}
}
