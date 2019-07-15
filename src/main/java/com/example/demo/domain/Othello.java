package com.example.demo.domain;

import lombok.Data;

@Data
public class Othello {
  Board board;

  public Othello() {
    this.start();
  }

  private void start() {
    this.board = new Board();
    this.board.setOthelloBoard();
  }

  public boolean canSetPlayerPiece(int player, int row, int column) {
    return board.canSetOthelloPlayerPiece(player, row, column);
  }
}
