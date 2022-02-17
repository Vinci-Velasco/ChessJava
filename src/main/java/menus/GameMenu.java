package menus;

import board.ChessBoard;
import misc.ChessNotation;
import misc.Coordinate;
import pieces.Piece;

import java.util.Scanner;

public class GameMenu {
    ChessBoard chessBoard;
    ChessNotation chessNotation;

    public GameMenu() {
        chessNotation = new ChessNotation();
    }

    public boolean startGame() {
        chessBoard = new ChessBoard();
        System.out.println("WELCOME TO CHESS");

        while (!chessBoard.checkmate()) {
            gameLoop();
        }

        // print victory/defeat screen
        System.out.println("GAME OVER");
        
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

            System.out.println(player + "'s turn! Enter square to move:");
            String moveFrom = scanner.next();

            System.out.println("Enter square to move to:");
            String moveTo = scanner.next();

            Coordinate moveFromAsCoordinate = chessNotation.convertToCoordinate(moveFrom);
            Coordinate moveToAsCoordinate = chessNotation.convertToCoordinate(moveTo);

            if (!chessBoard.updateBoard(moveFromAsCoordinate, moveToAsCoordinate)) {
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

        for (int i = 0; i < 9; i++) {

            if (i != 8) {
                System.out.print(8 - i + "  ");
            }
            else {
                System.out.print("   ");
            }

            for (int j = 0; j < 8; j++) {
                if (i == 8) {
                    char letter = (char)(97 + j);
                    System.out.print(letter + " ");
                } else {
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
            }

            System.out.println();
        }

        System.out.println();
    }
}
