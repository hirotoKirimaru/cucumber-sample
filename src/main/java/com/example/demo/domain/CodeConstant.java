package com.example.demo.domain;

public class CodeConstant {
  static class Othello {
    /**
     * 升目.
     */
    static int SQUARE = 7;

    /**
     * 駒.
     */
    static class piece {

      static int ANY_PLAYER_SET = 0;
      static int PLAYER_1 = 1;
      static int PLAYER_2 = 2;
      static int PLAYER_1_CAN_SET = 3;
      static int PLAYER_2_CAN_SET = 4;
      static int SOME_PLAYER_SET = 6;
    }

  }
}