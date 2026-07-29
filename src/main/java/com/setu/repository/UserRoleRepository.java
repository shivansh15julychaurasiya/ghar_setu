package com.setu.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.setu.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

}
