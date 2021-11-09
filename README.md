# Getting Started

## Setup

- ```mvn package -DskipTests```
- ```docker build -t config-watcher-demo -f Dockerfile .```
- ```kubectl create ns backend```
- ```kubectl apply -f kubernetes/ -n backend```

## Testcase

- the application will log secret-entries every 15 seconds
- watch logger via ```kubectl logs -n backend $(kubectl get pods -l app=config-watcher-demo -n backend) --follow```
- edit secret ```config-watcher-demo-secret2``` via ```kubectl edit secret -n backend demo-secret2```
- try adding another entry to ```demo-secret2``` starting with ```storageconnections.```
  - what is being logged?
  - what is in the mounted volume (```kùbectl exec -n backend $(kubectl get pods -l app=config-watcher-demo -n 
    backend) -it -- /bin/sh``` -> ```ls /etc/secrets2```)?
- try removing an entry
  - what is being logged?
  - what is in the mounted volume (```kùbectl exec -n backend $(kubectl get pods -l app=config-watcher-demo -n
    backend) -it -- /bin/sh``` -> ```ls /etc/secrets2```)?