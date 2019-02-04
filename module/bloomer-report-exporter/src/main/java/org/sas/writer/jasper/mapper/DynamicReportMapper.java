package org.sas.writer.jasper.mapper;

import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.sas.report.jasper.DynamicReportDto;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Vivek Tiwari
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = SimpleColumnToSimpleColumnMapper.class)
public abstract class DynamicReportMapper {

  @Autowired
  private SimpleColumnToSimpleColumnMapper simpleColumnToSimpleColumnMapper;

  /**
   * Builder is required as it set some custom default option values
   */
  public DynamicReport dynamicReportDtoToDynamicReport(DynamicReportDto dynamicReportDto) {
    FastReportBuilder fastReportBuilder = new FastReportBuilder();
    // Add all columns with default fast builder
    dynamicReportDto.getColumns().stream()
        .map(simpleColumnToSimpleColumnMapper::sourceToDestination)
        .forEach(simpleColumn -> {
          try {
            fastReportBuilder.addColumn(simpleColumn.getTitle(),
                simpleColumn.getColumnProperty().getProperty(),
                simpleColumn.getColumnProperty().getValueClassName(),
                simpleColumn.getWidth());
          } catch (ClassNotFoundException e) {
            e.printStackTrace();
          }
        });

    fastReportBuilder.setTitle(dynamicReportDto.getTitle());
    fastReportBuilder.setSubtitle(dynamicReportDto.getSubtitle());
    fastReportBuilder.setPrintBackgroundOnOddRows(dynamicReportDto.isPrintBackgroundOnOddRows());
    fastReportBuilder.setUseFullPageWidth(dynamicReportDto.isUseFullPageWidth());

    return fastReportBuilder.build();
  }

  public abstract DynamicReportDto dynamicReportToDynamicReportDto(DynamicReport dynamicReport);
}
