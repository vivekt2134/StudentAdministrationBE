package org.sas;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author Vivek Tiwari
 */
@SpringBootApplication
@EnableBatchProcessing
public class BloomerBatchApplication {

  public static void main(String[] args) {
    SpringApplicationBuilder bloomerBuilder = new SpringApplicationBuilder(BloomerBatchApplication.class);
    bloomerBuilder.run();
  }
}
