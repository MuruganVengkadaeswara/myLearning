# Go start
===========





Go environment setup
--------------------


Install GO

golang.org/dl/


Install GO
----------

ubuntu - its tar file - extract tar and set bin path in .profile for system wide installation


mac / windows comes with installer


we need to install golang extension


sample code
-----------

```
package main

import "fmt"

func main() {
	fmt.Println("Hello")
}

```
Running code inside project:
-----------------------------

     go run <filename>


GO CLI
-------

    go build - compiles bunch of go source code files to executable file
    
    go run   - compiles and executes one or two files
    
    go fmt   - formats all code in each file under dir
    
    go install - compiles and installs a package
    
    go get     - downloads raw source code or someone   else's package
   
    go test - Runs any tests associated with current project



GO packages:
------------

    package == project == workspace

    a package is a collection of common source code file

    package has many related files inside
    

    Types of packages:
    ------------------

     Executable -  generates a file that we can execute/run

     Reusable - code used as helpers (code deps / libraries) good place to put reusable logic



How to differentiate bw executable / dependency package?



if package is main -> then exectuable package

if something else -> reusable / dependency

e.g 

    package main

    package calculator


    if we run go run <main file> we get executable


    else we dont get executable


Import statements:
------------------

Import statments - import all code inside a package to the current package


whats import "fmt"?

fmt - format

import format


main package 

for doc on packages- golang.org/pkg

what is func?

    func - function 

    func <name>(<args>){
        <body>
    }

    

File organisation
==================

How main.go is organised


package declaration
import statments
file body (funcs etc)




Go project:
-----------

E.g of playing cards

newDeck -> create list of playing cards
print -> log out contents of deck of cards
shuffle 
deal


Created a new project ->
created a new folder

inside the root of new folder created main.go



Variable Declarations:
----------------------

    var card string = "Value"

    var <variableName> <Type> = <value>

    <variableName> := "value"


    the Type declaration is optional in Vscode
    
    if the variables are typed then only can assign 
    the specified Type

    GO is STATIC TYPED language (like java and C++)


    card := "value"

    we use this := in case on new variable assignment

    for reassigning the variables just =

    we can just initialise the variable first and assign it later also

    declaring a variable outside the main function didnt work
     with := 
     it works with var keyword 

     the first assignment of := will determine the type 

Basic go Types:
---------------

bool
string
int
float64
float32

There are many other types as well



Go functions
------------

    func funcName() <return type> {
        return value
    }

    
    func newCard() string {
	    return "Return value"
    }

To use  a function in another file inside same package we dont need to import it



Slices and for loops:
---------------------

Array
    
    fixed length list of things

Slice

    An array that can grow or shrink


Slices and Array must be of same type (Homegeneous)

    sliceName := []<type>{values...}
    cards := []string{"value","Value"}
    

How to add new element to slice?

    cards = append(cards , "value")

    this append will not modify the existing slice but will return new slice

How to iterate with slice?

    for i, el := range elementArr {
        fmt.printLn(i , card)
    }
    
why we use := in for loops

    in the i , el we are throwing away the prev values
    so we are reassigning


OO approach and GO approach
============================

    Go is not object oriented programming language

    Object oriented approach :

        we card a Deck class

        and give the properties and functions 

        and we create an instance of deck



    GO approach:

        with go we have basic go types
        such as 

        string
        integer
        float
        array 
        map

    we define a type using these basic types

    "we extend a base type and add some functionality to it"

            type deck []string

    functions with 'deck' as 'receiver'


Custom type declaration
------------------------

    type <name> Type

    type deck []string


    when we run 2 files 

    go run main.go other.go

    func(d deck) print(){

    }


    d = actual copy / reference of deck we are working on with is available in the function as variable called 'd'

    every variable of type deck can call the function on itself

    func(e type) funcName(){

    }

    any variable inside of application of type deck will get access to the print method

    and this is the receiver function


    func(reference Type) funcName(){
        // do
    }

    the reference can be assumed to be 'this' from oops


    we have deck class -> deck instance with functions



creating new deck:
------------------

func newDeck() deck {

	cards := deck{}
	cardSuits := []string{"Spades", "Hearts", "Diamonds", "Clubs"}
	cardValues := []string{"Ace", "Two", "Three", "Four", "Five", "Six", "Seven"}

	for _, suit := range cardSuits {
		for _, val := range cardValues {
			cards = append(cards, suit+"of"+val)
		}
	}

	return cards
}


if we dont wanna use the variable in iteration
we give _ 


slice range syntax:
-------------------


slices are zero indexed

slice is homogeneous


slice[startIndexIncluding  : upToNotIncluding]


slice[ :end] - returns start to endvalue
slice[ start:] - return startValue to all remaining


multiple return values:
------------------------


using args inside function call:


    func deal(d deck , handSize int) (deck , deck){
        return[:handSize],[handSize:]
    }


    after args we give return type inside ()


    to capture the multiple value

    var1 , var2 := deal(Cards , 5)

    	hand, remainingCards := deal(cards, 5)


Receiver function syntax:
------------------------

    func (type) name() (return){}

func (d deck) print() {
	for i, card := range d {
		fmt.Println(i, card)
	}
}

saveToFile:
--------------

    golang.org

        take frm packages


    Take a deck and save in to harddrive

    go - std lib packages

    under packages in golang.org

    
    [] -> slice

    data []byte -> slice of byte / byte slice


ByteSlice : 

    take a string of characters

    slice like array every element inside corresponds to ascii value
     
    this is byte slice
    
    convert stirng to []byte

    type conversion : 

        converting one type of value to another

    []byte("Hi there)

    typeWant(typeHave)



    should we use receiver / not (deciding)


	return strings.Join([]string(d), ",")


func (d deck) saveToFile(fileName string) error {
	return ioutil.WriteFile(fileName, []byte(d.toString()), 0666)
}



LoadFromFile:
-------------

    using ReadFile(fileName) ([]byte,error)

    func readFromFile(fileName string) deck {
	byteSlice, _ := ioutil.ReadFile(fileName)
	return strings.Split(string(byteSlice), ",")
    }



errorHandling:
--------------

    nil is a value in go

    (nil = noValue)

    err != nil

    error handling in go is tricky


    log the error and continue
    log the error and quit


    from OS package we can quit the system


    OS - exit(0) = success
        exit(code) = something wrong

Shuffling deck
--------------

    we can swap like this

		d[i], d[newPos] = d[newPos], d[i]

Random number generation:
--------------------------

    Random number requires seed value

    by default go random number generator use same seed

    type Rand is a source of random numbers


	source := rand.NewSource(time.Now().UnixNano())
	r := rand.New(source)

	for i := range d {
		newPos := r.Intn(len(d) - 1)
		d[i], d[newPos] = d[newPos], d[i]
	}




Creating go.mod file:
---------------------

    go mod init <modName>

Testing with GO:
-----------------

    Go testing is not Rspec mocha jasmine or selenium

    to make test
    create 

        _test.go


    every file should have package


    Errorf call has arguments but no formatting directives

    Deciding what to test?


    func TestNewDeck(t *testing.T) {
	d := newDeck()

	if len(d) != 16 {
		t.Errorf("Expected deck length of 16, but got %v", len(d))
	}
    }


    the * means type of value passed in to the function

    t is test handler

    to inject string value

    %value

    t.Errorf("Error : %value" , value)


    Deleting a file 

        os.remove("fileName") (error)


    to modify the type of the receiver function 
    we pass *
    


# Organising data with structs:
===============================

    to get the value of the card in the above example

    we had to do some string ops

    Data structure to solve this?

    Structs:
    --------

        struct -> structure

         A struct is a collection of fields. 

        Data structure -> collection of different props related together

        Struct - card

        struct card{
            suit string;
            value string;
        }
        
        its similar to javascript object
    
    Defining structs:
    ------------------

        Person struct

            firstName
            lastName


    type <Name> struct{
        firstName string
	    lastName  string
    }
        
    the value assignment is purely dependent on the order of the fields

    if the fields are changed for structs somewhere later in time -> it will mess up the code
    

    declaring : 
    -------------

        p := person{values}
        p1 := person{field : value}

        var name structType

        var p2 person 

        this will be init with 0 value

        string = ""
        int = 0
        float = 0
        bool = false

        etc

        %+v -> will print field names and values 
        with printf
    
    updating structs:
    -----------------

        person.firstName = "af"
        


    Embedding structs:
    -------------------

        embed one struct inside another

        type contact struct{

        }

         type person struct {
            name string
            contactInfo contact 
            // can simply give contactInfo to simply reuse the same name as struct
        }

        every single field should have comma

    Structs with receiver functions:
    -------------------------------

    normally declare a receiver function and use



    updating the fields through receiver function did not work

    person.updateName() -> didnt take effect



    Pointers in GO:
    ---------------

       Go is a  Pass by value language

       means it will take the value passed to function and make a copy of that in the memory


    structs with pointers:
    ----------------------


    we use 

    personPointer := &person

    to get memory address

    & - gives memory address of the value this variable is pointing at 

    * - give the value of this memory address pointing at


    * infront of a type

        this is type description 

        it means we are working with pointer to person


    * infront of a variable

        this is an operator

        it means we want to manipulate the value the pointer is referencing

    turn address in to value with *address
    turn value in to address with &value


    pointer shortcut:
    -----------------

    when the type is pointer 

    we pass pointer with &person

    when we have type pointer to person and pass the variable itself (pointer to person and pass person variable) 

    this is a shortcut

   
    if we have a pointer to type and pass the type 
    its fine with go



    Gotchas with pointers:
    ----------------------

    Go is pass by value language

    In case of slice -> when we modified the value it didnt make copy it modified the original 

    Whenever you pass an integer, float, string, or struct into a function, what does Go do with that argument?

    Arrays :
        Primitive DS
        cant be resized
        rarely used

    Slices:
        can grow and shrink
        used 99% of time
    

    when we create slice internally go creates 2 different data structures 


    first => slice (ptr to head , capacity , length)

    pointer points to the array head


    when we pass the slice to the function 
    its copied but the memory address it points to is the same array.

    so when we modify -> we are modifying the actual Array

    ** Important **
    ---------------
    value types :

        int , float , string ,bool , structs
        
        (worry about pointer)
    
    reference types:

        slices , maps , channels , pointers , functions

        (dont worry abt pointer)


    go still makes copy but makes copy of the memory address

## Maps in GO:
--------------

    map is a collection of key value pair
    
    similar to javascript object


    in Map -> keys and values are statically typed

    key - must be of same type
    value - must be of same type

    manipulating maps:
    ------------------

        we can also declare with var keyword


        var cols map[string]string

        cols := make(map[string]string)

        we dont have dot syntax in map

        we have to select using key 

        delete key value

        delete(mapName,key)

    iterating over maps:
    -------------------


    for key,value := range map{
        //do
    }


    for color , hex := range c{
        fmt.println(color)   
    }


    diff bw maps and structs:
    --------------------------

    
        how is this diff from structs?



        Map:
        ----

        all key must be same type

        all values must be same type

        all the keys are indexed so we can iterate

        map is reference type (passing ref)

        we dont need to know all the keys and values at compile time

        collection of related properties


        struct:
        --------

        all values can be diff type
        
        keys support indexing

        struct is value type

        different properties

        
        where to use which?

        map - collection of key value pairs

        struct - collection of different fields

## interfaces:
===============


    Every value has type

    every function has to specify the type of its args

    so every function we write must be rewritten to accomodate different types 
    even if logic in it is identical

    E.g 

        sum fun with int and float types

        the shuffle function doesnt have to be specific for the type deck

        it can be done for float slice , int slice anything


        example of english and spanish bots


    func (englistBot) getGreeting() string {
	return "Hello"
    }

    if we are not making use of the variable of receiver function we can just limit with the type


    in the bot example
    we had a function getGreeting that has different logic for both structs
    and printGreeting with kind of same logic implementation

    ****
    
    GO doesnt support method overloading even with different type
    
    ****


    to reuse the print function for 2 different types


    type bot interface {
	    getGreeting() string
    }



    To whom it may concern ...


        when we declare a type interface

        all different types with the same function name and return type as the interface will be the member of type interface (bot)

    in above example the type bot has getGreeting function which are the function similar to the functions declared for the interface


    any type that implements the type's function as interface they are member of the interface type

    type englishBot can be passed to the bot receiver function since it became member of type bot

interface Rules:
----------------

    interfaces

        type bot interface

        getGreeting() string


    type                englistBot                             

    func (englishBot) 
    getGreeting string

    

    type interfaceName interface {
        methodName(<args>)(<returns>)
    }


    type bot interface{
        getGreeting(string , int) (string , error)
        getBotVersion() float64
        respondToUser(user) string
    }


    type implementationType struct{

    }



    concrete type:

        map , struct , int , string

        concrete type is for which we can create values directly

    interface type :

        we can create values directly for interfaces


    interface notes:

    1. interfaces are not generic types

            other languages have generic types go (Famously ) does not 

            it doesnt have generic types

    2. interfaces are implicit

        we dont manually have to say our custom type satisfies our some inteface

        we dnt say implement interface in go   

    3. interfaces are contract to help us   manage types

        GIGO if custom types implementation of a function is broken then intefaces wont help us

        error when tried to return different type 

         cannot use eb (variable of type englistBot) as bot value in argument to printGreeting: englistBot does not implement bot (wrong type for method getGreeting)
                have getGreeting() int
                want getGreeting() string
        


    4. interfaces are tough (how to read them is step 1)


        understand how to read intefaces in standard lib. writing own interfaces are tough and requires exp
   
        first we need to learn to read interfaces

HTTP package:
-------------

    Example program

    makes http request to google.com

    print response in terminal



    func Get(url string) (resp *Response, err error)

    body of the resp is blob 

    in go io.ReadCloser


    ReadCloser is interface

    type ReadCloser{
        Reader
        Closer
    }


    type Reader interface {
        Read(p []byte) (n int, err error)
    }


    Response struct 

        status 
        statusCode
        Body (io.ReadCloser) -> interface


        the field of the struct here is interface

        io.Reader interface

        Read([] byte) (int,error)


    when struct has interface

    it can be assigned with anything if it fulfils the interface

    why inteface as type in struct?

        body -> any value if it fulfils io.ReadCloser

    if we define interface methods with Reader and Closer


    we can take multiple interfaces and assemble to form a single interface

    ReaderCloser has Reader and Closer interface


    
Reader interface:
-----------------

    purpose of Reader interface


    Http request body []flargen
    text file         []string
    image             jpegne
    user command      []byte
    analog sensor     []float

    we have these many types we have to write different functions for printing them

        func printHttp()
        func printText()

    we use reader interface to get the data out of these different sources of inputs

    for each of these different sources

        source of input -> Reader -> []byte 

    Reader is kind of an adapter that acts as a common point of contact that can act as input to those differnet sources



    in our example the request body has implemented it



    working with Read function
    --------------------------

	    bs := make([]byte, 999999)

        this is empty byteSlice with 999999 elemets

        Read function will read the source of data in to the byteSlice


    	bs := make([]byte, 99999)
	    resp.Body.Read(bs)
	    fmt.Println(string(bs))

        go has built in functions to do this

    
    this simplifies the above

    	io.Copy(os.Stdout, resp.Body)



    we use reader interface to take source of data and output in to byte

    we take byte slice we pass to writer inteface and to some form of output



Writer interface:
-----------------


    []byte -> writer -> source of output 
    
    
    
    (We need to find something in the standard library that implements writer interface )



    io.copy takes writer and Reader 

    writer has file as input and it has write method implemented


    we can hover over the function to see the implementation

    
    custom writer:
    ---------------



        type customWriter struct {}

        func (customWriter) Write(bs []byte) (int, error) {
	        return 1, nil
        }       





Example :

    type shape interface {
        area() int
    }
-*+
    type square struct {
        sideLength int
    }
     
    func (s square) area() int {
        return s.sideLength * s.sideLength
    }
     
    func printArea(s shape) {
        fmt.Println(s.area())
    }





## GO Channels  and GO Routines:
================================

    Structs inside GO for concurrent programming



    example of pinging url

    we are sequentially / serially checking the url


    we wait for the first request to complete 

    we dont move until the first process is completed


Go routines:
------------

    when we launch a go program -> we automatically create a go routine

    go routine executes our code line by line

    Blocking call

    main go routine will be frozen on the blocking call

    launching additional go routine


	for _, link := range links {
		go getResp(link)
	}

    we use go keyword 
    
    the go runtime will create a new routine and executed 

    every single time go keyword is used 

    new routine is created


        Go scheduler

    Go scheduler runs with one CPU 

    by default 1 cpu

    1cpu -> GO scheduler -> go routine 1..n

    as soon as scheduler detects one routine is finished subsequent go routines are executed in case of 1CPU


    in case of multiple cpus 1 cpu can run 1 go routine

    scheduler runs one thread on each logical core
    
    by default go tries to use one core

    "concurrency is not parallelism"

    concurrency -> we can have multiple threads executing code if one thread blocks another one is picked and worked on

        if there is block scheduler will switch 

        go will use one core

    parallelism -> multiple threads executed at exact same time
    
    requires multiple cpu


    main routine created when we launch program

    child routines are created by 'go' keyword

    child go routines are not given respect like main routine?

    we only use go keyword in front of function call

    when we ran our multi thread program it didnt print results

    when main routine creates child routines

    after the creation of routines there will be nothing for the main routine to do -> so it exits



Channels
--------    

    channels are used to communicate in
    between different running go routines

    one channel is created and use it to communicate between the routines

    channels are the only way to communicate between the routines
    

    channel are like messaging 

    channel is typed

    channel can be used to pass around any types

    channel of type string -> can only send string messages


    channel implementation:
    -----------------------

    c := make(chan string)

    its treated like any value in the go 

    func getResp(url string, c chan string)


    channel <- 4 
    (send value to channel)
    
    myNumber <- channel
    (wait for value to be sent to channel once we get assign to myNumber)

    fmt.println(<-channel)
    (wait for value to be sent to channel and print once we get value)

    channel is a 2 way messaging device

    1 person sending another receiving

    we may want to send message from a go routine to main routine and vice versa


Blocking channels:
-------------------

    after we create every single go routine

    the main routine is waiting for the message to be received in the 
    print statement

    whichever one responded first in the links




    func main() {

	links := []string{
		"http://google.com",
		"http://facebook.com",
		"http://golang.org",
		"http://stackoverflow.com",
		"http://ibm.com",
		"http://microsoft.com",
	}

	c := make(chan string)

	for _, link := range links {
		go getResp(link, c)
	}
	fmt.Println(<-c)

    }

    func getResp(url string, c chan string) {
	_, err := http.Get(url)

	if err != nil {
		fmt.Println(url + " is down")
		c <- url + "is down"
		return
	}

	fmt.Println(url + " is up")
	c <- url + " is up"
    }


    once the data is received in the channel main routine prints it and exits the program

    receiving a message in a channel is a blocking code


    the other routines will not finish in this case


    if we add another print line 

    2 locations are waiting for the message to be received in the channel

    so 2 steps will execute successfully

    if we place a channel to be receving message and no message comes to channel 

    the program wait endlessly


Receiving messages:
-------------------

    Receiving a message in a channel is a blocking operation


    for i := 0; i < len(links); i++ {
		fmt.Println(<-c)
	}



Repeating Routines:
-------------------

    we are pinging continually the links


    	for i := 0; i < len(links); i++ {
		go getResp(<-c, c)
    	}

        for 
        {
		go getResp(<-c, c)
	    }


    for l := range c {
		go getResp(l, c)
	}


 // Here we are using range with channel

 Sleeping a routine:
 ------------------

    in the time package

    sleep function
    

    for l := range c {
		time.Sleep(time.Second)
		go getResp(l, c)
	}


    when we sleep the main routine its a blocker and cant receive message in channel



function literals:
-------------------

    function literal is lambda function / anonymous function (js)

    
        func() {
            // do
			time.Sleep(time.Second * 2)
			getResp(l, c)
		}()

## Channels gotcha:
-------------------


    range variable l captured by function literal warning

    it didnt occur in our env

    function literal there was issue

    copied in memory

    