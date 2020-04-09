package com.tactfactory.monprojetsb.monprojetsb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tactfactory.monprojetsb.monprojetsb.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
