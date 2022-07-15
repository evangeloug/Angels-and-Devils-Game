/**
 * This class indicates an object referring to coordinates on a x-y layer.
 * 
 * The object we have here has characteristics : x-the corresponding x mark,y-the corresponding y mark.It represents a point on a 
 * x-y layer(table) and has methods for setting the coordinates returning coordinates to users and a toString() method which
 * returns the require printed format of our point. 
 * 
 * @author Giorgos Evangelou
 * @since 5/3/2019
 * @version 1.0
 */
public class Point {
	//our point coordinates
	protected int x,y;

	/**
	 * This is the constructor of our Point object.
	 * 
	 * Constructor creates the object by getting x,y coordinates.
	 * 
	 * @param x the corresponding x coordinate in a table
	 * @param y the corresponding y coordinate in a table
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * This method returns the x coordinate.
	 * 
	 * @return x the coordinate from the characteristics
	 */
	public int getX() {
		return x;
	}

	/**
	 * This method gets from the user and changes the x coordinate on our Point object.
	 * 
	 * @param x represents the x coordinate the user gave in order to set it as our Point's x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * This method returns the y coordinate.
	 * 
	 * @return y  the coordinate from the characteristics
	 */
	public int getY() {
		return y;
	}

	/**
	 * This method gets from the user and changes the y coordinate on our Point object.
	 * 
	 * @param y represents the y coordinate the user gave in order to set it as our Point's y
	 */
	public void setY(int y) {
		this.y = y;
	}
	/* 
	 * This methods returns the printed format of our point object
	 */
	public String toString() {
		return "x = "+x+" y = "+y;
	}
	
	
}
