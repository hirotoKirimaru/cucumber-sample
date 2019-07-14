package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

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
  @ArgumentsSource(CanSetPlayerPieceTest.class)
  @DisplayName("player?は?行?列に置けるか3")
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
          Arguments.of(
              CanSetPlayerPieceTest.builder().player(0).row(0).column(0).result(false).build()
          )
      );
    }
  }
}