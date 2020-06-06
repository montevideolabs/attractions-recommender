package com.montevideolabs.attractionsrecommender.common.feeds.alsmodel

import com.montevideolabs.attractionsrecommender.common.feeds.Feed
import com.montevideolabs.attractionsrecommender.common.feeds.io.FeedIO
import org.apache.spark.ml.recommendation.ALSModel

object AlsModelFeed {

  /**
    * @return a feed for als models as an ALSModel instance.
    */
  def apply(): Feed[ALSModel] = Feed(AlsModelFeedIO(), "training/als/v1.0/")
}

class AlsModelFeedIO extends FeedIO[ALSModel] {

  override def write(update: ALSModel, path: String): Unit = {
    update.write.save(path)
  }

  override def read(path: String): ALSModel = ALSModel.load(path)
}

object AlsModelFeedIO {

  def apply(): AlsModelFeedIO = new AlsModelFeedIO
}
