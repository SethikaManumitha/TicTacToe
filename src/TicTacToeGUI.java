import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TicTacToeGUI extends JFrame {

    private JButton[] buttons = new JButton[9];
    private String[] board = new String[9];
    private final String humanPlayer = "O";
    private final String aiPlayer = "X";
    private JLabel statusLabel;

    public TicTacToeGUI() {
        super("Tic Tac Toe - Human vs AI");

        setLayout(new BorderLayout());
        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        statusLabel = new JLabel("Your Turn (O)", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(statusLabel, BorderLayout.NORTH);
        initializeBoard(boardPanel);

        add(boardPanel, BorderLayout.CENTER);
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeBoard(JPanel panel) {
        for (int i = 0; i < 9; i++) {
            board[i] = " ";
            buttons[i] = new JButton(" ");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 40));
            int finalI = i;
            buttons[i].addActionListener(e -> {
                if (board[finalI].equals(" ") && checkWinner().equals(" ")) {
                    makeMove(finalI, humanPlayer);
                    buttons[finalI].setText(humanPlayer);
                    if (!isGameOver()) {
                        statusLabel.setText("AI's Turn (X)");
                        aiMove();
                    }
                    updateGameStatus();
                }
            });
            panel.add(buttons[i]);
        }
    }

    private void updateGameStatus() {
        String winner = checkWinner();
        if (!winner.equals(" ")) {
            statusLabel.setText(winner.equals(aiPlayer) ? "AI Wins!" : "You Win!");
            showRestartDialog(winner.equals(aiPlayer) ? "AI Wins!" : "You Win!");
        } else if (isBoardFull()) {
            statusLabel.setText("Draw!");
            showRestartDialog("It's a Draw!");
        } else {
            statusLabel.setText("Your Turn (O)");
        }
    }

    private void showRestartDialog(String message) {
        int option = JOptionPane.showConfirmDialog(this, message + " Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }

    private void resetGame() {
        for (int i = 0; i < 9; i++) {
            board[i] = " ";
            buttons[i].setText(" ");
        }
        statusLabel.setText("Your Turn (O)");
    }

    private void aiMove() {
        int move = getBestMove();
        makeMove(move, aiPlayer);
        buttons[move].setText(aiPlayer);
        updateGameStatus();
    }

    private ArrayList<Integer> getAvailableMoves() {
        ArrayList<Integer> availableMoves = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (board[i].equals(" ")) {
                availableMoves.add(i);
            }
        }
        return availableMoves;
    }

    private void makeMove(int position, String player) {
        if (board[position].equals(" ")) {
            board[position] = player;
        }
    }

    private boolean isBoardFull() {
        for (String s : board) {
            if (s.equals(" ")) return false;
        }
        return true;
    }

    private String checkWinner() {
        for (int i = 0; i < 9; i += 3) {
            if (!board[i].equals(" ") && board[i].equals(board[i + 1]) && board[i].equals(board[i + 2])) {
                return board[i];
            }
        }
        for (int i = 0; i < 3; i++) {
            if (!board[i].equals(" ") && board[i].equals(board[i + 3]) && board[i + 3].equals(board[i + 6])) {
                return board[i];
            }
        }
        if (!board[0].equals(" ") && board[0].equals(board[4]) && board[4].equals(board[8])) {
            return board[0];
        }
        if (!board[2].equals(" ") && board[2].equals(board[4]) && board[4].equals(board[6])) {
            return board[2];
        }
        return " ";
    }

    private boolean isGameOver() {
        return !checkWinner().equals(" ") || isBoardFull();
    }

    private double miniMax(int depth, boolean isMax) {
        String winner = checkWinner();
        if (!winner.equals(" ")) {
            if (winner.equals(aiPlayer)) return 1.0;
            if (winner.equals(humanPlayer)) return -1.0;
        }
        if (isBoardFull()) return 0.0;

        double bestScore = isMax ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
        for (int move : getAvailableMoves()) {
            board[move] = isMax ? aiPlayer : humanPlayer;
            double score = miniMax(depth + 1, !isMax);
            board[move] = " ";
            bestScore = isMax ? Math.max(score, bestScore) : Math.min(score, bestScore);
        }
        return bestScore;
    }

    private int getBestMove() {
        double bestScore = Double.NEGATIVE_INFINITY;
        int bestMove = 0;
        for (int move : getAvailableMoves()) {
            board[move] = aiPlayer;
            double score = miniMax(0, false);
            board[move] = " ";
            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }
        return bestMove;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToeGUI::new);
    }
}
