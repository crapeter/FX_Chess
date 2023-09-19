package com.example.fxchess;

public class Check {
  private final ChessController controller;
  public Check(ChessController controller) {
    this.controller = controller;
  }
  public boolean check(int r, int c) {
    boolean vertical = verticalCheck(r, c);
    boolean horizontal = horizontalCheck(r, c);
    boolean rightDiagonal1 = rightDiagonalCheck1(r, c);
    boolean rightDiagonal2 = rightDiagonalCheck2(r, c);
    boolean leftDiagonal1 = leftDiagonalCheck1(r, c);
    boolean leftDiagonal2 = leftDiagonalCheck2(r, c);
    boolean pawn = pawnCheck(r, c);
    boolean knight = knightCheck(r, c);

    return (vertical || horizontal ||
            rightDiagonal1 || rightDiagonal2 ||
            leftDiagonal1 || leftDiagonal2 ||
            pawn || knight);
  }
  public boolean horizontalCheck(int r, int c) {
    for (int i = c; i < 8; i++) {
      if (i == c) continue;
      if (controller.currentlyWhite) {
        if (controller.board[r][i] == -5 || controller.board[r][i] == -2) {
          return true;
        } else if (controller.board[r][i] != 0) {
          return false;
        }
      } else {
        if (controller.board[r][i] == 5 || controller.board[r][i] == 2) {
          return true;
        } else if (controller.board[r][i] != 0) {
          return false;
        }
      }
    }
    return false;
  }
  public boolean verticalCheck(int r, int c) {
    for (int i = r; i < 8; i++) {
      if (i == r) continue;
      if (controller.currentlyWhite) {
        if (controller.board[i][c] == -5 || controller.board[i][c] == -2) {
          return true;
        } else if (controller.board[i][c] != 0) {
          return false;
        }
      } else {
        if (controller.board[i][c] == 5 || controller.board[i][c] == 2) {
          return true;
        } else if (controller.board[i][c] != 0) {
          return false;
        }
      }
    }
    return false;
  }
  public boolean rightDiagonalCheck1(int r, int c) {
    int i = r + 1;
    int j = c - 1;
    while (i < 8 && j >= 0) {
      if (controller.currentlyWhite) {
        if (controller.board[i][j] == -5 || controller.board[i][j] == -4) {
          return true;
        } else if (controller.board[i][j] != 0) {
          return false;
        }
      } else {
        if (controller.board[i][j] == 5 || controller.board[i][j] == 4) {
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
  public boolean rightDiagonalCheck2(int r, int c) {
    int i = r - 1;
    int j = c + 1;
    while (i >= 0 && j < 8) {
      if (controller.currentlyWhite) {
        if (controller.board[i][j] == -5 || controller.board[i][j] == -4) {
          return true;
        } else if (controller.board[i][j] != 0) {
          return false;
        }
      } else {
        if (controller.board[i][j] == 5 || controller.board[i][j] == 4) {
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

  public boolean leftDiagonalCheck1(int r, int c) {
    int i = r + 1;
    int j = c + 1;
    while (i < 8 && j < 8) {
      if (controller.currentlyWhite) {
        if (controller.board[i][j] == -5 || controller.board[i][j] == -4) {
          return true;
        } else if (controller.board[i][j] != 0) {
          return false;
        }
      } else {
        if (controller.board[i][j] == 5 || controller.board[i][j] == 4) {
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
  public boolean leftDiagonalCheck2(int r, int c) {
    int i = r - 1;
    int j = c - 1;
    while (i >= 0 && j >= 0) {
      System.out.println(controller.board[i][j]);
      if (controller.currentlyWhite) {
        if (controller.board[i][j] == -5 || controller.board[i][j] == -4) {
          return true;
        } else if (controller.board[i][j] != 0) {
          return false;
        }
      } else {
        if (controller.board[i][j] == 5 || controller.board[i][j] == 4) {
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
  public boolean pawnCheck(int r, int c) {
    if (controller.currentlyWhite) {
      return (r - 1 >= 0 && c + 1 < 8 && controller.board[r - 1][c + 1] == -1) ||
              (r - 1 >= 0 && c - 1 >= 0 && controller.board[r - 1][c - 1] == -1);
    }
    else {
      return (r + 1 < 8 && c + 1 < 8 && controller.board[r + 1][c + 1] == 1) ||
              (r + 1 < 8 && c - 1 >= 0 && controller.board[r + 1][c - 1] == 1);
    }
  }
  public boolean knightCheck(int r, int c) {
    int[][] knightMoves = {
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
    };
    for (int[] move : knightMoves) {
      int newRow = r + move[0];
      int newCol = c + move[1];
      if (0 <= newRow && newRow < 8 && 0 <= newCol && newCol < 8) {
        if (controller.currentlyWhite && controller.board[newRow][newCol] == -3) {
          return true;
        } else if (!controller.currentlyWhite && controller.board[newRow][newCol] == 3) {
          return true;
        }
      }
    }
    return false;
  }
}