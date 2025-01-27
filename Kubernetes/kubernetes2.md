# kuberenetes:
===============


CNCF certified kubernetes partner



Kubernetes overview:
---------------------

    k8
   
    most popular container orchestration technology                




    container orchestration:


    we have application packaged in to docker container


    to enable functionalities like load balancing 


    Docker swarm
    Kubernetes
    mesos


    docker swarm is easy to set up


    application is highly available even on hardware failure

    user traffic is distributed

    we can scale nodes 

    we can do all of these with declarative object configuration file



Kubernetes architecture:
=========================

    Node:

        A node is a machine physical / virtual on which kubernetes is installed

        a node is a worker machine where containers is launched 

        (minions in the past)


        what if node fails?

        we need to have more than one nodes

        CLUSTER:

        a cluster is a set of more than one nodes


        MASTER:

            THe master is another node with kubernetes installed and configured as master
            it watched over the nodes and manages them
        
            performs actual orchestration



        when we install kubernetes

            we install 

            API server
            etcd service
            kubelet
            container runtime
            controller
            scheduler


    API server is the frontend  for kubernetes

    users , devices , CLI uses API server to talk to kubernetes cluster


    Etcd service:

        etcd is distributed key value store 

        when we have multiple nodes and master we have the info in etcd

        etcd is responsible for implementing locks within cluster
        

    Scheduler:

        Scheduler is responsible for distributing work to the containers across multiple nodes

        it looks for newly created containers and assigns them to nodes

    controllers:


        controllers are brain behind the orchestration
        responsible for noticing and responding when container goes down

        make decision to bring new containers up

    container runtimes:

        container run time is the underlying software thats uses to run run containers

    kubelet:

        is the agent that runs on each node in the cluster
        and making sure containers are running as expected



    
    master vs worker nodes:
    -----------------------

        worker nodes (minion) it where containers are hosted

        to run docker container in a system we need to install container runtime

        rkt
        cri-o 

        are runtimes alternatives


        master server has kube api server and it makes it master

        worker has kubelet agent - responsible for interacting with master to provide health info
        etc

        all information are stored in master in etcd

        master has controller and scheduler

        master                 worker


        kube api server     container run time
        etcd                kubelet
        controller 
        scheduler




Kubectl
---------

    kube command line tool

    kubectl run hello-minikube

    called kube control


    kubectl is used to deploy and manage applications on kubernetes cluster

    kubectl cluster-info

    kubectl get nodes



# Kubernetes concepts:
=======================


at this point we assume docker image is available and kubenetes cluster is already set up and working

Pods:
-----

    our aim is to deploy an application in form of container on a set of machines / worker nods

    kube doesnt deploy containers directly on the worker nodes

    containers are encapsulated in to a kubenetes object known as pods

    A pod is a single instance of application

    pod is the smallest object we can create in kubenetes


    in case of high load to have additional instance

    we create a new pod altogether with new instance of application in the node 

    if current node has no sufficient capacity 
    we can add new node to expand the physical capacity

    pods usually have 1->1 relationship with containers

    to scale up we create new pods
    to scale down we delete existing pods

    we dont create new container in same pod

Multi-container pods
--------------------

    we are not restricted to single container single pod


    sometimes we may need helper containers on the same pod
    
    we can have both of these part of same pod

    they can share same network and same storage



Kubectl:
--------

    kubectl run nginx

    when we say this where does this image come from

    kubectl run nginx --image nginx

    the image is pulled from public / private image repos

    now pod is created we can get pods using

    kubectl get pods


    to make the server accessible for external users



Demo:
-----

minikube lab

to install minikube we need virtualisation enabled

either 

    docker
    KVM
    QEMU
    virtualbox

in ubuntu :

    grep -E --color 'vmx|svm' /proc/cpuinfo



there maybe data loss in case of KVM ?
they went with virutalbox



when we install minikube




minikube start --driver=<driverName>



if we see docker ps
we will get the running vm


docker context use default (to use context default)

minikube stop

kubectl create deployment hello-node --image=registry.k8s.io/e2e-test-images/agnhost:2.39 -- /agnhost netexec --http-port=8080

kubectl get events
kubectl get pods


kubectl get deployments

minikube dashboard


minikube add ons list to enable / disable


kubectl delete service hello-node
kubectl delete deployment hello-node
minikube stop


minikube delete





kubectl create deployment name --image=image

kubectl get deployments

kubectl expost deployment hello-minikube --type=nodePort


minikube service serviceName --utl


Deploy pod in minikube cluster
-----------------------------



    kubectl run nginx --image-nginx

    we can specify tag , url of another registry


    kubectl get pods

    kubectl describe pod nginx

    kubectl get pods -o wide




# introduction to YAML:
========================


A yaml file is used to represent data

in this case configuration data



key value pair

we must have a space after colon
to diff key value

fruit: value

array :

    Fruits:
    -      orange
    -      Apple

Dictionary / Map:

    Banana:
        calories:
        fat:
        carbs: 

we must have equal number of blank spaces

    Banana:
        Cal: val
         fat:

    (syntax error)

we can have direct value 

we should have equal  number of spaces


Fruits:
    -  Banana:
            calories:
            fat:
            carbs:
    -   Orange:
            calories:
            dat:
            carbs:



when to use dict , list, list of dictionaries

    dictionary is unordered 
    list is ordered

    Banana:
        calories:
        carbs:
        fat:

    Banana:
        calories:
        fat:
        carbs:

    above are same

    but list are different



    if any line is starting with # -> its comment



# Kubernetes concepts:
======================


Kubernetes uses yaml file for creation of object such as pods , replicas ,deployments ,services etc

in any yaml file (kube)

apiVersion:
kind:
metadata:


spec:

these are mandatory

metadata is the data about the object

metadata is in form of dictionary




---------------------
kind : version
POD: v1
service: v1
ReplicaSet: apps/v1
Deployment: apps/v1


to group these pods  in metadata

metadata:
    name: 
    labels:
        app:
        type: 

under labels we can give any key value pair

the specification section is spec

we provide addl info to kubernetes


    spec:
     containers:
        - name:
          image:
        

    kubectl create -f pod-definition.yml
    kubectl apply -f pod.yml

    kubectl describe


    once pod is created we use kubectl get pods

    kubectl describe pod




Tips for creating yaml files using Vscode:
==========================================

    use extension YAML from redhat

    use the outline to see the file info graphically




    use kubectl get pods -o wide 

    to get more info



kubectl run redis --image=redis123 --dry-run -o yaml > fileName.yml

this will create yaml file


    we can edit the file 

    or use kubectl edit


    use kubectl apply -f redis.yaml


# Replication controllers and Replica sets:
=============================================


Kubernetes controllers:

    Replication controller:
    ------------------------

        if we have single pod in a node

        and to prevent users from losing access to the application

        Replication controller helps us run multiple instances of a pod


        even if we have single pod it will bring up the single pod if anything fails


        another reason is load balancing and scaling


        Replication controller is the older technology replaced by replica set


        Replica set is used to 

        rc-defn.yaml

        for any kube file the spec section is imp and tells what is gonna be created


        in rc defn -> template copy thing from pod


        kubectl get replicationController


        kubectl get pods - will 

        DIFF:
        ----

        Major diff - SELECTOR

        replica set requires selector definition 

        selector will tell what pods falls under it

        replica sets can also manage pods that is not created as part of replica set creation


        match labels user input is required as property

        match labels simply matches the label to the labels on the pod        




        kubectl create -f fileName
        kubectl get replicaset


## labels and selectors:
=========================

    the role of replica set is to monitor the pods and if any of them fails - deploy new one

    replica set is a process that monitors the pods


    how would the replica set know which pod to monitor


    now under selector section we give the labels to monitor which is given in the pod metadata


    say we create a replica set with matchlabel with 3 replicas and they are already running

    if we create a replica set now it will not create the new replicas


    even if this is the case we have to provide pod metadata


    scaling:
    -------

        how to update replicaSet to scale to 6 
        1. change the replicas in file

            kubectl replace -f fileName

        2. Kubectl scale --replicas=6 -f fileName

            (if we do this the file doesnt affect)


    commands:
    --------

        kubectl create -f <fileName>
        kubectl get 
        kubectl delete tyoe name

        kubectl replace -f 

        kubectl scale replicaset name --replicas=

        kubectl edit rs fileName / replicatSetName

    the metadata matchlabel should have a match in the template

    updating replica set:
    ---------------------

        kubectl edit replicaset replicasetname

        kubectl scale replicaset --replicas=3

# Deployments:
===============


    Kubernetes deployments

    How might we want to deploy the application ?

    we may want to deploy webserver with multiple instances

    we may want to upgrade the instances once image upgrade is available

    we may want to update one after other (rolling upgrade)

    we may want to rollback to the older instance


    upgrading webserver,
    modifying resources

    we can pause the env and make changes
    and resume 

    pods deploys single instances of application 
    each container is encapsulated in pods

    these are controlled by replica set


    deployment is kubernetes object above replica set 
    in the hierarchy

    deployment has capability to do rolling upgrade , undo changes , pause and resume changes



   deployment definition:
   ----------------------

        same as yaml

        kind = deployment


        kubectl create -f deployment-definition.yml

        kubectl get deployments

        kubectl get replicaset

        kubectl get pods




        hierarchy ->

            pods
            replicaset
            deployment

        to see all created objects

        kubectl get all


    
## Deployments updates and rollbacks
====================================



    when we create deployment it triggers a rollout 
    a new rollout creates a new deployment revision

    rollout command:
    ---------------

    kubectl rollout status name

    deployment strategy:
    --------------------

    destroy all old instance and deploy new instance

    because of this we have application downtime

    this strategy is known as recreate strategy

    2nd:

        we do it upgrade one by one

        so app never goes down

    ::: 
    ROLLING update is the default deployment strategy
    :::



    kubectl apply:
    --------------

        kubectl set image --- update the image
        (not applied in the defn file)

    diff bw recreate and rolling update can be seen in events


    upgrades:
    ----------

        deployment creates the new  replica set automatically
        taking down the pods in the old replica set one by one


        if we want to rollback

    Rollback:
    ----------

        kubectl rollout undo deployment/name
        
    create 
    get 
    update 
    status (kubectl rollout status deployment)

            rollout history 

            rollout undo


    if we use --record while creating the deployment it will record the command


    if there is any issues in the latest deployment then the older pods is still available






# Networking in kubernetes:
============================

    if we have minikube
    we will have minikube ip address

    or else we have the ip of the node and communicate

    unlike in docker world the IP address is assigned to a pod

    each pod gets internal ip

    10.244 series

    accessing other pods using internal ip is not ideal because it will change


    also when we have different nodes the pod may get same IP

    kubernetes doesn't automatically set up any network to hadnle this

    all containers can communicate without NAT

    all nodes can communicate with all containers and vice versa without NAT


    multiple prebuilt solutions available like cisco , flannel ,cilium , calico

    own systems - calico , flannel
    vm ware nev - nsx tag

    play with K8 labs 






# Services:
============



    kuberenetes services enable communication bw various components within and outside the application


    apps have group of pods like frontend , backend 


    it is services that enables connectivity between these pods


    services enable loose coupling between micro services



    External communication:
    ------------------------

        without SSH ing in the container
        we have to communicate to the app 

        service is the solution for this

        kubernetes service is an object like deployments , replica set and pods

        use case : listen to a port on the node and forward request to the pods (NodePort service)



        service types: 

            NodePort

            clusterIP (the service creates virtual IP inside the cluster to enable communtion between set of services)

            Load Balancer (it provisions load balancer for our services)

## Nodeport

        mapping port on node to a port on the pod

        3 ports involved

        1. targetPort (port on the pod)
        2. ServicePort (port on the service)
        3. Nodeport on the node (used to access)
            (30000 - 32767)

        inside the cluster service has it own IP (cluster IP)

        service-defn.yaml
        -----------------

            mandatory field is port

            we can give nodePort : 30000 range

            we can have multiple port mappings since its list of dictionary

            we also have to use labels and selector

            in spec section -> 

                selector:
                    app : appName
                    type : frontend

        kubectl create -f servicefile.yaml

        kubectl get services



        when there are multiple pods with same selector -> service selects all three pods to map the request 

        service acts as built in load balancer

                Random alogrithm to distribute load 


        when pods are distributed across multiple nodes kubernetes creates a service spand across nodes and map the same node port to the target

        so in this case we can use any IP in the cluster to access the pod

    Demo:
    -----

        we have deployment

        now to access from user 

        we create service

        


## clusterIP:


    we may have different types of pods for each part of the application


    what is the right way to establish connectivity

    since the IP addresses are not static

    also which pod to connect in a group

    a service created for backend pods wil help to group them together

    randomly it will forward the request

    
    this type of service is cluster ip 

    in metadata we have name of service

    in spec :
        the type : clusterIP


    kubectl create -f fileName

type of default service is clusterIp created by kubernetes


## load balancer

    the pods will have its own host and port

    we cam leverage native load balancer
    in cloud techs ilke aws gcp etc,.

    spec:
        type: LoadBalancer
        ports:
        - targetPort: 80
          port: 80
          nodePort: 30004


# Microservices architecture:
=============================


    Microservices:
    --------------

        example voting application from docker
    
    Microservices on kubernetes:
    ----------------------------

    smallest object we can create is a POD

    we must be clear on the connectivity
    (internal and external)


    worker app is not accesed by anyone in the voting example

    how to make one component accessible by another 

    we cant use internal IP because it will change

    so we should use service


    we create deployment for 

    1.redis
    2.postgres
    3.voting app
    4.result app
    5.worker



    Steps:

    1.deploy pods

    2.create services(clusterIP)
        1.redis
        2.db

    3.create services( NodePort)
        1.voting app
        2.result app

    

    kodekloudhub/example-voting-app

    (Exercise it later)


        we create pod files

        in pod file we give the env variable

        better way to do this is secrets 


        we create service file

        we dint specify nodeport for internal services (no external users)

        in service metadata we give names

        in frontend service we provided nodeport and type


    kubectl get pods,svc

    there was another example for this
    using kubenetes deployments

    we begun with 1 replica for every pod

    services remained the same

    also we scaled the pods


# Kubernetes on cloud:
======================

    Options to deploy kuberenetes cluster on the cloud

    gcp
    aws 
    azure


    selfhosted/turnkey solutions

        provision vm
        configure vm
        use scripts to deploy cluster
        maintain vms ourself
        
        kubenetes on aws using kops / kubeone

        in cka course we have kubeadm tool

        

    managed solution


        KAAS (kubernetes as a service)
        provider provisions vms

        provider installs kubernetes
        provider maintaines vms
        google container ENgine (GKE)

        we dont have access to master nodes

    
    GKE (google kubernetes engine)
    AKS (Azure)
    EKS (amazon Elastic kubernetes service)

    GKE:
    -----
        create kubernetes cluster on GCP platform

        inside k8s specification directory

        kubectl create -f . 


        


# Appendix:
============

    kubeadm setup

    kubeadm tool is used to bootstrap the kubernetes cluster

    STEPS:

        1. multiple systems or VMS provisioned
        2. designate one as master and others as worker nodes
        3. install container run time
        4. install kubeadm tool in all the nodes
        5. init master server

        6. network prerequisites

        7. pod network
        8. join nodes


    see the kubernetes docs for this


    we use virtualbox and vagrant

    vagrant can be used to bring up vms with one command


    bootstrap kubernetes cluster using kubeadm

     kubeadm - bootstrap cluster
     kubelet - runs on all machines
     kubectl - CLI to talk to the cluster


    kubectl get pods -A 
    (all namespaces)

    kubectl edit svc <name> -n  <namspace>
    
    (for namepsace)

    
    options available in setting up kubenetes cluster:

    local solution:
    ----------------

    minikube
    microk8s

    kubeadm (production grade)


    hosted:
    --------

    GCP
    aws
    Azure
    IBM cloud


    

















