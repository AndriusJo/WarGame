import scala.compiletime.ops.int
import scala.util._
import scala.collection.mutable.Queue
@main def War: Unit = {
  val FullDeck = CreateDeck().Shuffle()
  
  //Get a random trump suit and update the deck with it
  val TrumpSuit = CardParts.suits(Random.nextInt(CardParts.suits.length))
  FullDeck.Trumpify(TrumpSuit)

  //Split the deck in the middle, half for each player
  val PlayerDecks:(List[Card], List[Card])= FullDeck.Split()
  
  //Play the game
  val GameResults = PlayWar(PlayerDecks._1, PlayerDecks._2)
  
  //You can uncomment this function to see the result output in the console
  //PrintOutputs(PlayerDecks, GameResults)

  //Create a structure to hold the Result data
  val FinalResults = (("Player 1", GameResults._1.length, GameResults._1),
                      ("Player 2", GameResults._2.length, GameResults._2))
}