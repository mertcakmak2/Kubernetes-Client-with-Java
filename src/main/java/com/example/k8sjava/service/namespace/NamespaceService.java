package com.example.k8sjava.service.namespace;

import java.util.List;

public interface NamespaceService {

    List<String> getNameSpaces();

    boolean isNotExistNamespace(String name);

    String createNamespace(String namespace);
}
