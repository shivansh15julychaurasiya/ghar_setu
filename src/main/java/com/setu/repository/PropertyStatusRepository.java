package com.setu.repository;

//package com.gharsetu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.setu.entity.PropertyStatus;

//import com.gharsetu.entity.PropertyStatus;

public interface PropertyStatusRepository extends JpaRepository<PropertyStatus, Long> {

    Optional<PropertyStatus> findByName(String name);

    boolean existsByName(String name);

}