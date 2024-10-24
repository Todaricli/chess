import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class ChessGameController {
    private ChessBoard chessBoard;
    private ChessGameTurnManager turnManager;
    private boolean isPlayerWhite;

    public ChessGameController(boolean isPlayerWhite) {
        this.chessBoard = new ChessBoard();
        this.turnManager = new ChessGameTurnManager();
        this.isPlayerWhite = isPlayerWhite;
        chessBoard.initialize();
    }

    public void startGame() {
        while (!isGameOver()) {
            chessBoard.display();
            if (turnManager.isWhiteTurn() == isPlayerWhite) {
                System.out.println("Your turn (" + (isPlayerWhite ? "White" : "Black") + ")");
                String move = getPlayerInput();
                if (processMove(move)) {
                    turnManager.nextTurn();
                } else {
                    System.out.println("Invalid move, try again.");
                }
            } else {
                System.out.println("Computer's turn (" + (!isPlayerWhite ? "White" : "Black") + ")");
                String computerMove = getComputerMove();
                System.out.println("Computer moves: " + computerMove);
                processMove(computerMove);
                turnManager.nextTurn();
            }
        }
        System.out.println("Game over!");
    }

    private String getPlayerInput() {
        Scanner scanner = new Scanner(System.in);
        String input;
        String pattern = "^[a-h][1-8]\\s[a-h][1-8]$"; // Regex pattern for a valid move (e.g., "e2 e4")

        while (true) {
            System.out.print("Enter your move (e.g., e2 e4): ");
            input = scanner.nextLine().trim().toLowerCase();

            // Validate the input against the pattern
            if (input.matches(pattern)) {
                return input;
            } else {
                System.out.println("Invalid move format. Please use the format 'e2 e4'.");
            }
        }
    }

    private boolean processMove(String move) {
        String[] parts = move.split(" ");
        if (parts.length != 2) return false;

        int[] start = parsePosition(parts[0]);
        int[] end = parsePosition(parts[1]);

        if (start == null || end == null) return false;

        return chessBoard.movePiece(start[0], start[1], end[0], end[1], turnManager.isWhiteTurn());
    }

    private int[] parsePosition(String pos) {
        if (pos.length() != 2) return null;
        char file = pos.charAt(0);
        char rank = pos.charAt(1);
        int x = '8' - rank;
        int y = file - 'a';

        System.out.println(x + " " + y);
        if (x < 0 || x > 7 || y < 0 || y > 7) return null;
        return new int[]{x, y};
    }

    private String getComputerMove() {
        Random random = new Random();
        List<String> validMoves = getAllValidMoves(!isPlayerWhite);
        if (validMoves.isEmpty()) {
            return ""; // No valid moves, should handle this case in `isGameOver`
        }
        return validMoves.get(random.nextInt(validMoves.size()));
    }

    private List<String> getAllValidMoves(boolean isBlack) {
        List<String> validMoves = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = chessBoard.getPieceAt(i, j);
                if (piece != null && piece.isBlack() == isBlack) {
                    for (int x = 0; x < 8; x++) {
                        for (int y = 0; y < 8; y++) {
                            if (piece.isValidMove(i, j, x, y, chessBoard.getBoard()) && chessBoard.movePiece(i, j, x, y, turnManager.isWhiteTurn())) {
                                validMoves.add(getPositionString(i, j) + " " + getPositionString(x, y));
                            }
                        }
                    }
                }
            }
        }
        return validMoves;
    }

    private String getPositionString(int x, int y) {
        char file = (char) ('a' + y);
        char rank = (char) ('1' + x);
        return "" + file + rank;
    }

    private boolean isGameOver() {
        if (chessBoard.isCheckmate(turnManager.isWhiteTurn())) {
            System.out.println((turnManager.isWhiteTurn() ? "White" : "Black") + " is checkmated. Game over!");
            return true;
        }
        // Add additional conditions for stalemate if desired
        return false;
    }

}

