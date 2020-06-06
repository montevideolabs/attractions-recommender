package com.montevideolabs.attractionsrecommender.etl.visits.sigir

import com.montevideolabs.attractionsrecommender.common.config.EnvironmentConfiguration
import com.montevideolabs.attractionsrecommender.common.feeds.visits.VisitsColumnNames
import com.montevideolabs.attractionsrecommender.etl.visits.VisitsLoader
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.count

/**
  * Implementation of a visits loader that reads and transforms Sigir 17 data.
  */
class SigirVisitsLoader(reader: SigirVisitsReader, transformer: SigirVisitsTransformer)
  extends VisitsLoader {

  override def load(): DataFrame = {
    val rawVisits = reader.read()
    transformer.transform(rawVisits)
  }
}

object SigirVisitsLoader {

  def apply(spark: SparkSession): SigirVisitsLoader =
    new SigirVisitsLoader(new SigirVisitsReader(spark), new SigirVisitsTransformer)
}

class SigirVisitsReader(spark: SparkSession) {

  def read(): DataFrame =
    spark.read
      .option("header", true)
      .option("inferSchema", true)
      .option("sep", ";")
      .csv(s"${EnvironmentConfiguration().SigirRawDataPath}userVisits-sigir17/*.csv")
}

class SigirVisitsTransformer {

  def transform(rawVisits: DataFrame): DataFrame =
    rawVisits
      .withColumnRenamed("nsid", VisitsColumnNames.UserId)
      .withColumnRenamed("poiID", VisitsColumnNames.AttractionId)
      .groupBy(VisitsColumnNames.UserId, VisitsColumnNames.AttractionId)
      .agg(count("*").as(VisitsColumnNames.VisitsCount))
      .select(
        VisitsColumnNames.UserId,
        VisitsColumnNames.AttractionId,
        VisitsColumnNames.VisitsCount)
}
