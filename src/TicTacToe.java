import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private String[] board;
    private final String humanPlayer = "O";
    private final String aiPlayer = "X";

    public TicTacToe() {
        board = new String[9];
        for (int i = 0; i < 9; i++) {
            board[i] = " ";
        }
    }

    // Print the board
    public void printBoard() {
        for(int i=0;i<9;i += 3){
            System.out.println(board[i] + " | " + board[i + 1] + " | " + board[i + 2]);
            if(i < 6){
                System.out.println("---------");
            }
        }
    }

    // Get all available moves
    public ArrayList<Integer> getAvailableMoves() {
        ArrayList<Integer> availableMoves = new ArrayList<>();
        for(int i=0;i<9;i++){
            if(board[i].equals(" ")){
                availableMoves.add(i);
            }
        }
        return availableMoves;
    }

    // Make a move
    public void makeMove(Integer position, String player){
        if(board[position].equals(" ")){
            board[position] = player;
        }
    }

    // Is the board full?
    public boolean isBoardFull(){
        for(int i=0;i<9;i++){
            if(board[i].equals(" ")){
                return false;
            }
        }
        return true;
    }

    // Check winner
    public String checkWinner(){

        // Check row
        for (int i = 0;i<9;i+=3){
            if(!board[i].equals(" ") && board[i].equals(board[i + 1]) && board[i + 1].equals(board[i + 2])){
                return board[i];
            }
        }

        // Check column
        for (int j = 0;j<3;j++){
            if(!board[j].equals(" ") && board[j].equals(board[j + 3]) && board[j + 3].equals(board[j + 6])){
                return board[j];
            }
        }

        // Check diagonal
        if (board[0].equals(board[4]) && board[4].equals(board[8])){
            return board[0];
        }

        // Check anti-diagonal
        if(board[2].equals(board[4]) && board[4].equals(board[6])){
            return board[2];
        }
        return " ";
    }

    // Check if game is over
    public boolean isGameOver(){
        return !checkWinner().equals(" ") | isBoardFull();
    }

    // Implement AI move using minimax algorithm
    public double miniMax(int depth, boolean isMax) {
        String winner = checkWinner();

        if (winner != " ") {
            if (winner.equals(aiPlayer)) {
                return 1.0;
            } else if (winner.equals(humanPlayer)) {
                return -1.0;
            } else {
                return 0.0;
            }
        }

        double bestScore;
        if (isMax) {
            bestScore = Double.NEGATIVE_INFINITY;
            for (int move : getAvailableMoves()) {
                board[move] = aiPlayer;
                double score = miniMax(depth + 1, false);
                board[move] = " ";
                bestScore = Math.max(score, bestScore);
            }
        } else {
            bestScore = Double.POSITIVE_INFINITY;
            for (int move : getAvailableMoves()) {
                board[move] = humanPlayer;
                double score = miniMax(depth + 1, true);
                board[move] = " ";
                bestScore = Math.min(score, bestScore);
            }
        }
        return bestScore;
    }

    public int getBestMove(){
        double best_score = Double.NEGATIVE_INFINITY;
        int best_move = 0;

        for(int move : getAvailableMoves()){
            board[move] = aiPlayer;
            double score = miniMax(0,false);
            board[move] = " ";
            if(score > best_score){
                best_score = score;
                best_move = move;
            }
        }
        return best_move;
    }

    public void play(){
        System.out.println("Enter positions (0-8) as shown below:");
        System.out.println("0 | 1 | 2");
        System.out.println("3 | 4 | 5");
        System.out.println("6 | 7 | 8");

        boolean aiTurn = new Random().nextBoolean();
        while(!isGameOver()){
            printBoard();

            if(aiTurn){
                System.out.println("AI's Turn");
                int move = getBestMove();
                makeMove(move,aiPlayer);
            }else{
                while (true) {
                    try {
                        Scanner userInput = new Scanner(System.in);
                        System.out.print("Your Turn (0-8): ");
                        int move = Integer.parseInt(userInput.nextLine());
                        if(move >= 0 && move <=8){
                            makeMove(move,humanPlayer);
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a number between 0 and 8.");
                    }
                }
            }
            aiTurn = !aiTurn;
        }

        printBoard();
        String winner = checkWinner();
        if (winner.equals(aiPlayer)) {
            System.out.println("AI Wins!");
        } else if (winner.equals(humanPlayer)) {
            System.out.println("Congratulation,Human Wins!");
        } else {
            System.out.println("Draw!");
        }
    }


    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.play();
    }
}
