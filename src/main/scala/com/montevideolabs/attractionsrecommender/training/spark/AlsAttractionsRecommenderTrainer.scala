package com.montevideolabs.attractionsrecommender.training.spark

import com.montevideolabs.attractionsrecommender.common.feeds.visits.VisitsColumnNames
import org.apache.spark.ml.recommendation.{ALS, ALSModel}
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{col, hash}

class AlsAttractionsRecommenderTrainer {

  private lazy val als = new ALS()
    .setUserCol(VisitsColumnNames.UserId)
    .setItemCol(VisitsColumnNames.AttractionId)
    .setRatingCol(VisitsColumnNames.VisitsCount)
    .setColdStartStrategy("drop")
    .setSeed(321L)

  def train(visits: DataFrame): ALSModel = als.fit(trainingData(visits))

  private def trainingData(visits: DataFrame): DataFrame =
    visits.withColumn(VisitsColumnNames.UserId, hash(col(VisitsColumnNames.UserId)))
}

object AlsAttractionsRecommenderTrainer {

  def apply(): AlsAttractionsRecommenderTrainer = new AlsAttractionsRecommenderTrainer
}
