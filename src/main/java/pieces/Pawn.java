package pieces;

import board.ChessBoard;
import misc.Coordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Pawn Piece and how it moves.
 */
public class Pawn extends Piece{

    public Pawn(Coordinate coordinates, boolean isWhite) {
        this.coordinates = coordinates;
        this.isWhite = isWhite;
    }

    /**
     * Generate all possible coordinates the pawn can move to
     * @param board the current board position (with all the pieces)
     * @return the possible coordinates
     */
    @Override
    public List<Coordinate> generateMoves(ChessBoard board) {
        List<Coordinate> possibleMoves = new ArrayList<>();

        int direction;
        if (isWhite) {
            direction = -1;
        } else {
            direction = 1;
        }

        int newY = coordinates.y + direction;

        // front 3 squares
        for (int i = -1; i <= 1; i++) {
            int newX = coordinates.x + i;

            if (validateMove(newY, newX, board)) {
                Coordinate newCoordinate = new Coordinate(newY, newX);
                possibleMoves.add(newCoordinate);
            }
        }

        // square 2 ahead
        newY = coordinates.y + direction + direction;
        int newX = coordinates.x;

        if (validateMove(newY, newX, board)) {
            Coordinate newCoordinate = new Coordinate(newY, newX);
            possibleMoves.add(newCoordinate);
        }

        return possibleMoves;
    }

    /**
     * Helper method that validates if a move from the pawn can be done
     * @param newY the y coordinate the pawn is moving to
     * @param newX the x coordinate the pawn is moving to
     * @param board the current board position (with all the pieces)
     * @return if the move is valid
     */
    private boolean validateMove(int newY, int newX, ChessBoard board) {
        boolean fitsVertically = newY >= board.UP_BOUNDARY && newY <= board.DOWN_BOUNDARY;
        boolean fitsHorizontally = newX <= board.RIGHT_BOUNDARY && newX >= board.LEFT_BOUNDARY;

        if (!(fitsVertically && fitsHorizontally)) {
            return false;
        }

        int direction;
        if (isWhite) {
            direction = -1;
        } else {
            direction = 1;
        }

        // front square and 2 squares ahead
        boolean squareIsOpen = board.getBoard()[newY][newX] == null;
        if (newX == coordinates.x) {

            if (newY == coordinates.y + direction + direction) {
                boolean prevSquareIsOpen = board.getBoard()[newY+1][newX] == null;

                boolean onStartingPosition;
                if (isWhite) {
                    onStartingPosition = coordinates.y == 6;
                } else {
                    onStartingPosition = coordinates.y == 1;
                }

                return squareIsOpen && prevSquareIsOpen && onStartingPosition;

            } else {
                return squareIsOpen;
            }

        // diagonal squares
        } else {
            if (squareIsOpen) {
                return false;

            } else {
                Piece piece = board.getBoard()[newY][newX];
                boolean isEnemyPiece = piece.getIsWhite() != isWhite;
                return isEnemyPiece;
            }
        }
    }

    @Override
    public String toString() {
        if (isWhite) {
            return "♙";
        } else {
            return "♟";
        }
    }
}
