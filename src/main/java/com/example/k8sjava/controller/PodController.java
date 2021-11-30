package com.example.k8sjava.controller;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pods")
@RequiredArgsConstructor
public class PodController {

    @GetMapping(value = "/fabric")
    public List<String> listAllPodsWithFabric() {
        KubernetesClient client = new DefaultKubernetesClient();
        var podList = client.pods().list();
        List<String> pods = new ArrayList<>();
        for (Pod pod : podList.getItems()) {
            pods.add(pod.getMetadata().getName());
        }
        return pods;
    }
}
