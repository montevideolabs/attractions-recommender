package com.montevideolabs.attractionsrecommender.training

import com.montevideolabs.attractionsrecommender.common.debug.DataFrameDescriptor
import com.montevideolabs.attractionsrecommender.common.feeds.alsmodel.AlsModelFeed
import com.montevideolabs.attractionsrecommender.common.feeds.visits.VisitsFeed
import com.montevideolabs.attractionsrecommender.common.spark.SparkSessionManager
import com.montevideolabs.attractionsrecommender.training.spark.AlsAttractionsRecommenderTrainer

object TrainingDriver extends App {

  val spark = SparkSessionManager.session
  val data = VisitsFeed(spark).get()
  DataFrameDescriptor().describe(data)
  val model = AlsAttractionsRecommenderTrainer().train(data)
  AlsModelFeed().put(model)
}
