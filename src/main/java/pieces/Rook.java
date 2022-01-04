package pieces;

import board.ChessBoard;
import misc.Coordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Rook Piece and how it moves.
 */
public class Rook extends Piece{

    public Rook(Coordinate coordinates, boolean isWhite) {
        this.coordinates = coordinates;
        this.isWhite = isWhite;
    }

    /**
     * Generate all possible coordinates the rook can move to
     * @param board the current board position (with all the pieces)
     * @return the possible coordinates
     */
    @Override
    public List<Coordinate> generateMoves(ChessBoard board) {
        List<Coordinate> possibleMoves = new ArrayList<>();

        // left direction
        int newY = coordinates.y;
        int newX = coordinates.x - 1;

        while (validateMove(newY, newX, board)) {
            Coordinate coordinate = new Coordinate(newY, newX);
            possibleMoves.add(coordinate);

            // stop loop if piece is blocking
            if (board.getBoard()[newY][newX] != null) {
                break;
            }

            newX--;
        }

        // up direction
        newY = coordinates.y - 1;
        newX = coordinates.x;

        while (validateMove(newY, newX, board)) {
            Coordinate coordinate = new Coordinate(newY, newX);
            possibleMoves.add(coordinate);

            // stop loop if piece is blocking
            if (board.getBoard()[newY][newX] != null) {
                break;
            }

            newY--;
        }

        // right direction
        newY = coordinates.y;
        newX = coordinates.x + 1;

        while (validateMove(newY, newX, board)) {
            Coordinate coordinate = new Coordinate(newY, newX);
            possibleMoves.add(coordinate);

            // stop loop if piece is blocking
            if (board.getBoard()[newY][newX] != null) {
                break;
            }

            newX++;
        }

        // down direction
        newY = coordinates.y + 1;
        newX = coordinates.x;

        while (validateMove(newY, newX, board)) {
            Coordinate coordinate = new Coordinate(newY, newX);
            possibleMoves.add(coordinate);

            // stop loop if piece is blocking
            if (board.getBoard()[newY][newX] != null) {
                break;
            }

            newY++;
        }

        return possibleMoves;
    }

    /**
     * Helper method that validates if a move from the rook can be done
     * @param newY the y coordinate the rook is moving to
     * @param newX the x coordinate the rook is moving to
     * @param board the current board position (with all the pieces)
     * @return if the move is valid
     */
    private boolean validateMove(int newY, int newX, ChessBoard board) {
        boolean fitsVertically = newY >= board.UP_BOUNDARY && newY <= board.DOWN_BOUNDARY;
        boolean fitsHorizontally = newX <= board.RIGHT_BOUNDARY && newX >= board.LEFT_BOUNDARY;

        if (!(fitsVertically && fitsHorizontally)) {
            return false;
        }

        boolean squareIsOpen = board.getBoard()[newY][newX] == null;
        if (squareIsOpen) {
            return true;

        } else {
            Piece piece = board.getBoard()[newY][newX];
            boolean isEnemyPiece = piece.getIsWhite() != isWhite;
            return isEnemyPiece;
        }
    }


    @Override
    public String toString() {
        if (isWhite) {
            return "♖";
        } else {
            return "♜";
        }
    }
}
