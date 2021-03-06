import board.ChessBoard;

import java.util.List;

import misc.Coordinate;
import org.junit.Test;
import pieces.Pawn;
import pieces.Piece;

import static org.junit.Assert.*;


public class PawnTest {
    private ChessBoard chessBoard = new ChessBoard();

    @Test
    public void generateMovesTest() {
        Piece[][] board = chessBoard.getBoard();

        // front is clear (1 available move)
        Piece pawn = board[6][4];
        List<Coordinate> possibleMoves = pawn.generateMoves(chessBoard);
        assertEquals(2, possibleMoves.size());

        Coordinate coordinate1 = new Coordinate(5, 4);
        Coordinate coordinate2 = new Coordinate(4, 4);
        assertTrue(possibleMoves.get(0).equals(coordinate1));

        // enemy piece in front (0 available moves)
        board[5][4] = new Pawn(new Coordinate(5,4), false);
        chessBoard.setBoard(board);
        possibleMoves = pawn.generateMoves(chessBoard);
        assertEquals(0, possibleMoves.size());

        // friendly piece in front (0 available moves)
        board[5][4] = new Pawn(new Coordinate(5,4), true);
        chessBoard.setBoard(board);
        possibleMoves = pawn.generateMoves(chessBoard);
        assertEquals(0, possibleMoves.size());

        // enemy piece diagonal + empty front (2 moves)
        board[5][4] = null;
        board[5][5] = new Pawn(new Coordinate(5,4), false);
        chessBoard.setBoard(board);
        possibleMoves = pawn.generateMoves(chessBoard);
        assertEquals(3, possibleMoves.size());

        coordinate1.setCoordinates(5,4);
        coordinate2.setCoordinates(5, 5);
        Coordinate coordinate3 = new Coordinate(4,4);
        assertTrue(possibleMoves.get(0).equals(coordinate1));
        assertTrue(possibleMoves.get(1).equals(coordinate2));
        assertTrue(possibleMoves.get(2).equals(coordinate3));

        // both diagonals + front square filled with enemy pieces (3 moves)
        board[5][3] = new Pawn(new Coordinate(5,4), false);
        chessBoard.setBoard(board);
        possibleMoves = pawn.generateMoves(chessBoard);
        assertEquals(4, possibleMoves.size());

        coordinate3.setCoordinates(5, 3);
        Coordinate coordinate4 = new Coordinate(4, 4);
        assertTrue(possibleMoves.get(0).equals(coordinate3));
        assertTrue(possibleMoves.get(1).equals(coordinate1));
        assertTrue(possibleMoves.get(2).equals(coordinate2));
        assertTrue(possibleMoves.get(3).equals(coordinate4));

        // both diagonals + front square filled with friendly pieces (0 moves)
        board[5][3] = new Pawn(new Coordinate(5,3), true);
        board[5][4] = new Pawn(new Coordinate(5,4), true);
        board[5][5] = new Pawn(new Coordinate(5,5), true);
        chessBoard.setBoard(board);
        possibleMoves = pawn.generateMoves(chessBoard);
        assertEquals(0, possibleMoves.size());

        // en passant should be valid here (2 moves)
        chessBoard = new ChessBoard();
        chessBoard.updateBoard(new Coordinate(6, 4), new Coordinate(4,4));
        chessBoard.updateBoard(new Coordinate(1, 0), new Coordinate(2,0));
        chessBoard.updateBoard(new Coordinate(4, 4), new Coordinate(3,4));
        chessBoard.updateBoard(new Coordinate(1, 3), new Coordinate(3,3));

        board = chessBoard.getBoard();
        pawn = board[3][4];
        possibleMoves = pawn.generateMoves(chessBoard);

        coordinate4 = new Coordinate(2,3);
        Coordinate coordinate5 = new Coordinate(2,4);
        assertTrue(possibleMoves.get(0).equals(coordinate4));
        assertTrue(possibleMoves.get(1).equals(coordinate5));

    }
}
