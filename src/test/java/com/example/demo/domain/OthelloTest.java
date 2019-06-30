package com.example.demo.domain;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
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
  @MethodSource("kaiueo")
  @DisplayName("player?は?行?列に置けるか2")
  void hige(testClass testClass) {
    assertEquals(1, testClass.player);
    assertEquals(0, testClass.row);
    assertEquals(0, testClass.column);
    assertEquals(false, testClass.result);
  }

  private static Stream<testClass> kaiueo() {
    return Stream.of(
//        Arguments.of(testClass.builder().player(1).row(0).column(0).result(false).build())
    );
  }

  //
//  @Data
//  @Builder
  class testClass {
    int player;
    int row;
    int column;
    boolean result;
  }

}