package com.montevideolabs.attractionsrecommender

import com.montevideolabs.attractionsrecommender.common.spark.SparkSessionManager
import com.montevideolabs.attractionsrecommender.etl.attractions.sigir.SigirAttractionsLoader
import com.montevideolabs.attractionsrecommender.etl.visits.sigir.SigirVisitsLoader
import com.montevideolabs.attractionsrecommender.serving.recommender.spark.AlsAttractionsRecommender
import com.montevideolabs.attractionsrecommender.training.spark.AlsAttractionsRecommenderTrainer

object InMemoryDriver extends App {

  val spark = SparkSessionManager.session
  val visits = SigirVisitsLoader(spark).load()
  val attractions = SigirAttractionsLoader(spark).load()
  val model = AlsAttractionsRecommenderTrainer().train(visits)
  val recommender = new AlsAttractionsRecommender(model, spark)
  val users = Seq("10004778@N07")
  val recommendations = users.map(userId => (userId, recommender.recommend(userId)))
  println(recommendations)
}
