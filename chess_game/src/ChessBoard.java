import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
    private Piece[][] board;

    public ChessBoard() {
        board = new Piece[8][8];
    }

    public void initialize() {
        // Initialize black pieces
        board[0][0] = new Rook(true);
        board[0][1] = new Knight(true);
        board[0][2] = new Bishop(true);
        board[0][3] = new Queen(true);
        board[0][4] = new King(true);
        board[0][5] = new Bishop(true);
        board[0][6] = new Knight(true);
        board[0][7] = new Rook(true);
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(true);
        }

        // Initialize white pieces
        board[7][0] = new Rook(false);
        board[7][1] = new Knight(false);
        board[7][2] = new Bishop(false);
        board[7][3] = new Queen(false);
        board[7][4] = new King(false);
        board[7][5] = new Bishop(false);
        board[7][6] = new Knight(false);
        board[7][7] = new Rook(false);
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(false);
        }
    }

    public void display() {
        System.out.println("   a b c d e f g h");
        System.out.println("   ---------------");
        for (int i = 0; i < 8; i++) {
            System.out.print((8 - i) + " " + "|");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(". ");
                } else {
                    System.out.print(board[i][j].toString() + " ");
                }
            }
            System.out.print("|" + " " + (8 - i) + "\n");
        }
        System.out.println("   ---------------");
        System.out.println("   a b c d e f g h");
    }

    public boolean movePiece(int startX, int startY, int endX, int endY, boolean isWhiteTurn) {
        Piece piece = board[startX][startY];

        if (piece == null || piece.isBlack() == isWhiteTurn) {
            return false; // No piece or wrong turn
        }

        if (piece.isValidMove(startX, startY, endX, endY, board)) {
            // Additional logic to check if the path is clear
            board[endX][endY] = piece;
            board[startX][startY] = null;
            return true;
        }

        return false;
    }

    public Piece[][] getBoard() {
        return board;
    }

    public Piece getPieceAt(int x, int y) {
        return board[x][y];
    }

    public boolean isCheck(boolean isBlack) {
        int[] kingPosition = findKing(isBlack);
        if (kingPosition == null) {
            return false;
        }

        int kingX = kingPosition[0];
        int kingY = kingPosition[1];

        // Check if any opponent piece can move to the king's position
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece opponentPiece = board[i][j];
                if (opponentPiece != null && opponentPiece.isBlack() != isBlack) {
                    if (opponentPiece.isValidMove(i, j, kingX, kingY, board)) {
                        return true; // King is in check
                    }
                }
            }
        }
        return false;
    }

    public boolean isCheckmate(boolean isBlack) {
        if (!isCheck(isBlack)) {
            return false; // Not in check, so it cannot be checkmate
        }

        // Iterate over all pieces of the current player
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if (piece != null && piece.isBlack() == isBlack) {
                    // Try moving this piece to all other positions
                    for (int x = 0; x < 8; x++) {
                        for (int y = 0; y < 8; y++) {
                            if (piece.isValidMove(i, j, x, y, board)) {
                                Piece temp = board[x][y];
                                board[x][y] = piece;
                                board[i][j] = null;

                                boolean stillInCheck = isCheck(isBlack);
                                // Undo the move
                                board[i][j] = piece;
                                board[x][y] = temp;

                                if (!stillInCheck) {
                                    return false; // Found a valid move to escape check
                                }
                            }
                        }
                    }
                }
            }
        }

        // If no valid moves to escape check, it's checkmate
        return true;
    }

    private int[] findKing(boolean isBlack) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if (piece instanceof King && piece.isBlack() == isBlack) {
                    return new int[]{i, j};
                }
            }
        }
        return null; // Should not happen if the game is correctly initialized
    }
}



