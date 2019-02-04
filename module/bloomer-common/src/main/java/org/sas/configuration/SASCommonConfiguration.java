package org.sas.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Vivek Tiwari
 */
@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"org.sas.repository"})
@EntityScan({"org.sas.entity"})
@EnableJpaAuditing
public class SASCommonConfiguration {

}
