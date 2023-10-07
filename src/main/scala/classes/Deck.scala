import scala.compiletime.ops.int
import scala.util._
import scala.collection.mutable.Queue

class Deck(pCards: List[Card]){
  //Throws an exception if the deck is smaller than 52 cards or there are duplicates
  val cards = CheckDeck(pCards) match {
    case ValidDeck(message) => pCards
    case InvalidDeck(message) => List.empty[Card]
    case _ => List.empty[Card]
  }

  var deckTrumpSuit:Option[String] = None
  def getTrumpSuit() = deckTrumpSuit.getOrElse("None")
  //Shuffles the deck
  def Shuffle() = new Deck(Random.shuffle(cards))

  //Sets the value of trump cards higher than the rest (by 20 in this case)
  def Trumpify(TrumpSuit: String) = if(deckTrumpSuit == None) {
    for(card <- cards){
      if(card.getSuit() == TrumpSuit)
      card.value += 20
    }
    
    deckTrumpSuit = Some(TrumpSuit)
  }

  //Splits the deck in half
  def Split() = cards.splitAt(cards.length/2.toInt)
}

