package kirimaru.biz.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzTests {
  FizzBuzz target = new FizzBuzz();


  @Test
  void execute() {
    target.execute();
  }

  @ParameterizedTest
  @MethodSource(value = "param")
  void covert_test(int input, String output){
    assertThat(target.convert(input)).isEqualTo(output);
  }

  private static Stream<Arguments> param() {
    return Stream.of(
        Arguments.of(1, "1"),
        Arguments.of(2, "2"),
        Arguments.of(3, "Fizz"),
        Arguments.of(4, "4"),
        Arguments.of(5, "Buzz"),
        Arguments.of(15, "FizzBuzz")
    );
  }


}
