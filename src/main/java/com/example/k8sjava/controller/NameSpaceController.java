package com.example.k8sjava.controller;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.util.Config;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/ns")
public class NameSpaceController {

    public String createNamespace() throws IOException, ApiException {
        ApiClient client  = Config.defaultClient();
        Configuration.setDefaultApiClient(client);
        CoreV1Api api = new CoreV1Api();

        V1Namespace ns = new V1Namespace();
        ns.setApiVersion("Namespace");
        V1Namespace namespace = api.createNamespace(ns, null, null, null);
        return namespace.toString();
    }
}
