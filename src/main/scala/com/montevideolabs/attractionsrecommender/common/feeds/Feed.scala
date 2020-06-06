package com.montevideolabs.attractionsrecommender.common.feeds

import java.io.File

import com.montevideolabs.attractionsrecommender.common.config.EnvironmentConfiguration
import com.montevideolabs.attractionsrecommender.common.feeds.io.{CsvFeedIO, FeedIO, ParquetFeedIO}
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * Definition of a Feed that can put updates in a given path and get an update form that same path.
  * Delegates IO to a given FeedIO object.
  *
  * @param feedIO the object to be used for writing to and reading from the file system.
  * @param path the path in which to put and look for the updates
  * @tparam T the type of the updates.
  */
class Feed[T](feedIO: FeedIO[T], path: String, updateHandler: FeedUpdateHandler) {

  def put(update: T): Unit = {
    feedIO.write(update, updateHandler.newUpdate(path))
  }

  def get(): T =
    feedIO.read(updateHandler.latestUpdate(path))
}

object Feed {

  def parquet(spark: SparkSession, path: String): Feed[DataFrame] =
    apply(ParquetFeedIO(spark), path)

  def csv(spark: SparkSession, path: String): Feed[DataFrame] =
    apply(CsvFeedIO(spark), path)

  def apply[T](feedIO: FeedIO[T], path: String): Feed[T] = {
    val feedPath = EnvironmentConfiguration().FeedsRootPath + path
    new Feed(feedIO, feedPath, FeedUpdateHandler(feedPath))
  }
}

trait FeedUpdateHandler {

  def newUpdate(path: String): String

  def latestUpdate(path: String): String
}

object FeedUpdateHandler {

  def apply(feedPath: String): FeedUpdateHandler = feedPath.split("://")(0) match {
    case "file" => new LocalFileSystemFeedUpdateHandler
    case "s3" => new S3FeedUpdateHandler
    case other: String => throw new RuntimeException(s"Unsupported feeds scheme: $other")
  }
}

class LocalFileSystemFeedUpdateHandler extends FeedUpdateHandler {

  def newUpdate(path: String): String = path + System.currentTimeMillis() + "/"

  override def latestUpdate(path: String): String =
    path + new File(path.replace("file://", "")).listFiles
      .filter(_.isDirectory)
      .map(_.getName)
      .max + "/"
}

class S3FeedUpdateHandler extends FeedUpdateHandler {

  override def newUpdate(path: String): String = ???

  override def latestUpdate(path: String): String = ???
}
