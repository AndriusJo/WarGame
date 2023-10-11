
def checkDeck(pDeck:List[Card]): GameState = pDeck match {
  case pDeck if(pDeck.size <= 52 && pDeck.size >=2 && noDuplicates(pDeck)) =>
    ValidDeck("Deck created succesfully!")

  case _ => InvalidDeck("Deck has an improper number of cards or contains duplicates")
}

def noDuplicates(pDeck:List[Card]): Boolean = {
  pDeck.groupBy(obj => (obj.getRank(), obj.getSuit())).values.map(_.head).toList.size == pDeck.size
}

def checkHandEquality(hand1: List[Card], hand2: List[Card]): GameState = (hand1, hand2) match {
  case equal if(hand1.length == hand2.length) =>
    EqualHands("Player hands are equal")

  case _ => UneaqualHands("Player hands are not equal")
}
