import java.util.Scanner;
/**
 *
 * @author herna1135
 */

 // Enumeration to represent the status of the final game results
 enum Status
 {
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
        // Scanner for player input
        Scanner playerInput = new Scanner(System.in); 

        // Row and column positioin 
        int rPlace, cPlace;

        /********* Do we need this? *********/
        // Current player 
        int curPlay; 

        // Maximum amount of moves
        int MAX_PLAYS = 9; 

        // Player icon: 'x' = player one, 'o' = player two
        char player = ' ';
        
        /********* I guess I'm not sure what this is doing yet. Initializing the board? *********/
        // Loop through array and setup board
        for(int i = 0; i < BOARDSIZE; i++)
            for(int j = 0 ; j < BOARDSIZE; j++)
                board[i][j] = ' ';

        // Stops when the max number of plays it reached or the game is won
        while(MAX_PLAYS != 0 && gameOver == false)
        {
            // Display the board
            printBoard(); 

            // Determines player's move
            curPlay = MAX_PLAYS % 2;
            
            // Changes the formatting stuff
            switch(curPlay)
            {
                case 0:
                    player = 'O';
                    break;
                case 1:
                    player = 'X';
                    break;              
            }
            
            // Prompts player to enter move
            System.out.print("Player " + player + "'s: Enter Row( 0, 1 or 2 ): ");
            rPlace = playerInput.nextInt();

            System.out.print("\nPlayer " + player + "'s Enter col( 0, 1 or 2 ): ");
            cPlace = playerInput.nextInt();
            
            // Place player's move on board
            board[rPlace][cPlace] = player;
            
            // Reduce amount of plays
            MAX_PLAYS--;

            // Close scanner
            playerInput.close();
        }  
    }
    
    public static void printBoard()
    {
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
    
}
