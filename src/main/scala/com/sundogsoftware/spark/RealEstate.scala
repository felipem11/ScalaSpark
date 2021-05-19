package com.sundogsoftware.spark

import org.apache.spark.sql._
import org.apache.log4j._
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.regression.{DecisionTreeRegressor, LinearRegression}
import org.apache.spark.sql.types._

object RealEstate {

  case class RegressionSchema(No: Integer, TransactionDate: Double, HouseAge: Double,
                              DistanceToMRT: Double, NumberConvenienceStores: Integer, Latitude: Double,
                              Longitude: Double, PriceOfUnitArea:Double)

  /** Our main function where the action happens */
  def main(args: Array[String]) {
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    val spark = SparkSession
      .builder
      .appName("LinearRegressionDF")
      .master("local[*]")
      .getOrCreate()

    // Load up page speed / amount spent data in the format required by MLLib
    // (which is label, vector of features)

    // In machine learning lingo, "label" is just the value you're trying to predict, and
    // "feature" is the data you are given to make a prediction with. So in this example
    // the "labels" are the first column of our data, and "features" are the second column.
    // You can have more than one "feature" which is why a vector is required.
    //val regressionSchema = new StructType()
    //  .add("label", DoubleType, nullable = true)
    //  .add("features_raw", DoubleType, nullable = true)

    import spark.implicits._
    val dsRaw = spark.read
      .option("sep", ",")
      .option("header", "true")
      .option("inferSchema", "true")
      .csv("data/realestate.csv")
      .as[RegressionSchema]

    val assembler = new VectorAssembler().
      setInputCols(Array("HouseAge", "DistanceToMRT", "NumberConvenienceStores")).
      setOutputCol("features")
    val df = assembler.transform(dsRaw)
      .select("PriceOfUnitArea","features")

    // split data into training data and testing data
    val trainTest = df.randomSplit(Array(0.5, 0.5))
    val trainingDF = trainTest(0)
    val testDF = trainTest(1)

    // create decision tree regression model
    val dir = new DecisionTreeRegressor()
      .setFeaturesCol("features")
      .setLabelCol("PriceOfUnitArea")

    // Train the model using training data
    val model = dir.fit(trainingDF)

    //  predict values in test data
    // Generate predictions using linear regression model for all features in
    // test dataframe:
    val fullPredictions = model.transform(testDF).cache()

    // This basically adds a "prediction" column to our testDF dataframe.

    // Extract the predictions and the "known" correct labels.
    val predictionAndLabel = fullPredictions.select("prediction", "PriceOfUnitArea").collect()

    // Print out the predicted and actual values for each point
    for (prediction <- predictionAndLabel) {
      println(prediction)
    }

    // Stop the session
    spark.stop()

  }
}