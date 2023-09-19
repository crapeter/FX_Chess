package com.example.fxchess;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import java.util.Objects;

public class ChessController {
  private final ChessBoard chessBoard;
  private final ChessGame chessGame;
  private final ChessMoves chessMoves;
  private final Check check;
  private final Castling castle;
  private final Attacking attack;
  private final Promotion promotion;
  private final CapturedPieces whiteCapturedPieces;
  private final CapturedPieces blackCapturedPieces;
  public int halfMoves = 0;
  public String pieceName;
  public boolean holdingPiece = false;
  public boolean currentlyWhite = true;
  public int[] location = new int[2];
  public int[][] board = new int[8][8];
  public boolean[] whiteEnPassant = new boolean[] {
          false, false, false, false, false, false, false, false
  };
  public boolean[] blackEnPassant = new boolean[] {
          false, false, false, false, false, false, false, false
  };

  public ChessController() {
    chessBoard = new ChessBoard(this);
    chessGame = new ChessGame(this);
    chessMoves = new ChessMoves(this);
    check = new Check(this);
    castle = new Castling(this);
    attack = new Attacking(this);
    promotion = new Promotion(this);
    whiteCapturedPieces = new CapturedPieces();
    blackCapturedPieces = new CapturedPieces();
  }
  public ChessGame getChessGame() {
    return chessGame;
  }
  public ChessBoard getChessBoard() {
    return chessBoard;
  }
  public ChessMoves getChessMoves() {
    return chessMoves;
  }
  public Check getCheck() {
    return check;
  }
  public Castling getCastle() {
    return castle;
  }
  public CapturedPieces getWhiteCapturedPieces() {
    return whiteCapturedPieces;
  }
  public CapturedPieces getBlackCapturedPieces() {
    return blackCapturedPieces;
  }
  public void resetColors() {
    for (int r = 0; r < 8; r++) {
      System.out.println();
      for (int c = 0; c < 8; c++) {
        System.out.print(board[r][c]);
        if ((r + c) % 2 == 0) {
          Objects.requireNonNull(chessMoves.getCellPaneAt(r, c)).setStyle("-fx-background-color: rgb(238, 238, 210)");
        } else {
          Objects.requireNonNull(chessMoves.getCellPaneAt(r, c)).setStyle("-fx-background-color: rgb(118, 150, 86)");
        }
      }
    }
    System.out.println();
  }
  public void checkCastle() {
    if (board[0][0] != -2) {
      castle.blackLeft = false;
    }
    if (board[0][7] != -2) {
      castle.blackRight = false;
    }
    if (board[7][0] != 2) {
      castle.whiteLeft = false;
    }
    if (board[7][7] != 2) {
      castle.whiteRight = false;
    }
    if (board[0][4] != -6) {
      castle.blackLeft = false;
      castle.blackRight = false;
    }
    if (board[7][4] != 6) {
      castle.whiteLeft = false;
      castle.whiteRight = false;
    }
  }
  public boolean contains(int r, int c) {
    return board[r][c] != 0;
  }
  public void takePiece(int r, int c) {
    if (r == location[0] && c == location[1]) {
      holdingPiece = false;
      resetColors();
    } else if (board[r][c] > 0 && board[location[0]][location[1]] > 0 || board[r][c] < 0 && board[location[0]][location[1]] < 0) {
      holdingPiece = false;
      resetColors();
      pickUpPiece(r, c);
    } else if (board[r][c] < 0 && board[location[0]][location[1]] > 0 && chessMoves.legalMoves[r][c]) {
      System.out.println("White: " + board[r][c]);
      blackCapturedPieces.addCapturedPiece(board[r][c]);
      board[r][c] = board[location[0]][location[1]];
      board[location[0]][location[1]] = 0;

      StackPane destStackPane = getStackPaneAt(chessBoard.boardGrid, r, c);
      ImageView destImageView = getImageViewFromStackPane(Objects.requireNonNull(destStackPane));

      StackPane sourceStackPane = getStackPaneAt(chessBoard.boardGrid, location[0], location[1]);
      ImageView sourceImageView = getImageViewFromStackPane(Objects.requireNonNull(sourceStackPane));

      if (destImageView != null && sourceImageView != null) {
        destImageView.setImage(sourceImageView.getImage());
        sourceImageView.setImage(null);
      }

      if (currentlyWhite) {
        ChessApp.title.setText("Black's Turn");
      } else {
        ChessApp.title.setText("White's Turn");
      }
      currentlyWhite = !currentlyWhite;
    } else if (board[r][c] > 0 && board[location[0]][location[1]] < 0 && chessMoves.legalMoves[r][c]) {
      System.out.println("Black: " + board[r][c]);
      whiteCapturedPieces.addCapturedPiece(board[r][c]);
      board[r][c] = board[location[0]][location[1]];
      board[location[0]][location[1]] = 0;

      StackPane destStackPane = getStackPaneAt(chessBoard.boardGrid, r, c);
      ImageView destImageView = getImageViewFromStackPane(Objects.requireNonNull(destStackPane));

      StackPane sourceStackPane = getStackPaneAt(chessBoard.boardGrid, location[0], location[1]);
      ImageView sourceImageView = getImageViewFromStackPane(Objects.requireNonNull(sourceStackPane));

      if (destImageView != null && sourceImageView != null) {
        destImageView.setImage(sourceImageView.getImage());
        sourceImageView.setImage(null);
      }

      if (currentlyWhite) {
        ChessApp.title.setText("Black's Turn");
      } else {
        ChessApp.title.setText("White's Turn");
      }
      if (attack.attack(r, c)) {
        if (currentlyWhite) {
          ChessApp.title.setText("Black's in check");
        } else {
          ChessApp.title.setText("White's in check");
        }
      }
      currentlyWhite = !currentlyWhite;
    } else {
      System.out.println("You can't take a piece of the same color");
    }
    halfMoves++;
    if (!findKing()) {
      ChessGame.gameOver = true;
      boolean whiteKing = false;
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
          if (board[i][j] == 6) {
            whiteKing = true;
            break;
          }
        }
      }
      if (whiteKing) {
        ChessApp.title.setText("White wins");
      } else {
        ChessApp.title.setText("Black wins");
      }
    }
    resetColors();
  }
  public void movePiece(int r, int c) {
    if (r == location[0] && c == location[1]) {
      holdingPiece = false;
      resetColors();
    } else {
      if (pieceName.equals("wKing") && r == 7 && c == 1 && castle.whiteLeft) {
        displayCastle(r, c, 7, 0, 2);
      } else if (pieceName.equals("wKing") && r == 7 && c == 6 && castle.whiteRight) {
        displayCastle(r, c, 7, 7, 5);
      } else if (pieceName.equals("bKing") && r == 0 && c == 1 && castle.blackLeft) {
        displayCastle(r, c, 0, 0, 2);
      } else if (pieceName.equals("bKing") && r == 0 && c == 6 && castle.blackRight) {
        displayCastle(r, c, 0, 7, 5);
      } else if (board[r][c] == 0 && board[location[0]][location[1]] != 0 && chessMoves.legalMoves[r][c]) {
        if (board[location[0]][location[1]] == 1) {
          if (Math.abs(r - location[0]) == 2) {
            for (int i = 0; i < 8; i++) {
              if (i != c) {
                whiteEnPassant[i] = false;
                continue;
              }
              whiteEnPassant[c] = true;
            }
          }
        } else if (board[location[0]][location[1]] == -1) {
         if (Math.abs(r - location[0]) == 2) {
           for (int i = 0; i < 8; i++) {
             if (i != c) {
               blackEnPassant[i] = false;
               continue;
             }
             blackEnPassant[c] = true;
           }
          }
        }
        if (board[location[0]][location[1]] == 1) {
          if (chessMoves.whiteLeftEP && Math.abs(c - location[1]) == 1) {
            removePawn(r + 1, c);
          } else if (chessMoves.whiteRightEP && Math.abs(c - location[1]) == 1) {
            removePawn(r + 1, c);
          }
        }
        if (board[location[0]][location[1]] == -1) {
          if (chessMoves.blackLeftEP && Math.abs(c - location[1]) == 1) {
            removePawn(r - 1, c);
          } else if (chessMoves.blackRightEP && Math.abs(c - location[1]) == 1) {
            removePawn(r - 1, c);
          }
        }
        board[r][c] = board[location[0]][location[1]];
        board[location[0]][location[1]] = 0;
        StackPane destStackPane = getStackPaneAt(chessBoard.boardGrid, r, c);
        ImageView destImageView = getImageViewFromStackPane(Objects.requireNonNull(destStackPane));
        StackPane sourceStackPane = getStackPaneAt(chessBoard.boardGrid, location[0], location[1]);
        ImageView sourceImageView = getImageViewFromStackPane(Objects.requireNonNull(sourceStackPane));
        if (destImageView != null && sourceImageView != null) {
          destImageView.setImage(sourceImageView.getImage());
          sourceImageView.setImage(null);
        }
        if (currentlyWhite) {
          ChessApp.title.setText("Black's Turn");
        } else {
          ChessApp.title.setText("White's Turn");
        }
        if (attack.attack(r, c)) {
          if (currentlyWhite) {
            ChessApp.title.setText("Black's in check");
          } else {
            ChessApp.title.setText("White's in check");
          }
        }
        halfMoves++;
        currentlyWhite = !currentlyWhite;
      }
      resetColors();
    }
  }
  public void pickUpPiece(int r, int c) {
    switch (board[r][c]) {
      case -2 -> {
        pieceName = "bRook";
        chessMoves.initializeMoves();
        chessMoves.rook(r, c);
      }
      case -3 -> {
        pieceName = "bKnight";
        chessMoves.initializeMoves();
        chessMoves.knight(r, c);
      }
      case -4 -> {
        pieceName = "bBishop";
        chessMoves.initializeMoves();
        chessMoves.bishop(r, c);
      }
      case -5 -> {
        pieceName = "bQueen";
        chessMoves.initializeMoves();
        chessMoves.queen(r, c);
      }
      case -6 -> {
        pieceName = "bKing";
        chessMoves.initializeMoves();
        chessMoves.king(r, c);
        checkCastle();
      }
      case -1 -> {
        pieceName = "bPawn";
        chessMoves.initializeMoves();
        chessMoves.blackPawn(r, c);
      }
      case 2 -> {
        pieceName = "wRook";
        chessMoves.initializeMoves();
        chessMoves.rook(r, c);
      }
      case 3 -> {
        pieceName = "wKnight";
        chessMoves.initializeMoves();
        chessMoves.knight(r, c);
      }
      case 4 -> {
        pieceName = "wBishop";
        chessMoves.initializeMoves();
        chessMoves.bishop(r, c);
      }
      case 5 -> {
        pieceName = "wQueen";
        chessMoves.initializeMoves();
        chessMoves.queen(r, c);
      }
      case 6 -> {
        pieceName = "wKing";
        chessMoves.initializeMoves();
        chessMoves.king(r, c);
        checkCastle();
      }
      case 1 -> {
        pieceName = "wPawn";
        chessMoves.initializeMoves();
        chessMoves.whitePawn(r, c);
      }
    }
    location[0] = r;
    location[1] = c;
    holdingPiece = true;
    System.out.println(pieceName);
    System.out.println(holdingPiece);
    System.out.println(location[0]);
    System.out.println(location[1]);
  }
  public StackPane getStackPaneAt(GridPane grid, int r, int c) {
    for (Node node : grid.getChildren()) {
      if (GridPane.getRowIndex(node) == r && GridPane.getColumnIndex(node) == c && node instanceof StackPane) {
        return (StackPane) node;
      }
    }
    return null;
  }
  public ImageView getImageViewFromStackPane(StackPane sp) {
    for (Node node : sp.getChildren()) {
      if (node instanceof ImageView) {
        return (ImageView) node;
      }
    }
    return null;
  }
  public void removePawn(int r, int c) {
    StackPane epStackPane = getStackPaneAt(chessBoard.boardGrid, r, c);
    ImageView epImageView = getImageViewFromStackPane(Objects.requireNonNull(epStackPane));
    if (epImageView != null) {
      epImageView.setImage(null);
      board[r][c] = 0;
    }
  }
  private void displayCastle(int r, int c, int row, int oldCol, int newCol) {
    StackPane destStackPane = getStackPaneAt(chessBoard.boardGrid, r, c);
    ImageView destImageView = getImageViewFromStackPane(Objects.requireNonNull(destStackPane));

    StackPane sourceStackPane = getStackPaneAt(chessBoard.boardGrid, location[0], location[1]);
    ImageView sourceImageView = getImageViewFromStackPane(Objects.requireNonNull(sourceStackPane));

    if (destImageView != null && sourceImageView != null) {
      destImageView.setImage(sourceImageView.getImage());
      sourceImageView.setImage(null);
    }

    StackPane destStackPaneC = getStackPaneAt(chessBoard.boardGrid, row, newCol);
    ImageView destImageViewC = getImageViewFromStackPane(Objects.requireNonNull(destStackPaneC));

    StackPane sourceStackPaneC = getStackPaneAt(chessBoard.boardGrid, row, oldCol);
    ImageView sourceImageViewC = getImageViewFromStackPane(Objects.requireNonNull(sourceStackPaneC));

    if (destImageViewC != null && sourceImageViewC != null) {
      destImageViewC.setImage(sourceImageViewC.getImage());
      sourceImageViewC.setImage(null);
    }
    board[r][c] = board[location[0]][location[1]];
    board[location[0]][location[1]] = 0;
    board[row][newCol] = board[row][oldCol];
    board[row][oldCol] = 0;
  }
  private boolean findKing() {
    int count = 0;
    for (int r = 0; r < 8; r++) {
      for (int c = 0; c < 8; c++) {
        if (board[r][c] == 6 || board[r][c] == -6) {
          count++;
        }
      }
    }
    return count == 2;
  }
  public void handlePieceSelected(int r, int c) {
    if (!ChessGame.gameOver) {
      if (halfMoves >= 100) {
        ChessGame.gameOver = true;
      } else {
        if (holdingPiece) {
          if (contains(r, c)) {
            if ( (currentlyWhite && board[r][c] > 0) || (!currentlyWhite && board[r][c] < 0)) {
              resetColors();
              pickUpPiece(r, c);
              return;
            } else {
              takePiece(r, c);
            }
          } else {
            movePiece(r, c);
          }
          for (int i = 0; i < 8; i++) {
            if (board[0][i] == 1 && findKing()) {
              promotion.promote(0, i);
            } else if (board[7][i] == -1 && findKing()) {
              promotion.promote(7, i);
            }
          }
          holdingPiece = false;
        } else {
          if (currentlyWhite && board[r][c] < 0 || !currentlyWhite && board[r][c] > 0) {
            System.out.println("It is " + (currentlyWhite ? "white's turn" : "black's turn"));
          } else if (contains(r, c)) {
            pickUpPiece(r , c);
          } else {
            System.out.println("Cannot pick up a piece from an empty tile");
          }
        }
      }
    }
  }
}