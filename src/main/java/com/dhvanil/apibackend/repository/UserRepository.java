package com.dhvanil.apibackend.repository;

import com.dhvanil.apibackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

}
