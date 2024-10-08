Apache spark and Scala


sundog-education.com/sparkscala


download intellij and add scala plugin

Introduction to SPARK:
======================


What is spark?

A fast and general engine for large scale data processing

Scalable:
=========

key is scalability

we can give driver program / spark context


We are not limited to one machine's processing


Driver program/spark context 
     -> cluster manager 
         -> Executor tasks (will talk to     each   other)


 spark has built in cluster manager as well.
so we can use without hadoop also.


FAST :
=====

    spark is fast.

    it runs prgm up to 100x faster than hadoop mapreduce in memory or 10x faster on disk

    DAG(Directed Acyclic graphs) engine optimises work flow.


HOT:
==== 

    Amazon,ebay ,nasa 

NOT HARD:
=========

    we can code in python , java or scala

    if we know SQL -> we are home -> 
    spark datasets and dataframes

    lower level API is available also (RDD) resilient Distributed Dataset


Components of spark:
===================



        spark core

            |

    spark streaming,spark sql , MLLib, GraphX


E.g  spark streaming - notify on 500 errors on data

Transforming log data in a window of time


E.g sparksql -> we can treat data as big database.

E.g MLLib 

GraphX is networks of info. 
e.g users are connected to other users in social media
(not maintained lately)


we are using SCALA lang in this course.



Why Scala?
==========

1. spark is written is scala

2. scala is a functional programming language , it enforces us to write code that is distributable in clusters (parallelized safely and easily)

3. it gives faster performance

4. scala compiles to java bytecode

5. python is slower in comparison

6. less code than java





SCALA CRASH COURSE:
==================

Scala basics:
-------------


New feats of spark is scala first

run on top of JVM

can access java classes

its functional programming

    functions are crux



Create new scala worksheet

syntax:

// -- comments

values are immutable constants

    val hello: String= "Hello"

variables are mutable

    var hi : String = "Hi"

ctrl + alt + w --> run




why we have distinguished val and var?

avoid thread safety issues

avoiding race conditions

Data types:
-----------

    Int
    Boolean
    Char
    Double
    Float
    Long
    Byte (-127 to 127 or 0 to 255)

concat 

    we can use + to concat any data types but it implicitly converts to string 

NO semicolon 

    scala considers every new line as new command , no need for semi colon


this s prefix is not included to variables

println(s"")



Also regex


regex inside """.*"""

type Conversion

str.toInt


operators
==========

>
>=
<>
<=
diff bw & and &&

 && is logical and 
 & is bitwise and 
||
==




Flow control in SCALA
======================

if /else :

    usual

switch :

 for switch we use match statement
    
    number match{

    }


FOR :

    for(x <- 1 to 4){
        // dofor
    }



while : 

    while(cond){
        //do
        // loop break logic
    }



do while: 

   do while loops are dropped in scala 3


do{
    //
}while(cond)


Expressions :
=============

    when we have expressions it implicitly return the type of return value of the expression

    e.g 

    {val x = 10 ; x + 20}

    expressions can be passed as function (say lambda)


    
Functions in SCALA:
--------------------


    def <fun name>(param:type) : return type = {}

    def printit(x : String) : String =      {
            return x + " Hello "
            }   


    def transformInt (x : Int , f: Int=>Int): Int ={
        f(x)
    }

    transformInt(2 , x => x * x * x)
    transformInt(4 , x => { do })

    we can also write  inline lambda fn

    lamda fns can also contain multi line expressions





Data structures in SCALA :
----------------------------


    * Tuples
    * Immutable lists

    val stuff = ("Hi" , "Hello" , "Hola")

    // Refer to individual fields with ONE-BASED index

    stuff._1
    stuff._2


    key value pair 

    val ship = "name" -> "year"
    print(ship._2)


    Tuples are non uniform data types


    Lists :
    -------

    val list = List(
        "Hi" , "Hello", "GM"
    )

    lists are 0 based syntax

    lists have head and tail operators 

    head will give first one 

    tail will give remaining items other than first one

    
    val li = list.map((ship : string)=>ship.reverse)

    reverse the strings in list



    val numList = List(1,2,3,4,5);
    val sum = numList.reduce((x : int , y:int) => x+y)



    filter
    -------

     val newList=   list.filter((x : int ) => x != 5)



    spark also has its own map reduce but the difference is spark will make sure its parallelizable among the clusters

    concat 2 list : 

        val newList = list1 ++ list2

    reverse a list :

        val reverseList = list1.reverse

    sort a list :

        val sorted = reverseList.sorted

    
    list allows duplicates

    to get distince values :

        val distValues = dupList.distinct

    get max value : 

        list.max

    total :

        list.sum


    exists : 

        list.contains(element)


Maps :
========

    val map2 = Map("key"->"value" , "key1" -> "value2" )

    Try catch :
    ==========

    util.Try(//do) getOrElse "Unknown"





    create list of numbers 1-20 
    pritn out numbers that are evenly div by 3
    use filter fun




SPARK: begin
================


Resilient Distributed dataset

Its a dataset (rows of info)

its distributed

its resilient

we can create RDD from 
file (spark context)

hive context

can create from JDBC , cassandra , Hbase , Elastic search ,  JSON , CSV


Transforming RDDs:
================

map (one to one )
flatmap (one to may)
filter 
distinct
sample
union , intersection , subtract , cartesian


val rdd = sc.parallelize(List(1,2,3,4))
val squares = rdd.map(x => x * x)

        yields  1,4 ,9 ,16
        (this is distributed in different nodes)


* many RDD accepts function as param

    rdd.map(x => x * x)

    def sqIt(x : Int) :Int = {
        return x* x
    }

    rdd.map(sqIt)

    scala lang forces us to use this functional programming so it can be distributed


RDD actions:
=============

    -> collect
    -> count
    -> countByValue
    -> take
    -> top
    -> reduce
                
                and more



nothing actually happens in driver program until an action is called 

(lazy evaluation)






Ratings histogram example:
==========================

ratingsCounter.scala file


imports :

import org.apache.spark._
import org.apache.log4j._

setting spark context:
----------------------

val sc = new SparkContext("local[*]" , "name")

local[*] means only local machines
 * is for parallelizing in cores



Load data:
----------

val lines = sc.textFile(<pathtofile>)

extract required data:
----------------------

val ratings = lines.map(x => x.toString().split("\t
")(2))

extracting 3rd column


Perform action :
---------------

val results = ratings.countByValue()

results in driver script


sorted result = result.toSeq.sortBy(_._1)


Spark Internals:
=================




What happened?

when driver script got to the action an EXECUTION PLAN is created from RDDs

textfile()
map()
countByValue()


job is broken in to stages based on what needs to be reorganised

every stage is broken in to tasks (distributed across a cluster)

tasks are scheduled and executed 



Key value RDD:
==============

friends by age example


key is Age - value is no of friends

creating key value RDD

totalsByAge = rdd.map(x => (x,1))

its ok to have other object as values in key value RDD




reduceByKey() - combine values with same key using some function

    rdd.reduceByKey((x,y)=>x+y) 

groupByKey() - group values with same key
sortByKey() - sort RDD by key values
keys() values() - create RDD of just the keys


we can do SQL style joins with  2 key/value RDDs


for mapping ops in key value RDD

use mapValues() and flapMapValues() if it doesnt affect keys





parsing input data

def parseLine(line : string) = {
    val fields = line.split(",");
    val age = fields(2).toInt
}



rdd.mapValues(x => (x,1)).reduceByKey((x,y) => (
    x._+ y._+ x._2 , +y._2
))





Filtering RDD's
================


Weather data example


MinTemperatures.scala


Filters removes data from RDD

Just takes fn that returns boolean


parsedLines.filter(fun)


Counting word occurence in 
spark scala using flatMap


Map transforms each element of RDD in to a new Element

One to one relationship bw input RDD and output RDD

map takes one input row and gives one output row


Flatmap can return any number of rows

i.e it can create many new elements from each one

Using regex 

val words = input.flatMap(x => x.split("\\W+"))



Sorting 


Activity:
---------

add amount spent by customer





Introduction To SparkSQL:
=========================

Dataframes and datasets




Datasets are more efficient in world of scala




SparkSQL :
----------

 Dataframe object extends an RDD

 it has more automatic optimization with RDDs

 Dataframes :

    containes row objects
    can run sQL queries
    has schema
    Read and write to JSON , HIVE ,parquet
    JDBC ODBC and tableau

Datasets : 
----------

Dataframe is dataset of row objects

Dataset can explicitly wrap a given struct or type 

dataframes schema is inferred at runtime , but dataset is inferred at compile time

faster error detection and better optimisation

RDDs can be converted in to datasets with .toDS()



Trend in spark is datasets


datasets -> moreefficient
serialized more efficiently
optimal execution plans can be determined in compile time


dataset allow better interops




MLLib and spark streaming are moving toward using datasets instead of RDDs for primary API


Datasets simplify developement

we can perform most SQL ops on dataset with one line


Using SparkSQL in scala:
------------------------

create sparkSession object instead of sparkContext when using sql

stop session once done



resultDataSet.show()
resultDataSet.select("fieldName")
resultDataset.groupBy("name")
resultDataSet.rdd().map(mapperFun)

we can talk to JDBC / ODBC server

SparkSQL exposes JDBC / ODBC server (if built spark with HIVE support)

start it with sbin/start-thriftserver.sh

listens on port 10000 default

connect using bin/beeline -u jdbc:hive2://localhost:10000

now we have sql Shell to sparkSQL


we can cerate new tables or query exisiting ones that were cached using hiveCtx.cacheTable("tableName)




we can have userdefined functions


import udf to do this


CASE Class in sparkSql:
---------------------

case class ClassName(props)

this case class will correspond to a schema 





Init spark session :
 
 val spark = SparkSession.builder.appName("sparkSql").master("local[*]").getOrCreate()



val schema = spark.read.option("header","true").option("inferSchema", "true").csv("name").as[class]

we can print schema by

people.printSchema()


schema is visual configuration of entire relational database



if we are not inferring schema .as[class] 
it wil give dataframe


    import spark.implicits._
    val people = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv("data/fakefriends.csv")
      .as[Person]

this step requires implicits._ import

Using datasets:
---------------

    people.groupBy("age").avg("friends").sort("age").show();




    people.groupBy("age").avg("friends").alias("friends_Avg").sort("age").show();


    we have group by , aggregate, round and so on




    explode() - similar to flat map - explodes cols to rows

    split()
    lower()





    withColumn() - using existing column create new column with function
    

    if we dont have header

    create schema 

    val tempSchema = new StructType()
                    .add("col",Type,nullable=true)


Go through docs 
apache spark
if needed


we can convert Dataset to Dataframe also




Advanced SPARK:
===============


Find most popular movie

using movielens dataset


userId , movieId , rating , timestamp


we can have only movieID in case class


Joining 2 datasets in the course example we had 2 file u.data and u.item , trying to merge both
and display movie name


-> we could use another dataset to map ID to names then join with rating set but this is unnecessary overhead


-> we could just keep table (map) loaded in driver program 

-> or we could let spark automatically forward to each executor when needed.

-> what if the table were massive ? we'd only want to transfer it once to each executor and keep it there


## Broadcast variable:
======================

    Broadcast object to executors such as they are always there whenever needed

    jsut use sc.broadcast() to ship off whatever u want

    then use .value() to get object back

    use broadcasted object however u want - map fn / udf's waterver


    in example we wrote a scala function to return list of movies with names and id

    and passed to spark context broadcast


    val nameDict = sc.broadcast(loadMovieNames())


    we can use udf like this


    val lookupName : Int => String = (movieID:Int)=>{
      nameDict.value(movieID)
    }

    val lookupNameUDF = udf(lookupName)


    and passing 

        val moviesWithNames = movieCounts.withColumn("movieTitle", lookupNameUDF(col("movieID")))


 Popular superheroeS:
 ---------------------

 marvel-graph.txt 

 id in string

 4453 4452 1134 5521 ....

 marvel-names.txt

 4453 spiderman


Superhero degrees of seperation :
--------------------------------

    An iterative BFS impln in spark 
    introducting accumulators


    Breadth first search

    In a connected graph of n number of nodes 
    we are measuring the distance of other node from a root node 

    and marking the nodes with degrees of separation


Implementing BFS in spark:
--------------------------

    Represent data as node with connections , a color and distance

    our initial connection


    Map function to convert marvel-graph text file to BFS nodes


    using RDD and lower level map and reduce fn

    Iteratively process the RDD

    Mapper:
    -------

    creates new nodes for each connection of gray nodes , with a distance incremented by 1 , color gray and no conns

    colors the gray to black

    copies node itself to the result


    Reducer:
    --------

    combines together all nodes for the same hero ID

    preserves the shortest distance and darkest color found

    preserves list of connections from original node




## Accumulator:
---------------


An accumulator allows many executors to increment a shared variable

e.g 

val hitCounter : LongAccumulator("HitCounter")

this sets up shared accumulator with init value 0

each iteration if the value we need is hit we increment the counter

after each iteration we check hitcounter is > 1 then we finish the execution




Spark is not just SQL problem solving we can do much more than that



RDD version was faster compared to dataset version
(more than twice)

datasets are good at handling traditional data analytics problem

dont think everthing as SQL problem


## Item based collaborative filtering
---------------------------------------

basically recommender engine

e.g amazon -> people bought also this

course example -> finding similar movies



Objective : 

    find every pair of movies watched by same person

    Measure similarity of their ratings across all users  who watched both 

    group by movie , then sort by similarity strength

Making it spark problem

    select userId , movieId and rating columns

    find every movie pair rated by same user

        -> can be done with self join operation
        -> we have movie1, 2 and rating 1 ,2 
        -> filter duplicate pairs

    Compute cosine similarity scores for every pair

        -> add x^2 , y^2 , xy columns
        -> Group by (movie1, movie2) pairs
        -> compute similarity score for each aggregated pair

        -> fiter sort disply




Caching datasets
================


we'll have to query the final dataset more than once

anytime we will perform more than one action on a dataset u should cache it

otherwise spark might re-evaluate the entire dataset all over again

-> Use 

    .cache() or .persist() to do this

    persist optionally lets u cache in to disk instead of just memory , just in case node fails or smtng


Improving results 

    -> discard bad ratings - only recommend good movies

    -> try different similarity metrics

    (Pearson Correlation Coffiecient , Jaccard Coefficient , conditional probability)

    adjust threasholds

    invent new similarity metric that takes the number of co raters in to accounts

    use genre info 




## Running spark on a cluster
==============================


Running spark in a cluster

Using EMR , tuning performance on a cluster

Packaging and deploying application

** 
we should make sure there is no paths to your local file system used in script. we should use shared system / Distributed file system like S3 , HDFs etc.

package the scala project in to JAR file 

execute the driver script outside IDE

spark-submit -class <class obj that contains main fn> --jars --files 



We should use spark-submit 


we used this


../../../spark/spark-3.5.3-bin-hadoop3/bin/spark-submit --class com.sundogsoftware.spark.HelloWorld out/artifacts/sparkcourse/sparkcourse.jar

Packaging with SBT:
===================

SBT is like maven for scala

it manages library dependency tree for u

can package all dependencies in to a self contained jar

if u have many deps , it makes life easier by ton of --jars option 

get it from scala-sbt.org




Dir structure :

    src        project 

    main

    scala

-> scala source file in to source folder

-> create assembly.sbt file contains one line 

    addSbtPlugin("com.eed3si9n"%"sbt-assembly"%"0.14.10")

-> check latest sbt-assembly doc this will change over time

At the root 

create build.sbt file (along side src and project)


Example:
--------



name := "name"
version :="version"
organization :="org"
scalaVersion := "2.12.4"
libraryDependencies ++= Seq(
    "org.apache.spark"%%"spark-core" %"3.0.0" % "provided"
)

provided -> assuming its already present iht file system




from root folder 
run 

    sbt assembly

Jar is self contained we need to use spark-submit <jar file>


Use SBT To bundle a jar file and run locally 


## Amazon Elastic MapReduce:
============================

Spark Driver -> cluster Manager -> Cluster workers (1,2,3....)



Distributed Spark

E.g of cluster managers 

    mesos

    Hierarchy :

    Scripts (dont hardcode master )
    Command Line 
    Configuration



Spark submit parameters:
-----------------------

    --master 

    --num-executors

        2 by default

    --executor-memory

    --total-executor-cores


Amazon elastic mapreduce


    A quick way to create a cluster with spark , hadoop and yarn preinstalled

    pay by hour instance for network and storage IO




EMR (elastic map reduce)

        easy way to rent time on cluster of ur own

        it sets up default spark configuration for u on top of hadoop's YARN cluster manager

        Spark also has built in standalone cluster manager and scripts to set up its own EC2 based cluster

        EMR is not expensive , not cheap either

        remember to shutdown the cluster once done

        run locally first



In course example we created s3 bucket and stored the data there

Created cluster with s3 folder as input .. checking logging , choose spark version 


Even in free tier , its not free

Tip : check security group and configs in server side if running EMR

aws s3 cp <path to file> to download this


Partitioning:
=============

to the dataframe we can do repartition 


Running movie simliarity script as is might not work at all

self join is expensive and spark wont distribute it on its own

use 
 
 .repartition() on dataframe  or 
 .partitionBY() on RDD 
 
 before running a large operation that benifits from partitioning

    e.g join() cogroup() group


HOw to choose partition size

too few partition wont take full advantage

too many is too much overhead

at least as many partitions as u have cores or executors that fit within available memory



Best practices for running on a cluster
=======================================


Specifying memory per executor

avoid specifying spark configuration in your driver script (including master) 

if executors start failing u may need to adjust memory each executor has

    e.g spark-submit --executor-memory <file.py> [args]





-> get scripts and data in place where EMR can access

-> spin up EMR cluster for spark using aws console (billing starts)

-> get external DNS name for master node and log in to it using hadoop user acc and private key file

-> copy JAR file and any file needs using 

    aws cp <path>

-> run spark-submit and watch output

-> terminate


we can automatically start cluster and stop in aws EMR



Troubleshooting and managing deps:
----------------------------------

Its a dark art :D

master will run on console 4040

in EMR its almost impossible to connect actually

you have ur own cluster running on your netwoek life's easer in that respect


http://127.0.0.1:4040/jobs/



Logs : -> available in WebUI

in EMR its distributed

collect by using YARN logs --applicationId <appId>

when driver script runs it will log errors like executors failing

use partitionBy() to demand less from individual Executors by using smaller partitions




## MACHINE LEARNING WITH SPARK ML
=================================


Introducing Spark.ML


Movie recommendations using spark's maching learning library



Capabilities:
------------

    -> Feature extraction (TF-IDF)

    -> basic statistics

    -> linear regression , logistic regressions

    -> SVM

    -> Naive bayes classifier (Spam classification e.g)

    -> Decision trees

    -> K-Means clustering 

    -> Principal component analysis (PCA) , SVD

    -> Recommendations using alternating least square (ALS)



in spark 1 and 2 there was MLLib API and used RDD 

in spark 3 MLLib is deprecated

newer ML library just uses dataframes for everything


ML uses DataFrames



BOOK Recommendations 

    Advanced Analytics with Spark from O'reilly


hyper parameters


    val als = new ALS()
      .setMaxIter(5)
      .setRegParam(0.01)
      .setUserCol("userID")
      .setItemCol("movieID")
      .setRatingCol("rating")



mostly trial and error here


Train / test to evaluate various permutations of params

but what is good recommendation anyway?


Never blindly trust results when analyzing big data

    -> small problems in algorithms become big ones

    -> very often quality of ur input data is real issue

    -> garbage in garbage out


MLLIB is really useful though







Linear regression with MLLib:
------------------------------

Fit a line to a dataset of observations

use this line to predict unobserved values

term regression is misleading?
 
    u use it to predict points in future , the past whatever in fact time usually has nothing to do with it


linear regression is fitting the line to historical data and using this to predict new things

-> usually using least squares

-> minimizes the squared-error bw each point and the line

-> remember the slope intercept equation of line 

    y = mx+b




Spark streaming uses more complex technique called SGD (Stochastic Gradient Descent) it looks for contours




amazon did average page speed and revenue generated from session data 


Predict revenue based on page speed use Linear regression


Format of the data for linear Regression 

    val assembler = new VectorAssembler().
      setInputCols(Array("features_raw")).
      setOutputCol("features")



    val df = assembler.transform(dsRaw)
        .select("label","features")
     



    // Let's split our data into training data and testing data
    val trainTest = df.randomSplit(Array(0.5, 0.5))
    val trainingDF = trainTest(0)
    val testDF = trainTest(1)
    



    We have real estate example

    Refer to kaggle for more real time data


    //Price per unit area based on age , distance to MRT and number of nearby convenience store

    //Using DecisionTreeRegressor


    can do with hyperparameters

    .setLabelCol() so






## Spark Streaming:
====================


Streaming sources of data 


    -> Analyses continual streams of data

        e.g process log data from website or server
    
    -> Data is aggregated and analysed at some given interval


    -> can take data fed to some port e.g TCP , Amazon Kinesis , HDFS , Kafka

    -> checkpointing stores state to disk periodically for fault tolernace


DStream 

    A Dstream object breaks up the stream in to distinct RDDs

    e.g 

        val stream = new StreamingContext(conf , Seconds(1))
        val lines = stream.SocketTextStream("locahost" , port)

        val errors = line.filter(_.contains("error"))





    Starting and stopping stream

    stream.start()
    stream.awaitTermination()


   -> RDD contains one little chunk of      incoming data

   -> Windowed operations can combine results from multiple batches overs some sliding window time

    see window()

   -> updateStateByKey() will let u maintain state across many batches as time goes


    E.g live tweets from twitter


    use apps.twitter.com



    twitter4j-core

    sbt made spark available to us



Structured Streaming
====================

-> Spark2 introduced Structured streaming
-> it uses DataSets as primary API
-> imagine dataset keeps appending forever and query whenever u want

-> streaming is now real-time and not based on micro-batches


Set up like 

    spark.readStream

    e.g

    val inputDF = spark.readStream.json("path").inputDF.groupBy($"action" , window($"time" , "time")).count()
    .writeStream.format("jdbc").start("connection")

    Streaming log files 


    Spark.readstream.text(directory path)

    use SQL operations or mapper to parse out data from log lines

    Apply real time analysis



    A windowed operation looks back over some period of time


    e.g consider events that happended 10 minutes interval 

    slide interval defines how often to evalutae window


    
    // Keep a running count of endpoints


    val logsDF2 = logsDF.withColumn("eventTime", current_timestamp())

    we added this to manipulate data for 30 secs interval


    val endpointCounts = logsDF2.groupBy(window(col("eventTime"),
    "30 seconds", "10 seconds"), col("endpoint")).count()


    val query = sortedEndpointCounts.writeStream.outputMode("complete").format("console")
    .queryName("counts").start()






## GRAPHX
==========

its stuck on the old RDD api

Graph x uses RDD interface , its quite powerful

GraphX like our social network of superheroes 

graphs in computer network sense

-> it can measure things like connectedness , degree distribution , average path length , triangle counts


-> it can apply pageRank algorithm to it

-> it can also join graphs together and transform graphs quickly

-> there is no buildin suppport for degrees of separation


-> Introduces VertextRDD , EdgeRDD and Edge data type

-> otherwise , graphX code looks like any other spark code most part








https://spark.apache.org/





PregelAPI for BFS



Pregel 

-> sends messages to other vertices
-> graph is processed in iterations called supersteps


pregel's vertex program will preserve min distance bw the one it receives and what it has


its reduce op also preserves the min distance 







Diff bw spark session and spark context



MORE:
=====




Books - oreilly - learning spark

for scala -> learning scala - oreilly

ML - advanced analytics for spark













































































