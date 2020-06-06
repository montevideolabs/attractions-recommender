package com.montevideolabs.attractionsrecommender.common.feeds.attractions

import com.montevideolabs.attractionsrecommender.common.feeds.Feed
import org.apache.spark.sql.{DataFrame, SparkSession}

object AttractionsFeed {

  /**
    * @param spark the spark session to be used for the feed.
    * @return a feed for attractions as a data frame.
    */
  def apply(spark: SparkSession): Feed[DataFrame] = Feed.csv(spark, "etl/attractions/v1.0/")
}
