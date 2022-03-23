package kirimaru.biz.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.With;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LombokWithTest {

  @Builder(toBuilder = true)
  @AllArgsConstructor
  @With
  @ToString
  @EqualsAndHashCode
  static class Condition {

    boolean a;
    boolean b;
    boolean c;
    boolean d;

    public boolean allTrue() {
      return a && b && c && d;
    }
  }

  @MethodSource(value = "createTestParameter")
  @ParameterizedTest
  void test(Condition condition) {
    assertThat(condition.allTrue()).isFalse();
  }


  private static Stream<Arguments> createTestParameter() {
    Condition success = new Condition(true, true, true, true);

    return Stream.of(
        Arguments.of(success.toBuilder().a(false).build()),
        Arguments.of(success.toBuilder().b(false).build()),
        Arguments.of(success.toBuilder().c(false).build()),
        Arguments.of(success.toBuilder().d(false).build())
    );
  }


  @MethodSource(value = "createTestParameter2")
  @ParameterizedTest
  void test2(Condition condition) {
    assertThat(condition.allTrue()).isFalse();
  }

  private static Stream<Arguments> createTestParameter2() {
    Condition success = new Condition(true, true, true, true);

    return Stream.of(
        Arguments.of(success.withA(false)),
        Arguments.of(success.withB(false)),
        Arguments.of(success.withC(false)),
        Arguments.of(success.withD(false))
    );
  }




  static class WithExample {
    @With(AccessLevel.PROTECTED)
    @NonNull
    private final String name;
    @With
    private final int age;

    public WithExample(String name, int age) {
      if (name == null) {
        throw new NullPointerException();
      }
      this.name = name;
      this.age = age;
    }
  }



  static class VanillaWithExample {
    private @NonNull final String name;
    private final int age;

    public VanillaWithExample(String name, int age) {
      if (name == null) throw new NullPointerException();
      this.name = name;
      this.age = age;
    }

    protected VanillaWithExample withName(@NonNull String name) {
      if (name == null) throw new java.lang.NullPointerException("name");
      return this.name == name ? this : new VanillaWithExample(name, age);
    }

    public VanillaWithExample withAge(int age) {
      return this.age == age ? this : new VanillaWithExample(name, age);
    }
  }

}
