package com.montevideolabs.attractionsrecommender.etl

import com.montevideolabs.attractionsrecommender.common.feeds.attractions.AttractionsFeed
import com.montevideolabs.attractionsrecommender.common.feeds.visits.VisitsFeed
import com.montevideolabs.attractionsrecommender.common.spark.SparkSessionManager
import com.montevideolabs.attractionsrecommender.etl.attractions.sigir.SigirAttractionsLoader
import com.montevideolabs.attractionsrecommender.common.debug.DataFrameDescriptor
import com.montevideolabs.attractionsrecommender.etl.visits.sigir.SigirVisitsLoader

object EtlDriver extends App {

  val spark = SparkSessionManager.session

  val visits = SigirVisitsLoader(spark).load()
  DataFrameDescriptor().describe(visits)
  VisitsFeed(spark).put(visits)

  val attractions = SigirAttractionsLoader(spark).load()
  DataFrameDescriptor().describe(attractions)
  AttractionsFeed(spark).put(attractions)
}
