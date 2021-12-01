package com.example.k8sjava.controller;

import com.example.k8sjava.model.CreateServiceModel;
import com.example.k8sjava.service.svc.SvcService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/svcs")
@RequiredArgsConstructor
public class SvcController {

    private final SvcService svcService;

    @GetMapping(value = "")
    public List<String> getServices(){
        return svcService.getServices();
    }

    @PostMapping(value = "")
    public String getServices(@RequestBody CreateServiceModel serviceModel){
        return svcService.createService(serviceModel);
    }
}
