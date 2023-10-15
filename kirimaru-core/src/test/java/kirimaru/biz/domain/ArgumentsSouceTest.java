package kirimaru.biz.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Method;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

//@Disabled
@DisplayNameGeneration(ArgumentsSouceTest.Gen.class)
class ArgumentsSouceTest {
  private Othello target = new Othello();

  static class Gen extends DisplayNameGenerator.Standard {

    @Override
    public String generateDisplayNameForClass(Class<?> testClass) {
      return "1:"+ super.generateDisplayNameForClass(testClass);
    }

    @Override
    public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
      return "2:"+super.generateDisplayNameForNestedClass(nestedClass);
    }

    @Override
    public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
      return "3:" + super.generateDisplayNameForMethod(testClass, testMethod);
    }
  }

    @Disabled("staticな値ではないとできないため、Builderクラスを使えないので除外")
  @ParameterizedTest(name = "gagaga[index]")
  @ValueSource(strings = {"1", "2", "3"})
//  @DisplayName("player?は?行?列に置けるか4")
  void hoge3(String param) {
    assertEquals("1", param);
  }


  //  @Disabled("staticな値ではないとできないため、Builderクラスを使えないので除外")
  @ParameterizedTest
  @MethodSource("aiueo")
  @DisplayName("player?は?行?列に置けるか1")
  void hoge(int player, int row, int column, boolean result) {
    assertEquals(result, target.canSetPlayerPiece(player, row, column));
  }

  private static Stream<Arguments> aiueo() {
    return Stream.of(
        Arguments.of(1, 0, 0, false)
    );
  }

  //  @Disabled
  @ParameterizedTest
  @EnumSource(Saiueo.class)
  @DisplayName("player?は?行?列に置けるか2")
  void hige(Saiueo saiueo) {

    assertEquals(saiueo.result, target.canSetPlayerPiece(saiueo.player, saiueo.row, saiueo.column));
  }

  enum Saiueo {
    A(1, 0, 0, false),
    B(1, 0, 1, false),
    ;

    private final int player;
    private final int row;
    private final int column;
    private final boolean result;

    Saiueo(int player, int row, int column, boolean result) {
      this.player = player;
      this.row = row;
      this.column = column;
      this.result = result;
    }
  }

  @Disabled
  @ParameterizedTest
  @ArgumentsSource(Taiueo.class)
  @DisplayName("player?は?行?列に置けるか3")
  void fuga(Taiueo taiueo) {

    assertEquals(taiueo.result, target.canSetPlayerPiece(taiueo.player, taiueo.row, taiueo.column));
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  static class Taiueo implements ArgumentsProvider {
    private int player;
    private int row;
    private int column;
    private boolean result;

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
      return Stream.of(
          Taiueo.builder().player(1).row(0).column(0).result(false).build(),
          Taiueo.builder().player(2).row(0).column(0).result(true).build(),
          Taiueo.builder().player(1).row(0).column(1).result(true).build()
      ).map(Arguments::of);
    }
  }
}
