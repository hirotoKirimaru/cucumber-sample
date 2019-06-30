package com.example.demo.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}