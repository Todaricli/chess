public class ChessGameTurnManager {
    private boolean whiteTurn;

    public ChessGameTurnManager() {
        this.whiteTurn = true;
    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public void nextTurn() {
        whiteTurn = !whiteTurn;
    }
}
