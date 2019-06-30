package com.example.demo.domain;

import lombok.Data;

@Data
public class Board {
  int[][] board;


  void setOthelloBoard() {
    this.board = new int[CodeConstant.Othello.SQUARE][CodeConstant.Othello.SQUARE];
    this.board[3][3] = CodeConstant.Othello.PLAYER_1_PIECE;
    this.board[4][4] = CodeConstant.Othello.PLAYER_1_PIECE;
    this.board[3][4] = CodeConstant.Othello.PLAYER_2_PIECE;
    this.board[4][3] = CodeConstant.Othello.PLAYER_2_PIECE;
  }
}