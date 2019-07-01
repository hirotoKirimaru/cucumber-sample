package com.example.demo.domain;

import lombok.Builder;
import lombok.Data;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class OthelloTest {
  Othello target = new Othello();

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

  @Disabled
  @ParameterizedTest
//  @MethodSource("kaiueo")
  @EnumSource(Saiueo.class)
  @DisplayName("player?は?行?列に置けるか2")
  void hige(Saiueo saiueo) {
    assertEquals(1, saiueo.player);
    assertEquals(0, saiueo.row);
    assertEquals(0, saiueo.column);
    assertEquals(false, saiueo.result);
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
}