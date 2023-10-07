import scala.compiletime.ops.int
import scala.util._
import scala.collection.mutable.Queue

def CreateDeck(suits: List[String] = CardParts.suits, ranks: List[(String, Int)] = CardParts.ranks) = {
  new Deck(for (s <- suits; r <- ranks) yield Card(s, r))
}



def CheckDeck(pDeck:List[Card]): GameState = pDeck match {
  case pDeck if(pDeck.size <= 52 && pDeck.size >=2 && NoDuplicates(pDeck)) =>
    ValidDeck("Deck created succesfully!")

  case _ => InvalidDeck("Deck has an improper number of cards or contains duplicates")
}

def NoDuplicates(pDeck:List[Card]): Boolean = {
  pDeck.groupBy(obj => (obj.getRank(), obj.getSuit())).values.map(_.head).toList.size == pDeck.size
}

def CheckHandEquality(hand1: List[Card], hand2: List[Card]): GameState = (hand1, hand2) match {
  case equal if(hand1.length == hand2.length) =>
    EqualHands("Player hands are equal")

  case _ => UneaqualHands("Player hands are not equal")
}

def PlayWar(Player1Cards: List[Card], Player2Cards: List[Card]): Either[String, (List[Card], List[Card])] = {
  CheckHandEquality(Player1Cards, Player2Cards) match {
    case EqualHands(newmessage) => {
        //Crate score piles for each palyer
      val Player1ScorePile:Queue[Card] = new Queue[Card]
      val Player2ScorePile:Queue[Card] = new Queue[Card]

        //Fill score piles 
      for(Round <- 0 to Player1Cards.length - 1){
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
      Right((Player1ScorePile.toList, Player2ScorePile.toList))
    }
    case UneaqualHands(message) => Left(message)
    case _ => Left("Error in PlayWar")
  } 
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