package jp.co.kelly.biz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
class ArgumentsSouceTest {
  private Othello target = new Othello();


  //  @Disabled("staticな値ではないとできないため、Builderクラスを使えないので除外")
  @ParameterizedTest
  @ValueSource(strings = {"1", "2", "3"})
  @DisplayName("player?は?行?列に置けるか4")
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