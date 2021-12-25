import board.ChessBoard;
import misc.Coordinate;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class KnightTest {
    private ChessBoard chessBoard = new ChessBoard();

    @Test
    public void generateMovesTest() {
        Piece[][] board = chessBoard.getBoard();

        // from starting position (generates 2 possible moves)
        Piece knight = board[7][1];
        List<Coordinate> possibleMoves = knight.generateMoves(chessBoard);
        assertEquals(2, possibleMoves.size());
        Coordinate coordinate1 = new Coordinate(5, 0);
        Coordinate coordinate2 = new Coordinate(5, 2);
        assertTrue(possibleMoves.get(0).equals(coordinate1));
        assertTrue(possibleMoves.get(1).equals(coordinate2));


        // when enemies are on those squares (should still be the same)
        board[5][0] = new Pawn(new Coordinate(5,0), false);
        board[5][2] = new Pawn(new Coordinate(5,2), false);
        chessBoard.setBoard(board);
        possibleMoves = knight.generateMoves(chessBoard);
        assertEquals(2, possibleMoves.size());
        coordinate1.setCoordinates(5, 0);
        coordinate2.setCoordinates(5, 2);
        assertTrue(possibleMoves.get(0).equals(coordinate1));
        assertTrue(possibleMoves.get(1).equals(coordinate2));

        // when other friendly pieces are on those squares (should block those spots)
        board[5][0] = new Pawn(new Coordinate(5,0), true);
        board[5][2] = new Pawn(new Coordinate(5,2), true);
        chessBoard.setBoard(board);
        possibleMoves = knight.generateMoves(chessBoard);
        assertEquals(0, possibleMoves.size()); // no spots for the knight to move


        // when all spaces are open (4 should be generated)
        chessBoard.clearBoard();
        board[3][4] = new Knight(new Coordinate(3, 4), true);
        chessBoard.setBoard(board);
        knight = board[3][4];
        possibleMoves = knight.generateMoves(chessBoard);
        assertEquals(4, possibleMoves.size());
        coordinate1.setCoordinates(1, 3);
        coordinate2.setCoordinates(1, 5);
        Coordinate coordinate3 = new Coordinate(5, 5);
        Coordinate coordinate4 = new Coordinate(5, 3);
        assertTrue(possibleMoves.get(0).equals(coordinate1));
        assertTrue(possibleMoves.get(1).equals(coordinate2));
        assertTrue(possibleMoves.get(2).equals(coordinate3));
        assertTrue(possibleMoves.get(3).equals(coordinate4));
    }
}
