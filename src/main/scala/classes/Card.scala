import scala.compiletime.ops.int
import scala.util._
import scala.collection.mutable.Queue

class Card(private var suit: String,private var rank:(String, Int)){
  var value = if(CardParts.ranks.contains(rank) && rank._2 >= 0) rank._2 else 0

  def getSuit() : String = suit
  def getRank() : String = rank._1
  def getFullRank() : (String, Int) = rank
  def getValue() : Int = value

  def setSuit(suit : String): Unit ={
    if(CardParts.suits.contains(suit)){
      this.suit = suit
    }
    else
    {
      this.suit = "No Suit"
    }
  }
    
  def setRank(rank : (String, Int)): Unit ={
    if(CardParts.ranks.contains(rank)){
      this.rank = rank
    }
    else
    {
      this.rank = ("No Rank", 0)
    }
  }
}