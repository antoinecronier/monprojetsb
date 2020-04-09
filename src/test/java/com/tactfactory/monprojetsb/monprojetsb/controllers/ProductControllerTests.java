package com.tactfactory.monprojetsb.monprojetsb.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import java.util.List;

import javax.servlet.ServletContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.tactfactory.monprojetsb.monprojetsb.MonprojetsbApplication;
import com.tactfactory.monprojetsb.monprojetsb.entities.Product;
import com.tactfactory.monprojetsb.monprojetsb.repositories.ProductRepository;

@ActiveProfiles("test")
@TestPropertySource(locations = { "classpath:application-test.properties" })
@SpringBootTest(classes = MonprojetsbApplication.class)
public class ProductControllerTests {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;

  @BeforeEach
  public void setup() throws Exception {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  public void productControllerExists() {
    ServletContext servletContext = wac.getServletContext();

    assertNotNull(servletContext);
    assertTrue(servletContext instanceof MockServletContext);
    assertNotNull(wac.getBean("productController"));
  }

  @Test
  public void productControllerListPageExists() throws Exception {
    this.mockMvc.perform(get("/products/index")).andDo(print())
        .andExpect(view().name("product/index"));
  }

  @Test
  public void productControllerCreateProduct() throws Exception {
    Long countPrev = productRepository.count();

    MultiValueMap<String, String> params = new LinkedMultiValueMap<String,String>();
    params.add("name", "product 1");
    params.add("price", String.valueOf(10f));

    this.mockMvc.perform(post("/products/create").params(params)).andDo(print())
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:index"))
        .andExpect(redirectedUrl("index"));


    Long countAfter = productRepository.count();
    assertEquals(countPrev + 1, countAfter);

    List<Product> products = productRepository.findAll();
    assertEquals("product 1", products.get(0).getName());
    assertEquals(10f, products.get(0).getPrice());
  }

  @Test
  public void productControllerIndexContainProducts() throws Exception {
    Product p1 = new Product();
    p1.setName("p1");
    p1.setPrice(0f);
    this.productRepository.save(p1);
    Product p2 = new Product();
    p2.setName("p2");
    p2.setPrice(0f);
    this.productRepository.save(p2);
    Product p3 = new Product();
    p3.setName("p3");
    p3.setPrice(0f);
    this.productRepository.save(p3);

    this.mockMvc.perform(get("/products/index")).andDo(print())
        //.andExpect(status().isOk())
    .andExpect(xpath("//input[@name='id'][@value='" + p1.getId() +"']").exists())
    .andExpect(xpath("//input[@name='id'][@value='" + p2.getId() +"']").exists())
    .andExpect(xpath("//input[@name='id'][@value='" + p3.getId() +"']").exists())
    .andExpect(xpath("//input[@name='id']").nodeCount((int) productRepository.count()));
  }
}
