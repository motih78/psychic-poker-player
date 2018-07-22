package org.heller.poker

import util.control.Breaks._

object HandScorer {

  def getHandScore(hand: List[Card]): HandScore.Value = {
    var handScore: HandScore.Value = HandScore.HIGH_CARD
    //Calculate histogram based on card face
    val histogram = hand.groupBy(_.face)
    if (isStraightFlush(hand))
      handScore = HandScore.STRAIGHT_FLUSH
    else if (isFourOfAKind(histogram))
      handScore = HandScore.FOUR_OF_A_KIND
    else if (isFullHouse(histogram))
      handScore = HandScore.FULL_HOUSE
    else if (isFlush(hand))
      handScore = HandScore.FLUSH
    else if (isStraight(hand))
      handScore = HandScore.STRIAGHT
    else if (isThreeOfAKind(histogram))
      handScore = HandScore.THREE_OF_A_KIND
    else if (isTwoPairs(histogram))
      handScore = HandScore.TWO_PAIRS
    else if (isOnePair(histogram))
      handScore = HandScore.ONE_PAIR

    handScore
  }

  def isStraightFlush(hand: List[Card]): Boolean = {
    isStraight(hand) && isFlush(hand)
  }

  def isFlush(hand: List[Card]): Boolean = {
    val handSuits = hand.map(_.suit).toSet
    handSuits.size == 1
  }

  def isStraight(hand: List[Card]): Boolean = {
    var result = true
    //sort the hand in order to calculate straight
    val sortedHand = hand.sortBy(_.face.id)
    breakable {
      for (i <- 0 to hand.size - 2) {
        if (sortedHand(i).face.id != sortedHand(i + 1).face.id - 1) {
          result = false
          break
        }
      }
    }
    result
  }

  def isFourOfAKind(histogram: Map[Face.Value, List[Card]]): Boolean = {
    histogram.values.exists(_.size == 4)
  }

  def isFullHouse(histogram: Map[Face.Value, List[Card]]): Boolean = {
    histogram.values.exists(_.size == 3) && histogram.values.exists(_.size == 2)
  }

  def isThreeOfAKind(histogram: Map[Face.Value, List[Card]]): Boolean = {
    histogram.values.exists(_.size == 3)
  }

  def isTwoPairs(histogram: Map[Face.Value, List[Card]]): Boolean = {
    histogram.values.count(_.size == 2) == 2
  }

  def isOnePair(histogram: Map[Face.Value, List[Card]]): Boolean = {
    histogram.values.exists(_.size == 2)
  }
}
