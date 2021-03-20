# Kubernetes Dashboard


## Getting Started

### Installation

```
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.0.4/aio/deploy/recommended.yaml

# Create admin  service account and a cluster role binding.
kubectl apply -f ./deployment/k8s/k8s-dashboard/dashboard.admin.user.yaml
```

### Accessing the dashboard

```
kubectl proxy

http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/#/login
```

### Obtaining access token
```
kubectl -n kubernetes-dashboard describe secret $(kubectl -n kubernetes-dashboard get secret | grep admin-user | awk '{print $1}')
```


### Uninstallation

```
kubectl delete all --all -n kubernetes-dashboard
kubectl delete ns kubernetes-dashboard
```