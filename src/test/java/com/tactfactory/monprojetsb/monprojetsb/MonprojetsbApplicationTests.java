package com.tactfactory.monprojetsb.monprojetsb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;

@SpringBootApplication
@PropertySource(value = { "classpath:application-test.properties" })
public class MonprojetsbApplicationTests {

  @Test
  void contextLoads() {
  }

}
