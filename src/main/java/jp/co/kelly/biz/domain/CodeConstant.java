package jp.co.kelly.biz.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class CodeConstant {

  public static class Othello {
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

  public static class Questions {
    @Getter
    @AllArgsConstructor
    public enum Genre {
      JAVA(1),
      JAVASCRIPT(2),
      RUBY(3),
      PYTHON(4),

      ;
      int type;


    }


  }

}