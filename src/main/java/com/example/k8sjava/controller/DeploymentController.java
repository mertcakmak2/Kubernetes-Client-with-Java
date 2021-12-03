package com.example.k8sjava.controller;

import com.example.k8sjava.model.CreateDeploymentModel;
import com.example.k8sjava.service.deployment.DeploymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deployments")
@RequiredArgsConstructor
public class DeploymentController {

    private final DeploymentService deploymentService;

    @GetMapping(value = "")
    public List<String> getDeployments(){
        return deploymentService.getDeployments();
    }

    @PostMapping(value = "")
    public String getDeployments(@RequestBody CreateDeploymentModel deploymentModel){
        return deploymentService.createDeployment(deploymentModel);
    }

    @DeleteMapping(value = "")
    public String getDeployments(@RequestParam String deploymentName){
        return deploymentService.deleteDeploymentByName(deploymentName);
    }
}
