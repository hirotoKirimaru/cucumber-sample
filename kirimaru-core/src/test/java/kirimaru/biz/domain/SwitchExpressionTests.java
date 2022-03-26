package kirimaru.biz.domain;

/**
 * https://docs.oracle.com/en/java/javase/17/language/switch-expressions.html
 */
class SwitchExpressionTests {

  public enum Day {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY;
  }

  public int returnValue() {
    int numLetters = 0;
    Day day = Day.WEDNESDAY;
    switch (day) {
      case MONDAY:
      case FRIDAY:
      case SUNDAY:
        numLetters = 6;
        break;
      case TUESDAY:
        numLetters = 7;
        break;
      case THURSDAY:
      case SATURDAY:
        numLetters = 8;
        break;
      case WEDNESDAY:
        numLetters = 9;
        break;
      default:
        throw new IllegalStateException("Invalid day: " + day);
    }
    System.out.println(numLetters);
    return numLetters;
  }

  public int returnValu2() {
    Day day = Day.WEDNESDAY;
    int numLetters = switch (day) {
      case MONDAY, FRIDAY, SUNDAY -> 6;
      case TUESDAY -> 7;
      case THURSDAY, SATURDAY -> 8;
      case WEDNESDAY -> 9;
      default -> throw new IllegalStateException("Invalid day: " + day);
    };
    System.out.println(numLetters);
    return numLetters;
  }
}
