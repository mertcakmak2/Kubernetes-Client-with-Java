package com.example.k8sjava.service.Namespace;

import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NamespaceServiceImpl implements NamespaceService{

    @Override
    public List<String> getNameSpaces() {
        KubernetesClient client = new DefaultKubernetesClient();
        var namespaceList = client.namespaces().list();
        List<String> namespaces = new ArrayList<>();
        for (Namespace ns : namespaceList.getItems()) {
            namespaces.add(ns.getMetadata().getName());
        }
        return namespaces;
    }

    @Override
    public String createNamespace(String namespace) {
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
