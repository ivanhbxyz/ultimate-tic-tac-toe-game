/*
    Name: Ivan Huerta-Bernal
    Purpose: Project, CS 2336.002


    Analysis: For this project we were tasked to write an implementation for the Ultimate TicTacToe Game. The game is to utilize the concepts learned class
    line decoupling, abstract classes and interfaces, etc. The submittal must be a running/functional game that is properly documented.

    Design:: For my design I opted to use a non abstract DefaultBoard class, being that there is only one standard tictac toe board and write a "BigBoard"
    class that extends the DefaultBoard class.
    There is also an abstract Player class that is extended by the HumanPlayer and CPUPlayer classes.

    * MORE ON DESIGN IN EVERY CLASS

    Testing: Testing is accomplished by running the game using two CPUPlayer objects.


    Class Details:: This is the program driver. It calls for the instantiation of a UTTT_Game object, call the
    UTTT_Game setPlayer method and starts the game by calling the start() method.
 */
public class DriverMain {
    public static void main(String [] args) {
        UTTT_Game newGame = new UTTT_Game();

        // Uncomment for a human player vs CPU game
        //newGame.setPlayers(new HumanPlayer("Player Q", "Q"), new CPUPlayer("Player T", "T"));

        // uncomment for CPU vs CPU demo
        newGame.setPlayers(new CPUPlayer("Player Q", "Q"), new CPUPlayer("Player T", "T"));

        newGame.start();
        System.out.println("Thanks for playing.");
    }
}