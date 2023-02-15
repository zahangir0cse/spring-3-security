package com.crackcode.zahangir.repository;

import com.crackcode.zahangir.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    int countByUsername(String username);
}
