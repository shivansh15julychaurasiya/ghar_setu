package com.setu.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "amenities")
public class Amenity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 100)
    private String icon;

}