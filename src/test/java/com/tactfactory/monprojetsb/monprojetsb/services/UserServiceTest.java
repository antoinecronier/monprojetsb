package com.tactfactory.monprojetsb.monprojetsb.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import com.tactfactory.monprojetsb.monprojetsb.MonprojetsbApplicationTests;
import com.tactfactory.monprojetsb.monprojetsb.entities.User;
import com.tactfactory.monprojetsb.monprojetsb.mocks.repositories.MockitoProductRepository;
import com.tactfactory.monprojetsb.monprojetsb.mocks.repositories.MockitoUserRepository;
import com.tactfactory.monprojetsb.monprojetsb.repositories.ProductRepository;
import com.tactfactory.monprojetsb.monprojetsb.repositories.UserRepository;

@ActiveProfiles("test")
@TestPropertySource(locations = { "classpath:application-test.properties" })
@SpringBootTest(classes = MonprojetsbApplicationTests.class)
public class UserServiceTest {

  @Autowired
  private UserService userService;

  @MockBean
  private UserRepository userRepository;

  @MockBean
  private ProductRepository productRepository;

  private User entity;

  @BeforeEach
  public void setUp() throws Exception {
    final MockitoUserRepository mockUser = new MockitoUserRepository(this.userRepository);
    mockUser.intialize();
    this.entity = mockUser.entity;

    final MockitoProductRepository mockProduct = new MockitoProductRepository(this.productRepository);
    mockProduct.intialize();
  }

  @Test
  public void Test1() {
    long before = userRepository.count();
    userService.Save(this.entity);
    long after = userRepository.count();
    userRepository.delete(this.entity);
    productRepository.count();
    assertEquals(before + 1, after);
  }
}
