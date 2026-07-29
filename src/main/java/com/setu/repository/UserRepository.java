package com.setu.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.setu.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    //	Optional<User> findByMobile(String mobile);
    
    @EntityGraph(attributePaths = {"userRoles", "userRoles.role"})
    Optional<User> findByMobile(String mobile);


    Optional<User> findByEmail(String email);

    boolean existsByMobile(String mobile);

    boolean existsByEmail(String email);

}

