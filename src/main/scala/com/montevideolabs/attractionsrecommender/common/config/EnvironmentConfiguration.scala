package com.montevideolabs.attractionsrecommender.common.config

import scala.util.Try

/**
  * Utility object holding the configuration of the environment for the whole system.
  * The values of the different configurable properties are read from environment variables.
  */
/**
  * Definition of the environment configuration for the whole system.
  */
trait EnvironmentConfiguration {

  val LocalSpark: Boolean
  val FeedsRootPath: String
  val SigirRawDataPath: String
  val DebugEnabled: Boolean
}

object EnvironmentConfiguration {

  private var instance: EnvironmentConfiguration = EnvPropertiesEnvironmentConfiguration

  /**
    * @return the singleton configuration instance.
    */
  def apply(): EnvironmentConfiguration = instance

  /**
    * Forces the given configuration as the singleton instance.
    *
    * @param configuration the configuration to be forced.
    */
  def force(configuration: EnvironmentConfiguration): Unit = instance = configuration
}

/**
  * Implementation of the environment configuration that reads the values from env variables.
  */
object EnvPropertiesEnvironmentConfiguration extends EnvironmentConfiguration {

  val LocalSpark: Boolean = Try(System.getenv("spark.local").equals("true")).getOrElse(false)
  val FeedsRootPath: String = System.getenv("feeds.root.path")
  val SigirRawDataPath: String = System.getenv("sigir.path")
  val DebugEnabled: Boolean = Try(System.getenv("debug.enabled").equals("true")).getOrElse(false)
}
