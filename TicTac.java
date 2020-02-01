import java.util.Arrays;
import java.util.Scanner;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author herna1135 I CHANGED THIS
 */

public class TicTac {
public static int BS = 3;

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        
        // Two-D array for the board
        char[][] board = new char[BS][BS]; 
        
        for(int i = 0; i < BS; i++)
            for(int j = 0 ; j < BS; j++)
                board[i][j] = ' ';
        
        char place = ' ';
        place = input.next().charAt(0);
    
        System.out.println(" _______________________");
        System.out.println("|       |       |       |");
        System.out.println("|   " + board[0][0] + "   |   " + board[0][1] + "   |   " + board[0][2] + "   |");
        System.out.println("|_______|_______|_______|");
        System.out.println("|       |       |       |");
        System.out.println("|       |       |       |");
        System.out.println("|_______|_______|_______|");
        System.out.println("|       |       |       |");
        System.out.println("|       |       |       |");
        System.out.println("|_______|_______|_______|");
    }
    
}
