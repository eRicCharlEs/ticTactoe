import java.util.Scanner;
/**
 *
 * @author herna1135
 */

 // Enumeration to represent the status of the final game results
enum Status {
     WIN, DRAW, CONTINUE;
}

public class TicTac {
    // Board size constant
    private static int BOARDSIZE = 3;

    // 3x3 two-dimensional array representing board elements
    private static char[][] board = new char[BOARDSIZE][BOARDSIZE]; 

    // Player one boolean flag --> Need to find a way to utilize
    private static boolean firstPlayer = true;

    // Game over boolean flag --> Need to find a way to utilize
    private static boolean gameOver = false;

    /**** main ****/
    public static void main(String[] args) {
        // Start the game 
        play();
    }

    public static void play() {
        // Scanner for player input
        Scanner playerInput = new Scanner(System.in);

        // Row and column position 
        int row = 0;
        int column = 0;

        // Player icon: 'x' = player one, 'o' = player two
        char playerIcon = ' ';
        int player = 0;

        // Set up empty board
        for(int i = 0; i < BOARDSIZE; i++)
            for(int j = 0 ; j < BOARDSIZE; j++)
                board[i][j] = ' ';

        // Display the empty board
        printBoard(); 

        // While the game continues
        while(gameOver == false){

            // Determine who's turn
            if(firstPlayer == true) {
                player = 1;
                playerIcon = 'X';
            }
            else { 
                player = 2;
                playerIcon = 'O';
            }

            // Continue asking for input if the move is not valid
            do {
                // Prompt player to enter move
                System.out.print("Player " + playerIcon + "'s: Enter Row( 0, 1 or 2 ): ");
                row = playerInput.nextInt();
               
                System.out.print("\nPlayer " + playerIcon + "'s Enter col( 0, 1 or 2 ): ");
                column = playerInput.nextInt();
            } while(validMove(row, column) == false);

            // Place player's move on board
            printSymbol(row, column, playerIcon);

            // Print the results and determine status of the game
            if(printStatus(player) == 0) {
                gameOver = true;
            }
        }
        // Game over message
        System.out.println("--> GAME OVER <---");
        
        // Close scanner
        playerInput.close();
    }
    
    private static int printStatus(int player) {
        // Get the status of the game
        Status results = gameStatus();
        int status = 1;

        // Victory
        if(results == Status.WIN){
            if(player == 1) {
                System.out.println("Player X wins!");
            }
            else {
                System.out.println("Player O wins!");
            }
            status = 0;
        }
        
        // Draw
        if(results == Status.DRAW) {
            System.out.println("Draw"); 
            status = 0;
        }

        // Continue
        if(results == Status.CONTINUE) {
            System.out.println("Continue");
            status = 1;
        }

        return status;
    }

    private static Status gameStatus() {
        int i = 0; // Row
        int j = 0; // Column
        int maxMoves = BOARDSIZE * BOARDSIZE;
        int moveCount = 0;

        // Moves count 
        int countXs = 0;
        int countOs = 0;

        // Check for horizontal victory
        for(i = 0; i < BOARDSIZE; i++) {
            for(j = 0; j < BOARDSIZE; j++) {
                if(board[i][j] == 'O') countOs++;
                else if(board[i][j] == 'X')countXs++;
            }
            // Check status after each row
            if(countOs == BOARDSIZE || countXs == BOARDSIZE) {
                return Status.WIN;
            }
            else {
                // Reset count
                countOs = 0;
                countXs = 0;
            }
        }

        // Check for veritcal victory
        for(i = 0; i < BOARDSIZE; i++) {
            for(j = 0; j < BOARDSIZE; j++) {
                if(board[j][i] == 'O') countOs++;
                else if(board[j][i] == 'X')countXs++;
            }
            // Check status after each row
            if(countOs == BOARDSIZE || countXs == BOARDSIZE) {
                return Status.WIN;
            } 
            else {
                // Reset count
                countOs = 0;
                countXs = 0;
            }
        }

        // Check diagonal top left to bottom right.
        for (i = 0, j= 0; i < BOARDSIZE ; i++, j++) {
            if (board[i][j] == 'O') countOs++;
            else if(board[i][j] == 'X') countXs++;
        }
        if(countXs == BOARDSIZE || countOs == BOARDSIZE) {
            return Status.WIN;
        }
        else {
            // Reset count
            countOs = 0;
            countXs = 0;
        }

        // Check diagonal bottom left to top right
        for (i = 0, j= BOARDSIZE-1; i < BOARDSIZE; i++, j--) {
            if (board[i][j] == 'O') countOs++;
            else if(board[i][j] == 'X') countXs++;
        }
        if(countXs == BOARDSIZE || countOs == BOARDSIZE) {
            return Status.WIN;
        }
        else {
            // Reset count
            countOs = 0;
            countXs = 0;
        }

        // Check for a draw
        for(i = 0; i < BOARDSIZE; i++) {
            for(j = 0 ; j < BOARDSIZE; j++) {
                if(board[i][j] != ' ') moveCount++; 
            }     
        }
        if(moveCount == maxMoves) return Status.DRAW;

        // Continue 
        return Status.CONTINUE;
    }

    public static void printBoard() {
        System.out.println(" _______________________");
        System.out.println("|       |       |       |");
        System.out.println("|   " + board[0][0] + "   |   " + board[0][1] + "   |   " + board[0][2] + "   |");
        System.out.println("|_______|_______|_______|");
        System.out.println("|       |       |       |");
        System.out.println("|   " + board[1][0] + "   |   " + board[1][1] + "   |   " + board[1][2] + "   |");
        System.out.println("|_______|_______|_______|");
        System.out.println("|       |       |       |");
        System.out.println("|   " + board[2][0] + "   |   " + board[2][1] + "   |   " + board[2][2] + "   |");
        System.out.println("|_______|_______|_______|");
    }

    private static void printSymbol(int row, int column, char value) {
        // Place player symbol
        board[row][column] = value;

        // Switch turns
        if(value == 'X') firstPlayer = false;
        else if(value == 'O') firstPlayer = true;

        // Display the board
        printBoard(); 
    }

    private static boolean validMove(int row, int column) {

        // Make sure the user inputs a correct value
        if (row >= BOARDSIZE || column >= BOARDSIZE) {
            System.out.println("Invalid input: Please the values 0, 1, or 2.");
            return false;
        }

        // Check if the position is available
        if(board[row][column] != ' '){
            System.out.println("Position unavailable. Try again.");
            return false;
        }

        // Move is valid
        return true;
    }  
}
