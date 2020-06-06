package com.montevideolabs.attractionsrecommender.common.feeds.io

import org.apache.spark.sql.{DataFrame, SparkSession}

class ParquetFeedIO(spark: SparkSession) extends FeedIO[DataFrame] {

  override def write(update: DataFrame, path: String): Unit = {
    update.write.parquet(path)
  }

  override def read(path: String): DataFrame = spark.read.parquet(path)
}

object ParquetFeedIO {

  def apply(spark: SparkSession): ParquetFeedIO = new ParquetFeedIO(spark)
}
