import board.ChessBoard;
import misc.Coordinate;
import org.junit.Test;
import pieces.King;
import pieces.Pawn;
import pieces.Piece;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KingTest {
    private ChessBoard chessBoard = new ChessBoard();

    @Test
    public void generateMovesTest() {
        Piece[][] board = chessBoard.getBoard();

        // from starting position (0 possible moves)
        Piece king = board[7][4];
        List<Coordinate> possibleMoves = king.generateMoves(chessBoard);
        assertEquals(0, possibleMoves.size());

        // some spots are open (no enemies)
        board[6][3] = null;
        board[6][4] = null;
        board[6][5] = null;
        chessBoard.setBoard(board);
        possibleMoves = king.generateMoves(chessBoard);
        assertEquals(3, possibleMoves.size());

        Coordinate coordinate1 = new Coordinate(6, 3);
        Coordinate coordinate2 = new Coordinate(6, 4);
        Coordinate coordinate3 = new Coordinate(6, 5);
        assertTrue(possibleMoves.get(0).equals(coordinate1));
        assertTrue(possibleMoves.get(1).equals(coordinate2));
        assertTrue(possibleMoves.get(2).equals(coordinate3));

        // some spots are open (some enemies)
        board[6][4] = new Pawn(new Coordinate(6,4), false);
        chessBoard.setBoard(board);
        possibleMoves = king.generateMoves(chessBoard);
        assertEquals(3, possibleMoves.size());
        assertTrue(possibleMoves.get(0).equals(coordinate1));
        assertTrue(possibleMoves.get(1).equals(coordinate2));
        assertTrue(possibleMoves.get(2).equals(coordinate3));

        // when all spaces are open (8 should be generated)
        chessBoard.clearBoard();
        board = chessBoard.getBoard();
        board[4][4] = new King(new Coordinate(4,4), true);
        king = board[4][4];
        chessBoard.setBoard(board);
        possibleMoves = king.generateMoves(chessBoard);
        assertEquals(8, possibleMoves.size());

        coordinate1.setCoordinates(3,3);
        coordinate2.setCoordinates(3,4);
        coordinate3.setCoordinates(3,5);
        Coordinate coordinate4 = new Coordinate(4,5);
        Coordinate coordinate5 = new Coordinate(5,5);
        Coordinate coordinate6 = new Coordinate(5,4);
        Coordinate coordinate7 = new Coordinate(5,3);
        Coordinate coordinate8 = new Coordinate(4,3);
        assertTrue(possibleMoves.get(0).equals(coordinate1));
        assertTrue(possibleMoves.get(1).equals(coordinate2));
        assertTrue(possibleMoves.get(2).equals(coordinate3));
        assertTrue(possibleMoves.get(3).equals(coordinate4));
        assertTrue(possibleMoves.get(4).equals(coordinate5));
        assertTrue(possibleMoves.get(5).equals(coordinate6));
        assertTrue(possibleMoves.get(6).equals(coordinate7));
        assertTrue(possibleMoves.get(7).equals(coordinate8));

    }
}
