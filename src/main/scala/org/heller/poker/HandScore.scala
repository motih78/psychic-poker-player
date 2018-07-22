package org.heller.poker

object HandScore extends Enumeration {
  val STRAIGHT_FLUSH = Value(10, "Straight Flush")
  val FOUR_OF_A_KIND = Value(9, "Four of a Kind")
  val FULL_HOUSE = Value(8, "Full House")
  val FLUSH = Value(7, "Flush")
  val STRIAGHT = Value(6, "Straight")
  val THREE_OF_A_KIND = Value(5, "Three of a Kind")
  val TWO_PAIRS = Value(4, "Two Pairs")
  val ONE_PAIR = Value(3, "One Pair")
  val HIGH_CARD = Value(1, "High Card")
}
