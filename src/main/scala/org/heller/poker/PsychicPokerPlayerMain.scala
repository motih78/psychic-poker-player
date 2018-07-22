package org.heller.poker

object PsychicPokerPlayerMain {
  def main(args: Array[String]): Unit = {
    val games = CardReader.readGames("games.txt")
    games.foreach( game => {
      val bestHand = findBestHand(game)
      println(s"Hand: ${game.hand}, Deck: ${game.deck}, Best Hand: $bestHand")
    })
  }

  /**
    * Finds the best hand for a game (hand + deck)
    * @param game
    * @return
    */
  def findBestHand(game: Game): HandScore.Value = {
    var deck: List[Card] = game.deck
    var usedDeckCards: List[Card] = List.empty
    var bestHand: HandScore.Value = HandScore.HIGH_CARD

    while (deck.nonEmpty) {
      val score = calcHandScore(game.hand, usedDeckCards)
      if (score.id > bestHand.id)
        bestHand = score

      //For the next iteration, we take the first element from the deck and add it to the
      //used deck cards list
      usedDeckCards = usedDeckCards :+ deck.head
      deck = deck.drop(1)
    }
    bestHand
  }

  /**
    * Calculate best hand score of a hand and selected deck cards
    * @param hand
    * @param usedDeckCards
    * @return
    */
  def calcHandScore(hand: List[Card], usedDeckCards: List[Card]): HandScore.Value = {
    //Once the used deck cards list if full - we can calculate the score
    if (usedDeckCards.size == 5)
      return HandScorer.getHandScore(usedDeckCards)

    var bestHand: HandScore.Value = HandScore.HIGH_CARD

    hand.foreach( card => {
      var handInternal = List.empty ++ hand
      var usedCardsInternal = List.empty ++ usedDeckCards

      //For every card in the hand, we check all options with the used deck cards and other
      //cards in the hand
      usedCardsInternal = usedCardsInternal :+ card
      handInternal = handInternal.filterNot(_ == card)
      val score: HandScore.Value = calcHandScore(handInternal, usedCardsInternal)

      if (score.id > bestHand.id)
        bestHand = score
    })
    bestHand
  }
}
