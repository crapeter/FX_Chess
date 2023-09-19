package com.example.fxchess;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class ChessApp extends Application {
  public static Label title;
  @Override
  public void start(Stage stage) {
    ChessController controller = new ChessController();

    title = new Label("White's Turn");
    title.setFont(Font.font(35));
    title.setStyle("-fx-text-fill: rgb(200, 200, 200)");

    HBox capturedPiecesBox = new HBox(10);
    BorderPane mainLayout = new BorderPane();
    mainLayout.setCenter(controller.getChessBoard().getBoardGrid());

    VBox capturedWhitePieceBox = new VBox();
    capturedWhitePieceBox.getChildren().add(controller.getWhiteCapturedPieces().getWhiteCapturedPieces());
    VBox capturedWhitePawnsBox = new VBox();
    capturedWhitePawnsBox.getChildren().add(controller.getWhiteCapturedPieces().getCapturedWhitePawns());
    VBox capturedBlackPieceBox = new VBox();
    capturedBlackPieceBox.getChildren().add(controller.getBlackCapturedPieces().getBlackCapturedPieces());
    VBox capturedBlackPawnsBox = new VBox();
    capturedBlackPawnsBox.getChildren().add(controller.getBlackCapturedPieces().getCapturedBlackPawns());
    HBox buttons = new HBox(3);
    buttons.getChildren().add(controller.getChessGame().forfeitButton);
    buttons.getChildren().add(controller.getChessGame().resetButton);

    capturedPiecesBox.getChildren().add(capturedWhitePieceBox);
    capturedPiecesBox.getChildren().add(capturedWhitePawnsBox);
    capturedPiecesBox.getChildren().add(capturedBlackPieceBox);
    capturedPiecesBox.getChildren().add(capturedBlackPawnsBox);

    mainLayout.setRight(capturedPiecesBox);
    mainLayout.setBottom(buttons);
    mainLayout.setTop(title);
    mainLayout.setStyle("-fx-background-color: rgb(66, 66, 66)");

    Scene scene = new Scene(mainLayout, 875, 700);
    stage.setResizable(false);
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}