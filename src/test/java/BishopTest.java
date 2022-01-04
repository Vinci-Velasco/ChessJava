import java.util.List;

import board.ChessBoard;
import misc.Coordinate;
import org.junit.Test;
import pieces.Bishop;
import pieces.Piece;

import static org.junit.Assert.*;

public class BishopTest {
    private ChessBoard chessBoard = new ChessBoard();

    @Test
    public void generateMovesTest() {
        Piece[][] board = chessBoard.getBoard();

        // no directions open
        Piece bishop = board[7][5];
        List<Coordinate> possibleMoves = bishop.generateMoves(chessBoard);
        assertEquals(0, possibleMoves.size());

        // 1 direction open
        board[6][6] = null;
        chessBoard.setBoard(board);
        possibleMoves = bishop.generateMoves(chessBoard);
        assertEquals(2, possibleMoves.size());
        for (int i = 1; i <= 2; i++) {
            Coordinate coordinate = new Coordinate(7- i, 5 + i);
            assertTrue(possibleMoves.get(i-1).equals(coordinate));
        }

        // 2 directions open
        board[6][4] = null;
        chessBoard.setBoard(board);
        possibleMoves = bishop.generateMoves(chessBoard);
        assertEquals(7, possibleMoves.size());

        for (int i = 1; i <= 5; i++) {
            Coordinate coordinate = new Coordinate(7- i, 5 - i);
            assertTrue(possibleMoves.get(i-1).equals(coordinate));
        }

        for (int i = 1; i <= 2; i++) {
            Coordinate coordinate = new Coordinate(7- i, 5 + i);
            assertTrue(possibleMoves.get(i+4).equals(coordinate));
        }

        // 3 directions open
        board[7][5] = null;
        board[6][6] = new Bishop(new Coordinate(6,6),true);
        bishop = board[6][6];
        chessBoard.setBoard(board);
        possibleMoves = bishop.generateMoves(chessBoard);
        assertEquals(7, possibleMoves.size());

        for (int i = 1; i <= 5; i++) {
            Coordinate coordinate = new Coordinate(6 - i, 6 - i);
            assertTrue(possibleMoves.get(i-1).equals(coordinate));
        }

        Coordinate coordinate1 = new Coordinate(5,7);
        Coordinate coordinate2 = new Coordinate(7,5);
        assertTrue(possibleMoves.get(5).equals(coordinate1));
        assertTrue(possibleMoves.get(6).equals(coordinate2));


        // 4 (all) directions open
        board[7][7] = null;
        chessBoard.setBoard(board);
        possibleMoves = bishop.generateMoves(chessBoard);
        assertEquals(8, possibleMoves.size());

        for (int i = 1; i <= 5; i++) {
            Coordinate coordinate = new Coordinate(6 - i, 6 - i);
            assertTrue(possibleMoves.get(i-1).equals(coordinate));
        }

        coordinate1 = new Coordinate(5,7);
        coordinate2 = new Coordinate(7,7);
        Coordinate coordinate3 = new Coordinate(7,5);
        assertTrue(possibleMoves.get(5).equals(coordinate1));
        assertTrue(possibleMoves.get(6).equals(coordinate2));
        assertTrue(possibleMoves.get(7).equals(coordinate3));
    }
}
