import scala.compiletime.ops.int
import scala.util._
import scala.collection.mutable.Queue

sealed trait GameState

case class InvalidDeck(newmessage: String) extends GameState{
    val message = newmessage
}
case class ValidDeck(newmessage:String) extends GameState{
    val message = newmessage
}
case class EqualHands(newmessage: String) extends GameState{
    val message = newmessage
}
case class UneaqualHands(newmessage: String) extends GameState{
    val message = newmessage
}
