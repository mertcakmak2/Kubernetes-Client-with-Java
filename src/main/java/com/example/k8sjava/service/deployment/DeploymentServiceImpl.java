package com.example.k8sjava.service.deployment;

import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeploymentServiceImpl implements DeploymentService{

    @Override
    public List<String> getDeployments(){
        KubernetesClient client = new DefaultKubernetesClient();
        var deploymentList = client.apps().deployments().list().getItems();
        List<String> deployments = new ArrayList<>();
        for (Deployment deployment: deploymentList){
            deployments.add(deployment.getMetadata().getName());
        }
        return deployments;
    }
}
