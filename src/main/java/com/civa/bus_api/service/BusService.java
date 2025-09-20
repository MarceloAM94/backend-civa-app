package com.civa.bus_api.service;

import com.civa.bus_api.entity.Bus;
import com.civa.bus_api.repository.BusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusService {

    private final BusRepository busRepository;

    public Page<Bus> getAll(Pageable pageable) {
        return busRepository.findAll(pageable);
    }

    public Bus getById(Long id) {
        return busRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bus not found with id: " + id));
    }
}