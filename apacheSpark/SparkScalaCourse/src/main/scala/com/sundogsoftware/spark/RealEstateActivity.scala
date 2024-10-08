package com.sundogsoftware.spark


import org.apache.spark.sql._
import org.apache.log4j._
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.regression.DecisionTreeRegressor
import org.apache.spark.sql.types._

object RealEstateActivity {

  case class RegressionSchema(houseAge: Double, DistanceToMRT: Double, numberOfConvenienceStore: Double)

  def main(args: Array[String]): Unit = {


    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    val spark = SparkSession
      .builder
      .appName("RealEstateActivity")
      .master("local[*]")
      .getOrCreate()


    import spark.implicits._
    val dsRaw = spark.read
      .option("sep", ",")
      .option("header", "true")
      .option("inferSchema", "true")
      .csv("data/realestate.csv")

    dsRaw.printSchema();


    val assembler = new VectorAssembler().
      setInputCols(Array("HouseAge", "DistanceToMRT", "NumberConvenienceStores")).
      setOutputCol("features")
    val df = assembler.transform(dsRaw)
      .select("PriceOfUnitArea", "features")


    val trainTest = df.randomSplit(Array(0.5, 0.5))
    val trainingDF = trainTest(0)
    val testDF = trainTest(1)


    val lir = new DecisionTreeRegressor()
      .setFeaturesCol("features")
      .setLabelCol("PriceOfUnitArea") // regularization


    val model = lir.fit(trainingDF)

    val fullPredictions = model.transform(testDF).cache()

    val predictionAndLabel = fullPredictions.select(
      "prediction" , "PriceOfUnitArea"
    ).collect()

    for(prediction <- predictionAndLabel){
      println(prediction)
    }

    spark.stop()

  }

}
