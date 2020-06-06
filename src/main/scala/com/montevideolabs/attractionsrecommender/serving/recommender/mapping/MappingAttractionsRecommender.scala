package com.montevideolabs.attractionsrecommender.serving.recommender.mapping

import com.montevideolabs.attractionsrecommender.serving.recommender.AttractionsRecommender

class MappingAttractionsRecommender(val mappings: Map[String, Seq[String]])
  extends AttractionsRecommender {

  override def recommend(user: String): Seq[String] = mappings(user)
}
