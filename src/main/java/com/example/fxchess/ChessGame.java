package com.example.fxchess;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class ChessGame {

  private final ChessController controller;
  static boolean gameOver = false;
  Button forfeitButton = new Button("Forfeit");
  Button resetButton = new Button("Reset");

  public ChessGame(ChessController controller) {
    this.controller = controller;
    forfeitButton.setPrefSize(150, 50);
    forfeitButton.setStyle("-fx-background-color: rgb(66, 66, 66); -fx-text-fill: rgb(200, 200, 200); " +
            "-fx-border-color: black; -fx-border-width: 3px");
    forfeitButton.setFont(new Font(20));
    forfeitButton.setOnMousePressed(e -> forfeitButton.setStyle("-fx-background-color: rgb(125, 125, 125); -fx-border-color: black; -fx-border-width: 3px"));
    forfeitButton.setOnMouseReleased(e -> forfeitButton.setStyle("-fx-background-color: rgb(66, 66, 66); -fx-text-fill: rgb(200, 200, 200); " +
            "-fx-border-color: black; -fx-border-width: 3px"));
    resetButton.setPrefSize(150, 50);
    resetButton.setStyle("-fx-background-color: rgb(66, 66, 66); -fx-text-fill: rgb(200, 200, 200); " +
            "-fx-border-color: black; -fx-border-width: 3px");
    resetButton.setFont(new Font(20));
    resetButton.setOnMousePressed(e -> resetButton.setStyle("-fx-background-color: rgb(125, 125, 125); -fx-border-color: black; -fx-border-width: 3px"));
    resetButton.setOnMouseReleased(e -> resetButton.setStyle("-fx-background-color: rgb(66, 66, 66); -fx-text-fill: rgb(200, 200, 200); " +
            "-fx-border-color: black; -fx-border-width: 3px"));
    forfeitButton.setOnAction(e -> forfeit());
    resetButton.setOnAction(e -> reset());
  }
  public void forfeit() {
    gameOver = true;
    if (controller.currentlyWhite) {
      ChessApp.title.setText("White forfeited");
    } else {
      ChessApp.title.setText("Black forfeited");
    }
  }
  public void reset() {
    if (gameOver) {
      gameOver = false;
      System.out.println("Game is over... restarting");
      controller.getChessBoard().resetBoard();
      controller.getChessBoard().setupBoard();
      controller.getBlackCapturedPieces().clearBoard();
      controller.getWhiteCapturedPieces().clearBoard();
      controller.currentlyWhite = true;
      controller.holdingPiece = false;
      controller.pieceName = "";
      controller.location[0] = 0;
      controller.location[1] = 0;
      controller.halfMoves = 0;
      for (int i = 0; i < 8; i++) {
        controller.whiteEnPassant[i] = false;
        controller.blackEnPassant[i] = false;
      }
      ChessApp.title.setText("White's turn");
    } else {
      System.out.println("Game isn't over");
    }
  }
}
