package com.example.fxchess;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class Promotion {
  private final ChessController controller;

  public Promotion(ChessController controller) {
    this.controller = controller;
  }
  public void promote(int r, int c) {
    final Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    VBox vbox = new VBox();
    vbox.setAlignment(Pos.CENTER);
    HBox hbox1 =  new HBox();
    HBox hbox2 =  new HBox();
    hbox1.setAlignment(Pos.CENTER);
    hbox2.setAlignment(Pos.CENTER);

    Button queen = new Button("Promote to Queen");
    Button rook = new Button("Promote to Rook");
    Button knight = new Button("Promote to Knight");
    Button bishop = new Button("Promote to Bishop");

    queen.setPrefSize(125, 125);
    queen.setFont(new Font(12));
    queen.setStyle("-fx-background-color: rgb(238, 238, 210)");

    rook.setPrefSize(125, 125);
    rook.setFont(new Font(12));
    rook.setStyle("-fx-background-color: rgb(118, 150, 86)");

    knight.setPrefSize(125, 125);
    knight.setFont(new Font(12));
    knight.setStyle("-fx-background-color: rgb(118, 150, 86)");

    bishop.setPrefSize(125, 125);
    bishop.setFont(new Font(12));
    bishop.setStyle("-fx-background-color: rgb(238, 238, 210)");

    controller.removePawn(controller.location[0], controller.location[1]);

    queen.setOnAction(e -> {
      StackPane prStackPane = controller.getStackPaneAt(controller.getChessBoard().boardGrid, r, c);
      ImageView prImageView = controller.getImageViewFromStackPane(prStackPane);
      if (prImageView != null) {
        if (controller.currentlyWhite){
          prImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/assets/Black Queen.png")).toString()));
          controller.board[r][c] = -5;
        } else {
          prImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/assets/White Queen.png")).toString()));
          controller.board[r][c] = 5;
        }
        controller.board[controller.location[0]][controller.location[1]] = 0;
      }
      dialog.close();
    });
    rook.setOnAction(e -> {
      StackPane prStackPane = controller.getStackPaneAt(controller.getChessBoard().boardGrid, r, c);
      ImageView prImageView = controller.getImageViewFromStackPane(prStackPane);
      if (prImageView != null) {
        if (controller.currentlyWhite){
          prImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/assets/Black Rook.png")).toString()));
          controller.board[r][c] = -2;
        } else {
          prImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/assets/White Rook.png")).toString()));
          controller.board[r][c] = 2;
        }
        controller.board[controller.location[0]][controller.location[1]] = 0;
      }
      dialog.close();
    });
    knight.setOnAction(e -> {
      StackPane prStackPane = controller.getStackPaneAt(controller.getChessBoard().boardGrid, r, c);
      ImageView prImageView = controller.getImageViewFromStackPane(prStackPane);
      if (prImageView != null) {
        if (controller.currentlyWhite){
          prImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/assets/Black Knight.png")).toString()));
          controller.board[r][c] = -3;
        } else {
          prImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/assets/White Knight.png")).toString()));
          controller.board[r][c] = 3;
        }
        controller.board[controller.location[0]][controller.location[1]] = 0;
      }
      dialog.close();
    });
    bishop.setOnAction(e -> {
      StackPane prStackPane = controller.getStackPaneAt(controller.getChessBoard().boardGrid, r, c);
      ImageView prImageView = controller.getImageViewFromStackPane(prStackPane);
      if (prImageView != null) {
        if (controller.currentlyWhite){
          prImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/assets/Black Bishop.png")).toString()));
          controller.board[r][c] = -4;
        } else {
          prImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/assets/White Bishop.png")).toString()));
          controller.board[r][c] = 4;
        }
        controller.board[controller.location[0]][controller.location[1]] = 0;
      }
      dialog.close();
    });
    hbox1.getChildren().addAll(queen, knight);
    hbox2.getChildren().addAll(rook, bishop);
    vbox.getChildren().addAll(hbox1, hbox2);
    Scene scene = new Scene(vbox, 250, 250);
    dialog.setScene(scene);
    dialog.setResizable(false);
    dialog.showAndWait();
  }
}
