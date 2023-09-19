package com.example.fxchess;

import java.util.Objects;

public class Castling {
  private final ChessController controller;
  public boolean whiteLeft = true;
  public boolean whiteRight = true;
  public boolean blackLeft = true;
  public boolean blackRight = true;

  public Castling(ChessController controller) {
    this.controller = controller;
  }
  public void canCastle() {
    int count = 0;
    if (controller.currentlyWhite) {
      for (int i = 0; i < 5; i++) {
        if (i == 0 && controller.board[7][i] == 2) {
          count++;
        } else if ((i == 1 || i == 2 || i == 3) && controller.board[7][i] == 0) {
          count++;
        } else if (controller.board[7][i] == 6) {
          count++;
        }
      }
      if (count == 5 && whiteLeft) {
        Objects.requireNonNull(controller.getChessMoves().getCellPaneAt(7, 1))
                .setStyle("-fx-background-color: yellow");
        controller.getChessMoves().legalMoves[7][1] = true;
      }
      count = 0;
      for (int i = 7; i > 3; i--) {
        if (i == 7 && controller.board[7][i] == 2) {
          count++;
        } else if ((i == 6 || i == 5) && controller.board[7][i] == 0) {
          count++;
        } else if (controller.board[7][i] == 6) {
          count++;
        }
      }
      if (count == 4 && whiteRight) {
        Objects.requireNonNull(controller.getChessMoves().getCellPaneAt(7, 6))
                .setStyle("-fx-background-color: yellow");
        controller.getChessMoves().legalMoves[7][6] = true;
      }
    } else {
      for (int i = 0; i < 5; i++) {
        if (i == 0 && controller.board[0][i] == -2) {
          count++;
        } else if ((i == 1 || i == 2 || i == 3) && controller.board[0][i] == -0) {
          count++;
        } else if (controller.board[0][i] == -6) {
          count++;
        }
      }
      if (count == 5 && blackLeft) {
        Objects.requireNonNull(controller.getChessMoves().getCellPaneAt(0, 1))
                .setStyle("-fx-background-color: yellow");
        controller.getChessMoves().legalMoves[0][1] = true;
      }
      count = 0;
      for (int i = 7; i > 3; i--) {
        if (i == 7 && controller.board[0][i] == -2) {
          count++;
        } else if ((i == 6 || i == 5) && controller.board[0][i] == 0) {
          count++;
        } else if (controller.board[0][i] == -6) {
          count++;
        }
      }
      if (count == 4 && blackRight) {
        Objects.requireNonNull(controller.getChessMoves().getCellPaneAt(0, 6))
                .setStyle("-fx-background-color: yellow");
        controller.getChessMoves().legalMoves[0][6] = true;
      }
    }
  }
}