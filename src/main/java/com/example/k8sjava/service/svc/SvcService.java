package com.example.k8sjava.service.svc;

import com.example.k8sjava.model.CreateServiceModel;

import java.util.List;

public interface SvcService {
    List<String> getServices();

    String createService(CreateServiceModel serviceModel);
}
