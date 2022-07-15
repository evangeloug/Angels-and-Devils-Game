/**
 * This class creates a chess board.
 * 
 * A square chess board is built by requiring it's size from the user.After that
 * we are filling it with '+' mark. This class has methods for returning the
 * content of a block and the toString() method which returns the right format
 * of the chess board.Also a method which changes the content of a block with a
 * given character.
 * 
 * @author Giorgos Evangelou
 * @since 5/3/2019
 * @version 1.0
 *
 */
public class Chessboard {

	protected char board[][];

	/**
	 * This is the constructor of our object.
	 * 
	 * Constructor requires the size of the chess board and building it and also
	 * filling it with '+' mark.
	 * 
	 * @param size the size of a squared chess board
	 */
	public Chessboard(int size) {
		board = new char[size][size];
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board.length; j++)
				board[i][j] = '+';
	}

	/**
	 * This is a method which changes the content of a block with a given character.
	 * 
	 * @param x         the x coordinate of the block,which we want to change it's
	 *                  content
	 * @param y         the y coordinate of the block,which we want to change it's
	 *                  content
	 * @param character the character we are about to fill in the corresponding
	 *                  block
	 */
	public void changeContent(int x, int y, char character) {
		board[x - 1][y - 1] = character;
	}

	/**
	 * This method returns the content on a position in a board.
	 * 
	 * @param x x coordinate of the wanted position
	 * @param y y coordinate of the wanted position
	 * @return char the character inside this position
	 */
	public char returnBlock(int x, int y) {
		return board[x - 1][y - 1];
	}

	
	/**
	 * This method prints the required format of a chess board(with numbers 
	 * for it's rows).
	 */
	public void print() {
		int size=board.length;
		 String [][]board2=new String[size+2][size+2];
        for (int i=0;i<board2.length;i++) 
        	for (int j=0;j<board2.length;j++)
        		board2[i][j]="";
        for (int i=0;i<board2.length-1;i++) {
        	board2[0][i+1]=""+(i+1);
        	board2[board2.length-1][i+1]=""+(i+1);
        	}
        for (int i=0;i<board2.length-1;i++) {
        	board2[i+1][0]=""+(i+1);
        	board2[i+1][board2.length-1]=""+(i+1);
        	}
        for (int i=1; i<board2.length-1;i++) 
        	for (int j=1;j<board2.length-1;j++) {
        		board2[i][j]=""+board[i-1][j-1];
        	}
        board2[0][0]="";
        board2[board2.length-1][board2.length-1]="";
        board2[0][board2.length-1]="";
        board2[board2.length-1][0]="";
        for(int k=0;k<Integer.toString(size).length();k++) {
        board2[0][0]+=" ";
        board2[board2.length-1][board2.length-1]+=" ";
        board2[0][board2.length-1]+=" ";
        board2[board2.length-1][0]+=" ";}
        int max_length = board2[0][board2.length-1].length();
        for (int i=0; i<board2.length;i++) {
        	for (int j=0;j<board2.length;j++) {
        		int current_length=board2[i][j].length();
        		System.out.print(board2[i][j]);
        		for (int k=0;k<max_length-current_length;k++)
        			System.out.print(" ");
        		System.out.print("  ");
        		
        	}
        	System.out.println();
        }
	}
}
