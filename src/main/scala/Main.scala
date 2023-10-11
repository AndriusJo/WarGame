import scala.compiletime.ops.int
import scala.util._
import scala.collection.mutable.Queue

@main def War: Unit = {
  val fullDeck = createDeck().shuffle()

  //Get a random trump suit and update the deck with it
  val trumpSuit = Suit.fromOrdinal(Random.nextInt(Suit.values.length))
  
  //Split the deck in the middle, half for each player
  val splitDeck = fullDeck.split()
  val playerHands:(List[Card], List[Card])= (splitDeck._1, splitDeck._2)

  //Play the game
  val gameResults = playWar(playerHands._1, playerHands._2, trumpSuit)

  //You can comment this function if you do not want the result output in the console
  printOutputs(playerHands, gameResults, trumpSuit)

  //Create a structure to hold the Result data
  val finalResults = (("Player 1", gameResults._1),
                      ("Player 2", gameResults._2))
}