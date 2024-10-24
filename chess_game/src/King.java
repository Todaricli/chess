public class King extends Piece {

    private Cell atCell;
    public King(boolean isBlack) {
        super(isBlack);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        int dx = Math.abs(endX - startX);
        int dy = Math.abs(endY - startY);
        return (dx <= 1 && dy <= 1);
    }

    @Override
    public String toString() {
        return isBlack ? "k" : "K";
    }
}

