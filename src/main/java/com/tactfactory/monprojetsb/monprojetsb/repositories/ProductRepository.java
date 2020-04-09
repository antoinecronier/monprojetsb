package com.tactfactory.monprojetsb.monprojetsb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tactfactory.monprojetsb.monprojetsb.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
