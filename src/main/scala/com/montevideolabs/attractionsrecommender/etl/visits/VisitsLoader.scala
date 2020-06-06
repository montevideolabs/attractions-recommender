package com.montevideolabs.attractionsrecommender.etl.visits

import org.apache.spark.sql.DataFrame

trait VisitsLoader {

  /**
    * @return the visits loaded as a spark data frame.
    */
  def load(): DataFrame
}
