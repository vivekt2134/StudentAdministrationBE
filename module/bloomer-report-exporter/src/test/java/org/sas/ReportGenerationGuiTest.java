package org.sas;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.sas.writer.jasper.datasource.CollectionDataSource;

/**
 * @author Vivek Tiwari
 */
public class ReportGenerationGuiTest {

  public static void main(String[] args) {
    try {
      FastReportBuilder drb = new FastReportBuilder();
      DynamicReport dr;

      dr = drb.addColumn("State", "state", String.class.getName(), 30)
          .addColumn("Branch", "branch", String.class.getName(), 30)
          .addColumn("Product Line", "productLine", String.class.getName(), 50)
          .addColumn("Item", "item", String.class.getName(), 50)
          .addColumn("Item Code", "id", Long.class.getName(), 30, true)
          .addColumn("Quantity", "quantity", Long.class.getName(), 60, true)
          .addColumn("Amount", "amount", Float.class.getName(), 70, true)
          .addGroups(2)
          .setTitle("November 2006 sales report")
          .setSubtitle("This report was generated at " + new Date())
          .setPrintBackgroundOnOddRows(true)
          .setUseFullPageWidth(true)
          .build();

      JRDataSource ds = new CollectionDataSource(getDummyCollectionSmall());
      JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);
//      JasperViewer.viewReport(jp);    //finally display the report report

      int approxBytesRead;
      try (BufferedInputStream bufferedInputStream = new BufferedInputStream(
          new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jp)))) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("report.pdf")) {
          while ((approxBytesRead = bufferedInputStream.available()) > 0) {
            byte[] readBytes = new byte[approxBytesRead];
            bufferedInputStream.read(readBytes);
            fileOutputStream.write(readBytes);
          }
        }
      }


    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static List getDummyCollectionSmall() {

    SimpleDateFormat dateFormat = new SimpleDateFormat();
    dateFormat.applyPattern("dd/MM/yyyy");

    List col = new ArrayList();

    //The collection is ordered by State, Branch and Product Line
    col.add(new Product(new Long("1"), "book", "Harry Potter 7", "Florida", "Main Street",
        new Long("250"), new Float("10000"), true));

    return col;
  }

  @Getter
  @Setter
  @AllArgsConstructor
  public static class Product {

    private Long id;
    private String productLine;
    private String item;
    private String state;
    private String branch;
    private Long quantity;
    private Float amount;
    private Boolean showAsPrices;
  }


}
