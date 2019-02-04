package org.sas.writer.jasper;

import static java.util.Objects.nonNull;

import ar.com.fdvs.dj.domain.DynamicReport;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.sas.dto.JasperContext;
import org.sas.dto.ReportType;
import org.sas.exception.ReportNotFoundException;
import org.sas.service.ObjectMapperService;
import org.sas.writer.jasper.datasource.CollectionDataSource;
import org.sas.report.jasper.DynamicReportDto;
import org.sas.writer.jasper.resource.ResourceService;
import org.springframework.stereotype.Component;

/**
 * @author Vivek Tiwari
 */
@RequiredArgsConstructor
@Getter
@Setter
@Component
public class JasperContextHolderImpl implements JasperContextHolder {

  private static final Map<JasperContextKey, JasperContext> contextHolder = new ConcurrentHashMap<>();
  private final ObjectMapperService mapperService;
  private final ResourceService resourceService;


  /**
   * Create DynamicReport, initializes @{@link DynamicReport} format required to generate jasper
   * report.
   *
   * @return @{@link JasperContext}
   */
  @Override
  public <T> JasperContext<T> getJasperContext(Long stepExecutionId, ReportType reportType) {
    JasperContextKey jasperContextKey = JasperContextKey.builder()
        .id(stepExecutionId)
        .reportName(reportType.getReportName())
        .build();

    JasperContext jasperContext = contextHolder.get(jasperContextKey);

    if (nonNull(jasperContext)) {
      return jasperContext;
    }

    DynamicReportDto dynamicReport = mapperService.readValueAs(resourceService.getResourceAsText(reportType)
                .orElseThrow(() -> new ReportNotFoundException(reportType.getReportName())),
            DynamicReportDto.class);

    contextHolder.putIfAbsent(jasperContextKey, JasperContext.builder()
        .dynamicReport(dynamicReport)
        .dataSource(new CollectionDataSource<>())
        .build());

    return contextHolder.get(jasperContextKey);
  }

  @Getter
  @RequiredArgsConstructor
  @EqualsAndHashCode
  @Builder
  private static class JasperContextKey {

    private final Long id;
    private final String reportName;

  }

}
