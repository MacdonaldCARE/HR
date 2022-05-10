package com.tapiwanashembizvo.security.jpa.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);

  //  Optional<User> findByEmail(String email);

    Optional<User> findByEmployeeNumber(String employeeNumber);
}
