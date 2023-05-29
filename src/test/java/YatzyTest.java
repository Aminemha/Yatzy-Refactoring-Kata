import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class YatzyTest {
  public static Stream<Arguments> chanceInput() {
    return Stream.of(
        arguments(new Yatzy(2, 3, 4, 5, 1), 15), arguments(new Yatzy(3, 3, 4, 5, 1), 16));
  }

  public static Stream<Arguments> yatzyInput() {
    return Stream.of(
        arguments(new Yatzy(4, 4, 4, 4, 4), 50),
        arguments(new Yatzy(6, 6, 6, 6, 6), 50),
        arguments(new Yatzy(6, 6, 6, 6, 3), 0));
  }

  public static Stream<Arguments> onesInput() {
    return Stream.of(
        arguments(new Yatzy(1, 2, 3, 4, 5), 1),
        arguments(new Yatzy(1, 2, 1, 4, 5), 2),
        arguments(new Yatzy(6, 2, 2, 4, 5), 0),
        arguments(new Yatzy(1, 2, 1, 1, 1), 4));
  }

  public static Stream<Arguments> twosInput() {
    return Stream.of(
        arguments(new Yatzy(1, 2, 3, 2, 6), 4), arguments(new Yatzy(2, 2, 2, 2, 2), 10));
  }

  public static Stream<Arguments> threesInput() {
    return Stream.of(
        arguments(new Yatzy(1, 2, 3, 2, 3), 6), arguments(new Yatzy(2, 3, 3, 3, 3), 12));
  }

  public static Stream<Arguments> foursInput() {
    return Stream.of(
        arguments(new Yatzy(4, 4, 4, 5, 5), 12),
        arguments(new Yatzy(4, 4, 5, 5, 5), 8),
        arguments(new Yatzy(4, 5, 5, 5, 5), 4));
  }

  public static Stream<Arguments> fivesInput() {
    return Stream.of(
        arguments(new Yatzy(4, 4, 4, 5, 5), 10),
        arguments(new Yatzy(4, 4, 5, 5, 5), 15),
        arguments(new Yatzy(4, 5, 5, 5, 5), 20));
  }

  public static Stream<Arguments> sixesInput() {
    return Stream.of(
        arguments(new Yatzy(4, 4, 4, 5, 5), 0),
        arguments(new Yatzy(4, 4, 6, 5, 5), 6),
        arguments(new Yatzy(6, 5, 6, 6, 5), 18));
  }

  public static Stream<Arguments> pairInput() {
    return Stream.of(
        arguments(new Yatzy(3, 4, 3, 5, 6), 6),
        arguments(new Yatzy(5, 3, 3, 3, 5), 10),
        arguments(new Yatzy(5, 3, 6, 6, 5), 12),
        arguments(new Yatzy(2, 3, 6, 1, 5), 0));
  }

  public static Stream<Arguments> twoPairInput() {
    return Stream.of(
        arguments(new Yatzy(3, 3, 5, 4, 5), 16), arguments(new Yatzy(3, 3, 5, 5, 5), 16));
  }

  public static Stream<Arguments> threeOfAKindInput() {
    return Stream.of(
        arguments(new Yatzy(3, 3, 3, 4, 5), 9),
        arguments(new Yatzy(5, 3, 5, 4, 5), 15),
        arguments(new Yatzy(3, 3, 3, 3, 5), 9),
        arguments(new Yatzy(3, 3, 3, 3, 3), 9));
  }

  public static Stream<Arguments> fourOfAKindInput() {
    return Stream.of(
        arguments(new Yatzy(3, 3, 3, 3, 5), 12), arguments(new Yatzy(5, 5, 5, 4, 5), 20));
  }

  public static Stream<Arguments> smallStraightInput() {
    return Stream.of(
        arguments(new Yatzy(1, 2, 3, 4, 5), 15),
        arguments(new Yatzy(2, 3, 4, 5, 1), 15),
        arguments(new Yatzy(1, 2, 2, 4, 5), 0));
  }

  public static Stream<Arguments> largeStraightInput() {
    return Stream.of(
        arguments(new Yatzy(6, 2, 3, 4, 5), 20),
        arguments(new Yatzy(2, 3, 4, 5, 6), 20),
        arguments(new Yatzy(1, 2, 2, 4, 5), 0));
  }

  public static Stream<Arguments> fullHouseInput() {
    return Stream.of(
        arguments(new Yatzy(6, 2, 2, 2, 6), 18),
        arguments(new Yatzy(1, 1, 1, 5, 5), 13),
        arguments(new Yatzy(1, 2, 2, 2, 1), 8),
        arguments(new Yatzy(4, 6, 4, 6, 4), 24),
        arguments(new Yatzy(2, 3, 4, 5, 6), 0)
    );
  }

  @Test()
  @DisplayName("Should throws an IllegalArgumentException with invalid dice value")
  public void testInvalidDiceValue() {
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> new Yatzy(1, 2, 3, 7, 5));
    assertEquals("Dice value must be between 1 and 6.", exception.getMessage());
  }

  @ParameterizedTest()
  @DisplayName("Should return chance scores sum of all dice")
  @MethodSource("chanceInput")
  public void testCalculateChance(Yatzy yatzy, int expected) {
    assertEquals(expected, yatzy.calculateChance());
  }

  @ParameterizedTest
  @DisplayName("Should return if it's a Yatzy score")
  @MethodSource("yatzyInput")
  public void testCalculateYatzy(Yatzy yatzy, int expected) {
    assertEquals(expected, yatzy.calculateYatzy());
  }

  @ParameterizedTest()
  @DisplayName("Should return the sum of ones")
  @MethodSource("onesInput")
  public void testCalculateScoreForValueOnes(Yatzy yatzy, int expected) {
    assertEquals(expected, yatzy.calculateScoreForValue(1));
  }

  @ParameterizedTest()
  @DisplayName("Should return the sum of twos")
  @MethodSource("twosInput")
  public void testCalculateScoreForValueTwos(Yatzy yatzy, int expected) {
    assertEquals(expected, yatzy.calculateScoreForValue(2));
  }

  @ParameterizedTest()
  @DisplayName("Should return the sum of threes")
  @MethodSource("threesInput")
  public void testCalculateScoreForValueThrees(Yatzy yatzy, int expected) {
    assertEquals(expected, yatzy.calculateScoreForValue(3));
  }

  @ParameterizedTest()
  @DisplayName("Should return the sum of fours")
  @MethodSource("foursInput")
  public void testCalculateScoreForValueFours(Yatzy yatzy, int expected) {
    assertEquals(expected, yatzy.calculateScoreForValue(4));
  }

  @ParameterizedTest()
  @DisplayName("Should return the sum of fives")
  @MethodSource("fivesInput")
  public void testCalculateScoreForValueFives(Yatzy yatzy, int expected) {
    assertEquals(expected, yatzy.calculateScoreForValue(5));
  }

  @ParameterizedTest()
  @DisplayName("Should return the sum of sixes")
  @MethodSource("sixesInput")
  public void testCSalculateScoreForValueSixes(Yatzy yatzy, int expected) {
    assertEquals(expected, yatzy.calculateScoreForValue(6));
  }

  @ParameterizedTest()
  @DisplayName("Should return the score of the highest pair")
  @MethodSource("pairInput")
  public void testScorePair(Yatzy yatzy, int expected) {
    assertEquals(expected, yatzy.scorePair());
  }

  @ParameterizedTest()
  @DisplayName("Should return the score of two pairs")
  @MethodSource("twoPairInput")
  public void testTwoPair(Yatzy yatzy, int expected) {
    assertEquals(expected, yatzy.twoPair());
  }

  @ParameterizedTest()
  @DisplayName("Should return the score of three of a kind")
  @MethodSource("threeOfAKindInput")
  public void testThreeOfAKind(Yatzy yatzy, int expected) {
    assertEquals(expected, yatzy.threeOfAKind());
  }

  @ParameterizedTest()
  @DisplayName("Should return the score of four of a kind")
  @MethodSource("fourOfAKindInput")
  public void testFourOfAKind(Yatzy yatzy, int expected) {
    assertEquals(expected, yatzy.fourOfAKind());
  }

  @ParameterizedTest()
  @DisplayName("Should return the score of a small straight")
  @MethodSource("smallStraightInput")
  public void testSmallStraight(Yatzy yatzy, int expected) {
    assertEquals(expected, yatzy.smallStraight());
  }

  @ParameterizedTest()
  @DisplayName("Should return the score of a large straight")
  @MethodSource("largeStraightInput")
  public void testLargeStraight(Yatzy yatzy, int expected) {
    assertEquals(expected, yatzy.largeStraight());
  }

  @ParameterizedTest()
  @DisplayName("Should return the score of a full house")
  @MethodSource("fullHouseInput")
  public void testFullHouse(Yatzy yatzy, int expected) {
    assertEquals(expected, yatzy.fullHouse());
  }
}
