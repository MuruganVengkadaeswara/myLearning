course notes for course by Michael Pogrebinsky

START:
======

Java multithreading , concurrency

Motivation : 
------------
1. responsiveness

    E.g of poor responsiveness

    waiting for customer support
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