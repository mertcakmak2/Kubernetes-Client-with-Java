package com.example.k8sjava.controller;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.util.Config;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pods")
@RequiredArgsConstructor
public class PodController {

    @GetMapping(value = "")
    public List<String> listAllPods() throws IOException, ApiException {
        ApiClient client  = Config.defaultClient();
        Configuration.setDefaultApiClient(client);

        CoreV1Api api = new CoreV1Api();
        V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
        List<String> pods = new ArrayList<>();
        for (V1Pod item : list.getItems()) {
            pods.add(item.getMetadata().getName());
        }
        return pods;
    }

    @GetMapping(value = "/fabric")
    public List<String> listAllPodsWithFabric() throws IOException, ApiException {
        KubernetesClient client = new DefaultKubernetesClient();
        var podList = client.pods().list();
        List<String> pods = new ArrayList<>();
        for (Pod pod : podList.getItems()) {
            pods.add(pod.getMetadata().getName());
        }
        return pods;
    }
}
