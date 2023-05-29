import java.util.Arrays;

/** The Yatzy class. */
public class Yatzy {
  private static final int MIN_DICE_VALUE = 1;
  private static final int MAX_DICE_VALUE = 6;
  private final int[] dices;

  /**
   * Constructs a Yatzy object with the given dice values. Dice value should be between 1 and 6
   *
   * @param d1 the value of the first dice
   * @param d2 the value of the second dice
   * @param d3 the value of the third dice
   * @param d4 the value of the fourth dice
   * @param d5 the value of the fifth dice
   * @throws IllegalArgumentException if any dice value is not between 1 and 6
   */
  public Yatzy(int d1, int d2, int d3, int d4, int d5) {
    // Validate dice values before creating the object
    validateDiceValues(d1, d2, d3, d4, d5);
    dices = new int[] {d1, d2, d3, d4, d5};
  }

  /**
   * Validates the values of the dice.
   *
   * @param values the dice values to validate
   * @throws IllegalArgumentException if any dice value is not between 1 and 6
   */
  private void validateDiceValues(int... values) {
    if (Arrays.stream(values).anyMatch(value -> value < MIN_DICE_VALUE || value > MAX_DICE_VALUE)) {
      throw new IllegalArgumentException("Dice value must be between 1 and 6.");
    }
  }

  /**
   * Calculates the sum of all dice values.
   *
   * @return the sum of all dice values
   */
  public int calculateChance() {
    return Arrays.stream(dices).sum();
  }

  /**
   * Checks if all dice have the same value
   *
   * @return 50 if all dice have the same value, 0 otherwise
   */
  public int calculateYatzy() {
    /* For me, it is more practical and readable to return a boolean instead of an int. So, if
    all dice have the same value, we return true instead of 50. I don't have the context and I
    don't know how this method is used, so I left the return type as it is. */
    if (Arrays.stream(dices).distinct().count() == 1) {
      return 50;
    } else {
      return 0;
    }
  }

  /**
   * Helper method to count the occurrences of a specific dice value.
   *
   * @param value the dice value to count
   * @return the number of occurrences of the dice value
   */
  private int countOccurrences(int value) {
    return (int) Arrays.stream(dices).filter(dice -> dice == value).count();
  }

  /**
   * Calculates the sum of dice with the given value.
   *
   * @param value the dice value to calculate the score for
   * @return the sum of dice with the given value
   */
  public int calculateScoreForValue(int value) {
    return countOccurrences(value) * value;
  }

  /**
   * Gets the number of occurrences for each dice value.
   *
   * @return an array containing the number of occurrences for each dice value (from 1 to 6)
   */
  private int[] getCounts() {
    int[] counts = new int[6];
    for (int dice : dices) {
      counts[dice - 1]++;
    }
    return counts;
  }

  private int getScore(int[] counts, int target) {
    for (int i = counts.length - 1; i >= 0; i--) {
      if (counts[i] >= target) {
        return (i + 1) * target;
      }
    }
    return 0;
  }

  /**
   * Calculates the score for a pair of dice with the same value.
   *
   * @return the score for a pair of dice, or 0 if no pair is found
   */
  public int scorePair() {
    int[] counts = getCounts();
    return getScore(counts, 2);
  }

  /**
   * Calculates the score for three dice with the same value.
   *
   * @return the score for three of a kind, or 0 if no three of a kind is found
   */
  public int threeOfAKind() {
    int[] counts = getCounts();
    return getScore(counts, 3);
  }

  /**
   * Calculates the score for four dice with the same value.
   *
   * @return the score for four of a kind, or 0 if no four of a kind is found
   */
  public int fourOfAKind() {
    int[] counts = getCounts();
    return getScore(counts, 4);
  }

  /**
   * Calculates the score for two pairs of dice with the same value.
   *
   * @return the score for two pairs, or 0 if no two pairs are found
   */
  public int twoPair() {
    int[] counts = getCounts();
    int pairsFound = 0;
    int score = 0;
    for (int i = counts.length - 1; i >= 0; i--) {
      if (counts[i] >= 2) {
        pairsFound++;
        score += (i + 1) * 2;
      }
    }
    return pairsFound == 2 ? score : 0;
  }

  /**
   * Calculates the score for a small straight (sequence of dice from 1 to 5).
   *
   * @return the score for a small straight, or 0 if no small straight is found
   */
  public int smallStraight() {
    int[] counts = getCounts();
    if (counts[0] == 1 && counts[1] == 1 && counts[2] == 1 && counts[3] == 1 && counts[4] == 1) {
      return 15;
    }
    return 0;
  }

  /**
   * Calculates the score for a large straight (sequence of dice from 2 to 6).
   *
   * @return the score for a large straight, or 0 if no large straight is found
   */
  public int largeStraight() {
    int[] counts = getCounts();
    if (counts[1] == 1 && counts[2] == 1 && counts[3] == 1 && counts[4] == 1 && counts[5] == 1) {
      return 20;
    }
    return 0;
  }

  /**
   * Calculates the score for a full house (a pair and a three of a kind).
   *
   * @return the score for a full house, or 0 if no full house is found
   */
  public int fullHouse() {
    int[] counts = getCounts();

    boolean twoOfKind = false;
    int twoOfKindPosition = 0;

    boolean threeOfKind = false;
    int threeOfKindPosition = 0;

    for (int i = 0; i < counts.length; i++) {
      if (counts[i] == 2) {
        twoOfKind = true;
        twoOfKindPosition = i + 1;
      }
      if (counts[i] == 3) {
        threeOfKind = true;
        threeOfKindPosition = i + 1;
      }
    }

    if (twoOfKind && threeOfKind) {
      return twoOfKindPosition * 2 + threeOfKindPosition * 3;
    } else {
      return 0;
    }
  }
}
