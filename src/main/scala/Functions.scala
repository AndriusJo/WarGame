import scala.compiletime.ops.int
import scala.util._
import scala.collection.mutable.Queue

def PlayWar(Player1Cards: List[Card], Player2Cards: List[Card]): (List[Card], List[Card]) = {
  //Crate score piles for each palyer
  val Player1ScorePile:Queue[Card] = new Queue[Card]
  val Player2ScorePile:Queue[Card] = new Queue[Card]
  var Rounds = 0

  //Set how many rounds there will be based on the size of each palyers deck (In case the decks were split unevenly)
  if(Player1Cards.length >=Player2Cards.length)
    Rounds = Player2Cards.length
  else
    Rounds = Player1Cards.length

  //Fill score piles 
  for(Round <- 0 to Rounds - 1){
    val P1:Int = Player1Cards(Round).value
    val P2:Int = Player2Cards(Round).value

    if(P1 > P2){
      Player1ScorePile.addOne(Player1Cards(Round))
      Player1ScorePile.addOne(Player2Cards(Round))
    }
    else if(P1 < P2){
      Player2ScorePile.addOne(Player1Cards(Round))
      Player2ScorePile.addOne(Player2Cards(Round))
    }
    else{
      Player1ScorePile.addOne(Player1Cards(Round))
      Player2ScorePile.addOne(Player2Cards(Round))
    }
  }
  
  (Player1ScorePile.toList, Player2ScorePile.toList)
}

def CreateDeck() = {
  new Deck(for (s <- CardParts.suits; r <- CardParts.ranks) yield Card(s, r))
}

//Function just to output the results
def PrintOutputs(PlayerDecks: (List[Card], List[Card]), GameResults: (List[Card], List[Card])) = {
  println("-----Player1--------------")
  for (element <- PlayerDecks._1){
    println(element.getRank() +" "+ element.getSuit() +" "+ element.getValue())
  }

  println("-----Player2--------------")
  for (element <- PlayerDecks._2){
    println(element.getRank() +" "+ element.getSuit() +" "+ element.getValue())
  }

  println("-----Player1Result--------------")
  for (element <- GameResults._1){
    println(element.getRank() +" "+ element.getSuit() +" "+ element.getValue())
   }

  println("-----Player2Result--------------") 
  for (element <- GameResults._2){
    println(element.getRank() +" "+ element.getSuit() +" "+ element.getValue())
  }

  println(s"Player 1 score: ${GameResults._1.length}")
  println(s"Player 2 score: ${GameResults._2.length}")
}