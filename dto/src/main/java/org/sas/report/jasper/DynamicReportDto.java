package org.sas.report.jasper;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Vivek Tiwari
 */
@Getter
@RequiredArgsConstructor
public class DynamicReportDto {

  private String title;
  private String subtitle;
  private boolean printBackgroundOnOddRows;
  private boolean useFullPageWidth;
  private List<SimpleColumnDto> columns;

}
