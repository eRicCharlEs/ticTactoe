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
    private boolean firstPlayer = true;

    // Game over boolean flag
    private boolean gameOver = false;

    /** main **/
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in); //takes in user input
        //ints to hold row and col place //curplay holds which player is playing
        int rPlace, cPlace, curPlay; 
          //counter to make sure it doesnt go over 9 plus helps determine player
        int maxPlays = 9; 
        //helps with the formatting of the question
        char player = ' ';
        
        //for loops to fill up the 2D array with blank spaces
        for(int i = 0; i < BOARDSIZE; i++)
            for(int j = 0 ; j < BOARDSIZE; j++)
                board[i][j] = ' ';
        
        //stops when the max number of plays it reached
        while(maxPlays != 0)
        {
            //display the new board
            displayBoard(); 

            //get which player is going to place their piece ERIC
            curPlay = maxPlays % 2;
            
            //changes the formatting stuff
            switch(curPlay)
            {
                case 0:
                    player = 'O';
                    break;
                case 1:
                    player = 'X';
                    break;              
            }
            
            //prompts user to enter the place they want to play
            System.out.print("Player " + player + "'s: Enter Row( 0, 1 or 2 ): ");
            rPlace = input.nextInt();
            System.out.print("\nPlayer " + player + "'s Enter col( 0, 1 or 2 ): ");
            cPlace = input.nextInt();
            
            //changes the place of the board from blank space to letter
            board[rPlace][cPlace] = player;
            
            //decrements max plays so we dont go over 9
            maxPlays--;
        }  
    }
    
    public static void displayBoard()
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
