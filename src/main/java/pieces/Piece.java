package pieces;

import board.ChessBoard;
import misc.Coordinate;

import java.util.List;

/**
 * Represents a chess piece
 */
public abstract class Piece {
    protected boolean isWhite;
    protected Coordinate coordinates;
    protected Coordinate lastPosition; // ONLY USED FOR PAWNS (en passant specifically)

    /**
     * Generates all possible moves the piece can make given its position
     * and the current board position
     * @param board the current board position (with all the pieces)
     * @return all the coordinates the piece can move to
     */
    public abstract List<Coordinate> generateMoves(ChessBoard board);

    public boolean getIsWhite() {
        return isWhite;
    }

    public Coordinate getCoordinates() {return  coordinates;}

    public Coordinate getLastPosition() {return lastPosition;}

    public void setCoordinates(Coordinate coordinates) {this.coordinates = coordinates;}

    public void setLastPosition(Coordinate lastPosition) {this.lastPosition = lastPosition;}
}
