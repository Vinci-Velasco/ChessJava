import board.ChessBoard;
import misc.Coordinate;
import org.junit.Test;
import pieces.Pawn;
import pieces.Queen;
import pieces.Piece;

import java.util.List;

import static org.junit.Assert.*;

public class QueenTest {
    private ChessBoard chessBoard = new ChessBoard();

    @Test
    public void generateMovesTest() {
        Piece[][] board = chessBoard.getBoard();

        // no moves available
        Piece queen = board[7][3];
        List<Coordinate> possibleMoves = queen.generateMoves(chessBoard);
        assertEquals(0, possibleMoves.size());

        // vertical movement open
        board[6][3] = null;
        chessBoard.setBoard(board);
        possibleMoves = queen.generateMoves(chessBoard);
        assertEquals(6, possibleMoves.size());

        for (int i = 1; i <= 6; i++) {
            Coordinate coordinate = new Coordinate(7 - i, 3);
            assertTrue(possibleMoves.get(i-1).equals(coordinate));
        }

        // horizontal movement open
        board[6][3] = new Pawn(new Coordinate(6,3), true);
        board[7][1] = null;
        board[7][2] = null;
        board[7][4] = null;
        board[7][5] = null;
        chessBoard.setBoard(board);
        possibleMoves = queen.generateMoves(chessBoard);
        assertEquals(4, possibleMoves.size());

        Coordinate coordinate1 = new Coordinate(7,2);
        Coordinate coordinate2 = new Coordinate(7,1);
        Coordinate coordinate3 = new Coordinate(7,4);
        Coordinate coordinate4 = new Coordinate(7,5);
        assertTrue(possibleMoves.get(0).equals(coordinate1));
        assertTrue(possibleMoves.get(1).equals(coordinate2));
        assertTrue(possibleMoves.get(2).equals(coordinate3));
        assertTrue(possibleMoves.get(3).equals(coordinate4));

        // diagonal movement open
        chessBoard.setInitialBoard();
        board = chessBoard.getBoard();
        queen = board[7][3];
        board[6][2] = null;
        board[6][4] = null;
        chessBoard.setBoard(board);
        possibleMoves = queen.generateMoves(chessBoard);
        assertEquals(7, possibleMoves.size());

        for (int i = 1; i <= 3; i++) {
            Coordinate coordinate = new Coordinate(7 - i, 3 - i);
            possibleMoves.get(i - 1).equals(coordinate);
        }

        for (int i = 1; i <= 4; i++) {
            Coordinate coordinate = new Coordinate(7 - i, 3 + i);
            possibleMoves.get(i + 2).equals(coordinate);
        }

        // all possible movements open (8 directions)
        chessBoard.clearBoard();
        board = chessBoard.getBoard();
        board[6][3] = new Queen(new Coordinate(6, 3), true);
        queen = board[6][3];
        chessBoard.setBoard(board);
        possibleMoves = queen.generateMoves(chessBoard);
        assertEquals(23, possibleMoves.size());

            for (int i = 1; i <= 3; i++) {
                Coordinate coordinate = new Coordinate(6, 3 - i);
                possibleMoves.get(i - 1).equals(coordinate);
            }

        for (int i = 1; i <= 3; i++) {
            Coordinate coordinate = new Coordinate(6 - i, 3 - i);
            possibleMoves.get(i + 2).equals(coordinate);
        }

        for (int i = 1; i <= 6; i++) {
            Coordinate coordinate = new Coordinate(6 - i, 3);
            assertTrue(possibleMoves.get(i+5).equals(coordinate));
        }

        for (int i = 1; i <= 4; i++) {
            Coordinate coordinate = new Coordinate(6 - i, 3 + i);
            possibleMoves.get(i + 11).equals(coordinate);
        }

        for (int i = 1; i <= 4; i++) {
            Coordinate coordinate = new Coordinate(6, 3 + i);
            possibleMoves.get(i + 15).equals(coordinate);
        }

        coordinate1.setCoordinates(7,4);
        coordinate2.setCoordinates(7,3);
        coordinate3.setCoordinates(7,2);
        assertTrue(possibleMoves.get(20).equals(coordinate1));
        assertTrue(possibleMoves.get(21).equals(coordinate2));
        assertTrue(possibleMoves.get(22).equals(coordinate3));
    }
}
