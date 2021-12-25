package pieces;

import board.ChessBoard;
import misc.Coordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Knight Piece and how it moves.
 */
public class Knight extends Piece{

    public Knight(Coordinate coordinates, boolean isWhite) {
        this.coordinates = coordinates;
        this.isWhite = isWhite;
    }

    /**
     * Generate all possible coordinates the knight can move to
     * @param board the current board position (with all the pieces)
     * @return the possible coordinates
     */
    @Override
    public List<Coordinate> generateMoves(ChessBoard board) {
        List<Coordinate> possibleMoves = new ArrayList<>();
        int newY = coordinates.y - 2;
        int newX = coordinates.x - 1;

        Coordinate newCoordinate = new Coordinate(newY, newX);
        if (validateMove(newY, newX, board)) {
            possibleMoves.add(newCoordinate);
        }

        newX = coordinates.x + 1;
        Coordinate newCoordinate2 = new Coordinate(newY, newX);
        if (validateMove(newY, newX, board)) {
            possibleMoves.add(newCoordinate2);
        }

        newY = coordinates.y + 2;
        Coordinate newCoordinate3 = new Coordinate(newY, newX);
        if (validateMove(newY, newX, board)) {
            possibleMoves.add(newCoordinate3);
        }

        newX = coordinates.x - 1;
        Coordinate newCoordinate4 = new Coordinate(newY, newX);
        if (validateMove(newY, newX, board)) {
            possibleMoves.add(newCoordinate4);
        }

        for (Coordinate coordinate: possibleMoves) {
            System.out.println(coordinate.y + ", " + coordinate.x);
        }

        return possibleMoves;
    }

    @Override
    public String toString() {
        if (isWhite) {
            return "♘";
        } else {
            return "♞";
        }
    }

    /**
     * Helper method that validates if a move from the knight can be done
     * @param newY the y coordinate the knight is moving to
     * @param newX the x coordinate the knight is moving to
     * @param board the current board position (with all the pieces)
     * @return if the move is valid
     */
    private boolean validateMove(int newY, int newX, ChessBoard board) {
        boolean fitsVertically = newY <= board.UP_BOUNDARY && newY >= board.DOWN_BOUNDARY;
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
}
