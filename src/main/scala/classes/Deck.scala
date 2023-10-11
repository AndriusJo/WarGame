import scala.compiletime.ops.int
import scala.util._
import scala.collection.mutable.Queue

class Deck(pCards: List[Card]){
  val cards = pCards
  //Shuffles the deck
  def shuffle() = new Deck(Random.shuffle(cards))
  //Splits the deck in half
  def split() = cards.splitAt(cards.length/2.toInt)
}

