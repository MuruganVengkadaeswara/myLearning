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


































