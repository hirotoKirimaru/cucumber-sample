package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArgumentsSouceTest {
  private Othello target = new Othello();

  @Test
  @DisplayName("ゲーム開始")
  void start() {
    target.start();

    Board expect = new Board();
    expect.setBoard(new int[CodeConstant.Othello.SQUARE][CodeConstant.Othello.SQUARE]);
    (expect.getBoard())[3][3] = CodeConstant.Othello.PLAYER_1_PIECE;
    (expect.getBoard())[4][4] = CodeConstant.Othello.PLAYER_1_PIECE;
    (expect.getBoard())[3][4] = CodeConstant.Othello.PLAYER_2_PIECE;
    (expect.getBoard())[4][3] = CodeConstant.Othello.PLAYER_2_PIECE;

    assertEquals(expect, target.board);
  }

  //  @Disabled("staticな値ではないとできないため、Builderクラスを使えないので除外")
  @ParameterizedTest
  @MethodSource("aiueo")
  @DisplayName("player?は?行?列に置けるか")
  void hoge(int player, int row, int column, boolean result) {
    assertEquals(1, player);
    assertEquals(0, row);
    assertEquals(0, column);
    assertEquals(false, result);
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
              Taiueo.builder().player(0).row(0).column(0).result(false).build()
      ).map(Arguments::of);
    }
  }
}