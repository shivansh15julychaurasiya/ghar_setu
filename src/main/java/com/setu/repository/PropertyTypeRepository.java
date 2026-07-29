package com.setu.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.setu.entity.PropertyType;



public interface PropertyTypeRepository extends JpaRepository<PropertyType, Long> {

    Optional<PropertyType> findByName(String name);

    boolean existsByName(String name);

}
