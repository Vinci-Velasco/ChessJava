package pieces;

import board.ChessBoard;
import misc.Coordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the King Piece and how it moves.
 */
public class King extends Piece{

    public King(Coordinate coordinates, boolean isWhite) {
        this.coordinates = coordinates;
        this.isWhite = isWhite;
    }

    /**
     * Generate all possible coordinates the king can move to
     * @param board the current board position (with all the pieces)
     * @return the possible coordinates
     */
    @Override
    public List<Coordinate> generateMoves(ChessBoard board) {
        List<Coordinate> possibleMoves = new ArrayList<>();
        int newY = coordinates.y - 1;;
        int newX;

        // top row squares
        for (int i = -1; i <= 1; i++) {
            newX = coordinates.x + i;
            Coordinate upperCoordinate = new Coordinate(newY, newX);

            if (validateMove(newY, newX, board)) {
                possibleMoves.add(upperCoordinate);
            }
        }

        // middle right square
        newY = coordinates.y;
        newX = coordinates.x + 1;
        Coordinate rightCoordinate = new Coordinate(newY, newX);

        if (validateMove(newY, newX, board)) {
            possibleMoves.add(rightCoordinate);
        }

        // bottom row squares
        newY = coordinates.y + 1;;

        // top row squares
        for (int i = 1; i >= -1; i--) {
            newX = coordinates.x + i;
            Coordinate lowerCoordinate = new Coordinate(newY, newX);

            if (validateMove(newY, newX, board)) {
                possibleMoves.add(lowerCoordinate);
            }
        }

        // middle left square
        newY = coordinates.y;
        newX = coordinates.x - 1;
        Coordinate leftCoordinate = new Coordinate(newY, newX);

        if (validateMove(newY, newX, board)) {
            possibleMoves.add(leftCoordinate);
        }

        return possibleMoves;
    }

    /**
     * Helper method that validates if a move from the king can be done
     * @param newY the y coordinate the knight is moving to
     * @param newX the x coordinate the knight is moving to
     * @param board the current board position (with all the pieces)
     * @return if the move is valid
     */
    private boolean validateMove(int newY, int newX, ChessBoard board) {
        boolean fitsVertically = newY >= board.UP_BOUNDARY && newY <= board.DOWN_BOUNDARY;
        boolean fitsHorizontally = newX <= board.RIGHT_BOUNDARY && newX >= board.LEFT_BOUNDARY;

        if (!(fitsVertically && fitsHorizontally)) {
            return false;
        }

        boolean notFriendly; // either empty space or enemy piece if true

        Piece piece = board.getBoard()[newY][newX];

        if (piece == null) {
            notFriendly = true;
        } else {
            notFriendly = piece.getIsWhite() != isWhite;
        }

        // move should be ok if empty space or on enemy piece
        return notFriendly;
    }

    @Override
    public String toString() {
        if (isWhite) {
            return "♔";
        } else {
            return "♚";
        }
    }
}
