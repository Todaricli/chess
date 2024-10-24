public class Knight extends Piece {
    public Knight(boolean isBlack) {
        super(isBlack);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        int dx = Math.abs(endX - startX);
        int dy = Math.abs(endY - startY);
        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }

    @Override
    public String toString() {
        return isBlack ? "n" : "N";
    }
}
