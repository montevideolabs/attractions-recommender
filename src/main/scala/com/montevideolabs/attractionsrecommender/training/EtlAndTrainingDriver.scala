package com.montevideolabs.attractionsrecommender.training

import com.montevideolabs.attractionsrecommender.common.debug.DataFrameDescriptor
import com.montevideolabs.attractionsrecommender.common.feeds.alsmodel.AlsModelFeed
import com.montevideolabs.attractionsrecommender.common.spark.SparkSessionManager
import com.montevideolabs.attractionsrecommender.etl.visits.sigir.SigirVisitsLoader
import com.montevideolabs.attractionsrecommender.training.spark.AlsAttractionsRecommenderTrainer

object EtlAndTrainingDriver extends App {

  val data = SigirVisitsLoader(SparkSessionManager.session).load()
  DataFrameDescriptor().describe(data)
  val model = AlsAttractionsRecommenderTrainer().train(data)
  AlsModelFeed().put(model)
}
