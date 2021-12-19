package pieces;

import board.ChessBoard;
import misc.Coordinate;

import java.util.List;

/**
 * Represents the Pawn Piece and how it moves.
 */
public class Pawn extends Piece{

    public Pawn(Coordinate coordinates, boolean isWhite) {
        this.coordinates = coordinates;
        this.isWhite = isWhite;
    }

    @Override
    public List<Coordinate> generateMoves(ChessBoard board) {
        return null;
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
