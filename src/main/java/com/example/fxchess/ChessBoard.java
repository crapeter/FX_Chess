package com.example.fxchess;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class ChessBoard {
  private final ChessController controller;
  public StackPane cellPane;
  public GridPane boardGrid;
  public ChessBoard(ChessController controller) {
    this.controller = controller;
    boardGrid = new GridPane();
    setupBoard();
  }

  public void setupBoard() {
    for (int r = 0; r < 8; r++) {
      for (int c = 0; c < 8; c++) {
        cellPane = new StackPane();
        if ((r + c) % 2 == 0) {
          cellPane.setStyle("-fx-background-color: rgb(238, 238, 210)");
        } else {
          cellPane.setStyle("-fx-background-color: rgb(118, 150, 86)");
        }
        initializeBoard();
        ImageView cell = getPieceImageView(r, c);
        int finalR = r;
        int finalC = c;
        cellPane.setOnMouseClicked(e -> controller.handlePieceSelected(finalR, finalC));
        cellPane.getChildren().add(cell);
        boardGrid.add(cellPane, c, r);
      }
    }
  }
  public void resetBoard() {
    boardGrid.getChildren().clear();
  }
  public GridPane getBoardGrid() {
    return boardGrid;
  }
  private void initializeBoard() {
    for (int r = 0; r < 8; r++) {
      for (int c = 0; c < 8; c++) {
        if (r == 0) {
          switch (c) {
            case 0, 7 -> controller.board[r][c] = -2;
            case 1, 6 -> controller.board[r][c] = -3;
            case 2, 5 -> controller.board[r][c] = -4;
            case 3 -> controller.board[r][c] = -5;
            case 4 -> controller.board[r][c] = -6;
          }
        } else if (r == 1) {
          controller.board[r][c] = -1;
        } else if (r == 6) {
          controller.board[r][c] = 1;
        } else if (r == 7) {
          switch (c) {
            case 0, 7 -> controller.board[r][c] = 2;
            case 1, 6 -> controller.board[r][c] = 3;
            case 2, 5 -> controller.board[r][c] = 4;
            case 3 -> controller.board[r][c] = 5;
            case 4 -> controller.board[r][c] = 6;
          }
        } else {
          controller.board[r][c] = 0;
        }
      }
    }
  }
  private ImageView getPieceImageView(int r, int c) {
    ImageView cell = new ImageView();
    if (r == 0) {
      switch (c) {
        case 0, 7 -> cell = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/assets/Black Rook.png")).toString()));
        case 1, 6 -> cell = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/assets/Black Knight.png")).toString()));
        case 2, 5 -> cell = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/assets/Black Bishop.png")).toString()));
        case 3 -> cell = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/assets/Black Queen.png")).toString()));
        case 4 -> cell = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/assets/Black King.png")).toString()));
      }
    } else if (r == 1) {
      cell = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/assets/Black Pawn.png")).toString()));
    } else if (r == 6) {
      cell = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/assets/White Pawn.png")).toString()));
    } else if (r == 7) {
      switch (c) {
        case 0, 7 -> cell = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/assets/White Rook.png")).toString()));
        case 1, 6 -> cell = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/assets/White Knight.png")).toString()));
        case 2, 5 -> cell = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/assets/White Bishop.png")).toString()));
        case 3 -> cell = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/assets/White Queen.png")).toString()));
        case 4 -> cell = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/assets/White King.png")).toString()));
      }
    } else {
      cell = new ImageView();
    }
    cell.setFitWidth(75);
    cell.setFitHeight(75);
    return cell;
  }
}
