/**
 * This class represents our game board-layer where the Angel Demon game is
 * going to be held on.
 * 
 * This is the 'layer' where our game will be held.User decides the size of it
 * and the game begins. It contains the constructor which is making the NxN
 * chess board(2D character array) by using it's chess board super class
 * constructor. It also contains methods in order to move angel and demon, with
 * the help of a change content method inside the super class chess board. Also
 * there are methods which are checking if angel is trapped, and the toString
 * method which returns the right format of our game table with the help of
 * super class' toString() method.
 * 
 * @author Giorgos Evangelou
 * @since 5/3/2019
 * @version 1.0
 */
public class ADGameBoard extends Chessboard {

	/**
	 * This is the constructor of our object.
	 * 
	 * Constructor receives the size of the chess board(NxN).It's making the board
	 * by calling it's super class (Chessboard) constructor.
	 * 
	 * @param n indicates the size of the game board.
	 */
	public ADGameBoard(int n) {
		super(n);
	}

	/**
	 * This method makes angel move.
	 * 
	 * This method gets the new coordinates for angel as an Angel object and moves
	 * it from the existing point in the board to a new one. The character A
	 * indicates the current angel's position.The previous angel exists in order to
	 * put '+' on his previous position as his is moving to a new one.This methods
	 * uses it's super class method: changeContent to change the character in a
	 * block.
	 * 
	 * @param angel         it's the new coordinates for angel in order to get it
	 *                      move in the board.
	 * @param previousangel contains the coordinates for it's previous position in
	 *                      order to move the 'A' character and set it as '+'
	 */
	public void moveAngel(Angel angel, Angel previousangel) {
		changeContent(previousangel.getX(), previousangel.getY(), '+');
		changeContent(angel.getX(), angel.getY(), 'A');
	}

	/**
	 * This method makes Demon move.
	 * 
	 * This method gets demon's current position and blocks the corresponding chess
	 * board 's position.The 'B' symbol indicates blocked blocks.This methods uses
	 * it's super class method: changeContent to change the character in a block.
	 * 
	 * @param demon contains the new coordinates for the demon,in order to block the
	 *              corresponding block
	 */
	public void moveDemon(Demon demon) {
		changeContent(demon.getX(), demon.getY(), 'B');
	}

	/**
	 * This method is called to tell us if angel is trapped.
	 * 
	 * Angel is trapped if he is surrounded by it's power or more blocked squares
	 * from demon.We are also shaping squares out of our board size and checking the
	 * columns or rows that are inside the board.For columns and rows outside of the
	 * board size we assume that are blocked,as angel cannot jump to them.Also angel
	 * is trapped if all the blocks on the edges are blocked as angel cannot win in
	 * any scenario.
	 * 
	 * @param angel angel's current position
	 * @return true if angel is trapped and false if he is not
	 */
	public boolean trappedAngel(Angel angel) {
		// we are checking if all the edges on the board are blocked
		boolean sidesblocked = rowChecker(0, board.length - 1, 0, angel)
				&& rowChecker(0, board.length - 1, board.length - 1, angel)
				&& columnChecker(0, board.length - 1, 0, angel)
				&& columnChecker(0, board.length - 1, board.length - 1, angel);
		if (sidesblocked)
			return true;
		// this number indicates how far away we are from angel's power
		int distnum = 1;
		// the current and max number of continuous complete squares surrounding angel
		int squaresnum = 0;
		// corresponding angel's coordinates to the board
		int angelx = angel.getX() - 1, angely = angel.getY() - 1;
		// we are checking if the squares surrounding angel are all blocked by using row
		// and column checker methods
		// we are checking a larger square only if the smaller one is made with blocked
		// blocks
		while (squaresnum < angel.getPower()) {
			if (columnChecker(angely - distnum, angely + distnum, angelx - distnum, angel)
					&& columnChecker(angely - distnum, angely + distnum, angelx + distnum, angel)
					&& rowChecker(angelx - distnum, angelx + distnum, angely + distnum, angel)
					&& rowChecker(angelx - distnum, angelx + distnum, angely - distnum, angel)) {
				squaresnum++;
				distnum++;
				// We are stopping it after one of our coordinates becomes out of our angel
				// power,even if the coordinates are out of the board's size
				if ((angely + distnum > (board.length + angel.minEdgeDistance(board.length))
						|| (angely - distnum < (-1 * angel.minEdgeDistance(board.length)))
						|| (angelx + distnum > (board.length + angel.minEdgeDistance(board.length)))
						|| (angelx - distnum < (-1 * angel.minEdgeDistance(board.length)))))
					break;
			} else
				break;
		}
		// if the squares surrounding angel are equal or larger than angel's power then
		// angel is trapped
		return (angel.getPower() <= squaresnum);

	}

	/**
	 * This method checks if a number is out of our board's bounds
	 * 
	 * @param x is the parameter we are going to check
	 * @return true if it's out of bounds and false otherwise
	 */
	protected boolean outBounds(int x) {
		return (x < 0) || (x >= board.length);
	}

	/**
	 * Checking if a specified size of rows on a specific column doesn't have any
	 * unblocked blocks.If angel is closer to the edges than his power,we need from
	 * that specific side not to have unblocked blocks with length the distance
	 * between angel's power and the edge.
	 * 
	 * @param rstart the beginning of the rows we are going to check
	 * @param rend   the end of the rows we are going to check
	 * @param column the column where the above rows exist
	 * @param angel  we are getting the angel in order to get his x coordinate and
	 *               to check if column is in his range
	 * @return true if there are all blocked and false otherwise
	 */
	private boolean rowChecker(int rstart, int rend, int column, Angel angel) {
		// if this column is out of bounds we consider that angel is trapped(in this
		// column)
		if ((outBounds(column)) && (angel.inRange(angel.getX(), column + 1)))
			return true;
		for (; rstart <= rend; rstart++)
			// if block is not out of bounds we check if it's blocked otherwise we consider
			// it blocked
			if (!outBounds(rstart))
				if (board[rstart][column] != 'B')
					return false;
		return true;
	}

	/**
	 * Checking if a specified size of columns on a specific row doesn't have any
	 * unblocked blocks.If angel is closer to the edges than his power,we need from
	 * this specific side not to have unblocked blocks with length the distance
	 * between angel's power and the edge.
	 * 
	 * @param cstart the beginning of the columns we are going to check
	 * @param cend   the end of the columns we are going to check
	 * @param row    the row where the above columns exist
	 * @param angel  we are getting the angel in order to get his y coordinate and
	 *               to check if row is in his range
	 * @return true if there are all blocked and false otherwise
	 */
	private boolean columnChecker(int cstart, int cend, int row, Angel angel) {
		// if this row is out of bounds we consider that angel is trapped(in this row)
		if ((outBounds(row)) && (angel.inRange(row + 1, angel.getY())))
			return true;
		for (; cstart <= cend; cstart++)
			// if block is not out of bounds we check if it's blocked otherwise we consider
			// it blocked
			if (!outBounds(cstart))
				if (board[row][cstart] != 'B')
					return false;
		return true;
	}

	/*
	 * Returns the string which implies to the right format of our chess board
	 * 
	 * @return String the right format of the table
	 */
	public String toString() {
		return super.toString();
	}
}
