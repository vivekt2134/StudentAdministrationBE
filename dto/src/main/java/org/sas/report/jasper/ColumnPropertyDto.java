package org.sas.report.jasper;

import java.util.HashMap;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Vivek Tiwari
 */
@Getter
@Setter
public class ColumnPropertyDto {

  private String property;
  private String valueClassName;
  private HashMap fieldProperties = new HashMap();

}
