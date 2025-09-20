package com.civa.bus_api.repository;

import com.civa.bus_api.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository <Bus, Long> {
}
