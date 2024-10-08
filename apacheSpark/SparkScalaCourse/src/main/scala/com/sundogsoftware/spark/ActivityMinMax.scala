package com.sundogsoftware.spark

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object ActivityMinMax {


//  case class Temp(stationId : String , date)

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)


    val spark = SparkSession.builder
      .appName("activityWc")
      .master("local[*]").getOrCreate();


  }

}
