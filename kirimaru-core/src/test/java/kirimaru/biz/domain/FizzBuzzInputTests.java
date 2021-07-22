package kirimaru.biz.domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FizzBuzzInputTests {
  private StandardInputStream in = new StandardInputStream();
  private StandardOutputStream out = new StandardOutputStream();

  FizzBuzzInput target = new FizzBuzzInput();

  @BeforeEach
  public void before() {
    System.setIn(in);
    System.setOut(out);
  }

  @AfterEach
  void tearDown() {
    System.setIn(null);
    System.setOut(null);
  }

  @Test
  public void _1and2AndFizzAndNull() {
    // Given
    in.inputln("1");
    in.inputln("2");
    in.inputln("3");
    // When
    target.main();

    // Then
    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(out.readLine()).isEqualTo("1");
    softly.assertThat(out.readLine()).isEqualTo("2");
    softly.assertThat(out.readLine()).isEqualTo("Fizz");
    softly.assertThat(out.readLine()).isNull();
    softly.assertAll();
  }

}
