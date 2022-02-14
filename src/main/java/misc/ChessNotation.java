package misc;

import java.util.HashMap;

/**
 * Contains information about chess notation (ex: e4) and
 * can map chess notation to the 2D array of the board representation
 */
public class ChessNotation {
    HashMap<Character, Integer> horizontalNotation;
    HashMap<Integer, Integer> verticalNotation;

    /**
     * Initialize hashmaps
     */
    public ChessNotation() {
        horizontalNotation = new HashMap<>();
        verticalNotation = new HashMap<>();

        horizontalNotation.put('a', 0);
        horizontalNotation.put('b', 1);
        horizontalNotation.put('c', 2);
        horizontalNotation.put('d', 3);
        horizontalNotation.put('e', 4);
        horizontalNotation.put('f', 5);
        horizontalNotation.put('g', 6);
        horizontalNotation.put('h', 7);

        verticalNotation.put(1, 7);
        verticalNotation.put(2, 6);
        verticalNotation.put(3, 5);
        verticalNotation.put(4, 4);
        verticalNotation.put(5, 3);
        verticalNotation.put(6, 2);
        verticalNotation.put(7, 1);
        verticalNotation.put(8, 0);
    }

    /**
     * Converts chess notation to coordinates that map to how chessBoard is actually
     * represented. (Ex: e4 -> (4, 4))
     * @param moveInChessNotation a move from the user in chess notation
     * @return a coordinate equivalent to that move
     */
    public Coordinate convertToCoordinate(String moveInChessNotation) {
        char horizontalMovement = moveInChessNotation.charAt(0);
        String verticalMovementAsString = moveInChessNotation.substring(1);
        int verticalMovement = Integer.parseInt(verticalMovementAsString);

        int yCoordinate = verticalNotation.get(verticalMovement);
        int xCoordinate = horizontalNotation.get(horizontalMovement);

        return new Coordinate(yCoordinate, xCoordinate);
    }
}
