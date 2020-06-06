package com.montevideolabs.attractionsrecommender.common.spark

import com.montevideolabs.attractionsrecommender.common.config.EnvironmentConfiguration
import org.apache.spark.sql.SparkSession

/**
  * Utility object managing the creation and provisioning of a spark session.
  */
object SparkSessionManager {

  lazy val session: SparkSession = if (EnvironmentConfiguration().LocalSpark) {
    local
  } else {
    provided
  }

  private lazy val local: SparkSession = SparkSession
    .builder()
    .appName("attractions-recommender")
    .master(s"local[5]")
    .getOrCreate()

  private lazy val provided: SparkSession = SparkSession.builder().getOrCreate()
}
