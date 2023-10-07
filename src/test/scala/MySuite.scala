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
  test("Eaven Split Test (52 Cards)") {
    val pDeck = CreateDeck().Split()
    val p1 = pDeck._1.length
    val p2 = pDeck._2.length
    assertEquals(p1, p2)
  }
  test("Uneaven Split Test (39 Cards)") {
    val pDeck = CreateDeck(CardPartsTest.suits).Split()
    val p1 = pDeck._1.length
    val p2 = pDeck._2.length
    assertNotEquals(p1, p2)
  }
  test("PlayWar with eaven ammounts of cards") {
    val pDeck = CreateDeck().Shuffle().Split()
    val p1 = pDeck._1
    val p2 = pDeck._2
    val result = PlayWar(p1, p2)
    assertEquals(result.isRight, true)
  }
  test("PlayWar with uneaven ammounts of cards") {
    val pDeck = CreateDeck(CardPartsTest.suits).Shuffle().Split()
    val p1 = pDeck._1
    val p2 = pDeck._2
    val result = PlayWar(p1, p2)
    assertEquals(result.isLeft, true)
  }
  test("CheckDeck when Deck has appropriate cards") {
    val result = CheckDeck(CreateDeck().cards)
    assertEquals(result, ValidDeck("Deck created succesfully!"))
  }

  test("CheckDeck when there are < 2 cards") {
    val result = CheckDeck(CreateDeck(CardPartsTest2.suits, CardPartsTest2.ranks).cards)
    assertEquals(result, InvalidDeck("Deck has an improper number of cards or contains duplicates"))
  }

  test("CheckDeck when there are duplicates") {
    val result = CheckDeck(CreateDeck(CardPartsTest3.suits, CardPartsTest3.ranks).cards)
    assertEquals(result, InvalidDeck("Deck has an improper number of cards or contains duplicates"))
  }
}
