package menus;

import board.ChessBoard;
import misc.Coordinate;
import pieces.Piece;

import java.util.Scanner;

public class GameMenu {
    ChessBoard chessBoard;
    Piece[][] board;

    public GameMenu() {
        chessBoard = new ChessBoard();
        board = chessBoard.getBoard();
    }

    public boolean startGame() {
        gameLoop();
        return true;
    }

    /**
     * Main game loop of chess. Ends when checkmate is achieved.
     */
    private void gameLoop() {
        displayBoard();
        Scanner scanner = new Scanner(System.in);
        boolean invalidMove = true;

        // get input from the user to move piece
        // repeats until valid move chosen
        while (invalidMove) {
            String player = chessBoard.getIsCurrentPlayerWhite() ? "Player white": "Player black";

            System.out.println(player + "'s turn! Enter y position of piece to move:");
            int yFrom = scanner.nextInt();

            System.out.println("Enter x position of piece to move:");
            int xFrom = scanner.nextInt();
            Coordinate moveFrom = new Coordinate(yFrom, xFrom);

            System.out.println("Enter y position you want to move to:");
            int yTo = scanner.nextInt();

            System.out.println("Enter x position you want to move to:");
            int xTo = scanner.nextInt();

            Coordinate moveTo = new Coordinate(yTo, xTo);

            if (!chessBoard.updateBoard(moveFrom, moveTo)) {
                System.out.println("Invalid move! Please try again");
            } else {
                invalidMove = false;
            }
        }
    }

    /**
     * Displays the current board state
     */
    private void displayBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (j == 0) {
                    System.out.print("|");

                }

                String piece;
                if (board[i][j] == null) {
                    piece = " "; // unicode for empty character (with longer width)
                } else {
                    piece = board[i][j].toString();
                }

                System.out.print(piece);
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println();
    }
}
