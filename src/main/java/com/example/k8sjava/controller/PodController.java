package com.example.k8sjava.controller;

import com.example.k8sjava.service.pod.PodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pods")
@RequiredArgsConstructor
public class PodController {

    private final PodService podService;

    @GetMapping(value = "")
    public List<String> getPods() {
        return podService.getPods();
    }
}
