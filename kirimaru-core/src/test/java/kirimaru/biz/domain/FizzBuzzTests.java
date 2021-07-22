package kirimaru.biz.domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FizzBuzzTests {
  FizzBuzz target = new FizzBuzz();

  private StandardOutputStream out;

  @BeforeEach
  void setUp() {
    out = new StandardOutputStream();
    System.setOut(out);
  }

  @AfterEach
  void tearDown() throws Exception {
    System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(FileDescriptor.out), 128), true, StandardCharsets.UTF_8));
  }

  @Test
  void execute() {
    target.execute();
    int i = 0;
    SoftAssertions softly = new SoftAssertions();
    String actual = out.readLine();

    while (actual != null) {
      softly.assertThat(target.convert(++i)).isEqualTo(actual);
      actual = out.readLine();
    }
    softly.assertThat(100).isEqualTo(i);
    softly.assertAll();

  }

  @ParameterizedTest
  @MethodSource(value = "param")
  void covert_test(int input, String output) {
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
