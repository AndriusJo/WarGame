import scala.compiletime.ops.int
import scala.util._
import scala.collection.mutable.Queue

class Card(private val suit: Suit, private val rank: Rank){
  def getSuit() : Suit = suit
  def getRank() : Rank = rank

  def prettyPrint() = {
    println(s"${rank} of ${suit}")
  }
}