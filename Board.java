import java.util.Random;

public class Board {

    private static int pieces;

    public static void populate() {
        Random random = new Random();
        pieces = random.nextInt(41) + 10;
    }
    public static int getNumPieces() {
        return pieces;
    }
    public static void removePieces(int numPiecesToRemove) {
        pieces -= numPiecesToRemove;
    }
    
}
