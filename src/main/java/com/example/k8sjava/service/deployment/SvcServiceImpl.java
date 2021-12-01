package com.example.k8sjava.service.deployment;

import com.example.k8sjava.model.CreateServiceModel;
import com.example.k8sjava.service.namespace.NamespaceService;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SvcServiceImpl implements SvcService {

    @Value("${k8s.svc.kind}")
    private final String svcKind;
    @Value("${k8s.svc.api-version}")
    private final String svcApiVersion;
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

        Service service = new Service();
        service.setApiVersion(svcApiVersion);
        service.setKind(svcKind);

        // Service yaml Metadata
        ObjectMeta meta = new ObjectMeta();
        meta.setName(serviceModel.getName());
        meta.setNamespace(namespace);
        meta.setLabels(serviceModel.getLabels());
        service.setMetadata(meta);

        // Service yaml Spec
        IntOrString targetPort = new IntOrString(serviceModel.getTargetPort());
        ServicePort port = new ServicePort(null,null, serviceModel.getNodePort(), serviceModel.getPort(), serviceModel.getProtocol(), targetPort);
        ServiceSpec spec = new ServiceSpec();
        spec.setPorts(Arrays.asList(port));
        spec.setType(serviceModel.getType());
        service.setSpec(spec);

        Service createdService = client.services().create(service);
        return createdService.getMetadata().getName()+" service created";
    }

}
