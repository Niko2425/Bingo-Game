/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class TicTacToe 
{    
    public static void main(String[] args)
    {
        char[][] gameBoard = TicTacToeController.initializeBoard();
        //Creates our scanner object we'll use for user input, and initializes the player and cpu symbols. Aslo creates random object for later CPU use
        Scanner input = new Scanner(System.in);         
        Random rand = new Random();
        char playerSymbol =' ';
        char cpuSymbol = ' ';
        //Creates the two arraylists we'll use to store the positions (will compare these lists to the win conditions of Bingo with .containsAll)
        ArrayList<Integer> playerPositions = new ArrayList<>();
        ArrayList<Integer> cpuPositions = new ArrayList<>();
        //Prepares the board, and the chosen sides
        TicTacToeController.printBoard(gameBoard);
        //Need to return two char symbols so make temporary char array to return them
        char[] symbols = TicTacToeController.chooseSide(playerSymbol, cpuSymbol, input);
        playerSymbol = symbols[0];
        cpuSymbol = symbols[1];
        //Starts the turns of the game
        startMatch(gameBoard, playerSymbol, cpuSymbol, input, rand, playerPositions, cpuPositions);
    }

    public static void startMatch(char[][] gameBoard, char playerSymbol, char cpuSymbol,Scanner input,Random rand, 
                                ArrayList<Integer> playerPositions,  ArrayList<Integer> cpuPositions)
    {
        while(true)
        {
            TicTacToeController.playerMove(gameBoard,playerSymbol,input, playerPositions, cpuPositions);
            TicTacToeController.cpuMove(gameBoard,cpuSymbol,rand, playerPositions, cpuPositions);
            TicTacToeController.printBoard(gameBoard);
            System.out.println("The CPU made his pick right after you!"); 
            if(TicTacToeController.checkPlayerWin(playerPositions))
            {
                System.out.println("Congratulations, you won!");
                break;
            }
            else if(TicTacToeController.checkCpuWin(cpuPositions))
            {
                System.out.println("Sorry, but the CPU beat you :( ");
                break;
            }
            else if (TicTacToeController.checkDraw(gameBoard))
            {
                System.out.println("Looks like there's a draw! Game Over!");
                break;
            }
            else
            {
                //We don't break here so the while(true) loop continues until match over
            }
        }
    }    
}
