package com.tactfactory.monprojetsb.monprojetsb.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tactfactory.monprojetsb.monprojetsb.MonprojetsbApplicationTests;
import com.tactfactory.monprojetsb.monprojetsb.entities.User;
import com.tactfactory.monprojetsb.monprojetsb.mocks.repositories.MockitoUserRepository;
import com.tactfactory.monprojetsb.monprojetsb.repositories.UserRepository;

import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

@ActiveProfiles("test")
@TestPropertySource(locations = { "classpath:application-test.properties" })
@SpringBootTest(classes = MonprojetsbApplicationTests.class, webEnvironment = WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {

  @Autowired
  private WebApplicationContext wac;

  @Autowired
  private UserController controller;

  @MockBean
  private UserRepository userRepository;

  private MockMvc mockMvc;

  @BeforeEach
  public void setup() throws Exception {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    final MockitoUserRepository mockUser = new MockitoUserRepository(this.userRepository);
    mockUser.intialize();
  }

  @Test
  public void contexLoads() throws Exception {
    assertThat(controller).isNotNull();
  }

  @Test
  public void indexTestView() throws URISyntaxException, Exception {
    ModelAndView mav = mockMvc.perform(
        get(new URI("http://localhost:1234/users/index")))
        .andReturn()
        .getModelAndView();
    assertEquals(mav.getViewName(), "user/index");
  }

  @Test
  public void indexTestPage() throws URISyntaxException, Exception {
    mockMvc.perform(
        get(new URI("http://localhost:1243/users/index")))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("<body>\n" +
            "    <h1> Product index </h1>\n" +
            "    <a href=\"create\">Create new</a>\n" +
            "    <table class=\"table table-bordered table-hover\">\n" +
            "        <tr>\n" +
            "            <th>Firstname</th>\n" +
            "            <th>Lastname</th>\n" +
            "        </tr>"
            )));
  }

  @Test
  public void indexTestPageWrong() throws URISyntaxException, Exception {
    mockMvc.perform(
        get(new URI("http://localhost:1243/users/index")))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("<body>\n" +
            "    <h1> User index </h1>\n" +
            "    <a href=\"create\">Create new</a>\n" +
            "    <table class=\"table table-bordered table-hover\">\n" +
            "        <tr>\n" +
            "            <th>Firstname</th>\n" +
            "            <th>Lastname</th>\n" +
            "        </tr>"
            )));
  }

  @Test
  public void createGetTest() {
  }

  @Test
  public void createPostTest() {
  }

  @Test
  public void deleteTest() {
  }

  @Test
  public void detailsTest() {
  }
}
