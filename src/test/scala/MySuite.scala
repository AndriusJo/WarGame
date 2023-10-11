class MySuite extends munit.FunSuite {
  test("Eaven Split Test (52 Cards)") {
    val pDeck = createDeck().split()
    val p1 = pDeck._1
    val p2 = pDeck._2
    assertEquals(checkHandEquality(p1, p2), EqualHands("Player hands are equal"))
  }
  test("Uneaven Split Test (51 Cards)") {
    val pDeck = createDeck().split()
    val p1 = pDeck._1.drop(1)
    val p2 = pDeck._2
    assertEquals(checkHandEquality(p1, p2), UneaqualHands("Player hands are not equal"))
  }
  test("playWar with eaven ammounts of cards") {
    val pDeck = createDeck().split()
    val p1 = pDeck._1
    val p2 = pDeck._2
    val gameresult = playWar(p1, p2, Suit.Clubs)
    //When cards are not shuffled only these results are achieved
    val result = gameresult._1.length == 13 && gameresult._2.length == 39
    assertEquals(result, true)
  }
  test("playWar with uneaven ammounts of cards") {
    val pDeck = createDeck().split()
    val p1 = pDeck._1
    val p2 = pDeck._2.drop(1)
    val gameresult = playWar(p1, p2, Suit.Clubs)
    //When cards are not shuffled only these results are achieved
    val result = gameresult._1.length == 2 && gameresult._2.length == 48
    assertEquals(result, true)
  }
  test("CheckDeck when Deck has appropriate cards") {
    val result = checkDeck(createDeck().cards)
    assertEquals(result, ValidDeck("Deck created succesfully!"))
  }

  test("CheckDeck when there are < 2 cards") {
    val pDeck = List(createDeck().cards.last)
    val result = checkDeck(pDeck)
    assertEquals(result, InvalidDeck("Deck has an improper number of cards or contains duplicates"))
  }

  test("CheckDeck when there are duplicates") {
    val pDeck = List(createDeck().cards.last, createDeck().cards.last)
    val result = checkDeck(pDeck)
    assertEquals(result, InvalidDeck("Deck has an improper number of cards or contains duplicates"))
  }
}
