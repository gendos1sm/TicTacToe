/**
 * The class <b>TicTacToeGame</b> is the class that implements the Tic Tac Toe
 * Game. It contains the grid and tracks its progress. It automatically maintain
 * the current state of the game as players are making moves.
 *
 * 
 */
public class TicTacToeGame {
	/**
	 * The board of the game, stored as a one dimension array.
	 */
	private CellValue[] board;

	/**
	 * level records the number of rounds that have been played so far.
	 */
	private int level;

	/**
	 * gameState records the current state of the game
	 */
	private GameState gameState;

	/**
	 * lines is the number of lines in the grid
	 */
	private int lines;

	/**
	 * columns is the number of columns in the grid
	 */
	private int columns;

	/**
	 * sizeWin is the number of cell of the same type that must be aligned to win
	 * the game
	 */
	private int sizeWin;

	/**
	 * default constructor, for a game of 3x3, which must align 3 cells
	 */
	public TicTacToeGame() {
		// Your Code Here
		lines = 3;
		columns = 3;
		sizeWin = 3;

		level = 0;
		gameState = GameState.PLAYING;
		board = new CellValue[9];
		int i;
		for (i = 0; i < 9; i++){
			board[i] = CellValue.EMPTY;
		}
	}

	/**
	 * constructor allowing to specify the number of lines and the number of columns
	 * for the game. 3 cells must be aligned.
	 * 
	 * @param lines   the number of lines in the game
	 * @param columns the number of columns in the game
	 */
	public TicTacToeGame(int lines, int columns) {
		// Your Code Here
		this.lines = lines;
		this.columns = columns;
		sizeWin = 3;

		level = 0;
		gameState = GameState.PLAYING;
		board = new CellValue[lines*columns];
		int i;
		for (i = 0; i < lines*columns; i++){
			board[i] = CellValue.EMPTY;
		}
	}

	/**
	 * constructor allowing to specify the number of lines and the number of columns
	 * for the game, as well as the number of cells that must be aligned to win.
	 * 
	 * @param lines   the number of lines in the game
	 * @param columns the number of columns in the game
	 * @param sizeWin the number of cells that must be aligned to win.
	 */
	public TicTacToeGame(int lines, int columns, int sizeWin) {
		// Your Code Here
		this.lines = lines;
		this.columns = columns;
		this.sizeWin = sizeWin;

		level = 0;
		gameState = GameState.PLAYING;
		board = new CellValue[lines*columns];
		int i;
		for (i = 0; i < lines*columns; i++){
			board[i] = CellValue.EMPTY;
		}
	}


	/**
	 * getter for the variable lines
	 * 
	 * @return the value of lines
	 */
	public int getLines() {
		// Your Code Here
		return lines;
	}

	/**
	 * getter for the variable columns
	 * 
	 * @return the value of columns
	 */
	public int getColumns() {
		// Your Code Here
		return columns;
	}

	/**
	 * getter for the variable level
	 * 
	 * @return the value of level
	 */
	public int getLevel() {
		// Your Code Here
		return level;
	}

	/**
	 * getter for the variable sizeWin
	 * 
	 * @return the value of sizeWin
	 */
	public int getSizeWin() {
		// Your Code Here
		return sizeWin;
	}

	/**
	 * getter for the variable gameState
	 * 
	 * @return the value of gameState
	 */
	public GameState getGameState() {
		// Your Code Here
		return gameState;
	}

	/**
	 * returns the cellValue that is expected next, in other word, which player (X
	 * or O) should play next. This method does not modify the state of the game.
	 * 
	 * @return the value of the enum CellValue corresponding to the next expected
	 *         value.
	 */
	public CellValue nextCellValue() {
		// Your Code Here
		if (level % 2 == 0){
			return CellValue.X;
		}
		else {
			return CellValue.O;
		}
	}

	/**
	 * returns the value of the cell at index i. If the index is invalid, an error
	 * message is printed out. The behavior is then unspecified
	 * 
	 * @param i the index of the cell in the array board
	 * @return the value at index i in the variable board.
	 */
	public CellValue valueAt(int i) {
		// Your Code Here
		if (i < 0 || i >= lines*columns){
			System.out.println("Error! The index should be between 0 and " + ((lines*columns)-1));
		}
		return board[i];
	}

   /**
	* This method is called by the next player to play at the cell  at index i.
	* If the index is invalid, an error message is printed out. The behaviour is then unspecified
	* If the chosen cell is not empty, an error message is printed out. The behaviour is then unspecified
	* If the move is valide, the board is updated, as well as the state of the game.
	* To faciliate testing, it is acceptable to keep playing after a game is already won. 
	* If that is the case, the a message should be printed out and the move recorded. 
	* The winner of the game is the player who won first
   	* @param i
    *  the index of the cell in the array board that has been selected by the next player
  	*/
	public void play(int i) {

		// your code here
		//check for invalid values or non-empty cells and print the corrosponding msg
		// otherwise, place x or o in the index i, then
		//if the game state is still PLAYING, then check if the current move change the state
		if (board[i] != CellValue.EMPTY){
			System.out.println("Error! This cell has already been played");
		}
		if (i < 0 || i >= lines*columns){
			System.out.println("Error! The index should be between 0 and " + ((lines*columns)-1));
		}
		board[i] = nextCellValue();
		level++;
		if (gameState != GameState.PLAYING){
			System.out.println("Keep playing even though the game is over");
		}
		else {
			setGameState(i);
		}
	}


   /**
	* A helper method which updates the gameState variable
	* correctly after the cell at index i was just set.
	* The method assumes that prior to setting the cell
	* at index i, the gameState variable was correctly set.
	* it also assumes that it is only called if the game was
	* not already finished when the cell at index i was played
	* (the the game was playing). Therefore, it only needs to
	* check if playing at index i has concluded the game
	****************************** 
	*So check if the required number of sizeWin cells are formed to win.
	******************************
    *  the index of the cell in the array board that has just
    * been set
  	*/

	private void setGameState(int index){

		// your code here
		int i, j;
		int count_x = 0;
		int count_o = 0;

		// checking all possible cases
		// checking lines for X and O count
		for (i = 0; i <= lines*columns-columns; i+=columns){
			for (j = i; j < i+columns; j++){
				if (board[j] == CellValue.X){
					count_x++;
					count_o = 0;
					if (count_x == sizeWin){
						gameState = GameState.XWIN;
					}
				}
				if (board[j] == CellValue.O){
					count_o++;
					count_x = 0;
					if (count_o == sizeWin){
						gameState = GameState.OWIN;
					}
				}
				if (board[j] == CellValue.EMPTY){
					count_x = 0;
					count_o = 0;
				}
			}
			count_x = 0;
			count_o = 0;
		}

		// checking columns for X and O count
		count_x = 0;
		count_o = 0;
		for (i = 0; i < columns; i++){
			for (j = i; j < lines*columns; j+=columns){
				if (board[j] == CellValue.X){
					count_x++;
					count_o = 0;
					if (count_x == sizeWin){
						gameState = GameState.XWIN;
					}
				}
				if (board[j] == CellValue.O){
					count_o++;
					count_x = 0;
					if (count_o == sizeWin){
						gameState = GameState.OWIN;
					}
				}
				if (board[j] == CellValue.EMPTY){
					count_x = 0;
					count_o = 0;
				}
			}
			count_x = 0;
			count_o = 0;
		}

		// checking diagonals
		// main diag
		count_x = 0;
		count_o = 0;
		for (i = 0; i < lines*columns; i+=columns+1){
			if (board[i] == CellValue.X){
				count_x++;
				count_o = 0;
				if (count_x == sizeWin){
					gameState = GameState.XWIN;
				}
			}
			if (board[i] == CellValue.O){
				count_o++;
				count_x = 0;
				if (count_o == sizeWin){
					gameState = GameState.OWIN;
				}
			}
			if (board[i] == CellValue.EMPTY){
				count_x = 0;
				count_o = 0;
			}
		}
		// counter diag
		count_x = 0;
		count_o = 0;
		for (i = columns-1; i <= lines*columns-columns; i+=columns-1){
			if (board[i] == CellValue.X){
				count_x++;
				count_o = 0;
				if (count_x == sizeWin){
					gameState = GameState.XWIN;
				}
			}
			if (board[i] == CellValue.O){
				count_o++;
				count_x = 0;
				if (count_o == sizeWin){
					gameState = GameState.OWIN;
				}
			}
			if (board[i] == CellValue.EMPTY){
				count_x = 0;
				count_o = 0;
			}	
		}

		if (level == lines*columns && gameState != GameState.XWIN && gameState != GameState.OWIN){
			gameState = GameState.DRAW;
		}
		
	}



	final String NEW_LINE = System.getProperty("line.separator");
	// returns the OS dependent line separator

   /**
	* Returns a String representation of the game matching
	* the example provided in the assignment's description
	*
   	* @return
    *  String representation of the game
  	*/

	public String toString(){
		// your code here
		// use NEW_LINE defined above rather than \n
		String string_output = "";
		int i, j;
		for (i = 0; i < lines; i++){
			if (i > 0){
				for (j = 0; j < 4*columns-1; j++){
					string_output += "-";
				}
				string_output += NEW_LINE;
			}
			for (j = 0; j < columns; j++){
				if (board[i*columns + j] == CellValue.EMPTY){
					string_output += "   ";
				}
				if (board[i*columns + j] == CellValue.X){
					string_output += " X ";
				}
				if (board[i*columns + j] == CellValue.O){
					string_output += " O ";
				}
				if (j < columns-1){
					string_output += "|";
				}
				else{
					string_output += NEW_LINE;
				}
			}
		}
		return string_output;

	}
}

	

	
	
	
	
	
	
	
	                            

	