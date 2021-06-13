package kirimaru.biz.domain;

import kirimaru.biz.domain.constant.CodeConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OthelloTest {
  private final Othello target;

  OthelloTest() {
    target = new Othello();
  }

  @Disabled
  @Test
  @DisplayName("ゲーム開始")
  void start() {
    Board expect = new Board();
    expect.setBoard(new int[CodeConstant.Othello.SQUARE][CodeConstant.Othello.SQUARE]);
    (expect.getBoard())[3][3] = CodeConstant.Othello.piece.PLAYER_1;
    (expect.getBoard())[4][4] = CodeConstant.Othello.piece.PLAYER_1;
    (expect.getBoard())[3][4] = CodeConstant.Othello.piece.PLAYER_2;
    (expect.getBoard())[4][3] = CodeConstant.Othello.piece.PLAYER_2;

    assertEquals(expect, target.board);
  }

  @ParameterizedTest
  @ArgumentsSource(CanSetPlayerPieceTest.class)
  @DisplayName("player?は?行?列に置けるか")
  void fuga(CanSetPlayerPieceTest param) {

    assertEquals(param.result, target.canSetPlayerPiece(param.player, param.row, param.column));
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  static class CanSetPlayerPieceTest implements ArgumentsProvider {
    private int player;
    private int row;
    private int column;
    private boolean result;

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
      return Stream.of(
          CanSetPlayerPieceTest.builder().player(1).row(0).column(0).result(false).build(),
          CanSetPlayerPieceTest.builder().player(1).row(2).column(3).result(false).build(),
          CanSetPlayerPieceTest.builder().player(2).row(2).column(3).result(true).build(),
          CanSetPlayerPieceTest.builder().player(1).row(4).column(2).result(true).build(),
          CanSetPlayerPieceTest.builder().player(2).row(4).column(2).result(false).build()
      ).map(Arguments::of);
    }
  }
}
