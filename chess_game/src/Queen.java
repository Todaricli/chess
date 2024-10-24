public class Queen extends Piece {
    public Queen(boolean isBlack) {
        super(isBlack);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        int dx = Math.abs(endX - startX);
        int dy = Math.abs(endY - startY);
        return (dx == dy || dx == 0 || dy == 0);
    }

    @Override
    public String toString() {
        return isBlack ? "q" : "Q";
    }
}

