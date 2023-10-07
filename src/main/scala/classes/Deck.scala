import scala.compiletime.ops.int
import scala.util._
import scala.collection.mutable.Queue

//On first call creates a base deck of 52 cards, by multiplying all the suites with all the ranks (held in CardParts.scala)
class Deck(pCards: List[Card]){
  //Throws an exception if the deck is smaller than 52 cards or there are duplicates
  val cards = if (isValidDeck(pCards)) pCards
  else throw new RuntimeException("Deck is invalid!")

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
    
  private def isValidDeck(cards: List[Card]) = cards.size <= 52 && cards.size >=2 && cards.distinct.size == cards.size
}