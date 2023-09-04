## Executando a aplicação no cluster Kubernetes

`OBS: para rodar a aplicação no kubernets, é necessário fazer o build da imagem docker dessa forma: docker build -t techfood:v1`

### 1 -  Criando POD responsável pelo banco de dados MySql

1.1 - Criar o ConfigMap
```
kubectl apply -f .\db-configMap.yaml
```

1.2 - Criar o POD
```
kubectl apply -f .\db-techfood.yaml 
```

1.3 - Criar o service
```
kubectl apply -f .\svc-db-techfood.yaml
```

### 2 -  Criando Instâncias da Aplicação via Deployment

2.1 - Criar o ConfigMap
```
kubectl apply -f .\app-configMap.yaml
```

2.2 - Criar o Secret
```
kubectl apply -f .\app-secret.yaml
```

2.3 - Criar o Deployment
```
kubectl apply -f .\techfood-deployment.yaml
```

2.4 - Criar o service
```
kubectl apply -f .\svc-techfood.yaml
```

__Como estamos executando em um cluster local, configuramos a service do tipo NodePort
Criamos um exemplo de service do tipo LoadBalancer (*svc-techfood-loadBalancer.yaml*) conforme solicitado no Tech Challange,
pois não será executado em um Cloud Provider__

