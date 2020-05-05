package com.tactfactory.monprojetsb.monprojetsb.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import com.tactfactory.monprojetsb.monprojetsb.entities.User;
import com.tactfactory.monprojetsb.monprojetsb.repositories.UserRepository;

@EntityScan(basePackages ="com.tactfactory.monprojetsb")
@ComponentScan(basePackages ="com.tactfactory.monprojetsb")
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
public class UserServiceTest {

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;

  @Test
  public void Test1() {
    long before = userRepository.count();
    userService.Save(new User());
    long after = userRepository.count();
    assertEquals(before + 1, after);
  }
}
