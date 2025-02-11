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

        all key must be same type

        all values must be same type

        all the keys are indexed so we can iterate

        map is 


        struct:

        all values can be diff type
        
        keys support indexing

        struct is value type


