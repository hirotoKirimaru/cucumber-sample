package kirimaru.restapi.anotation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import java.util.stream.Stream;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
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
          new Target("あいうえお", false),
          new Target("吉野家", false),
          new Target("𠮷野家", true),
          new Target("🥺", true),
          new Target("あい🥺うえお", true),
          new Target("∥", true),
          new Target("あい∥うえお", true)
      ).map(Arguments::of);
    }
  }

  private static class TestBean {

    @AppStringValid
    private String targetStr;

    TestBean(String targetStr) {
      this.targetStr = targetStr;
    }
  }
}