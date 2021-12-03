package com.example.k8sjava.service.deployment;

import com.example.k8sjava.model.CreateDeploymentModel;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.DeploymentBuilder;
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

    public String createDeployment(CreateDeploymentModel deploymentModel){

        //Todo : null vermeyi dene  yada deployment yaml dosyasındaki container portu sil.
        Deployment deployment = new DeploymentBuilder()
                .withNewMetadata()
                .withName(deploymentModel.getName())
                .endMetadata()
                .withNewSpec()
                .withReplicas(deploymentModel.getReplicas())
                .withNewTemplate()
                .withNewMetadata()
                .addToLabels(deploymentModel.getMetadataLabels())
                .endMetadata()
                .withNewSpec()
                .addNewContainer()
                .withCommand(deploymentModel.getCommand())
                .withArgs(deploymentModel.getArgs())
                .withName(deploymentModel.getContainerName())
                .withImage(deploymentModel.getImage())
                .addNewPort()
                .withContainerPort(deploymentModel.getContainerPort())
                .endPort()
                .endContainer()
                .endSpec()
                .endTemplate()
                .withNewSelector()
                .addToMatchLabels(deploymentModel.getSelectorLabels())
                .endSelector()
                .endSpec()
                .build();

        return "deployment created";
    }
}
