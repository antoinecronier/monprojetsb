package com.tactfactory.monprojetsb.monprojetsb.mocks.repositories;

import java.util.Arrays;
import java.util.Optional;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.tactfactory.monprojetsb.monprojetsb.entities.User;
import com.tactfactory.monprojetsb.monprojetsb.repositories.UserRepository;

public class MockitoUserRepository {
  protected final UserRepository repository;

  public User entity;

  public User resultEntity;

  public Optional<User> resultOptional;

  public MockitoUserRepository(UserRepository repository) {
    this.repository = repository;

    this.entity = new User();
    this.entity.setFirstname("f1");
    this.entity.setLastname("l1");
  }

  public void intialize() {
    // this.configure();

    this.resultEntity = new User();
    this.resultEntity.setId(this.entity.getId());
    this.resultEntity.setFirstname(this.entity.getFirstname());
    this.resultEntity.setLastname(this.entity.getLastname());

    this.resultEntity.setId(1L);
    this.resultOptional = Optional.of(this.resultEntity);

    Mockito.when(this.repository.findById(1L)).thenReturn(this.resultOptional);

    Mockito.when(this.repository.findAll((Pageable) ArgumentMatchers.any()))
        .thenReturn(new PageImpl<>(Arrays.asList(this.resultEntity)));

    Mockito.when(this.repository.save(ArgumentMatchers.any())).thenAnswer(new Answer<User>() {

      @Override
      public User answer(InvocationOnMock invocation) throws Throwable {
        User user = invocation.getArgument(0);
        user.setId(1L);
        return MockitoUserRepository.this.resultEntity;
      }
    });
  }
}
