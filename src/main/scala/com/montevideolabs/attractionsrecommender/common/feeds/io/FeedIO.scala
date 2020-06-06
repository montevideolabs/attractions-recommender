package com.montevideolabs.attractionsrecommender.common.feeds.io

trait FeedIO[T] {

  /**
    * Writes the given update in the given file system path.
    *
    * @param update the update to be written.
    * @param path the path in which to write.
    */
  def write(update: T, path: String): Unit

  /**
    * @param path the path from which to read the update.
    * @return the update in the given path.
    */
  def read(path: String): T
}
