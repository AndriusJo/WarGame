import scala.compiletime.ops.int
import scala.util._
import scala.collection.mutable.Queue

def createDeck(newDeck: List[Card] = (for (s <- Suit.values; r <- Rank.values) yield Card(s, r)).toList) = {
  new Deck(newDeck)
}

def playWar(player1Cards: List[Card], player2Cards: List[Card], trumpSuit: Suit): (List[Card], List[Card]) = {
  //Crate score piles for each palyer
  val player1ScorePile:Queue[Card] = new Queue[Card]
  val player2ScorePile:Queue[Card] = new Queue[Card]

  val Rounds = if (player1Cards.length <= player2Cards.length) player1Cards.length else player2Cards.length
  //Fill score piles 
  for(Round <- 0 to Rounds - 1){
    val P1:Int = setValue(player1Cards(Round), trumpSuit)
    val P2:Int = setValue(player2Cards(Round), trumpSuit)

    if(P1 > P2){
      player1ScorePile.enqueue(player1Cards(Round), player2Cards(Round))
    }
    else if(P1 < P2){
      player2ScorePile.enqueue(player1Cards(Round), player2Cards(Round))
    }
    else{
      player1ScorePile.addOne(player1Cards(Round))
      player2ScorePile.addOne(player2Cards(Round))
    }
  }
  (player1ScorePile.toList, player2ScorePile.toList)
}

def setValue(card: Card, trumpSuit: Suit): Int = {
  if(card.getSuit() == trumpSuit)
    card.getRank().value + 20
  else
    card.getRank().value
}

//Function just to output the results
def printOutputs(playerDecks: (List[Card], List[Card]), gameResults: (List[Card], List[Card]), trumpSuit: Suit) = {  
  println(s"Trump suit: ${trumpSuit}")
  println("---------------Player One Cards----------------")
  printCards(playerDecks._1)
  println("---------------Player Two Cards----------------")
  printCards(playerDecks._2)
  println("-------------Player One Score Pile-------------")
  printCards(gameResults._1)
  println("-------------Player Two Score Pile-------------")
  printCards(gameResults._2)
  
  println(s"Player 1 score: ${gameResults._1.length}")
  println(s"Player 2 score: ${gameResults._2.length}")
}

def printCards(cards: List[Card]) = {
  for (card <- cards){
    card.prettyPrint()
  }
}