/**
 * The class <b>TicTacToe</b> is the class that implements the actual Tic Tac
 * Toe game, where it
 * controls the human and computer activity and prints the result of the game at
 * the end. It also
 * asks the player if he/she wants to continue playing once this game is over.
 * 
 * 
 */
import java.util.*;
import java.util.Scanner;

public class TicTacToe {

    /**
     * <b>main</b> of the application. Creates the instance of GameController
     * and starts the game. If two parameters line and column
     * are passed, they are used.
     * Otherwise, a default value is used. Defaults values are also
     * used if the paramters are too small (less than 2).
     * 
     * @param args
     *             command line parameters
     */
    public static void main(String[] args) {    
		
		//default values used if args are not there:
        int lines = 3;
        int columns = 3;
        int win = 3;

        //change lines, columns and win based on the args values

        if (args.length >= 2) {
            lines = Integer.parseInt(args[0]);
            if(lines<2){
                System.out.println("Invalid argument, using default...");
                lines = 3;
            }
            columns = Integer.parseInt(args[1]);
            if(columns<2){
                System.out.println("Invalid argument, using default...");
                columns = 3;
            }
        }
        if (args.length == 3){
            win = Integer.parseInt(args[2]);
            if(win<2){
                System.out.println("Invalid argument, using default...");
                win = 3;
            }
        } 
        
        if (args.length > 3){
            System.out.println("Too many arguments. Only the first 3 are used.");
        }


		//define an array (say p) of two players (use interface playe for the refernce)
        Player[] p;
        p = new Player[2];
		// The first playe is an object of type HumanPlayer and 
        p[0] = new HumanPlayer();
		// the second player is an object of type  ComputerRandomPlayer()
        p[1] = new ComputerRandomPlayer();
		
		//choose player randomly (p[0] or p[1])
        int i = Utils.generator.nextInt(2);
        int next_start = i; 
		
		// create a refernce to an object of type TicTacToeGame
        TicTacToeGame game;
		
		// loop until the input is not 'y' 
		do {
		     // create object for TicTacToeGame
            game = new TicTacToeGame(lines, columns, win);

		     // for loop that prints who's turn it is, the board, and who is to play, until
             // the game ends
            for (int j = 0; j <= game.getLines()*game.getColumns(); j++) {
                if (i == 0) {
                    System.out.println("Player 1's turn.");
                    p[0].play(game);
                    i = 1;
                    if (game.getGameState() != GameState.PLAYING) {
                        break;
                    }
                }
                if (i == 1) {
                    System.out.println("Player 2's turn.");
                    p[1].play(game);
                    i = 0;
                    if (game.getGameState() != GameState.PLAYING) {
                        break;
                    }
                }
            }

		     // prints result of game and ask if you want to play again
            System.out.println("Game over");
            System.out.println(game);
            System.out.println("Result: " + game.getGameState());
            System.out.println("Play again (Y or N)? ");
            // choose another player to start if answer 'y'
            if (next_start == 0) {
                i = 1;
                next_start = 1;
            }
            else {
                i = 0;
                next_start = 0;
            }

		}while(Utils.console.readLine().compareToIgnoreCase("y") == 0);


    }
}
