package com.montevideolabs.attractionsrecommender

import java.io.File

import com.montevideolabs.attractionsrecommender.common.config.EnvironmentConfiguration
import com.montevideolabs.attractionsrecommender.etl.EtlDriver
import com.montevideolabs.attractionsrecommender.serving.ServingDriver
import com.montevideolabs.attractionsrecommender.training.TrainingDriver
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must.Matchers

class FullIntegrationTest extends AnyFunSpec with Matchers {

  EnvironmentConfiguration.force(new EnvironmentConfiguration {
    override val LocalSpark: Boolean = true
    override val FeedsRootPath
      : String = "file://" + new File("target/integration-test/feeds").getAbsolutePath + "/"
    override val SigirRawDataPath
      : String = "file://" + new File("src/test/data/sigir17").getAbsolutePath + "/"
    override val DebugEnabled: Boolean = false
  })

  describe("the attractions recommender") {
    it("should run all the components successfully") {
      EtlDriver.main(Array())
      TrainingDriver.main(Array())
      ServingDriver.main(Array())
      new File("target/integration-test/feeds/etl/visits/").exists() must be(true)
      new File("target/integration-test/feeds/etl/attractions/").exists() must be(true)
      new File("target/integration-test/feeds/training/als/").exists() must be(true)
    }
  }
}
