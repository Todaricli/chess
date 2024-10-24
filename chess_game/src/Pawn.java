public class Pawn extends Piece {
    public Pawn(boolean isBlack) {
        super(isBlack);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        int direction = isBlack ? 1 : -1;
        if (startY == endY) {
            if (startX + direction == endX && board[endX][endY] == null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return isBlack ? "p" : "P";
    }
}

