
## API Kullanımı

#### Kubernetes içerisinde bir Service nesnesi oluşturur.

```http
  POST /api/svcs
```

| Parametre | Tip     | 
| :-------- | :------- 
| `createServiceModel` | `CreateServiceModel`

#### Kubernetes içerisinde bir Deployment nesnesi oluşturur.

```http
  POST /api/deployments
```

| Parametre | Tip     | 
| :-------- | :------- | 
| `createDeploymentModel`      | `CreateDeploymentModel` | 




## Kullanım/Örnekler

```java

createServiceModel:
{
  "namespace": "redis-cluster",
  "name": "svc-redis",
  "labels": {
    "app": "deployment-redis"
  },
  "port": 6380,
  "targetPort": 6380,
  "nodePort": 32666,
  "protocol": "TCP",
  "type": "NodePort"
}

createDeploymentModel:
{
  "namespace": "redis-cluster",
  "name": "deployment-redis",
  "metadataLabels": {
    "app": "deployment-redis"
  },
  "replicas": 2,
  "selectorLabels": {
    "app": "deployment-redis"
  },
  "image": "redis",
  "containerName": "redis",
  "containerPort":6380,
  "command": [
    "redis-server"
  ],
  "args": [
    "--replica-announce-ip svc-redis-instance-1"
  ]
}
```
![image](https://user-images.githubusercontent.com/21373505/144596598-ba7a0013-4e9c-4265-a426-3eedd9b515b3.png)
  