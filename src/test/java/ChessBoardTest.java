import board.ChessBoard;
import misc.Coordinate;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChessBoardTest {

    @Test
    public void updateBoardTest() {
        // invalid moves for white (impossible move, out of bounds, moving black piece,
        // choosing empty square)
        ChessBoard chessBoard = new ChessBoard();
        assertFalse(chessBoard.updateBoard(new Coordinate(6, 0), new Coordinate(6, 3)));
        assertFalse(chessBoard.updateBoard(new Coordinate(8, 0), new Coordinate(8, 2)));
        assertFalse(chessBoard.updateBoard(new Coordinate(1, 4), new Coordinate(2, 4)));
        assertFalse(chessBoard.updateBoard(new Coordinate(3, 1), new Coordinate(4, 1)));

        // valid moves for white + black (back and forth)
        assertTrue(chessBoard.updateBoard(new Coordinate(6, 0), new Coordinate(4, 0)));
        assertTrue(chessBoard.updateBoard(new Coordinate(1, 4), new Coordinate(3, 4)));
        assertTrue(chessBoard.updateBoard(new Coordinate(7, 6), new Coordinate(5, 5)));
        assertTrue(chessBoard.updateBoard(new Coordinate(0, 1), new Coordinate(2, 2)));
        assertTrue(chessBoard.updateBoard(new Coordinate(5, 5), new Coordinate(3, 4)));

        // invalid moves for black
        assertFalse(chessBoard.updateBoard(new Coordinate(6, 0), new Coordinate(6, 3)));
        assertFalse(chessBoard.updateBoard(new Coordinate(8, 0), new Coordinate(8, 2)));
        assertFalse(chessBoard.updateBoard(new Coordinate(1, 4), new Coordinate(2, 4)));
        assertFalse(chessBoard.updateBoard(new Coordinate(3, 1), new Coordinate(4, 1)));

        // cannot do move that puts player into check
        chessBoard.setInitialBoard();
        assertTrue(chessBoard.updateBoard(new Coordinate(6, 4), new Coordinate(4, 4)));
        assertTrue(chessBoard.updateBoard(new Coordinate(1, 4), new Coordinate(3, 4)));
        assertTrue(chessBoard.updateBoard(new Coordinate(7, 5), new Coordinate(4, 2)));
        assertTrue(chessBoard.updateBoard(new Coordinate(0, 1), new Coordinate(2, 2)));
        assertTrue(chessBoard.updateBoard(new Coordinate(7, 3), new Coordinate(3, 7)));
        assertFalse(chessBoard.updateBoard(new Coordinate(1, 5), new Coordinate(2, 5)));

        // cannot do move that doesn't remove the check
        assertTrue(chessBoard.updateBoard(new Coordinate(2, 2), new Coordinate(4, 1)));
        assertTrue(chessBoard.updateBoard(new Coordinate(3, 7), new Coordinate(3, 4)));
        assertFalse(chessBoard.updateBoard(new Coordinate(4, 1), new Coordinate(3, 3)));
        assertFalse(chessBoard.updateBoard(new Coordinate(1, 3), new Coordinate(2, 3)));
        assertFalse(chessBoard.updateBoard(new Coordinate(0, 6), new Coordinate(2, 5)));
        assertTrue(chessBoard.updateBoard(new Coordinate(0, 3), new Coordinate(1, 4)));
    }

    @Test
    public void checkmateTest() {
        ChessBoard chessBoard = new ChessBoard();

        // normal board state (false)
        chessBoard.updateBoard(new Coordinate(6, 4), new Coordinate(4,4));
        assertFalse(chessBoard.checkmate());

        chessBoard.updateBoard(new Coordinate(1, 4), new Coordinate(3,4));
        assertFalse(chessBoard.checkmate());

        chessBoard.updateBoard(new Coordinate(7, 5), new Coordinate(4,2));
        assertFalse(chessBoard.checkmate());

        chessBoard.updateBoard(new Coordinate(0, 1), new Coordinate(2,2));
        assertFalse(chessBoard.checkmate());

        chessBoard.updateBoard(new Coordinate(7, 3), new Coordinate(3,7));
        assertFalse(chessBoard.checkmate());

        chessBoard.updateBoard(new Coordinate(0, 6), new Coordinate(2,5));
        assertFalse(chessBoard.checkmate());

        // in check and check cannot be removed (true)
        chessBoard.updateBoard(new Coordinate(3, 7), new Coordinate(1,5));
        assertTrue(chessBoard.checkmate());

        // when in check but possible moves to get rid of check (false)
        chessBoard = new ChessBoard();
        chessBoard.updateBoard(new Coordinate(6, 4), new Coordinate(4,4));
        chessBoard.updateBoard(new Coordinate(1, 4), new Coordinate(3,4));
        chessBoard.updateBoard(new Coordinate(7, 3), new Coordinate(3,7));
        chessBoard.updateBoard(new Coordinate(0, 1), new Coordinate(2,2));
        chessBoard.updateBoard(new Coordinate(3, 7), new Coordinate(1,5));
        assertFalse(chessBoard.checkmate()); // king can take the queen

    }
}
