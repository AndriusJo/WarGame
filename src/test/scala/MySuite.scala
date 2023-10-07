// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
class MySuite extends munit.FunSuite {
  test("Trumpify Deck Test") { 
    val TrumpSuit = "Clubs"
    val pDeck = CreateDeck()
    pDeck.Trumpify(TrumpSuit)
    val obtained = pDeck.getTrumpSuit()
    val expected = "Clubs"
    assertEquals(obtained, expected)
  }
  test("Trumpify Value Test") {
    val TrumpSuit = "Spades"
    val pDeck = CreateDeck()
    pDeck.Trumpify(TrumpSuit)
    val obtained = pDeck.cards.head.getValue()
    val expected = 22
    assertEquals(obtained, expected)
  }
  test("Eaven Split Test") {
    val pDeck = CreateDeck().Split()
    val p1 = pDeck._1.length
    val p2 = pDeck._2.length
    assertEquals(p1, p2)
  }
}
