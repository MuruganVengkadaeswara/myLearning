package com.sundogsoftware.spark

import org.apache.log4j.{Level, Logger}
import org.apache.parquet.format.IntType
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{FloatType, IntegerType, StringType, StructType}

object ActivityTotalSpend {

  case class Customer(id: Int, itemNo: Int, amount: Float);

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)


    val spark = SparkSession.builder
      .appName("totalSpendByCustomer")
      .master("local[*]").getOrCreate();


    val customerSchema = new StructType().add("id", IntegerType, nullable = false)
      .add("itemNo", IntegerType, nullable = true)
      .add("amount", FloatType, nullable = true)


    import spark.implicits._
    val ds = spark.read.schema(customerSchema).csv("data/customer-orders.csv").as[Customer];


    val results = ds.groupBy("id").sum("amount").orderBy("id").show()


    spark.stop();

  }

}
