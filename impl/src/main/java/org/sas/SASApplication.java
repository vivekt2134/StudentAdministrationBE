package org.sas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Vivek Tiwari
 */
@SpringBootApplication
public class SASApplication {

	public static void main(String[] args) {
		SpringApplication.run(SASApplication.class, args);
	}

}
