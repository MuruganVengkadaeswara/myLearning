## Kubernetes
=============

CNCF certified


CKA 

certified kubernetes administrator - course


CKAD 

certified kubernetes application developer - course


kubernetes for absolute beginners
=================================


Kubernetes overview

K8

built by google based on exp running containers in prod

Container orchestration

Containers


Docker overview
--------------

There is a stack of softwares / technologies such as webserver , db , messaging queue and orchestration (ansible)

this becomes hard to manage in one OS for the developers as well as for deployment since there might be incompatibility between these softwares


so we use docker - we run each component in one container
under one os



What are containers?
====================

Containers are completely isolated environments they can have their own processes networks mounts but share the same os / kernels


Containers lxc , lxd etc..,


All OS contains kernel ,
& set of software

Main purpose of docker is to


docker container share the  underlying kernels

docker is not meant to virtualise OS but to containerise the application

so the software of windows cant be containerised for linux ?


Containers vs Virtual machine
-----------------------------


    Hypervisor - has its own os running so its also heavy

    docker containers are light weight 
    it will boot up in matter of seconds

    Vms have complete isolation from each other

    we can have different os on same hypervisor (linux / windows)

    but not possible in docker


How its done?
-------------

    there is a public registry - docker hub

    docker run ansible -> will run ansible 

    docker run nodejs


Container vs images
---------------------

    docker image is a package or template (like vm template)

        package 
        template
        plan

    its used to create one or more containers


with docker developer will give war file + dockerFile

this docker file will contain configuration info


with docker file the docker image is created


Container orchestration
------------------------

    Application packaged to docker container

    the orchestrator will manage the containers


    kubernetes is a container orchestration technology

    mesos / docker swarm / kubernetes


Application is highly available even on hardware failure.

this configuration is with a set of declarative object config files

kubernetes is a container orchestration technology used to orchestrate deployment and management of 100s of 1000s of containers in a clustered environment

Kubernetes architecture:
------------------------

Node is a machine (physical / virtual)
in which kubernetes is installed

containers will be launched on the node by kubernetes (minions in the past)

what if a node fails?

So we need more than one node

a cluster is a set of nodes grouped together

Master
------

Master is a node in which kubernetes is installed and configured as a master.

Master is responsible for container orchestration on the worker nodes


Kubernetes components
----------------------

    API server
    etcd service
    kubelet service
    container runtime
    controller
    scheduler
    
API server acts as frontend for kubernetes

etcd key store -> store key value to manage key value 

etcd is responsible for implementing locks among the nodes so that there is no conflict 

scheduler -> distributing work among the nodes

Controllers -> brain behind orchestration

container runtime -> underlying software that used to run containers (docker)


kubelet -> agent that runs on each node 



2 types of servers

    master
    worker

    worker node or minions is where the containers are hosted

    to run docker container we need to install container runtime

    there are other container runtimes also such ns rkt (rocket) / cri-o


    Throughout this course we use docker container.



    master server has kube api server

    worker has kubelet agent (responsible for interacting with master carry out action requested by master)

    the key value store (etcd) stores the information gathered 

    controller and scheduler also present in the master.



kubectl:
========

    kubectl / kube control


    kubectl run hello-minikube

    kubectl run command will help us run / deploy the application on the cluster

    kubectl cluster-info

    kubectl get nodes


Docker Vs ContainerD:
=====================

ctr , nerdctl , crictl

Kubernetes was specifically built for orchestrating docker


Kubernetes introduced CRI (Container Runtime Interface)


OCI (open container initiative) standard

imagespec runtimespec

OCI has imagespec and runtimespec

anyone can build container runtime 

docker wasn't built to support OCI

dockershim - temp way to support docker by kubernetes

rkt adhere to OCI standards


docker continued to work without CRI

docker contains multipe tools

    CLI 
    API
    BUILD
    VOLUMES
    AUTH
    SECURITY
    ContainerD

    containerD is CRI compatible

    docker shim is unnecessary effort to maintain

    docker was removed from kubernetes

    kubernetes continued to work with containerD

containerD
===========

    is a part of docker
    but  is a separate project

    is a member of CNCF 

    we can install containerD without having to install docker


    containerD comes with command line tool ctr (not user friendly)

    CLI - ctr


    ctr images pull <img>
    ctr run <image>


    nerdctl - CLI similar to docker

    it supports all support like docker


    nerdctl supports newest features in containerd

        -> encrypted container images
        -> lazy pulling
        -> p2p image distribution
        -> image signing and verifying
        -> namespaces in kubernetes


    instead of docker -> nerdctl

    nerdctl run --name redis

    nerdctl run --name webserver -p 80:80


    crictl
    ------

        crictl provides a CLI for CRI compatible container runtimes

        installed separately

        by kubernetes community

        works across different runtimes

        used for spl debugging


        crictl pull busybox

        crictl exec -i -t <containerID> ls


        crictl pods


    





































