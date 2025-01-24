## DOCKER
==========


What are containers?

what is docker?


why do you need it?


what can it do?

run docker containers

create dockerimage

networks in docker

docker compose

docker in depth

docker for windows / mac


Docker:
--------

    set up e2e application stack with various technologies with 

    webserver       
    database
    messaging system
    orchestration tool 


    we have to make sure the versions of these various tools are compatible with the OS we are planning to use

    also the compatibility of the dependent library such as java 17 / 21 for one tech / other

    (The matrix from hell)

    when new dev is coming in he has to take time to setup

    to help us with these compatibility issue we have docker


    run each service with its own dependencies in a container


what are containers?
--------------------

    they all share the same OS kernel

    lxc container is what docker use


    each container has its processes , network , mounts etc


    OS :

        All os container OS kernel and software

        if the OS is ubuntu and we have docker installed on it 

        we can run any OS on docker as long as the underlying kernel is same


        windows and linux dont share same kernel

        when we run linux on windows using docker we are not running linux but VM using a container

        is nt it disadvantage to not run another kernel on OS?

        docker is not hypervisor


Containers VS virtual machines
------------------------------


        we have 

            hypervisor -> os / libs - deps / application
                          os / libs - deps / application   


            docker -> OS / docker / libs-deps / application

        
        Containers are lightweight 
        but VMS will need GBs of data


        docker have less isolation as the resources are shared between containers

        its not either containers or vms



        but VMs and containers

        we can see containers provisioned on VMs


How is it done?
---------------

    
Containers VS image
--------------------

Image is a package or template or plan

containers are running instances of images 


Traditionally developers developed applications and handed it over to the OPS team for deployment with instructions

with docker devs and ops team works hand in hand and create a docker file 



Getting started
----------------

    docker has 2 editions

    community
    enterprise (add ons - image management etc..,)





Installing docker:
------------------

    https://docs.docker.com/install

    select os and flavor

    cat etc/*release*

    remove any older version of docker if exists

    (get command from doc)

    set up repo and install

    using apt-get 

    or 

    using convenience script

    we can download script and run it

    use docker run hello-world


    hub.docker.com

    get whalesay 

    whalesay didnt work - get cowsay

 CH2 -Docker Commands :
========================



    Docker commands

    docker run


    docker run is used to run the image 
    if not present it will download


    docker ps


        will list all running containers and basic info about them

    docker ps -a

        will list all the images present (running or not)

    docker stop 

        will stop the image
        pass container id / name


    docker rm

        remove stopped / exited container

        docker rm <containerName>
    
    docker images

        list all the images

    docker pull 

        will download the image

        docker pull  <imageName>

    
containers are not meant to host operating system

once the task is complete the container exits
the container lives only as long as the process is running 

if the webservice in the container is stopped / crashed it stops



so when we run ubuntu it exits because no process is running 

so if we want it to continue

    docker run ubuntu sleep 5




executing a command of running container


    Exec:
    ----

        docker exec <name> <command>

    Run - attach and detach:
    -------------------------

        docker run kodecloud/simple-webapp

        it runs on attached mode

        docker run -d 'image'

        it runs on background mode


        run docker ps to see the container

        docker attach <containerId>

        when we provide container id first few characters are enough




Docker commands:
=================



    we downloaded centos image

    we ran the docker image

    docker run -it centos bash

    docer run -d centos sleep 10


    we can see the process using above


    ** docker ps -a also gives us what command was run previously


    docker stop <containerName>
    
        we also get the exit code 

        in status column

    docker ps -a result cols


    CONTAINER ID   IMAGE                                               COMMAND                  
    CREATED            
    STATUS                        
    PORTS     
    NAMES

    docker rm <containerId>
    docker rm <containerName>

    to reclaim disk space

    all the container images that run is there we can remove the dead / live container


    to remove an image 

    docker rmi <imageName>

    before we remove the image 
    we will have to remove all the containers - exited / alive


    docker pull imageName:version


    kodekloud.com/p/docker-labs


    docker system prune will delete all the dangling images

    docker export - for container

    docker save for images

    docker version -gives detailed 

    docker -it -> interactive mode


Docker RUN:
============

  kodekloud.com/p/docker-labs
    visit here  




    docker run redis


    will run redis latest

    docker run redis:tag


    if we want to run specific tag / version of image we give docker run image:version/tag

    if nothing is given it automatically takes the latest


    By default the docker container will not listent to any STDIN

    if we want to provide input to the image




    docker run -i image

    i means interactive

    t means pseudo terminal

    Port mapping:
    -------------

        we have 2 options - use the default ip of docker

        also this is internal 

        we have to map the internal IP 

        like 

        docker run -p 80:5000 kodecloud/simple-webapp

        docker run -p 9000:5000 kodecloud/simple-webapp

        we can run many applications like this and map them to the Port

    
    Volume mapping:
    ----------------

        the data files are created for mysql

        /var/lib/mysql (this is inside the docker container)

        if we delete the container the data gets deletd with this


        if we want to persist data 
        we have to map the directory outside the container


        docker run -v /opt/datadir:/var/lib/mysql mysql

        we are mapping internal directory to external directory 

        so even docker container is deleted its persisted



    inspect container:
    -----------------

    docker inspect container_name


    will return json


    container logs:
    --------------

        docker logs <container_name / id>



Docker run Advanced:
====================



what if we want to run another version of a docker image


docker run image:tag < command>

we can tag images for different purposes / versioning etc.



    docker run -d image:tag 

    detached

    to attach back to container

    docker attach <containerId>


    docker run jenkins -p 8000:8000
    

    using internal IP



    to access externally / outside the container we have to add port mapping


    docker run -p sysPort:dockerPort imageName


    docker run -v sysVolume:dockerDir imageName

    this example of jenkins did to check whether the changes are living / configurational change is living



default nw mode of docker - bridge



DOCKER images
==============

    Docker images

    we are containerize web application

    why we need to create a docker image?

    How to create my own image?

    Steps:
    ------

        1. OS 
        2. Update apt
        3. install dependencies using apt
        4. install python deps using pip
        5. copy source code to /opt folder
        6. Run web server using flask command

    create docker file

        FROM ubuntu
        RUN apt-get Update
        RUN apt-get install update
        RUN pip install flask
        RUN pip install flask-mysql

        COPY . /opt/source-code
        ENTRYPOINT FLASK_APP=/dir/app.py flask run


    Build image using

    docker build Dockerfile -t imageName

    docker push accName/imageName (to push to registry)



    Dockerfile - text file

    INSTRUCTION     ARGUMENT

    FROM            command / arg


    All docker file must start with FROM instructions


    Layered architecture

    The images are built in layered architecture

    each line in docker file will create a layer and following layers will use the prev layer changes

    docker history imageName


    Docker build output:
    -------------------

        docker build is incremental

        if any step fails -> the cached layer will be used

    
    We can containerize anything like chrome , curl ,spotify 



    docker build . -t tagName

    pushing docker image 

    if we dint specify org - it will push to library

    we need to build and tag with accName

    docker build . -t accName/imageName


    we have to login 

    using docker login

    with docker free acc 1 private repo is allowed 



Environment variables:
-----------------------

    os.environ.get('env_name')

    docker run -e env_Variable = value imageName


    how to find environment variable?

    using docker insect we can find out the env variables under configurations


docker CMD vs ENTRYPOINT:
-------------------------

    containers are meant to run a specific task / service

    if web service is stopped -> it exits


    CMD["nginx"]


    when container is run -> it runs with the default command

    CMD['bash']

    we  can append a command to make default

    

    CMD ["Command" , "param1" ,....]

    CMD ["sleep" , "5"]

    
    from ubuntu

    CMD sleep 5

    docker run ubunut-sleeper 


    from ubuntu

    ENTRYPOINT["Sleep"]


    docker run ubunut-sleeper 10



      from ubuntu

      ENTRYPOINT["Sleep"]

      CMD["5"]

    in the above case if user gives the value cmd will not be taken or cmd will take


    if we want to modify entry point in run time

    docker run --entrypoint <entrypoint>



Docker Compose:
===============


    Configurations with yaml files

    if need to setup multiple services in to one app

    we may need to use docker compose 




    docker-compose.yaml

    docker-compose up

    instead of running multiple docker commands


    This is all applicable for single host





    voting application example from docker


    voting app - in-memory db

    worker - db

    db -  resultApp


    the vote is stored in redis

    worker application in .net 

    worker application then puts to DB (postgres)

    result app web interface reads data from DB


    all images are built 

Naming the container
====================

    docker run --name=name <image>


Cont:
-----

    docker run -d --name=redis redis
    docker run -d --name=db
    docker run -d --name=vote -p 5000:80 voting-app
    docker run -d --name=result -p 5001:80 result-app
    docker run -d --name=worker worker


    we have to tell the voting application which redis to use

    docker run -d --name=vote -p 5000:80 --link redis:redis voting-app 



    we are linking redis app to the voting app

    this creates an entry in /etc/hosts with redis name and internal ip of redis container


    using --link is deprecated



    in docker compose

    we have imagename as key 
    and each key will have its properties


    redis : 
        image : redis
    
    db: 
        image:postgres:9.4

    result:

        image:result

    vote :
        image : voting-app

        we can also give 

        build : ./vote

        this directory will have Dockerfile

    docker compose build

        build : ./vote
        
        if we give image  = then its assumed we have already built the application





Docker compose versions
========================


version 1 had number of limitations

there is no way of specifying different network (other than bridge)

also the support for specifying the order of container execution / application stack

trimmed version - v1

if we wanted to 

   v2 :

        we have services

    for v2 and up we should specify the version in docker compose file

    docker compose automatically creates dedicated bridged network and attaches all containers

    so we dont need to use links in version 2

    the apps can be accessed by the names

    for the dependencies we have 

        depends_on:
            -image name


    v3:

        we have services similar to v2

        this has support for docker swarm 

    Networks in docker compose:
    ---------------------------

        we modify the arch bit to contain the traffic

        we have nw for frontend and backend


        in root level of docker compose

            networks:
                frontend:
                backend:

            in the services we have to specify the network to which it has to attach


    

this is the example used


    https://github.com/dockersamples/example-voting-app




docker system prune removes the built images also if there is no name




v2 and v3 are now combined






docker compose is not installed by default



start with 



services:
    redis:
        image: redis
    db:
        image: postgres
    vote:
        image: 
        ports:
            - 5000:80
        links:
            - redis
    
    worker:
        image: 
        links:
            - db
            - redis



    after all done :

        docker compose up




   in version 3  it will automatically create a network

   so links are not required in version 3

    we can specify environment variables using 

    db:
        image: 
        environment:
            key : value
    



    docker compose uses service names as host name to communicate between images


    An Alpine-based image provides a minimalistic environment ideal for containerized apps.


    redis for real time data processing



Docker registry:
================

docker has central repo for all the docker images



    image : nginx

    docker has image naming convention


    so it means nginx/nginx

    image : registry / user-Account / image - repository

    docker.io / nginx / nginx


    google registry - gcr.io


    these are all publicly accessible resources

    there are also private registries

    it can be accessed with creds


    docker login private-registry.io

    docker run private-registry/apps/internal-app

    how to deploy a private registry?

        docker registry is itself an application

        name of image = registry

        docker run -d -p 5000:5000 --name registry registry:2


        docker image tag my-image localhost:5000/my-image

        (tag the image )

        docker push localhost:5000/my-image
        (used to push to registry)

        docker pull localhost:5000/my-image


        default port of docker registry is 5000



Docker engine:
==============

    docker engine is referred to host with docker installed on it


    when we install docker engine consists of

        -> docker daemon
        -> REST API
        -> Docker CLI


        DOCKER CLI uses Rest API to interact with the docker daemon

        docker CLI need not be in same device

        docker -H=remote-docker-engine:2375

        on remote docker host

        docker -H=host:port run image

        docker CLI can be in a laptop



Namespace isolation

    linux - ps to list all the processes


    docker is a subsystem which has processes


    so inside the container the process id will be different but according to the system its just another process

    e.g 

    PID : 5 = PID 1 in container

    
    a container may use all of the resources in the host

    but we can restrict the amount of resources it should use

    e.g

        docker run --cpus=.6 ubuntu

        -> 60 % of all the cpus
        
        dokcer run --memory=100m ubunut

        restricted to 100mb


Namespace PID:
--------------

    docker exec containerId ps -eaf


    lists the processes

    ps -eaf | grep docker-java-home

    this will list the process outsife container
    


    with namespaces we can give multiple process ids to the same process


Docker storage:
===============

    docker storage drivers and file system

    on docker installation

        /var/lib/docker 
            aufs
            containers
            image
            volumes

        
    layered architecture

        when docker builds image it builds on layered architecture

        layer 1 - from ubunut 120mb 
        layer 3 - run apt     306mb  

        each layer has memory


    if there are 2 dockerfiles with some layers same as other 

    it will reuse the layers from already built


    it will save time

    all of these layers are created when we build the docker image

    

    docker build Dockerfile -t imageName

    these layers are stored and readonly

    these layers are modified onlywhen we init a new build
 
    when we run the container 
    docker creates a container on top of these layers
    and writable layer

    this layer is container layer

    the life of the layer is container life

    files are stored using container layer


    image layer - read only

    container layer - read / writable

    cannot edit anything in image layer (code is read only)

    same image layer may be shared bw multiple containers


    we can modify the file in container layer only
    in the copy of the file

    when container is deleted the changes to the file also get removed

    if we wish to persist this data 

    we can add a persistent volume to the container

    "docker volume create data_volume"


    we can mount the volume using

    docker volume create data_volume

    docker run -v data_volume:/var/lib/mysql mysql


    even if container is destroyed it will persist the data

    if the volume is not present - it will create the volume

    if the data is somewhere else 


    data run -v /dir/path:/var/lib mysql


    this is called bind mounting



    -v is old

    now we use --mount

    docker run --mount type=bind,source=/data/mysql,target=/var/lib/mysql



    docker uses storage drivers for these

    for ubuntu AUFS is default

    storage drivers

        AUFS
        ZFS
        BTRFS
        Device mapper
        overlay
        overlay2

    docker will automatically choose the drivers

    use docker info command to see driver used

    each storage drive stores data differently


** use

    docker history <image id>

    to see how the image is built


    we can run the script directly

    using the file directory 


    e.g 

    /var/lib/docker/filesystem/diff/script

    this is a hack


    if there are no changes made to dockerfile / application code - it will use cache and rebuild

    we can see --> using cache

    in ubuntu latest we used -> overlay2 was the file system


    if only source code changed and other layers are same
    it will reuse

    we can specify docker file using param

    docker build . -f DockerFile2 -t tagName


    even if one application uses cached layers the memory is shown equal 

    the total space for each image is not the consumed / actual image consumption


    to see the consumption actual 

    docker system df

    docker system df -v

    TYPE            TOTAL     ACTIVE    SIZE      RECLAIMABLE
    Images          16        0         2.864GB   2.864GB (100%)
    Containers      0         0         0B        0B
    Local Volumes   25        0         1.644GB   1.644GB (100%)
    Build Cache     38        0         0B        0B



    there is also shared column if we run -v (true / false)




DOCKER networking:
==================

    when we install docker it creates 3 networks

    bridge
    none
    host

    bridge is default host the container gets attached to 

    docker run image --network=host / none


    default is bridge



    the internal IP is in 172.17 series

    to access any of the containers from outside 

    we use ports

    another way is to associate the container with host network

    docker run image --network=host

    it will use host network

    with none network its not mapped to any network host / container

    its isolated


    to isolate the container inside container
    we can create internal nw using 

    docker network create --driver bridge --subnet IP 
    custom-isolate-nw


    docker inspect name will give the network

    Embedded DNS:
    -------------

    containers can reach each other by name


    we can use internal ip address (not ideal since IP changes)

    so we use container name

    all containers can resolve each other with container name

    how?

    docker uses network namespaces that creates a seprate name space for container and uses ethernet
    (more on Advanced concepts)






    docker tag applies new tag to existing image





DOCKER WINDOWS:
===============

    1. docker toolbox
    2. docker desktop

    
































