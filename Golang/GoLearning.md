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


    func deal(d deck , handSize int){

    }

    