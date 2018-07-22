package org.heller.poker

import scala.io.Source

object CardReader {
  def readGames(fileName: String):Seq[Game] = {
    val lines = Source.fromFile(Source.getClass.getClassLoader.getResource(fileName).getFile).getLines.toList
    val games = lines.map{ line =>
      val cards = line.split(" ").toList
      val hand = cards.take(5).map(Card(_))
      val deck = cards.takeRight(5).map(Card(_))
      println(s"Hand: $hand, Deck: $deck")
      new Game(hand, deck)
    }
    games
  }
}
