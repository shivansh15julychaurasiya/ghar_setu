package com.setu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.setu.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findById(Long id);

    Optional<Role> findByName(String name);

}
