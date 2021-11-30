package com.example.k8sjava.service.Namespace;

import java.util.List;

public interface NamespaceService {

    List<String> getNameSpaces();

    String createNamespace(String namespace);
}
