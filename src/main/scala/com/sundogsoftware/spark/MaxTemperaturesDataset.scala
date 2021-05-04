package com.sundogsoftware.spark

import org.apache.spark.sql.types.{FloatType, IntegerType, StringType, StructType}
import org.apache.log4j._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

/** Find the minimum temperature by weather station */
object MaxTemperaturesDataset {

  case class Temperature(stationID: String, date: Int, measure_type: String, temperature: Float)

  /** Our main function where the action happens */
  def main(args: Array[String]) {
   
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkSession using every core of the local machine
    val spark = SparkSession
      .builder
      .appName("MaxTemperatures")
      .master("local[*]")
      .getOrCreate()

    val temperatureSchema = new StructType()
      .add("stationID", StringType, nullable = true)
      .add("date", IntegerType, nullable = true)
      .add("measure_type", StringType, nullable = true)
      .add("temperature", FloatType, nullable = true)

    // Read the file as dataset
    import spark.implicits._
    val ds = spark.read
      .schema(temperatureSchema)
      .csv("data/1800.csv")
      .as[Temperature]
    
    // Filter out all but TMAX entries
    val minTemps = ds.filter($"measure_type" === "TMAX")
    
    // Select only stationID and temperature)
    val stationTemps = minTemps.select("stationID", "temperature")
    
    // Aggregate to find minimum temperature for every station
    val maxTempsByStation = stationTemps.groupBy("stationID").max("temperature")

    // do not Convert temperature to fahrenheit, show in celsius and sort the dataset
    val maxTempsByStationF = maxTempsByStation
      .withColumn("temperature", round($"max(temperature)" * 0.1f, 2))
      .select("stationID", "temperature").sort("temperature")

    // Collect, format, and print the results
    val results = maxTempsByStationF.collect()
    
    for (result <- results) {
       val station = result(0)
       val temp = result(1).asInstanceOf[Float]
       val formattedTemp = f"$temp%.2f C"
       println(s"$station minimum temperature: $formattedTemp")
    }
  }
}