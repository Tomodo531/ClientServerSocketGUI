public class Data {
    private static int player1X;
    private static int player1Y;
    private static boolean player1Moved = false;
    private static int player2X;
    private static int player2Y;
    private static boolean player2Moved = false;

    public synchronized static int getPlayer1X() {
        return player1X;
    }

    public synchronized static void setPlayer1X(int player1X) {
        Data.player1X = player1X;
    }

    public synchronized static int getPlayer1Y() {
        return player1Y;
    }

    public synchronized static void setPlayer1Y(int player1Y) {
        Data.player1Y = player1Y;
    }

    public synchronized static int getPlayer2X() {
        return player2X;
    }

    public synchronized static void setPlayer2X(int player2X) {
        Data.player2X = player2X;
    }

    public synchronized static int getPlayer2Y() {
        return player2Y;
    }

    public synchronized static void setPlayer2Y(int player2Y) {
        Data.player2Y = player2Y;
    }

    public synchronized static boolean isPlayer1Moved() {
        return player1Moved;
    }

    public synchronized static void setPlayer1Moved(boolean player1Moved) {
        Data.player1Moved = player1Moved;
    }

    public synchronized static boolean isPlayer2Moved() {
        return player2Moved;
    }

    public synchronized static void setPlayer2Moved(boolean player2Moved) {
        Data.player2Moved = player2Moved;
    }
}
