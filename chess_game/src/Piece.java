public abstract class Piece {
    protected boolean isBlack;

    public Piece(boolean isBlack) {
        this.isBlack = isBlack;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public abstract boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board);

    @Override
    public abstract String toString();
}

