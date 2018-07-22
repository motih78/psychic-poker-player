package org.heller.poker

class Game(val hand: List[Card], val deck: List[Card]) {
  override def toString = s"Hand: $hand, Deck: $deck"
}
