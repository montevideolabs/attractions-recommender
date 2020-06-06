package com.montevideolabs.attractionsrecommender.common.feeds.visits

import com.montevideolabs.attractionsrecommender.common.feeds.Feed
import org.apache.spark.sql.{DataFrame, SparkSession}

object VisitsFeed {

  /**
    * @param spark the spark session to be used for the feed.
    * @return a feed for visits as a data frame.
    */
  def apply(spark: SparkSession): Feed[DataFrame] = Feed.parquet(spark, "etl/visits/v1.0/")
}
