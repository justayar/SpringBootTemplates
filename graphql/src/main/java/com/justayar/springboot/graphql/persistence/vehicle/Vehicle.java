package com.justayar.springboot.graphql.persistence.vehicle;


import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.justayar.springboot.graphql.dto.FuelType;
import com.justayar.springboot.graphql.persistence.brand.Brand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique=true)
    private String name;

    private int horsePower;

    private int bodyMass;

    private FuelType fuelType;

    @OneToOne
    private Brand brand;

    @CreatedDate
    private Date createdAt;
}
