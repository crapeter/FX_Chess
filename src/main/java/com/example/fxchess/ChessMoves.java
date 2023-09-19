package com.example.fxchess;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class ChessMoves {
  private final ChessController controller;
  public boolean whiteLeftEP = false;
  public boolean whiteRightEP = false;
  public boolean blackLeftEP = false;
  public boolean blackRightEP = false;
  public boolean[][] legalMoves = new boolean[8][8];
  public ChessMoves(ChessController controller) {
    this.controller = controller;
    initializeMoves();
  }
  public void whitePawn(int r , int c) {
    initializeMoves();
    if  (r == 6 && controller.board[r - 1][c] == 0 && controller.board[r - 2][c] == 0) {
      Objects.requireNonNull(getCellPaneAt(r - 2, c)).setStyle("-fx-background-color: yellow");
      legalMoves[r - 2][c] = true;
    }
    if (r - 1 >= 0 && controller.board[r - 1][c] == 0) {
      Objects.requireNonNull(getCellPaneAt(r - 1, c)).setStyle("-fx-background-color: yellow");
      legalMoves[r - 1][c] = true;
    }
    if (r - 1 >= 0 && c - 1 >= 0 &&controller.board[r - 1][c - 1] < 0) {
      Objects.requireNonNull(getCellPaneAt(r - 1, c - 1)).setStyle("-fx-background-color: yellow");
      legalMoves[r - 1][c - 1] = true;
    }
    if (r - 1 >= 0 && c + 1 < 8 && controller.board[r - 1][c + 1] < 0) {
      Objects.requireNonNull(getCellPaneAt(r - 1, c + 1)).setStyle("-fx-background-color: yellow");
      legalMoves[r - 1][c + 1] = true;
    }
    if (r == 3) {
      if (c - 1 > 0 && controller.board[r][c - 1] == -1 && controller.blackEnPassant[c - 1]) {
        System.out.println(controller.blackEnPassant[c - 1]);
        Objects.requireNonNull(getCellPaneAt(r - 1, c - 1)).setStyle("-fx-background-color: yellow");
        legalMoves[r - 1][c - 1] = true;
        whiteLeftEP = true;
        legalMoves[r - 1][c + 1] = false;
        whiteRightEP = false;
      }
      if (c + 1 < 8 && controller.board[r][c + 1] == -1 && controller.blackEnPassant[c + 1]) {
        Objects.requireNonNull(getCellPaneAt(r - 1, c + 1)).setStyle("-fx-background-color: yellow");
        legalMoves[r - 1][c + 1] = true;
        whiteRightEP = true;
        legalMoves[r - 1][c - 1] = false;
        whiteLeftEP = false;
      }
    }
  }
  public void blackPawn(int r , int c) {
    initializeMoves();
    if (r == 1 && controller.board[r + 1][c] == 0 && controller.board[r + 2][c] == 0) {
      Objects.requireNonNull(getCellPaneAt(r + 2, c)).setStyle("-fx-background-color: yellow");
      legalMoves[r + 2][c] = true;
    }
    if (r + 1 < 8 && controller.board[r + 1][c] == 0) {
      Objects.requireNonNull(getCellPaneAt(r + 1, c)).setStyle("-fx-background-color: yellow");
      legalMoves[r + 1][c] = true;
    }
    if (r + 1 < 8 && c - 1 >= 0 && controller.board[r + 1][c - 1] > 0) {
      Objects.requireNonNull(getCellPaneAt(r + 1, c - 1)).setStyle("-fx-background-color: yellow");
      legalMoves[r + 1][c - 1] = true;
    }
    if (r + 1 < 8 && c + 1 < 8 && controller.board[r + 1][c + 1] > 0) {
      Objects.requireNonNull(getCellPaneAt(r + 1, c + 1)).setStyle("-fx-background-color: yellow");
      legalMoves[r + 1][c + 1] = true;
    }
    if (r == 4) {
      if (c - 1 >= 0 && controller.board[r][c - 1] == 1 && controller.whiteEnPassant[c - 1]) {
        Objects.requireNonNull(getCellPaneAt(r + 1, c - 1)).setStyle("-fx-background-color: yellow");
        legalMoves[r + 1][c - 1] = true;
        blackLeftEP = true;
        legalMoves[r + 1][c + 1] = false;
        blackRightEP = false;
      }
      if (c + 1 < 8 && controller.board[r][c + 1] == 1 && controller.whiteEnPassant[c + 1]) {
        Objects.requireNonNull(getCellPaneAt(r + 1, c + 1)).setStyle("-fx-background-color: yellow");
        legalMoves[r + 1][c + 1] = true;
        blackRightEP = true;
        legalMoves[r + 1][c - 1] = false;
        blackLeftEP = false;
      }
    }
  }
  public void rook(int r , int c) {
    if (controller.currentlyWhite) {
      highlightMoves(r, c, 1, 0, 1);
      highlightMoves(r, c, -1, 0, 1);
      highlightMoves(r, c, 0, 1, 1);
      highlightMoves(r, c, 0, -1, 1);
    } else {
      highlightMoves(r, c, 1, 0, -1);
      highlightMoves(r, c, -1, 0, -1);
      highlightMoves(r, c, 0, 1, -1);
      highlightMoves(r, c, 0, -1, -1);
    }
  }
  public void knight(int r , int c) {
    int[][] knightMoves = {
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
    };

    for (int[] move : knightMoves) {
      int newRow = r + move[0];
      int newCol = c + move[1];

      if (isInBounds(newRow, newCol)) {
        if ((controller.currentlyWhite && controller.board[newRow][newCol] <= 0) ||
                (!controller.currentlyWhite && controller.board[newRow][newCol] >= 0)) {

          Objects.requireNonNull(getCellPaneAt(newRow, newCol)).setStyle("-fx-background-color: yellow");
          legalMoves[newRow][newCol] = true;
        }
      }
    }
  }
  public void bishop(int r , int c) {
    if (controller.currentlyWhite) {
      highlightMoves(r, c, 1, 1, 1);
      highlightMoves(r, c, 1, -1, 1);
      highlightMoves(r, c, -1, 1, 1);
      highlightMoves(r, c, -1, -1, 1);
    } else {
      highlightMoves(r, c, 1, 1, -1);
      highlightMoves(r, c, 1, -1, -1);
      highlightMoves(r, c, -1, 1, -1);
      highlightMoves(r, c, -1, -1, -1);
    }
  }
  public void queen(int r , int c) {
    rook(r, c);
    bishop(r, c);
  }
  public void king(int r , int c) {
    int[][] kingMoves = {
            {1, -1}, {1, 0}, {1, 1}, {0, 1},
            {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}
    };
    controller.getCastle().canCastle();
    for (int[] move : kingMoves) {
      int newRow = r + move[0];
      int newCol = c + move[1];

      if (isInBounds(newRow, newCol)) {
        if ((controller.currentlyWhite && controller.board[newRow][newCol] <= 0) ||
                (!controller.currentlyWhite && controller.board[newRow][newCol] >= 0)) {
          System.out.println(controller.currentlyWhite);
          if (!controller.getCheck().check(newRow, newCol)) {
            Objects.requireNonNull(getCellPaneAt(newRow, newCol)).setStyle("-fx-background-color: yellow");
            legalMoves[newRow][newCol] = true;
          } else {
            Objects.requireNonNull(getCellPaneAt(newRow, newCol)).setStyle("-fx-background-color: red");
          }

        }
      }
    }
  }
  public void initializeMoves() {
    for (int r = 0; r < 8; r++) {
      for (int c = 0; c < 8; c++) {
        legalMoves[r][c] = false;
      }
    }
  }
  public StackPane getCellPaneAt(int r, int c) {
    for (Node node : controller.getChessBoard().boardGrid.getChildren()) {
      if (GridPane.getRowIndex(node) == r && GridPane.getColumnIndex(node) == c) {
        return (StackPane) node;
      }
    }
    return null;
  }
  private boolean isInBounds(int r, int c) {
    return r >= 0 && r < 8 && c >= 0 && c < 8;
  }
  private void highlightMoves(int r, int c, int rInc, int cInc, int valueCondition) {
    while (true) {
      r += rInc;
      c += cInc;

      if (r < 0 || r >= 8 || c < 0 || c >= 8) break;

      if ((valueCondition > 0 && controller.board[r][c] > 0) ||
              (valueCondition < 0 && controller.board[r][c] < 0)) break;

      Objects.requireNonNull(getCellPaneAt(r, c)).setStyle("-fx-background-color: yellow");
      legalMoves[r][c] = true;
      if (controller.board[r][c] != 0) break;
    }
  }
}