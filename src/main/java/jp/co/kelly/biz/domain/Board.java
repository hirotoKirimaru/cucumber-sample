package jp.co.kelly.biz.domain;

import lombok.Data;

import static jp.co.kelly.biz.domain.CodeConstant.Othello.*;

@Data
public class Board {
  int[][] board;


  void setOthelloBoard() {
    this.board = new int[SQUARE][SQUARE];
    this.board[3][3] = piece.PLAYER_1;
    this.board[4][4] = piece.PLAYER_1;
    this.board[3][4] = piece.PLAYER_2;
    this.board[4][3] = piece.PLAYER_2;

    this.board[2][3] = piece.PLAYER_2_CAN_SET;
    this.board[3][2] = piece.PLAYER_2_CAN_SET;
    this.board[4][5] = piece.PLAYER_2_CAN_SET;
    this.board[5][4] = piece.PLAYER_2_CAN_SET;

    this.board[4][2] = piece.PLAYER_1_CAN_SET;
    this.board[5][3] = piece.PLAYER_1_CAN_SET;
    this.board[2][4] = piece.PLAYER_1_CAN_SET;
    this.board[3][5] = piece.PLAYER_1_CAN_SET;
  }

  public boolean canSetOthelloPlayerPiece(int player, int row, int column) {
    int target = board[row][column];
    if (player == piece.PLAYER_1) {
      if (target != piece.PLAYER_1_CAN_SET) {
        return false;
      }
    } else {
      if (target != piece.PLAYER_2_CAN_SET) {
        return false;
      }
    }

    return true;
  }
}