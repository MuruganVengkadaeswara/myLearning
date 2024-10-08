package com.sundogsoftware.spark

import org.apache.spark._
import org.apache.log4j._

object ActivityCustomer {


  def parseLine(line: String): (Int, Float) = {

    val fields = line.split(",")


    val customerId = fields(0).toInt
    val amount = fields(2).toFloat

    (customerId, amount)
  }


  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkContext using every core of the local machine
    val sc = new SparkContext("local[*]", "Customer amount")

    val lines = sc.textFile("data/customer-orders.csv");

    val rdd = lines.map(parseLine)


    val amountsById = rdd.reduceByKey((x,y) => x+y)

    val flip = amountsById.map(x => (x._2 , x._1)).sortByKey();

    val results = flip.collect()


    results.foreach(println)
  }


}
