package com.civa.bus_api.controller;

import com.civa.bus_api.entity.Bus;
import com.civa.bus_api.service.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bus")
@RequiredArgsConstructor
public class BusController {

    private final BusService busService;

    // GET /bus?page=0&size=5
    @GetMapping
    public Page<Bus> getAllBuses(Pageable pageable) {
        return busService.getAll(pageable);
    }

    // GET /bus/{id}
    @GetMapping("/{id}")
    public Bus getBusById(@PathVariable Long id) {
        return busService.getById(id);
    }

}
