package com.montevideolabs.attractionsrecommender.serving.recommender

trait AttractionsRecommender {

  /**
    * @param user an identifier of a user for which to get recommendations.
    * @return the recommended attractions for the given user.
    */
  def recommend(user: String): Seq[String]
}
