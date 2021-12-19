package board;

import misc.Coordinate;
import pieces.*;

/**
 * Represents the Chess Board. Contains all the pieces, their positions, and contains methods
 * calculating check, checkmate, stalemate etc.
 */
public class ChessBoard {
    private final int LEFT_BOUNDARY = 0;
    private final int RIGHT_BOUNDARY = 7;
    private final int UP_BOUNDARY = 0;
    private final int DOWN_BOUNDARY = 7;

    private Piece[][] board;
    private boolean isCurrentPlayerWhite;

    /**
     * Initialize the board
     */
    public ChessBoard() {
        board = new Piece[8][8];
        isCurrentPlayerWhite = true;

        // set black position
        board[0][0] = new Rook(new Coordinate(0, 0), false);
        board[0][1] = new Knight(new Coordinate(0, 1), false);
        board[0][2] = new Bishop(new Coordinate(0, 2), false);
        board[0][3] = new King(new Coordinate(0, 3), false);
        board[0][4] = new Queen(new Coordinate(0, 4), false);
        board[0][5] = new Bishop(new Coordinate(0, 5), false);
        board[0][6] = new Knight(new Coordinate(0, 6), false);
        board[0][7] = new Rook(new Coordinate(0, 7), false);

        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(new Coordinate(1, i), false);
        }

        // set middle board
        for (int i = 2; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = null;
            }
        }

        // set white position
        board[7][0] = new Rook(new Coordinate(7, 0), true);
        board[7][1] = new Knight(new Coordinate(7, 1), true);
        board[7][2] = new Bishop(new Coordinate(7, 2), true);
        board[7][3] = new King(new Coordinate(7, 3), true);
        board[7][4] = new Queen(new Coordinate(7, 4), true);
        board[7][5] = new Bishop(new Coordinate(7, 5), true);
        board[7][6] = new Knight(new Coordinate(7, 6), true);
        board[7][7] = new Rook(new Coordinate(7, 7), true);

        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(new Coordinate(6, i), true);
        }
    }

    public boolean updateBoard(Coordinate moveFrom, Coordinate moveTo) {
        return true;
    }

    public boolean checkmate() {
        return true;
    }

    public Piece[][] getBoard() {
        return board;
    }

    // ONLY USE FOR TESTS
    public void setBoard(Piece[][] board) {
        this.board = board;
    }

    public boolean currentPlayerInCheck() {
        return true;
    }

    private boolean otherPlayerInCheck() {
        return true;
    }
}
