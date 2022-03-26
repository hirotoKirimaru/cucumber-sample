package kirimaru.biz.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

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

  public int returnValue(Day day) {
    int numLetters = 0;
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

  public int returnValue2(Day day) {
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

  public int returnValue3(Day day) {
    int numLetters = switch (day) {
      case MONDAY:
      case FRIDAY:
      case SUNDAY:
        System.out.println(6);
        yield 6;
      case TUESDAY:
        System.out.println(7);
        yield 7;
      case THURSDAY:
      case SATURDAY:
        System.out.println(8);
        yield 8;
      case WEDNESDAY:
        System.out.println(9);
        yield 9;
      default:
        throw new IllegalStateException("Invalid day: " + day);
    };
    return numLetters;
  }

  public int returnValue4(Day day) {
    int numLetters = switch (day) {
      case MONDAY, FRIDAY, SUNDAY -> {
        System.out.println(6);
        yield 6;
      }
      case TUESDAY -> {
        System.out.println(7);
        yield 7;
      }
      case THURSDAY, SATURDAY -> {
        System.out.println(8);
        yield 8;
      }
      case WEDNESDAY -> {
        System.out.println(9);
        yield 9;
      }
      default -> {
        throw new IllegalStateException("Invalid day: " + day);
      }
    };
    return numLetters;
  }

  public int returnValue5(Day day) {
    switch (day) {
      case MONDAY, FRIDAY, SUNDAY -> {
        System.out.println(6);
        return 6;
      }
      case TUESDAY -> {
        System.out.println(7);
        return 7;
      }
      case THURSDAY, SATURDAY -> {
        System.out.println(8);
        return 8;
      }
      case WEDNESDAY -> {
        System.out.println(9);
        return 9;
      }
      default -> {
        throw new IllegalStateException("Invalid day: " + day);
      }
    }
  }

  @EnumSource(value = Day.class)
  @ParameterizedTest
  public void test_01(Day day) {
    System.out.println(returnValue(day));
  }

  @EnumSource(value = Day.class)
  @ParameterizedTest
  public void test_02(Day day) {
    System.out.println(returnValue2(day));
  }

  @EnumSource(value = Day.class)
  @ParameterizedTest
  public void test_03(Day day) {
    System.out.println(returnValue3(day));
  }

  @EnumSource(value = Day.class)
  @ParameterizedTest
  public void test_04(Day day) {
    System.out.println(returnValue4(day));
  }

  @EnumSource(value = Day.class)
  @ParameterizedTest
  public void test_05(Day day) {
    System.out.println(returnValue5(day));
  }

}
