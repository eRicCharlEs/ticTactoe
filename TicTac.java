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
    //board size constant
    private static int BOARDSIZE = 3;

    // 3x3 two-dimensional array representing board elements
    private static char[][] board = new char[BOARDSIZE][BOARDSIZE]; 

    // Player one boolean flag
    private static boolean firstPlayer = true;

    // Game over boolean flag
    private static boolean gameOver = false;

    /** main **/
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
        char player = ' ';

        /********* Do we need this? *********/
        // Current player 
        int curPlay; 

        // Maximum amount of moves
        int MAX_PLAYS = 9; 

        // int results flag
        int results = 0;

        // Loop through array and initialize to ' '
        for(int i = 0; i < BOARDSIZE; i++)
            for(int j = 0 ; j < BOARDSIZE; j++)
                board[i][j] = ' ';

        // Display the board
        printBoard(); 

        // Stops when the max number of plays it reached or the game is won
        while(results == 0) {

            // Determines player's move
            curPlay = MAX_PLAYS % 2;

            // Changes the formatting stuff
            switch(curPlay) {
                case 0:
                    player = 'O';
                    break;
                case 1:
                    player = 'X';
                    break;              
            }

            // Reduce amount of plays
            MAX_PLAYS--;

            // Prompts player to enter move
            System.out.print("Player " + player + "'s: Enter Row( 0, 1 or 2 ): ");
            row = playerInput.nextInt();
            System.out.print("\nPlayer " + player + "'s Enter col( 0, 1 or 2 ): ");
            column = playerInput.nextInt();

            while(validMove(column, row) == false) {
                // Prompts player to enter move
                System.out.print("Player " + player + "'s: Enter Row( 0, 1 or 2 ): ");
                row = playerInput.nextInt();
                System.out.print("\nPlayer " + player + "'s Enter col( 0, 1 or 2 ): ");
                column = playerInput.nextInt();
            }

            // Place player move on board
            printSymbol(column, row, player);

            // Print the results of current move
            results = printStatus(player);
        } 

        // Close scanner
        playerInput.close();
    }
    
    private static int printStatus(int player) {

        Status results = gameStatus();
        int status = 0;

        if(results == Status.WIN){
            System.out.println("Player " + player + " wins!");
            status = 1;
        }
        else if(results == Status.DRAW) {
            System.out.println("Draw"); 
            status = 1;
        }
        else {
            System.out.println("Continue");
            status = 0;
        }

        return status;
    }

    private static Status gameStatus() {
        // Moves count 
        int countXs = 0;
        int countOs = 0;

        for (int i = 0; i < BOARDSIZE; i++) {

            for (int j = 0; j < BOARDSIZE; j++) {

                if(board[i][j] == 'O') countOs++;
                else if(board[i][j] == 'X')countXs++;
            }

            if(countOs == BOARDSIZE || countXs == BOARDSIZE) return Status.WIN;

            countXs = 0;
            countOs = 0;
        }
        countXs = 0;
        countOs = 0;

        //check diagonal right
        for (int i = 0, j= 0; i <BOARDSIZE ; i++, j++) {
            if (board[i][j] == 'O') countOs++;
            else if(board[i][j] == 'X') countXs++;
        }
        if(countXs == BOARDSIZE || countOs == BOARDSIZE) return Status.WIN;

        //check diagonal left
        for (int i = BOARDSIZE-1, j= BOARDSIZE-1; i >= 0 ; i--, j--) {
            if (board[i][j] == 'O') countOs++;
            else if(board[i][j] == 'X') countXs++;
        }
        if(countXs == BOARDSIZE || countOs == BOARDSIZE) return Status.WIN;

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

    private static void printSymbol(int column, int row, char value) {
        // Place player symbol
        board[column][row] = value;

        // Display the board
        printBoard(); 
    }

    private static boolean validMove(int column, int row) {
        // Check if the position is available
        if(board[column][row] != ' '){
            System.out.println("That position is unavailable. Try again.");
            return false;
        }
        // Move is valid
        return true;
    }  
}
