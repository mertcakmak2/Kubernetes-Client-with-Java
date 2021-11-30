package com.example.k8sjava.controller;

import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ns")
public class NameSpaceController {

    @PostMapping(value = "")
    public String createNamespace(@RequestParam String namespace) {
        KubernetesClient client = new DefaultKubernetesClient();

        Namespace ns = new Namespace();
        ns.setApiVersion("v1");
        ns.setKind("Namespace");

        ObjectMeta objectMeta = new ObjectMeta();
        objectMeta.setName(namespace);

        ns.setMetadata(objectMeta);

        var savedNamespace = client.namespaces().create(ns);
        return savedNamespace.getMetadata().getName()+" namespace created";
    }
}
