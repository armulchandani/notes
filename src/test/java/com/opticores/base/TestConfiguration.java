package com.opticores.base;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.opticores.config.DatabaseConfiguration;

@Configuration
@ComponentScan(basePackages={"com.opticores.service","com.opticores.dao"})
@Import(DatabaseConfiguration.class)
public class TestConfiguration {

}
