package com.sparta.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sparta.scheduler.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

}
