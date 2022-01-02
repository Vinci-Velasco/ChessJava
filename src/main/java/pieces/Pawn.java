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
        int newY = coordinates.y - 1;

        for (int i = -1; i <= 1; i++) {
            int newX = coordinates.x + i;

            if (validateMove(newY, newX, board)) {
                Coordinate newCoordinate = new Coordinate(newY, newX);
                possibleMoves.add(newCoordinate);
            }
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

        // front square
        boolean squareIsOpen = board.getBoard()[newY][newX] == null;
        if (newX == coordinates.x) {
            return squareIsOpen;

        // diagonal squares
        } else {
            if (squareIsOpen) {
                return false;

            } else {
                Piece piece = board.getBoard()[newY][newX];
                boolean enemyPiece = piece.getIsWhite() != isWhite;
                return enemyPiece;
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
