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

    // Player one boolean flagm --> Need to find a way to utilize
    private static boolean firstPlayer = true;

    // Game over boolean flag --> Need to find a way to utilize
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

            while(validMove(row, column) == false) {
                // Prompts player to enter move
                System.out.print("Player " + player + "'s: Enter Row( 0, 1 or 2 ): ");
                row = playerInput.nextInt();

                System.out.print("\nPlayer " + player + "'s Enter col( 0, 1 or 2 ): ");
                column = playerInput.nextInt();
            }

            // Place player move on board
            printSymbol(row, column, player);

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
        
        if(results == Status.DRAW) {
            System.out.println("Draw"); 
            status = 1;
        }

        if(results == Status.CONTINUE) {
            System.out.println("Continue");
            status = 0;
        }

        return status;
    }

    private static Status gameStatus() {
        // Incrementers 
        int i = 0; // Row
        int j = 0; // Column

        // Moves count 
        int countXs = 0;
        int countOs = 0;

        /**** I think this works ****/
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

        /**** Appears to be working ****/
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

        /**** This on works ****/
        // Check diagonal top left to bottom right.
        for (i = 0, j= 0; i < BOARDSIZE ; i++, j++) {
            if (board[i][j] == 'O') countOs++;
            else if(board[i][j] == 'X') countXs++;
        }
        if(countXs == BOARDSIZE || countOs == BOARDSIZE) {
            System.out.println("Top left to bottom right");
            return Status.WIN;
        }
        else {
            // Reset count
            countOs = 0;
            countXs = 0;
        }

        /**** This Works ****/
        // Check diagonal bottom left to top right
        for (i = 0, j= BOARDSIZE-1; i < BOARDSIZE; i++, j--) {
            if (board[i][j] == 'O') countOs++;
            else if(board[i][j] == 'X') countXs++;
        }
        if(countXs == BOARDSIZE || countOs == BOARDSIZE) {
            System.out.println("Bottom left to top right");
            return Status.WIN;
        }
        else {
            // Reset count
            countOs = 0;
            countXs = 0;
        }

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

        // Display the board
        printBoard(); 
    }

    private static boolean validMove(int row, int column) {

        if (row >= 3 || column >= 3) {
            System.out.println("Invalid input: Please the values 0, 1, or 2.");
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
