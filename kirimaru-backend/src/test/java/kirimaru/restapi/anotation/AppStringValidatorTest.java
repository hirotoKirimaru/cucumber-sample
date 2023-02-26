package kirimaru.restapi.anotation;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import java.util.stream.Stream;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

class AppStringValidatorTest {

  private Validator validator;

  @BeforeEach
  void setUp() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @ArgumentsSource(Target.class)
  @ParameterizedTest
  void checkTest(Target target) {
    TestBean testBean = new TestBean(target.string);
    Set<ConstraintViolation<TestBean>> violations = validator.validate(testBean);
    assertThat(violations.isEmpty()).isEqualTo(target.expected);
  }

  @Data
  static class Target implements ArgumentsProvider {

    private String string;
    private boolean expected;

    Target() {
    }

    Target(String string, boolean expected) {
      this.string = string;
      this.expected = expected;
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
      return Stream.of(
          new Target(null, false),
          new Target("あいうえおかきくけこ", false),
          new Target("あいうえおかきくけこさ", true),
          new Target("1234567890", false),
          new Target("12345678901", true)

      ).map(Arguments::of);
    }
  }

  private static class TestBean {

    @AppStringValid(byteCount = 30, wordCount = 10)
    private String targetStr;

    TestBean(String targetStr) {
      this.targetStr = targetStr;
    }
  }
}