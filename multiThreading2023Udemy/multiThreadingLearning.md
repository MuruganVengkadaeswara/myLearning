course notes for course by Michael Pogrebinsky

START:
======

Java multithreading , concurrency

Motivation : 
------------
1. responsiveness

    E.g of poor responsiveness

    waiticng for customer support
    let response
    no feedback from application

    reponsiveness with multi threading

    -> its very critical in app with UI

    1.concurrency - multitasking

        -> Achieved by multi tasking bw threads
        -> concurrency = multitasking

        even with one core we can 



2. performance

    we can create illusion of multipe tasks executing in parallel using a single core
    
    with multiple cores we can truly run tasks compltely in parallel



    finish more work in same time
    high scal service
        fewer machine

3. Why we need threads
    responsivenss - concurrency 
    performance -   parallelism

* multi threaded programming is fundamentally different from single threaded programming


4. What are threads and where they live ? 
    
    from an OS perspective

    the OS 
        
        takes a program from disk and create instance in the memory -> this is called process / context

        each process is completely isolated from one another 

        some other things a process contains
            -> metadata (File, PID, code ,data heap)
            -> atleast 1 thread -> main thread

            a thread contains stack and instruction pointer

            -> in multithreaded application thread contains its own stack and instruction pointer
            -> all other process components are shared


            -> a stack is a region in memory where local variables are stored and functions are executed

            -> instruction pointer points to address to next instruction

5. Context switch

        normally there are way more processes than threads. and each process have more than 1 thread

        the act of stopping a thread and scheduling out and scheduling 1 thread and start another thread is called context switching

        stop t1 -> schedule out t1 -> schedule in t2 -> start t2 

        is called context switching

        this has a cost
        they are not cheap

        each thread consumes resources like memory , CPU etc 

        when swithced to different thread we need to store this data and restore the data from another thread.

*Too many threads is thrashing -> spending more time in management than real productive work*

*threads consume less resources than processes*

*context switch bw 2 threads in same process is cheaper than the context swiches bw 2 threads in different process*

6. Thread scheduling

    E.g music player and text editor
        -> music and UI 

    we have 1 core 

    arrival order , length

    who runs first?

        if FIFO order

        problem with this is starvation because of long thread

        this will cause delay in UI threads



    the operating system divides the time in to moderately sized pieces to epoch

    in each epoch the OS allocates different time slice 
    for each thread
    not all the threads get to run / complete in each epoch

    decision on how to allocate the time for each thread
  
    dynamic priority = static priority + bonus

    (bonus can be negative)

    static priority -> set by dev

when to use threads and process?

when to prefer multithreaded architecture?

    1. prefere if tasks share lot of data
    2. threads are faster to create and destroy
    3. context switching is cheaper


when to prefer multi process architecture?

    1. security and stability of higher importance
    2. tasks are unrelated to each other


# CODING
=========

1. Thread creation with runnable interface
2. Thread debugging
3. Thread class capabilities


1. 
    In java all thread related properties and methods are encapsulated in Thread class by JDK.

    Thread object is empty by default
    new Thread(new Runnable)
    pass a class that implements a runnable interface

    we can also use lambda from java 8
    we have to start the thread after creating

    Thread.currentThread().getName() gives name of thread


    to set name to thread 

        thread.setName(<name>)

    to set priority 

        thread.setPriority(Thread.MAX_PRIORITY)
        10 is max 

2. Thread debugging

    intellij thread tab can see the thread

    thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                System.out.println(throwable.getMessage());
            }
        });

    set this for getting any uncaught exception in thread


3. Another way to create thread

    instead of creating a new class for thread
    directly create new class extending Thread

    Thread will implement Runnable interface


4. Case study

    design vault
    
    HackerThread -> will brute force vault


    can implement a OOPs inhertiance hierarchy  and encapsulate the common functionality for threads


    E.g HackerThread implemented by 2 classes


*Thread class encapsulated all thread related functionality*

*2 ways to run a code on a new thread*

**implement Runnable interface and pass to thread object**

**Extend Thread class and create object of that class**

Both are correct

**Difference bw start and run**
when start is called new thread is created and code inside run is executed

when run is called no new thread is created only it will execute the current thread's run code


## Thread coordination

1. Thread termination
2. thread.interrupt()
3. daemon threads

Why stop a thread?

Threads consumes resources
even idle

like memory and kernel resources
CPU cycles and cache memory

if thread finished its work but app is still running we want to clean up the thread's resources

if thread is misbehaving 
like if its performing very long calculation

By default the application will not stop as long as one thread is still running , even if main thread stopped


## Thread.interrupt()


we can send interrupt signal from thread a to thread b as attempt

2 scenarios

1. if the thread is executing a method that throws an interrupted exception

2. if thread's code is handling interrupt signal explicitly
(e.g long computations)

find hotspot in code 
check if interrupted from outside world


3. Daemon threads

These are background threads that do not prevent the application from exiting if the main thread terminates

These are background tasks that should not block our application from terminating 

    e.g file saving thread in text editor

code in worked thread is not under our control , we dont want this to block our app from terminating

    e.g

    set property of thread to daemon

    thread.setDaemon(true)


to prevent thread from blocking our app from exiting we set the thread to be daemon

## Thread.join()

Thread co ordination

1. Different threads run independently
2. order of execution is out of control

    **scenarios**
    Thread a finishes before b 
    thread b finishes before a
    concurrently
    parallel

3. what if one thread depends on other?

    what if a depends on b

    naive soln :

        Thread b run in loop 
        and check if a's result is ready

    Desired soln : 

        Thread b to sleep until thread a completes its work        


Thread.join() is used for this

e.g factorial calculation is cpu intensive task



now thread.join() will join all the threads and make the main thread to wait

we can specify time to join() in ms
to wait to a specific time

after that time the app will still run until the thread is interrupted
or set to daemon

* More control over threads
* safely collect and aggregate results
* gracefully handle runaway threads using Thread.join(timeout)

summary : 

    we cant rely on order of execution
    of other threads

    always use thread coordination

    Design for worst case 

    threads may take unreasonably long time

    Always use Thread.join with time limit.

    stop the run away thread if its not done in time

## Performance optimisation

Performance criteria : 

    High speed trading system
    measured by latency
    the faster txn faster the app

video player : we need to show the content in correct frame rate 

Machine learning : metric is throughput
latency and jitter dont matter here

**latency**
    time to complete a single task
    measured in time units

1. how can we reduce latency in multithreaded programming?

we can break task in to n subtasks
and schedule to run parallel

    latency = T/N
    number of subtasks = N

    Theoretical reduction of latency by N = performace improvement by factor of N

    does breaking original task and aggregating in to results come for free?

    can any task be broken to subtask?

    N = ?

    N should be equal to Number of cores on general purpose computer

    # threads = # cores 
    is optimal only if all threads are runnable and can run without interruption

    we assume nothing else is running that consumes cpu


    HyperThreading : 
        
        virtual core :

        one core can run multiple threads


    Cost of breaking the tasks:

        inherent cost of parallelisation and aggregation

        it costs to create , schedule threads and aggregate the thread result


        small tasks are not worth breaking to run in parallel

    we cant break any task in to subtask

    1. parallelizable task
    2. unbreakable / sequential task
    3. partially parallelisable / partially sequential task


*Image processing case study*

    pixel - ARGB

    A- transparency value

    we can improve the performance
    by dividing to sub problems

    more threads is counterproductive 
    called thrashing

    


**throughput**
    the amount of tasks completed in a give  period. measured in tasks/time unit

    its the number of tasks completed in given period of time

    tasks/ time

    2 approaches 

    1.breaking tasks in to subtasks
        
        improving latency

    T = time to execute original task
    N = #subtaks / #threads

    latency = T/N

    Throughtput  = N/T

    this approach has overhead of creating tasks , creating threads ,  scheduling threads , waiting for thread to complete , aggreagating the thread results 
    
    2. Running tasks in parallel

    in this approach we have only one result for each thread , so no need for waiting and aggregating

    also using thread pooling and cache friendly non blocking queue

## Thread Pooling

Creating the threads once and reusing them for future tasks

each thread takes task from the queue

Thread pool implementation is not trivial

JDK comes with few implns for thread pools

we use Fixed thread pool executor in this course

we pass runnable task to executor method

    summary :

    By serving each task on dif


Fixed thread pool executor 



## optimising throughput

    practical example of http server jmeter


    App desc : 

        Http server load a book
        and app acts as search engine

    Apache jmeter:

        java performance automation tool

    
    steep increase in perfromace if we add number of threads
    up to physical cores

## Data sharing between threads



memory managment fundamentals

**IMP**

stack memory region
--------------------

* each stack region belongs to a particular thread

* its a memory region where 
    methods are called
    arguments are passed
    local variables are stored

* stack + instruction pointer
= state of each thread execution


* T main's stack

    every single local variable is pushed to stack memory

    when current method needs to call another method new stack frame is allocated on top of the stack


    when main method is finishd 
    its stack frame is invalidated

    this is LIFO 


* stack's properties


    All variables belong to thread executing on the 
    stack

    statically allocated when thread is created

    stack's size is fixed and relatively s all (platform specific) 

    if calling hierarchy is too deep we may get an stackoverflow exception
    (like recursive call)

    other thread cant access

Heap memory region
-------------------

* the heap is shared memory region that belongs to the process

* all threads share data in heap

* what are allocated on the heap?

    1. Objects (any thing with new operator)
        * String
        * Object
        * Collection
    2. Members of classed
    3. static variables

* Heap Management

    1. Governed and managed by garbage collector

    2. Objects stay as long as we have ref to them

    3. Members of classes - exist as long as thier parent class exists
    (same cycle as parents)

    4. static variables - stay forever (application lifetime)

* references <> Objects

    Object ref1 = new Object();

    Object ref2 = ref1;

    there are 2 ref variables and 1 object

**What is allocated where?**

    References can be allocated on the stack

    References can be allocated on the heap if they are members of the class

    Objects are always allocated on the heap



Heap is shared and stack is exclusive

In heap -  Objects , class members , static variables are stored

In stack - Local primitive types
Local references are stored.


## Resource sharing between threads

* What is a resource?

    These are 
        variables
        Data structures
        file or connection handles
        objects we create
        Message queue

* Why share resources?

    UI thread and Document Saver
    thread in text editor example

    Both has same access to Document data strucure

  Input ->  Work Dispatcher -> Queue -> worker threads

  
  queue is DS ans its a shared resource


  Database microservice:
    
    Connection to DB is an Object / set of objects which shared by request threads

## Race condition

    E.g inventory counter is shared bw 2 threads (inc and dec)

    if run sequentially and waiting for each other to finish

    if run concurrently its unexpected result

Core problem:
    Inventory counter is shared bw 2 threads and accessible to both 
    it perfoms i++ and i-- at same time

    these are not atomic operations

## Atomic operations

    An operation / set of operations is said to be atomic if it appears to the rest of the system as if it occured at once

    single step - all or nothing 
    (atomicity)
     
    No intermediate state

    items ++
        1.get current value
        2. inc by 1
        3. store the new value


## Concurrency challenges and solutions

    if we want to execute a set of instructions atomic using threads
    
    1. Critical section

        enter critical section
            
            execute

        exit critical section

    if a thread is executing a critical section then any other thread is denied access to that section
    and is suspended until former thread completes


    JVM provides tools for this critical section


## Synchronized

    this is a locking mechanism designed to prevent an access to a block of code or entire method to multiple threads


    E.g 
    public class cl{

        public synchronized m1(){

        }
         public synchronized m2(){

        }
        
    }

    only one thread will be able to execute either of those methods


    IMP NOTE: 

        in above code if thread A is executing m1 thread B is deprived from executing both m1 and m2 
        because synchronized is applied per object -> TERM IS MONITOR

another way:

    public class cl1{
        public void m1{
            synchronized(this){
                --block
            }
        }
    }

this is flexible



public class cl1{

Object lock1= new Object();
Object lock2 = new Object();

        public void m1{
            synchronized(lock1){
                --block
            }
        }

          public void m2{
            synchronized(lock2){
                --block
            }
        }
    }


Thread A and B can access m1 and m2 respectively

But not the same critical section

* synchrnoized block is reentrant
* A thread cannot prevent itself from entering critical section

I.e 

if thread A is accessing a sync method while already being in sync method / block it will be able to access

that is a thread cannot prevent itself from entering a sync code / critical section



## Atomic operations , Volatile And metrics

