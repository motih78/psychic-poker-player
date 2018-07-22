package org.heller.poker

object Suit extends Enumeration {
  val Clubs = Value("C")
  val Diamonds = Value("D")
  val Hearts = Value("H")
  val Spades = Value("S")
}

object Face extends Enumeration {
  val Two = Value(2, "2")
  val Three = Value(3, "3")
  val Four = Value(4, "4")
  val Five = Value(5, "5")
  val Six = Value(6, "6")
  val Seven = Value(7, "7")
  val Eight = Value(8, "8")
  val Nine = Value(9, "9")
  val Ten = Value(10, "T")
  val Jack = Value(11, "J")
  val Queen = Value(12, "Q")
  val King = Value(13, "K")
  val Ace = Value(14, "A")
}

class Card (val suit: Suit.Value, val face: Face.Value) {
  override def toString = s"$face of ${suit.toString}"
}

object Card {
  def apply(card: String): Card = {
    if (card.size != 2) throw new Exception("Card representation must be exactly 2 characters")
    new Card(Suit.withName(card(1).toString()), Face.withName(card(0).toString))
  }
}



