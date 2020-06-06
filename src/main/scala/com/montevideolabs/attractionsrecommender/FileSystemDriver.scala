package com.montevideolabs.attractionsrecommender

import com.montevideolabs.attractionsrecommender.etl.EtlDriver
import com.montevideolabs.attractionsrecommender.serving.ServingDriver
import com.montevideolabs.attractionsrecommender.training.TrainingDriver

object FileSystemDriver extends App {

  EtlDriver.main(Array())
  TrainingDriver.main(Array())
  ServingDriver.main(Array())
}
