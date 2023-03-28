1. Do I need to use Jupyter Notebooks and Anaconda?

No, you can use whatever IDE you want! All the code we show in the course is compatible with any text editor or IDE that works with Python! You can even convert the notebooks to .py files by using nbconvert or just by clicking Save As -> .py file.

2. Where do I get the notebooks for this course?

You can get them as a zipped file resource in this lecture or you can also get them in the lecture titled "Jupyter Notebooks"

3. Is there a good companion book for the Machine Learning Section?

Yep! You can find it here. I mention it again many times in the ML section.

4. I'm having technical issues with Video or Sound not showing up, who do I contact?

Make sure to contact support@udemy.com I don't control the technical platform, I just create the content! Again please direct all technical platform issue questions to support@udemy.com

5. How do get my certificate of completion?

Please follow Udemy's instructions here: 
https://support.udemy.com/hc/en-us/articles/229603868-Certificate-of-Completion

6. Hey Jose, you talk a bit slow in the lectures, any way I can speed it up?

Sure, you can click on the speed changer on the lower left hand part of the video player, up to 2x speed! Since we have many students how are non-native english speakers in the course, I try my best to talk a steady pace to help their understanding!

7. How do you get the Docstring and method list pop-ups in Jupyter Notebook?

Use Tab with your cursor directly after a defined variable to see the list of methods. For example, given: my_list = [1,2,3] you could then run that cell to define my_list as a variable, afterwards you could just type: my_list. (notice the dot) and then press Tab to see the list of methods. For the doctrings of functions, use Shift+Tab with your cursor right after the function.

8. Do you provide personal or corporate training?

Yes! Check out my profile for a link to my webpage at Pierian Data to get more info!

9. How do I know where my Notebooks are being saved?

To find out where your notebooks are type: pwd in a cell. This will print your working directory.

10. How can I change where the Notebooks are being saved?

You will need to change the directory in which you are starting you jupyter notebook. Use cd in the terminal or command prompt to change to your desired directory.

11. How do I open .ipynb files? What program do I choose?

In order to open the Notebook Files you'll need to have Python and the Jupyter Notebook system installed, check out the Python Set-up section for more details on the installation of Python and the Jupyter Notebook system (or you can just follow the relevant instructions here if you feel more technical). Once you have python and the jupyter notebooks installed you are ready to open the notebooks using the following steps:

    First open up your Command Prompt (search for cmd on a Windows machine) or if you are on a Mac use your terminal (Spotlight search for terminal).
    Next in you terminal/command prompt type pwd and press enter (this will print your working directory)
    Take note of what file directory was displayed, this is where you should save your .ipynb files (or a folder containing your .ipynb files)
    Once your ipynb files or folder containing the files is in the location displayed from the pwd step go back to your terminal and type jupyter notebook and press Enter.
    After Step 4 you should have a browser tab open up with the Jupyter Notebook system running inside of it.
    Click on your Notebook (or go to your folder of Notebooks) displayed in the Jupyter Notbeook and it will open in a new tab with the Notebook you selected.
    You should now have successfully opened a Notebook file.

12. How do I get help if I'm stuck on something?

Do the following:

1. Search Google and StackOverflow for your error and see if you can find a posted solution

2. No luck? Try searching the QA posts in this course for a previously answered similar question

3. Still can't find the answer? No worries! Post a new QA forum question and the other students and I are happy to help out!

4. Browse the recent QA posts and see if you can help out other students!

====================================



New notebook 

-> Code cell
-> Run to run

shift + enter -> run
alt+ enter -> add cell

 

 virtual environments - anaconda

 -> can alt bw versions 
    (e.g python / scikit)

-> virtualenv library 
-> anaconda has build in virtual env


Create env : 

==========

conda create --name [name] [basepackage/lib]

conda asks y/n 

now env is created /envs/[name]




-> activate [name]
-> deactivate


Python crash course:
======================




Basic data types:

2 basic number types : 

->int
->float


2 ** 4 = 16 
2 power of 4

python will follow BODMAS

mod fun -> %

4 % 2 = 0 (can check if number us even)


Variable assignment
-------------------

varName  = 'sn'

cant start with numbers for variable
use _
special chars not allowd



# to comment


Strings
--------
 can use '' or ""

 can wrap '' around ""

printing strings

-> call var name to print (console)
-> print(varName) - right way


formatting print stmts

num = 12 , name = 'mv'

'My Number is {} and name is {}'.format(num , name)

replaces {} with the format values


'My Number is {one} and name is {two}'.format(one=num , two=name)

indexing 
=======

s = 'hello'

s[0] = 'h'

s[0:] => slice notation = 'hello'

s[:0] = ''
s[:1]='h'


s[3:6]

lists 
-------

lists are sequence of elements

list= ['a','b']

list.append['c'] 

['a','b','c']

list[1:2] = ['b','c']

list[0]='hrr'
reassigned

list in list => nest list

[2,3,[4,5]]

Dictionary:
---------------

d= {'key':'value'}
(hashtable)

nested dictionary / list


boolean
-------
True
False
(only caps)

Tuples
-------

similar to list but immutable

t =(1,2,3)
similar to list 

t[0] = 1

tuples are immutable 
(we cant reassign t[0])


set
----

Collection of unique elements

using curly braces

{1,2,3}

{1,1,2,3} = {1,2,3}

set([1,2,2,3,3]) = {1,2,3}

s= {1,2,3}

s.add(el)

comparision operators
-----------------------

1 > 2 = False
1 == 2 
1 = 1 (var assignment)
1 != 3

1 < 2 and 2 > 3
2 < 3 or 2 > 4

and / or 

blocks:
-------

if , elif , else

if 1 < 3 :
 print('jrll')

python doesn't use brackets only space


if (Cond):
    some
elif (Cond):
    some
elif(cond):
    some
else
    some



Loops:
======

seq =[1,1,2,3]

for el in seq:
    some code


i= 1;
while i < 4:
    some code

if infinite loop is there 
there ll be * in notebook cell
restart kernel


range
------
 range(start , end)

 range is generator 

 for x in range(0,4):
    print x;

range(end)

list(range(10))

list is keyword

list comprehension:
------------------

x=[1,2,3,4]

out = []

for num in x :
    out.append(num ** 2)


out = [1,4,9,16]

(Eg scenario above)

[num**2 for num in x] = [1,4,9,16]

functions:
-----------

def funName(params):
    exec

default value for params : def funName(name="sfda")

if value is not passed default is taken

functions can have doc string 

eg :

    def square(num):
        """
            Comments
        """

shift + tab => gives docs



Lambda expressions
-------------------

    def times2(num):
        return num*2
    

    seq = [1,2,2,3]


map(fun,  seq)


list(map(times2 , seq))


we can map fun to all the element in list

syntax : 
     lambda var:var*2

     t = lambda el:el*2
     t(el)

    assign to variable to call


filter :
-------

similar to map .
instead of mapping element to function
filter out the element

list(filter(lambda num:num > 2 , seq))


some methods:
------------
press tab to see
string methods = upper,lower,split

dictionary = keys,values

list methods= pop , append

el in [list] => boolean

tuple unpacking:
---------------

x = [(1,2) , (3,4)]

for item in x:
    print item

 -- unpacking

for(a,b) in x:
    print a , b

Numpy:
=====================


linear algebra library for python

is fast

conda install numpy 
pip install numpy


-> Numpy arrays comes in 2 flavors
    -> vectos and matrices

-> vectors are stricly 1d arrays
-> matrices are 2d 
    -> matrices can hav only 1 row or one column


numpy arrays:
=============

import numpy as np;

np.array(1d or 2d arr)

returns array[()]

np.arange(0,10)

gives array([0,1,...,10])

np.arange(0,11,2) -> with step size
(will skip numbers)

np.zeros(3)

array([0.,0.,0.])

np.zeros((2,3)) = will give 0

similarly np.ones((4,4)) = will give 1

np.linspace(start,stop,num=10)

np.eye(4) => diag 1 all other 0
(identity matrix)

np.random.rand() (0-1)
np.random.randn() (neg)

np.random.randint(0,1 ,size)=> 1 is exclusive




arr.reshape(5,5) => reshape to 2 d 5 by5 matrix


arr.max()
arr.min()

arr.argmax() => index of max value
arr.argmin() => index of min value

arr.shape() => returns shape


arr.dtype() => return datatype

also : 
    from numpy.random import randint



numpy indexing and selection
============================



arr[index]

arr[startIndex : endIndex]

e.g arr[0:5]


also arr[:6] == arr[0:6]


arr2d[:2,1:]



arr =[1,3,4,5,6,7]
arrr > 5

returns array of boolean value with 
the condition



bool_arr = arr > 5
arr[bool_arr]




arr[arr>5] => returns element > 5


we are passing the boolean array (note)




arr_2d[1:3,2:4] 



Numpy Operations
====================

array with array Operations


to add array to element by element
basis

arr + arr

arr * arr etc


arr - 200 

np.sqrt(arr) =  squareroot
np.exp(arr) = exponent


visit docs.scipy for common functions
ufunc (universal functions)


np.random.randn(25) = returns 25 random numbres

np.linspace(0.01,1,100).reshape(10,10)



get standard deviation in matrix 
 
  => mat.std()

get sum of all columns in matrix

    => mat.sum(axis=0)


pandas
===========

intro : 
=> pandas 

open source lib build over numpy
pythons version of excel



series
=======

pd.Series(data = my_data)

pd.Series(data = my_data , index = labels)

panda series can hold diff data types


ser + ser => will add the values based on key => if no key is present returns nan

(labeled index and datapoint)




dataframes
==========

np.random.seed(101)


