package com.example.fxchess;

public class Attacking {
  private final ChessController controller;
  public Attacking(ChessController controller) {
    this.controller = controller;
  }
  public boolean attack(int r, int c) {
    switch (controller.pieceName) {
      case "wRook", "bRook" -> {
        return rook(r, c);
      }
      case "wBishop", "bBishop" -> {
        return bishop(r, c);
      }
      case "wQueen", "bQueen" -> {
        return rook(r, c) || bishop(r, c);
      }
      case "wKnight", "bKnight" -> {
        return knight(r, c);
      }
      case "wPawn", "bPawn" -> {
        return pawn(r, c);
      }
      default -> {
        System.out.println("King can't attack another king");
        return false;
      }
    }
  }
  private boolean rook(int r, int c) {
    return horizontalAttack(r, c) || verticalAttack(r, c);
  }
  private boolean bishop(int r, int c) {
    return rightDiagonalAttack1(r, c) || rightDiagonalAttack2(r, c) ||
            leftDiagonalAttack1(r, c) || leftDiagonalAttack2(r, c);
  }
  private boolean knight(int r, int c) {
    int[][] knightMoves = {
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
    };
    for (int[] move : knightMoves) {
      int newRow = r + move[0];
      int newCol = c + move[1];
      if (0 <= newRow && newRow < 8 && 0 <= newCol && newCol < 8) {
        if (controller.currentlyWhite && controller.board[newRow][newCol] == -6) {
          return true;
        } else if (!controller.currentlyWhite && controller.board[newRow][newCol] == 6) {
          return true;
        }
      }
    }
    return false;
  }
  private boolean pawn(int r, int c) {
    if (controller.currentlyWhite) {
      return (r - 1 >= 0 && c + 1 < 8 && controller.board[r - 1][c + 1] == -6) ||
              (r - 1 >= 0 && c - 1 >= 0 && controller.board[r - 1][c - 1] == -6);
    }
    else {
      return (r + 1 < 8 && c + 1 < 8 && controller.board[r + 1][c + 1] == 6) ||
              (r + 1 < 8 && c - 1 >= 0 && controller.board[r + 1][c - 1] == 6);
    }
  }
  private boolean horizontalAttack(int r, int c) {
    for (int i = c; i < 8; i++) {
      if (controller.currentlyWhite) {
        if (controller.board[r][i] == -6) {
          return true;
        } else if (controller.board[r][i] != 0) {
          return false;
        }
      } else {
        if (controller.board[r][i] == 6) {
          return true;
        } else if (controller.board[r][i] != 0) {
          return false;
        }
      }
    }
    return false;
  }
  private boolean verticalAttack(int r, int c) {
    for (int i = r; i < 8; i++) {
      if (controller.currentlyWhite) {
        if (controller.board[i][c] == -6) {
          return true;
        } else if (controller.board[i][c] != 0) {
          return false;
        }
      } else {
        if (controller.board[i][c] == 6) {
          return true;
        } else if (controller.board[i][c] != 0) {
          return false;
        }
      }
    }
    return false;
  }

  private boolean rightDiagonalAttack1(int r, int c) {
    int i = r + 1;
    int j = c - 1;
    while (i < 8 && j >= 0) {
      if (controller.currentlyWhite) {
        if (controller.board[i][j] == -6) {
          return true;
        } else if (controller.board[i][j] != 0) {
          return false;
        }
      } else {
        if (controller.board[i][j] == 6) {
          return true;
        } else if (controller.board[i][j] != 0) {
          return false;
        }
      }
      i++;
      j--;
    }
    return false;
  }
  private boolean rightDiagonalAttack2(int r, int c) {
    int i = r - 1;
    int j = c + 1;
    while (i >= 0 && j < 8) {
      if (controller.currentlyWhite) {
        if (controller.board[i][j] == -6) {
          return true;
        } else if (controller.board[i][j] != 0) {
          return false;
        }
      } else {
        if (controller.board[i][j] == 6) {
          return true;
        } else if (controller.board[i][j] != 0) {
          return false;
        }
      }
      i--;
      j++;
    }
    return false;
  }
  private boolean leftDiagonalAttack1(int r, int c) {
    int i = r + 1;
    int j = c + 1;
    while (i < 8 && j < 8) {
      if (controller.currentlyWhite) {
        if (controller.board[i][j] == -6) {
          return true;
        } else if (controller.board[i][j] != 0) {
          return false;
        }
      } else {
        if (controller.board[i][j] == 6) {
          return true;
        } else if (controller.board[i][j] != 0) {
          return false;
        }
      }
      i++;
      j++;
    }
    return false;
  }
  private boolean leftDiagonalAttack2(int r, int c) {
    int i = r - 1;
    int j = c - 1;
    while (i >= 0 && j >= 0) {
      System.out.println(controller.board[i][j]);
      if (controller.currentlyWhite) {
        if (controller.board[i][j] == -6) {
          return true;
        } else if (controller.board[i][j] != 0) {
          return false;
        }
      } else {
        if (controller.board[i][j] == 6) {
          return true;
        } else if (controller.board[i][j] != 0) {
          return false;
        }
      }
      i--;
      j--;
    }
    return false;
  }
}