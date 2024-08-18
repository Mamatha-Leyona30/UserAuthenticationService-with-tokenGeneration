package com.example.UserAuthenticationService.repository;

import com.example.UserAuthenticationService.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,String> {

    User findByUsernameAndPassword(String username,String password);

    Optional<User> findByUsername(String username);
}
