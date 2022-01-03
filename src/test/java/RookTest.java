import board.ChessBoard;
import misc.Coordinate;
import org.junit.Test;
import pieces.Piece;
import pieces.Rook;

import java.util.List;

import static org.junit.Assert.*;

public class RookTest {
    private ChessBoard chessBoard = new ChessBoard();

    @Test
    public void generateMovesTest() {
        Piece[][] board = chessBoard.getBoard();

        // blocked on all 4 directions (0 moves)
        Piece rook = board[7][0];
        List<Coordinate> possibleMoves = rook.generateMoves(chessBoard);
        assertEquals(0, possibleMoves.size());

        // 1 direction open (up direction)
        board[6][0] = null;
        chessBoard.setBoard(board);
        possibleMoves = rook.generateMoves(chessBoard);
        assertEquals(6, possibleMoves.size());

        for (int i = 1; i <= 6; i++) {
            Coordinate coordinate = new Coordinate(7 - i, 0);
            assertTrue(possibleMoves.get(i - 1).equals(coordinate));
        }


        // 2 directions open (up and left)
        board[5][7] = new Rook(new Coordinate(5,7), true);
        rook = board[5][7];
        chessBoard.setBoard(board);
        possibleMoves = rook.generateMoves(chessBoard);
        assertEquals(11, possibleMoves.size());

        for (int i = 1; i <= 7; i++) {
            Coordinate coordinate = new Coordinate(5, 7 - i);
            assertTrue(possibleMoves.get(i - 1).equals(coordinate));
        }

        for (int i = 1; i <= 4; i++) {
            Coordinate coordinate = new Coordinate(5 - i, 7);
            assertTrue(possibleMoves.get(i + 6).equals(coordinate));
        }

        // 3 directions open
        board[6][7] = null;
        board[7][7] = null;
        chessBoard.setBoard(board);
        possibleMoves = rook.generateMoves(chessBoard);
        assertEquals(13, possibleMoves.size());

        for (int i = 1; i <= 7; i++) {
            Coordinate coordinate = new Coordinate(5, 7 - i);
            assertTrue(possibleMoves.get(i - 1).equals(coordinate));
        }

        for (int i = 1; i <= 4; i++) {
            Coordinate coordinate = new Coordinate(5 - i, 7);
            assertTrue(possibleMoves.get(i + 6).equals(coordinate));
        }

        Coordinate coordinate1 = new Coordinate(6,7);
        Coordinate coordinate2 = new Coordinate(7,7);
        assertTrue(possibleMoves.get(11).equals(coordinate1));
        assertTrue(possibleMoves.get(12).equals(coordinate2));

        // all 4 directions open
        chessBoard.clearBoard();
        board = chessBoard.getBoard();
        board[5][6] = new Rook(new Coordinate(5, 6), true);
        rook = board[5][6];
        chessBoard.setBoard(board);
        possibleMoves = rook.generateMoves(chessBoard);
        assertEquals(14, possibleMoves.size());

        for (int i = 1; i <= 6; i++) {
            Coordinate coordinate = new Coordinate(5, 6 - i);
            assertTrue(possibleMoves.get(i - 1).equals(coordinate));
        }

        for (int i = 1; i <= 5; i++) {
            Coordinate coordinate = new Coordinate(5 - i, 6);
            assertTrue(possibleMoves.get(i + 5).equals(coordinate));
        }

        Coordinate coordinate3 = new Coordinate(5, 7);
        assertTrue(possibleMoves.get(11).equals(coordinate3));
        coordinate1.setCoordinates(6,6);
        coordinate2.setCoordinates(7,6);
        assertTrue(possibleMoves.get(12).equals(coordinate1));
        assertTrue(possibleMoves.get(13).equals(coordinate2));
    }
}
