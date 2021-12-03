# Kubernetes-Client-with-Java

`Create Redis Service`

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

`Create Redis Deployment`

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
