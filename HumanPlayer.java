/**
 * The class <b>HumanPlayer</b> is the class that controls the human's plays.
 * 
 * 
 */
import java.util.*;
import java.io.Console;

public class HumanPlayer implements Player {
	//read a position to play from the console and call 
	// game.play(position): if the level was advanced after the call, then finish, otherwise repeat and get another position
    public void play(TicTacToeGame game) {
        if (game.getGameState() != GameState.PLAYING) {
            System.out.println("Game is over!");
        }
        if (game.getLevel() == game.getLines()*game.getColumns()) {
            System.out.println("Game is over!");
        }

        int count = 0;
        String user_input;
        int position;

        while (count == 0) {
            System.out.println(game);
            System.out.print(game.nextCellValue() + " to play: ");
            user_input = Utils.console.readLine();
            position = Integer.parseInt(user_input) - 1;

            if (position < 0 || position >= game.getLines()*game.getColumns()){
                System.out.println("Error! Try again! The entry should be between 1 and " + (game.getLines()*game.getColumns()));
            }
            else if (game.valueAt(position) != CellValue.EMPTY){
                System.out.println("Error! Try again! This cell has already been played");
            }
            else {
                game.play(position);
                count = 1;
            }
        }
    }
}