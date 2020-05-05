package com.tactfactory.monprojetsb.monprojetsb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tactfactory.monprojetsb.monprojetsb.entities.Product;
import com.tactfactory.monprojetsb.monprojetsb.entities.User;
import com.tactfactory.monprojetsb.monprojetsb.repositories.ProductRepository;
import com.tactfactory.monprojetsb.monprojetsb.repositories.UserRepository;

@Controller
@RequestMapping(value= {"users"})
public class UserController {

  @Autowired
  private UserRepository userRepository;
//  @Autowired
//  private ProductRepository productRepository;

  @RequestMapping(value = {"/index", "/"})
  public String index(Model model) {
//    User newUser = new User();
//    newUser.setFirstname("utilisateur");
//    newUser.setLastname("2");
//    Product u2P1 = new Product();
//    u2P1.setName("p2");
//    u2P1.setPrice(10.36F);
//    Product u2P2 = new Product();
//    u2P2.setName("p3");
//    u2P2.setPrice(20.36F);
//
//    productRepository.saveAndFlush(u2P1);
//    productRepository.saveAndFlush(u2P2);
//
//    newUser.getProducts().add(u2P1);
//    newUser.getProducts().add(u2P2);
//
//    userRepository.saveAndFlush(newUser);

    model.addAttribute("page", "Product index");
    model.addAttribute("items", userRepository.findAll());
    return "user/index";
  }

  @GetMapping(value = {"/create"})
  public String createGet(Model model) {
    model.addAttribute("page", "Product create");
    return "user/create";
  }

  @PostMapping(value = {"/create"})
  public String createPost(@ModelAttribute User user) {

    if (user != null) {
      userRepository.save(user);
    }

    return "redirect:index";
  }

  @PostMapping(value = {"/delete"})
  public String delete(@RequestParam Long id) {

    if (id != null) {
      userRepository.deleteById(id);
    }

    return "redirect:/users/index";
  }

  @GetMapping(value = {"/{id}/details"})
  public String details(@PathVariable(value = "id") Long id, Model model) {

    model.addAttribute("page", "User details");
    model.addAttribute("backlink", "/users/index");

    if (id != null) {
      model.addAttribute("item",userRepository.findById(id).get());
    }

    return "user/details";
  }
}
