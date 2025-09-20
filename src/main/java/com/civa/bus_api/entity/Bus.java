package com.civa.bus_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String busNumber;

    @Column(nullable = false, unique = true)
    private String licensePlate;

    private LocalDateTime createdAt = LocalDateTime.now();

    private String features;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    private boolean active;


}
