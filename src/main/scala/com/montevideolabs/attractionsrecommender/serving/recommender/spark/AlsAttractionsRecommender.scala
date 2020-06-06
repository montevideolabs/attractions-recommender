package com.montevideolabs.attractionsrecommender.serving.recommender.spark

import scala.util.{Success, Try}

import com.montevideolabs.attractionsrecommender.common.feeds.visits.VisitsColumnNames
import com.montevideolabs.attractionsrecommender.serving.recommender.AttractionsRecommender
import org.apache.spark.ml.recommendation.ALSModel
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.sql.functions.{col, hash}
import org.apache.spark.sql.types.{StringType, StructField, StructType}

class AlsAttractionsRecommender(val model: ALSModel, spark: SparkSession)
  extends AttractionsRecommender {

  override def recommend(user: String): Seq[String] = {
    val recommendationsDf = model.recommendForUserSubset(predictionsDf(user), 10)
    Try(recommendationsDf.first()) match {
      case Success(Row(_: Int, recommendations: Seq[Row])) =>
        cleanRecommendations(recommendations)
      case _ => Seq()
    }
  }

  private def predictionsDf(user: String): DataFrame =
    spark
      .createDataFrame(
        spark.sparkContext.parallelize(Seq(Row(user))),
        StructType(Seq(StructField(VisitsColumnNames.UserId, StringType, nullable = false)))
      )
      .withColumn(VisitsColumnNames.UserId, hash(col(VisitsColumnNames.UserId)))

  private def cleanRecommendations(recommendationRows: Seq[Row]): Seq[String] =
    recommendationRows.map {
      case Row(attraction_id: Int, _: Float) => attraction_id.toString
    }
}

object AlsAttractionsRecommender {

  def apply(model: ALSModel, spark: SparkSession): AlsAttractionsRecommender =
    new AlsAttractionsRecommender(model, spark)
}
