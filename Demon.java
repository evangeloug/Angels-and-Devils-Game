/**
 * This is a class which makes a Demon object for the Angel Game. 
 * 
 * Demon class is making a demon object for the angel game.It extends from the Point class in order to have demon's
 * coordinates and because demon is a point in a game's chess board.This class contains methods for printing message
 * in order to give x and y coordinates for the upcoming block the user wants to block 
 * 
 * @author Giorgos Evangelou
 * @since 5/3/2019
 * @version 1.0
 *
 */
public class Demon extends Point {
	/**
	 * This is the constructor of our object.
	 * 
	 * In order to construct a demon object we want his position by getting his x and y coordinates.
	 * With the x and y coordinates we are making demon's position as a point in the game board.
	 * 
	 * @param xi x coordinate for angel
	 * @param yi y coordinate for angel
	 */
	public Demon(int xi,int yi) {
		super(xi,yi);
	}
	/**
	 * This method prints a message in order for the user to give us the x coordinate for the next demon's position
	 */
	public void requireXcoordinate() {
		System.out.println("x ? Demon =");
	}
	/**
	 * This method prints a message in order for the user to give us the y coordinate for the next demon's position
	 */
	public void requireYcoordinate() {
		System.out.println("y ? Demon =");
	}

}
