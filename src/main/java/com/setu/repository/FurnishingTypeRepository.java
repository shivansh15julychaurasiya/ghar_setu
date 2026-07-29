package com.setu.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.setu.entity.FurnishingType;



public interface FurnishingTypeRepository extends JpaRepository<FurnishingType, Long> {

    Optional<FurnishingType> findByName(String name);

    boolean existsByName(String name);

}