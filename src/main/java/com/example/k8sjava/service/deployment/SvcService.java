package com.example.k8sjava.service.deployment;

import com.example.k8sjava.model.CreateServiceModel;

import java.util.List;

public interface SvcService {
    List<String> getServices();

    String createService(CreateServiceModel serviceModel);
}
