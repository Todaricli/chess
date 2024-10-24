public class Bishop extends Piece {
    public Bishop(boolean isBlack) {
        super(isBlack);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        int dx = Math.abs(endX - startX);
        int dy = Math.abs(endY - startY);
        return dx == dy;
    }

    @Override
    public String toString() {
        return isBlack ? "b" : "B";
    }
}

