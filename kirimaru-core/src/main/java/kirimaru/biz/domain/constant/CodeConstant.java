package kirimaru.biz.domain.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

public final class CodeConstant {

  @AllArgsConstructor
  @Getter
  public enum DbTable {
    BOOK("book"),
    USERS("users"),
    COMPANY("company"),
    COMPANY_DEPARTMENT("company_department"),
    DEPARTMENT("department"),
    DEPARTMENT_MEMBER("department_member")


    ;

    String table;
  }

  public static class Othello {
    /**
     * 升目.
     */
    public static int SQUARE = 7;

    /**
     * 駒.
     */
    public static class piece {

      public static int ANY_PLAYER_SET = 0;
      public static int PLAYER_1 = 1;
      public static int PLAYER_2 = 2;
      public static int PLAYER_1_CAN_SET = 3;
      public static int PLAYER_2_CAN_SET = 4;
      public static int SOME_PLAYER_SET = 6;
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
