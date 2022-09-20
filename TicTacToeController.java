/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author arake
 */
public class TicTacToeController 
{
    public static char[][] initializeBoard()
    {
        //Our key: first position is [0][0] while the very last one would be [4][4]
        char[][] gameBoard = {{' ','|',' ','|',' '},
        {'-','+','-','+','-'},
        {' ','|',' ','|',' '},
        {'-','+','-','+','-'},
        {' ','|',' ','|',' '}};
        return gameBoard;
    }
    public static void printBoard(char[][] gameBoard)
    {
        for(char[] row: gameBoard) // Loops through each of the 5 arrays in the 'big array'
        {
            for(char x: row) // Loops throug each row to print every element 
            {
                System.out.print(x);
            }
            System.out.println(); //After looping through each row, we go to next line
        }        
    }
    
    public static char[] chooseSide(char playerSymbol, char cpuSymbol, Scanner input)
    {
        //Will continue asking for correct side until valid side is set to true
        boolean validSide = false;
        while(!validSide)
        {
            System.out.println("Hello, please choose whether you'd like to be 'X' or 'O'");
            char userInput = input.next().charAt(0);            
            switch (userInput) {
                case 'X':
                case 'x':
                    playerSymbol = 'X';
                    cpuSymbol = 'O';
                    validSide = true;
                    break;
                case 'O':
                case 'o':
                    playerSymbol = 'O';
                    cpuSymbol = 'X';
                    validSide = true;
                    break;
                default:
                    System.out.println("You didn't enter an 'X' or 'O'. Try again!");
                    break;
            }
        }
        char[] symbols = {playerSymbol, cpuSymbol};
        return symbols;
    }
    
    public static void playerMove(char[][] gameBoard, char playerSymbol,Scanner input, ArrayList<Integer> playerPositions, ArrayList<Integer> cpuPositions )
    {
        System.out.println("The board is labeled from 1 to 9, with 1 being the top left square and 9 being the bottom right");
        System.out.println("Where would you like to place your " + playerSymbol + "?");
        int playerChoice = input.nextInt();
        //Checks to make sure random number won't replace an existing X or O, and also makes sure number is in range
        while(playerPositions.contains(playerChoice) || cpuPositions.contains(playerChoice) || playerChoice>9 || playerChoice < 1)
        {
            System.out.println("That spot is already taken, or your input wasn't in the range of 1 to 9. Try again.");
            System.out.println("Remember: The board is labeled such that the top left space is 1 and bottom right is 9");
            playerChoice = input.nextInt();
        }
        switch(playerChoice)
        {
            case 1: gameBoard[0][0] = playerSymbol;
                    playerPositions.add(playerChoice);
                    break;
            case 2: gameBoard[0][2] = playerSymbol;
                    playerPositions.add(playerChoice);
                    break;
            case 3: gameBoard[0][4] = playerSymbol;
                    playerPositions.add(playerChoice);
                    break;
            case 4: gameBoard[2][0] = playerSymbol;
                    playerPositions.add(playerChoice);
                    break;
            case 5: gameBoard[2][2] = playerSymbol;
                    playerPositions.add(playerChoice);
                    break;
            case 6: gameBoard[2][4] = playerSymbol;
                    playerPositions.add(playerChoice);
                    break;
            case 7: gameBoard[4][0] = playerSymbol;
                    playerPositions.add(playerChoice);
                    break;
            case 8: gameBoard[4][2] = playerSymbol;
                    playerPositions.add(playerChoice);
                    break;
            case 9: gameBoard[4][4] = playerSymbol;
                    playerPositions.add(playerChoice);
                    break;
            default: System.out.println("Invalid input!");
        }
    }
    
     public static void cpuMove(char[][] gameBoard, char cpuSymbol, Random rand, ArrayList<Integer> playerPositions, ArrayList<Integer> cpuPositions)
    {
        int cpuChoice = rand.nextInt(9)+1; //Gets a random number from 1-9
        //Checks to make sure random number won't replace an existing X or O
        while(playerPositions.contains(cpuChoice) || cpuPositions.contains(cpuChoice))
        {
            cpuChoice = rand.nextInt(9)+1;
            //A bypass for our while loop in the event that the game ends so there's no infinite loop
            if(TicTacToeController.checkPlayerWin(playerPositions) || TicTacToeController.checkDraw(gameBoard))
            {
                cpuChoice = -1;
            }
        }
        switch(cpuChoice)
        {
            case 1: gameBoard[0][0] = cpuSymbol;
                    cpuPositions.add(cpuChoice);
                    break;
            case 2: gameBoard[0][2] = cpuSymbol;
                    cpuPositions.add(cpuChoice);
                    break;
            case 3: gameBoard[0][4] = cpuSymbol;
                    cpuPositions.add(cpuChoice);
                    break;
            case 4: gameBoard[2][0] = cpuSymbol;
                    cpuPositions.add(cpuChoice);
                    break;
            case 5: gameBoard[2][2] = cpuSymbol;
                    cpuPositions.add(cpuChoice);
                    break;
            case 6: gameBoard[2][4] = cpuSymbol;
                    cpuPositions.add(cpuChoice);
                    break;
            case 7: gameBoard[4][0] = cpuSymbol;
                    cpuPositions.add(cpuChoice);
                    break;
            case 8: gameBoard[4][2] = cpuSymbol;
                    cpuPositions.add(cpuChoice);
                    break;
            case 9: gameBoard[4][4] = cpuSymbol;
                    cpuPositions.add(cpuChoice);
                    break;
            default:
        }  
    }
    
    public static boolean checkPlayerWin(ArrayList<Integer> playerPositions)
    {
        //Our win conditions
        ArrayList<Integer> TopRow = new ArrayList<>();
        TopRow.add(1);
        TopRow.add(2);
        TopRow.add(3);
        ArrayList<Integer> MiddleRow = new ArrayList<>();
        MiddleRow.add(4);
        MiddleRow.add(5);
        MiddleRow.add(6);
        ArrayList<Integer> BottomRow = new ArrayList<>();
        BottomRow.add(7);
        BottomRow.add(8);
        BottomRow.add(9);
        ArrayList<Integer> LeftCol = new ArrayList<>();
        LeftCol.add(1);
        LeftCol.add(4);
        LeftCol.add(7);
        ArrayList<Integer> MiddleCol = new ArrayList<>();
        MiddleCol.add(2);
        MiddleCol.add(5);
        MiddleCol.add(8);
        ArrayList<Integer> RightCol = new ArrayList<>();
        RightCol.add(3);
        RightCol.add(6);
        RightCol.add(9);
        ArrayList<Integer> Cross1 = new ArrayList<>();
        Cross1.add(1);
        Cross1.add(5);
        Cross1.add(9);
        ArrayList<Integer> Cross2 = new ArrayList<>();
        Cross2.add(3);
        Cross2.add(5);
        Cross2.add(7);
        
        //If any of these win conditions are true, then the method will return true
        return playerPositions.containsAll(TopRow) || playerPositions.containsAll(MiddleRow) || playerPositions.containsAll(BottomRow) || 
                playerPositions.containsAll(LeftCol) || playerPositions.containsAll(MiddleCol) || playerPositions.containsAll(RightCol) ||
                playerPositions.containsAll(Cross1) || playerPositions.containsAll(Cross2);
    }
    
    public static boolean checkCpuWin( ArrayList<Integer> cpuPositions)
    {
        //Our win conditions
        ArrayList<Integer> TopRow = new ArrayList<>();
        TopRow.add(1);
        TopRow.add(2);
        TopRow.add(3);
        ArrayList<Integer> MiddleRow = new ArrayList<>();
        MiddleRow.add(4);
        MiddleRow.add(5);
        MiddleRow.add(6);
        ArrayList<Integer> BottomRow = new ArrayList<>();
        BottomRow.add(7);
        BottomRow.add(8);
        BottomRow.add(9);
        ArrayList<Integer> LeftCol = new ArrayList<>();
        LeftCol.add(1);
        LeftCol.add(4);
        LeftCol.add(7);
        ArrayList<Integer> MiddleCol = new ArrayList<>();
        MiddleCol.add(2);
        MiddleCol.add(5);
        MiddleCol.add(8);
        ArrayList<Integer> RightCol = new ArrayList<>();
        RightCol.add(3);
        RightCol.add(6);
        RightCol.add(9);
        ArrayList<Integer> Cross1 = new ArrayList<>();
        Cross1.add(1);
        Cross1.add(5);
        Cross1.add(9);
        ArrayList<Integer> Cross2 = new ArrayList<>();
        Cross2.add(3);
        Cross2.add(5);
        Cross2.add(7);
        
        //If any of these win conditions are true, then the functino will return true
        return cpuPositions.containsAll(TopRow) || cpuPositions.containsAll(MiddleRow) || cpuPositions.containsAll(BottomRow) || 
                cpuPositions.containsAll(LeftCol) || cpuPositions.containsAll(MiddleCol) || cpuPositions.containsAll(RightCol) ||
                cpuPositions.containsAll(Cross1) || cpuPositions.containsAll(Cross2);
    }
    
    public static boolean checkDraw(char[][] gameBoard)
    {
        //Checks if every single playing position is full
        return gameBoard[0][0] != ' ' && gameBoard[0][2] != ' ' && gameBoard[0][4] != ' ' && gameBoard[2][0] != ' ' 
                && gameBoard[2][2] != ' ' && gameBoard[2][4] != ' ' && gameBoard[4][0] != ' ' && gameBoard[4][2] != ' ' && gameBoard[4][4] != ' ';
    }
    
}
