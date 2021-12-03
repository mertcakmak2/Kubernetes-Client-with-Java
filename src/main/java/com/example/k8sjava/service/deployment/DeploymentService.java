package com.example.k8sjava.service.deployment;

import com.example.k8sjava.model.CreateDeploymentModel;

import java.util.List;

public interface DeploymentService {
    List<String> getDeployments();

    String createDeployment(CreateDeploymentModel deploymentModel);
}
