package com.tactfactory.monprojetsb.monprojetsb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = { "classpath:application.properties" })
public class MonprojetsbApplication {

  public static void main(String[] args) {
    SpringApplication.run(MonprojetsbApplication.class, args);
  }

}
