package com.tactfactory.monprojetsb.monprojetsb.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import com.tactfactory.monprojetsb.monprojetsb.entities.Product;
import com.tactfactory.monprojetsb.monprojetsb.repositories.ProductRepository;

@EntityScan(basePackages ="com.tactfactory.monprojetsb")
@ComponentScan(basePackages ="com.tactfactory.monprojetsb")
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
public class ProductServiceTest {

  @Autowired
  private ProductService productService;

  @Autowired
  private ProductRepository productRepository;

  @Test
  public void Test1() {
    long before = productRepository.count();
    productService.Save(new Product());
    long after = productRepository.count();
    assertEquals(before + 1, after);
  }
}
