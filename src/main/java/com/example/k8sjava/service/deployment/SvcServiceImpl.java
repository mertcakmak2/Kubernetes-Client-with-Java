package com.example.k8sjava.service.deployment;

import com.example.k8sjava.model.CreateServiceModel;
import com.example.k8sjava.service.namespace.NamespaceService;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SvcServiceImpl implements SvcService {

    @Value("${k8s.svc.kind}")
    private String svcKind;
    @Value("${k8s.svc.api-version}")
    private String svcApiVersion;
    private final NamespaceService namespaceService;

    @Override
    public List<String> getServices(){
        KubernetesClient client = new DefaultKubernetesClient();
        var serviceList = client.services().list();
        List<String> services = new ArrayList<>();
        for (Service service : serviceList.getItems()) {
            services.add(service.getMetadata().getName());
        }
        return services;
    }

    @Override
    public String createService(CreateServiceModel serviceModel){
        KubernetesClient client = new DefaultKubernetesClient();

        String namespace = namespaceService.createNamespace(serviceModel.getNamespace());

        Service service = new ServiceBuilder()
                .withNewMetadata()
                .withName(serviceModel.getName())
                .withLabels(serviceModel.getLabels())
                .endMetadata()
                .withNewSpec()
                //.withSelector(Collections.singletonMap("app", "MyApp"))
                .addNewPort()
                .withProtocol(serviceModel.getProtocol())
                .withPort(serviceModel.getPort())
                .withTargetPort(new IntOrString(serviceModel.getTargetPort()))
                .withNodePort(serviceModel.getNodePort())
                .endPort()
                .withType(serviceModel.getType())
                .endSpec()
                .build();

        System.out.println(namespace);
        service = client.services().inNamespace(namespace).create(service);

        return service.getMetadata().getName()+" service created";
    }

}
