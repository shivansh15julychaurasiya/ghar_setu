package com.setu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.setu.entity.PropertyPurpose;



public interface PropertyPurposeRepository extends JpaRepository<PropertyPurpose, Long> {

    Optional<PropertyPurpose> findByName(String name);

    boolean existsByName(String name);

}
