package com.montevideolabs.attractionsrecommender.common.debug

import com.montevideolabs.attractionsrecommender.common.config.EnvironmentConfiguration
import org.apache.spark.sql.DataFrame

trait DataFrameDescriptor {

  /**
    * Describes the given data frame.
    *
    * @param df the data frame to be described.
    */
  def describe(df: DataFrame): Unit
}

object DataFrameDescriptor {

  /**
    * @return a debug descriptor is debug is enabled, a no-op descriptor otherwise.
    */
  def apply(): DataFrameDescriptor =
    if (EnvironmentConfiguration().DebugEnabled) {
      new DebugDataFrameDescriptor
    } else {
      new NoOpDataFrameDescriptor
    }
}

class NoOpDataFrameDescriptor extends DataFrameDescriptor {

  override def describe(df: DataFrame): Unit = {}
}

class DebugDataFrameDescriptor extends DataFrameDescriptor {

  override def describe(df: DataFrame): Unit = {
    df.show()
    df.describe().show()
  }
}
