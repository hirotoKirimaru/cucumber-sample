package kirimaru.config;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class VersionTests {

  @Test
  void test_01() {
    var target = new Version("0.0.1");

    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(target.getMajor()).isEqualTo(0);
    softly.assertThat(target.getMinor()).isEqualTo(0);
    softly.assertThat(target.getPatch()).isEqualTo(1);
    softly.assertAll();
  }

  @Test
  void test_02() {
    var target = new Version("1.10.100");

    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(target.getMajor()).isEqualTo(1);
    softly.assertThat(target.getMinor()).isEqualTo(10);
    softly.assertThat(target.getPatch()).isEqualTo(100);
    softly.assertAll();
  }
}