package com.example.k8sjava.service.pod;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class PodServiceImpl implements PodService{

    @Override
    public List<String> getPods() {
        KubernetesClient client = new DefaultKubernetesClient();
        var podList = client.pods().list();
        List<String> pods = new ArrayList<>();
        for (Pod pod : podList.getItems()) {
            pods.add(pod.getMetadata().getName());
        }
        return pods;
    }
}
