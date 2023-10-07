import scala.compiletime.ops.int
import scala.util._
import scala.collection.mutable.Queue
@main def War: Unit = {
  val FullDeck = CreateDeck().Shuffle()
  
  CheckDeck(FullDeck.cards) match {
    case ValidDeck(message) => {
      println(message)

      //Get a random trump suit and update the deck with it
      val TrumpSuit = CardParts.suits(Random.nextInt(CardParts.suits.length))
      FullDeck.Trumpify(TrumpSuit)

      //Split the deck in the middle, half for each player
      val SplitDeck = FullDeck.Split()
      val PlayerHands:(List[Card], List[Card])= (SplitDeck._1, SplitDeck._2)
      
      //Play the game
      val GameResults = PlayWar(PlayerHands._1, PlayerHands._2)
      GameResults match {
        case Right(results) => {
          //You can comment this function if you do not want the result output in the console
          PrintOutputs(PlayerHands, results)

          //Create a structure to hold the Result data
          val FinalResults = (("Player 1", results._1.length, results._1),
                              ("Player 2", results._2.length, results._2))
        }
        case Left(message) => println(message)
      }
    }
    case InvalidDeck(error) => println(error)
    case _ => println("Error in Deck")
  }
}