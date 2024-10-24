public class Rook extends Piece {
    public Rook(boolean isBlack) {
        super(isBlack);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        return (startX == endX || startY == endY);
    }

    @Override
    public String toString() {
        return isBlack ? "r" : "R";
    }
}

