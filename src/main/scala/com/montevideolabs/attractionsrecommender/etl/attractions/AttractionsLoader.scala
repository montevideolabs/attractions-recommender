package com.montevideolabs.attractionsrecommender.etl.attractions

import org.apache.spark.sql.DataFrame

trait AttractionsLoader {

  /**
    * @return the attractions as a spark data frame.
    */
  def load(): DataFrame
}
