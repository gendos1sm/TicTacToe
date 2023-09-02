/**
 * The class <b>ComputerRandomPlayer</b> is the class that controls the computer's plays.
 * 
 * 
 */

import java.util.*;

public class ComputerRandomPlayer implements Player {
	//generate random position at an empty cell!!
	//call game.play(position)
    public void play(TicTacToeGame game) {
        if (game.getGameState() != GameState.PLAYING) {
            System.out.println("Game is over!");
        }
        if (game.getLevel() == game.getLines()*game.getColumns()) {
            System.out.println("Game is over!");
        }

        int position;
        position = Utils.generator.nextInt(game.getLines()*game.getColumns());

        if (game.valueAt(position) != CellValue.EMPTY) {
            while (game.valueAt(position) != CellValue.EMPTY) {
                position = Utils.generator.nextInt(game.getLines()*game.getColumns());
            }
        }
        game.play(position);
    }

}