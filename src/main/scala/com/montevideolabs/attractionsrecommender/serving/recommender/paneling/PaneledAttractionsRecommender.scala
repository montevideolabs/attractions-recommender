package com.montevideolabs.attractionsrecommender.serving.recommender.paneling

import java.util.Calendar

import scala.util.hashing.MurmurHash3

import com.montevideolabs.attractionsrecommender.serving.recommender.AttractionsRecommender

class PaneledAttractionsRecommender(val panelToRecommender: Map[Int, AttractionsRecommender])
  extends AttractionsRecommender {

  override def recommend(user: String): Seq[String] =
    panelToRecommender(panelFromUser(user)).recommend(user)

  private def panelFromUser(user: String): Int = hash(user + salt) % amountOfPanels

  private def salt: String = Calendar.getInstance().get(Calendar.MINUTE).toString

  private def hash(string: String): Int = MurmurHash3.stringHash(string).abs

  private val amountOfPanels: Int = panelToRecommender.keys.size
}
