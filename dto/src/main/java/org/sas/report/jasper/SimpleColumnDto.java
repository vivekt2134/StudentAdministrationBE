package org.sas.report.jasper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Vivek Tiwari
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimpleColumnDto {

  private String name;
  private String displayValue;
  private String targetClass;
  private ColumnPropertyDto columnProperty;
  private int width;

}
