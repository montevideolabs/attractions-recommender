package com.montevideolabs.attractionsrecommender.common.feeds.io

import org.apache.spark.sql.{DataFrame, SparkSession}

class CsvFeedIO(spark: SparkSession) extends FeedIO[DataFrame] {

  override def write(update: DataFrame, path: String): Unit = {
    update.write.csv(path)
  }

  override def read(path: String): DataFrame = spark.read.csv(path)
}

object CsvFeedIO {

  def apply(spark: SparkSession): CsvFeedIO = new CsvFeedIO(spark)
}
