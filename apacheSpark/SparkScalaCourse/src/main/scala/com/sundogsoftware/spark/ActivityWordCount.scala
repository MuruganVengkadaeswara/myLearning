package com.sundogsoftware.spark

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object ActivityWordCount {


  case class Book(value : String)

  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.ERROR)


    val spark = SparkSession.builder
                .appName("activityWc")
                .master("local[*]").getOrCreate();


    import spark.implicits._
    val input = spark.read.text("data/book.txt").as[Book];

    val words = input.select("value")

    words.show()

    words.printSchema()

    spark.stop();
  }

}
