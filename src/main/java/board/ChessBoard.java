package board;

import misc.Coordinate;
import pieces.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Chess Board. Contains all the pieces, their positions, and contains methods
 * calculating check, checkmate, stalemate etc.
 */
public class ChessBoard {
    public final int LEFT_BOUNDARY = 0;
    public final int RIGHT_BOUNDARY = 7;
    public final int UP_BOUNDARY = 0;
    public final int DOWN_BOUNDARY = 7;

    private Piece[][] board;
    private List<Piece> whitePieces;
    private List<Piece> blackPieces;
    private List<Piece> enemyPieces;
    private List<Piece> friendlyPieces;

    private boolean isCurrentPlayerWhite;

    public ChessBoard() {
        setInitialBoard();
    }

    /**
     * Updates the board state by moving a piece. Returns true if move is valid, false otherwise
     * @param moveFrom Coordinate of the piece that is going to be moved
     * @param moveTo New coordinate piece is being moved to
     * @return true if move is valid and board updated, false otherwise
     */
    public boolean updateBoard(Coordinate moveFrom, Coordinate moveTo) {
        // tyring to move a piece not on the board
        boolean verticalOutOfBounds = moveFrom.y < UP_BOUNDARY || moveFrom.y > DOWN_BOUNDARY;
        boolean horizontalOutOfBounds = moveFrom.x < LEFT_BOUNDARY || moveFrom.x > RIGHT_BOUNDARY;

        if (verticalOutOfBounds || horizontalOutOfBounds) {
            return false;
        }

        // trying to move an empty piece
        if (board[moveFrom.y][moveFrom.x] == null) {
            return false;
        }

        // trying to move enemy piece
        Piece selectedPiece = board[moveFrom.y][moveFrom.x];
        if (isCurrentPlayerWhite != selectedPiece.getIsWhite()) {
            return  false;
        }

        // not a valid move
        List<Coordinate> possibleMoves = selectedPiece.generateMoves(this);
        if (possibleMoves.isEmpty() || !possibleMoves.contains(moveTo))  {
            return false;
        }

        // save copy of previous board state
        Piece[][] previousBoardState = cloneCurrentBoard();
        List<Piece> previousWhitePieces = new ArrayList<>(whitePieces);
        List<Piece> previousBlackPieces = new ArrayList<>(blackPieces);
        Coordinate lastPosition = selectedPiece.getLastPosition();

        setFriendlyAndEnemyPieces();

        // preform the move
        if (board[moveTo.y][moveTo.x] != null) {
            enemyPieces.remove(board[moveTo.y][moveTo.x]);

        } else if (selectedPiece instanceof Pawn) {

            // move is en passant (pawn diagonal move where there is no enemy piece)
            if (moveFrom.x != moveTo .x) {
                enemyPieces.remove(board[moveFrom.y][moveTo.x]);
                board[moveFrom.y][moveTo.x] = null;
            }
        }

        board[moveTo.y][moveTo.x] = selectedPiece;
        board[moveFrom.y][moveFrom.x] = null;
        selectedPiece.setCoordinates(new Coordinate(moveTo.y, moveTo.x));
        if (selectedPiece instanceof Pawn) {
            selectedPiece.setLastPosition(moveFrom);
        }

        // see if the player is in check after said move (which is invalid), revert to previous
        // board state
        if (currentPlayerInCheck()) {
            board = previousBoardState;
            whitePieces = previousWhitePieces;
            blackPieces = previousBlackPieces;
            selectedPiece.setCoordinates(new Coordinate(moveFrom.y, moveFrom.x));
            selectedPiece.setLastPosition(lastPosition);
            return false;
        }

        isCurrentPlayerWhite = !isCurrentPlayerWhite;

        return true;
    }

    /**
     * Initialize the board
     */
    public void setInitialBoard() {
        board = new Piece[8][8];
        isCurrentPlayerWhite = true;
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();

        // set black position
        board[0][0] = new Rook(new Coordinate(0, 0), false);
        board[0][1] = new Knight(new Coordinate(0, 1), false);
        board[0][2] = new Bishop(new Coordinate(0, 2), false);
        board[0][3] = new Queen(new Coordinate(0, 3), false);
        board[0][4] = new King(new Coordinate(0, 4), false);
        board[0][5] = new Bishop(new Coordinate(0, 5), false);
        board[0][6] = new Knight(new Coordinate(0, 6), false);
        board[0][7] = new Rook(new Coordinate(0, 7), false);

        for (int i = 0; i < 8; i++) {
            blackPieces.add(board[0][i]);
        }

        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(new Coordinate(1, i), false);
            blackPieces.add(board[1][i]);
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
        board[7][3] = new Queen(new Coordinate(7, 3), true);
        board[7][4] = new King(new Coordinate(7, 4), true);
        board[7][5] = new Bishop(new Coordinate(7, 5), true);
        board[7][6] = new Knight(new Coordinate(7, 6), true);
        board[7][7] = new Rook(new Coordinate(7, 7), true);

        for (int i = 0; i < 8; i++) {
            whitePieces.add(board[7][i]);
        }

        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(new Coordinate(6, i), true);
            whitePieces.add(board[6][i]);
        }
    }

    /**
     * Determines if the current player is in check
     * @return true if current player in check, false otherwise
     */
    public boolean currentPlayerInCheck() {
        setFriendlyAndEnemyPieces();

        // there should always be a king. This declaration is just placeholder
        Piece king = new King(new Coordinate(0,0), true);
        for (Piece piece: friendlyPieces) {
            if (piece instanceof King) {
                king = piece;
                break;
            }
        }

        // check every enemy pieces' moves. If the king is in one of the possible moves,
        // the king is currently in check
        for (Piece piece : enemyPieces) {
            List<Coordinate> possibleMoves = piece.generateMoves(this);

            for (Coordinate move: possibleMoves) {

                if (move.equals(king.getCoordinates())) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Determines if the current player has no legal moves
     * @return true if the current player has no legal moves, false otherwise
     */
    public boolean noLegalMoves() {
        setFriendlyAndEnemyPieces();

        for (Piece friendlyPiece: friendlyPieces) {
            List<Coordinate> possibleMoves = friendlyPiece.generateMoves(this);

            for (Coordinate moveTo: possibleMoves) {

                // save copy of previous board state
                Piece[][] previousBoardState = cloneCurrentBoard();
                List<Piece> previousWhitePieces = new ArrayList<>(whitePieces);
                List<Piece> previousBlackPieces = new ArrayList<>(blackPieces);


                // preform the move
                if (board[moveTo.y][moveTo.x] != null) {
                    enemyPieces.remove(board[moveTo.y][moveTo.x]);
                }

                Coordinate moveFrom = friendlyPiece.getCoordinates();

                board[moveTo.y][moveTo.x] = friendlyPiece;
                board[moveFrom.y][moveFrom.x] = null;
                friendlyPiece.setCoordinates(new Coordinate(moveTo.y, moveTo.x));

                // see if the player is in check after said move and revert to previous
                // board state
                boolean inCheck = currentPlayerInCheck();
                board = previousBoardState;
                whitePieces = previousWhitePieces;
                blackPieces = previousBlackPieces;
                friendlyPiece.setCoordinates(new Coordinate(moveFrom.y, moveFrom.x));

                // if a move is possible where the player is NOT check, then player is NOT in checkmate
                if (!inCheck) {
                    return false;
                }
            }
        }

        return true;
    }

    public Piece[][] getBoard() {
        return board;
    }

    public boolean getIsCurrentPlayerWhite() {
        return isCurrentPlayerWhite;
    }

    // ONLY USE FOR TESTS
    public void setBoard(Piece[][] board) {
        this.board = board;
    }

    // ONLY USE FOR TESTS
    public void clearBoard() {
         for (int i = 0; i <= DOWN_BOUNDARY; i++) {
             for (int j = 0; j <= RIGHT_BOUNDARY; j++) {
                 board[i][j] = null;
             }
         }
    }

    private Piece[][] cloneCurrentBoard() {
        Piece[][] copiedBoard = new Piece[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                copiedBoard[i][j] = board[i][j];
            }
        }

        return copiedBoard;
    }

    private void setFriendlyAndEnemyPieces() {
        if (isCurrentPlayerWhite) {
            enemyPieces = blackPieces;
            friendlyPieces = whitePieces;

        } else {
            enemyPieces = whitePieces;
            friendlyPieces = blackPieces;
        }

    }
}
