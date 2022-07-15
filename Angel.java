/**
 * This is a class for an Angel for the Angel Game.
 * 
 * Angel class is making angel object for the angel game.It extends from the Point class in order to have angel's
 * coordinates and because angel is a point in a game's chess board.Angel has a power and he can only move
 * to non-blocked blocks inside his power range.Angel wins if he is at the edges of the game's chess board.
 * This class contains also methods for finding angel's smaller distance from the board's edges and for
 * printing the right format when we require it's coordinates.
 * 
 * @author Giorgos Evangelou
 * @since 5/3/2019
 * @version 1.0
 *
 */
public class Angel extends Point{
	private int power;
	/**
	 * This is the constructor of our object.
	 * 
	 * In order to construct an angel object we want his position by x,y coordinates and his power.
	 * With the x and y coordinates we are making angel's position as a point in the game board.
	 * 
	 * @param x  x coordinate for angel
	 * @param y  y coordinate for angel
	 * @param pow  angel's power
	 */
	public Angel(int x,int y,int pow) {
		super(x,y);
		power=pow;
	}
	/**
	 * This is a copy constructor.
	 * 
	 * Get's an Angel object and make an identical one with same values but different memory addresses
	 * 
	 * @param a the Angel object we are about to copy
	 */
	public Angel(Angel a) {
		this(a.x,a.y,a.power);
	}
	/**
	 * Returns if the x and y point is inside the angel's range according to his power
	 * 
	 * @param xi the x coordinate we are going to check
	 * @param yi the y coordinate we are going to check
	 * @return boolean true if the point is inside the range and false if it's not
	 */
	public boolean inRange(int xi,int yi) {
		return ((Math.abs((x-1)-(xi-1))<=power)&&(Math.abs((y-1)-(yi-1))<=power));
	}
	/**
	 * This method returns the angel's power
	 * 
	 * @return the power of angel
	 */
	public int getPower() {
		return power;
	}
	/**
	 * This method prints a message in order for the user to give us the x coordinate for the next angel's position
	 */
	public void requireXcoordinate() {
		System.out.println("x ? Angel =");
	}
	/**
	 * This method prints a message in order for the user to give us the y coordinate for the next angel's position
	 */
	public void requireYcoordinate() {
		System.out.println("y ? Angel =");
	}
	/**
	 * A method which returns the minimum distance the angel has from the edges
	 * 
	 * @param boardsize it's the size of the board we are playing
	 * @return the minimum distance angel has from he edges
	 */
	public int minEdgeDistance(int boardsize) {
		//minimum distance on x-axis
		int minx=Math.min(x-1,boardsize-x);
		//minimum distance on y-axis
		int miny=Math.min(y-1,boardsize-y);		
		//minimum distance between the axis
		return Math.min(minx, miny);
	}
	/**
	 * This method returns if angel has won
	 * 
	 * We are checking if any of the current coordinates of the angel are equal either with 0 or with board's length minus 1.
	 * If it is angel has won the game. 
	 * 
	 * @param boardsize it's the size of the board we are playing
	 * @return true if angel won and false if he didn't
	 */
	public boolean winAngel(int boardsize) {
		int angelx=x-1;
		int angely=y-1;
		return ((angelx==0)||(angelx==boardsize-1)||(angely==0)||(angely==boardsize-1));
	}
}
