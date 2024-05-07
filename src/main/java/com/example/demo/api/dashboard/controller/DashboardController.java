package com.example.demo.api.dashboard.controller;

import com.example.demo.api.dashboard.dto.DashboardInfoDTO;
import com.example.demo.api.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardInfoDTO> getDashboardInfo() {
        return ResponseEntity.ok(dashboardService.getDashboardInfo());
    }
}
