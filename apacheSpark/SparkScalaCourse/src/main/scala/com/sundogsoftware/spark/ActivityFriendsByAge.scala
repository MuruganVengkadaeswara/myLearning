package com.sundogsoftware.spark

import org.apache.spark.sql._
import org.apache.log4j._

object ActivityFriendsByAge {

  case class Person(id: Int, name: String, age: Int, friends: Int)

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)


    val spark = SparkSession.builder()
                .appName("Friends")
                .master("local[*]").getOrCreate();

    import spark.implicits._
    val people = spark.read.option("header", "true")
      .option("inferSchema", "true").csv("data/fakefriends.csv")
      .as[Person]

    people.printSchema();



    people.select("name").show()

    people.groupBy("age").avg("friends").alias("friends_Avg").sort("age").show();


    spark.stop()
  }


}
