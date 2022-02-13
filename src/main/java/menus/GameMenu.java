package menus;

import board.ChessBoard;
import misc.Coordinate;
import pieces.Piece;

import java.util.Scanner;

public class GameMenu {
    ChessBoard chessBoard;

    public GameMenu() {
        chessBoard = new ChessBoard();
    }

    public boolean startGame() {
        while (!chessBoard.checkmate()) {
            gameLoop();
        }

        // print victory/defeat screen

        // return true to play again, false to quit

        return true;
    }

    /**
     * Main game loop of chess. Ends when checkmate is achieved.
     */
    private void gameLoop() {
        displayBoard();

        Scanner scanner = new Scanner(System.in);
        String player = "";

        // get input from the user to move piece
        boolean invalidMove = true;
        while (invalidMove) {
            player = chessBoard.getIsCurrentPlayerWhite() ? "Player white": "Player black";

            if (chessBoard.currentPlayerInCheck()) {
                System.out.println(player + " is in check!");
            }

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
                displayBoard();
            } else {
                invalidMove = false;
            }
        }
    }

    /**
     * Displays the current board state
     */
    private void displayBoard() {
        Piece[][] board = board = chessBoard.getBoard();

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
