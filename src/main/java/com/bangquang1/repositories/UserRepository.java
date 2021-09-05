package com.bangquang1.repositories;

import com.bangquang1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByUsername(String username);
//
//    Boolean existsByUsername(String username);
//
//    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByPassword(String password);

    User getUserByUsername(String username);

}
