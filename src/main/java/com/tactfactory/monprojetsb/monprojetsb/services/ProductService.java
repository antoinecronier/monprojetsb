package com.tactfactory.monprojetsb.monprojetsb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tactfactory.monprojetsb.monprojetsb.entities.Product;
import com.tactfactory.monprojetsb.monprojetsb.repositories.ProductRepository;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public void Save(Product product) {
    this.productRepository.save(product);
  }
}
