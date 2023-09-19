package com.example.fxchess;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class CapturedPieces {

  VBox whiteCapturedPieces = new VBox();
  VBox blackCapturedPieces = new VBox();
  VBox capturedWhitePawns = new VBox();
  VBox capturedBlackPawns = new VBox();

  public VBox getWhiteCapturedPieces() {
    return whiteCapturedPieces;
  }
  public VBox getBlackCapturedPieces() {
    return blackCapturedPieces;
  }
  public VBox getCapturedWhitePawns() {
    return capturedWhitePawns;
  }
  public VBox getCapturedBlackPawns() {
    return capturedBlackPawns;
  }
  public void addCapturedPiece(int pieceValue) {
    switch (pieceValue) {
      case 1 -> {
        capturedWhitePawns.getChildren().add(new ImageView(new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/White Pawn.png")).toString())));
        capturedWhitePawns.requestLayout();
      }
      case -1 -> {
        capturedBlackPawns.getChildren().add(new ImageView(new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/Black Pawn.png")).toString())));
        capturedBlackPawns.requestLayout();
      }
      case 2 -> {
        blackCapturedPieces.getChildren().add(new ImageView(new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/White Rook.png")).toString())));
        whiteCapturedPieces.requestLayout();
      }
      case -2 -> {
        blackCapturedPieces.getChildren().add(new ImageView(new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/Black Rook.png")).toString())));
        blackCapturedPieces.requestLayout();
      }
      case 3 -> {
        whiteCapturedPieces.getChildren().add(new ImageView(new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/White Knight.png")).toString())));
        whiteCapturedPieces.requestLayout();
      }
      case -3 -> {
        blackCapturedPieces.getChildren().add(new ImageView(new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/Black Knight.png")).toString())));
        blackCapturedPieces.requestLayout();
      }
      case 4 -> {
        whiteCapturedPieces.getChildren().add(new ImageView(new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/White Bishop.png")).toString())));
        whiteCapturedPieces.requestLayout();
      }
      case -4 -> {
        blackCapturedPieces.getChildren().add(new ImageView(new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/Black Bishop.png")).toString())));
        blackCapturedPieces.requestLayout();
      }
      case 5 -> {
        whiteCapturedPieces.getChildren().add(new ImageView(new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/White Queen.png")).toString())));
        whiteCapturedPieces.requestLayout();
      }
      case -5 -> {
        blackCapturedPieces.getChildren().add(new ImageView(new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/Black Queen.png")).toString())));
        blackCapturedPieces.requestLayout();
      }
      case 6 -> {
        whiteCapturedPieces.getChildren().add(new ImageView(new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/White King.png")).toString())));
        whiteCapturedPieces.requestLayout();
      }
      case -6 -> {
        blackCapturedPieces.getChildren().add(new ImageView(new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/Black King.png")).toString())));
        blackCapturedPieces.requestLayout();
      }
    }
  }
  public void clearBoard() {
    whiteCapturedPieces.getChildren().clear();
    capturedWhitePawns.getChildren().clear();
    blackCapturedPieces.getChildren().clear();
    capturedBlackPawns.getChildren().clear();
  }
}