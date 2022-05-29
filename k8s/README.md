# Kubernetes resources

`N:` use resources from `/db-dev` to run database

If you are using GCP Cloud Sql you can just apply `k8s-product-app.yaml`. Make sure you are on VPC-native 
GKE cluster to connect to the database using private IP without the Cloud SQL Auth proxy.

To connect to GCP Cloud Sql using the Cloud SQL Auth proxy, you can use `sidecar` container pattern. 
[here](https://cloud.google.com/sql/docs/postgres/connect-kubernetes-engine#proxy) 

- Service:
  - Product Service - NodePort 
  - Product database service - ClusterIP


- Deployment:
  - Product Deployment - Stateless
  - Product database Deployment - Stateful - has pvc