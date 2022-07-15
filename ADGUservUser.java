import java.util.Scanner;

/**
 * This is our test class for the Angel and Demon game
 * 
 * The game is going to be held in the main of this class.We are creating a chess board and the angel is being placed in the middle of the table.
 * Angel's power is given b the user.This power indicates the possible positions for the angel to move.First angel user chooses the position he
 * want to move his angel.After that demon user blocks one block in the chess board.If any of the 2 users gave a position which is out of
 * bounds or  a position which is already blocked,we are printing an error message and we require from them to get another position.The game terminates immediately 
 * if the user gives negative coordinate.
 * The goal of the angel is to move to the edge of the chess board in order to escape,and for the demon to surround angel with blocked blocks
 * with size of angel's power and above in order to secure he won't escape.
 * 
 * @author Giorgos Evangelou
 * @since 5/3/2019
 */
public class ADGUservUser {
	/**
	 * This is a method where we check if a number is negative.If it is we must terminate the 
	 * program, and also displaying a message that we are terminating.
	 * 
	 * @param num the number we are about to check
	 */
	private static void negative(int num) {
		if (num<0) {
			System.out.println("Game is terminated.");
			System.exit(1);
		}
	}
	/**
	 * This method conducts the angel game. 
	 */
	public static void play() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Size of the board ? ");
		//we are checking if the user gave a number
		while (!sc.hasNextInt()) {
			System.out.println("You must enter a number");
			System.out.print("Size of the board ? ");
			sc.next();
		}
		int boardsize=sc.nextInt();
		//while the number is negative or 0 we are requiring a positive number
		while (boardsize<=0) {
			System.out.println("The board size must be a positive number");
			boardsize=sc.nextInt();
		}
		ADGameBoard board=new ADGameBoard(boardsize);
		System.out.print("Power of Angel ? ");
		//we are checking if the user gave a number
		while (!sc.hasNextInt()) {
			System.out.println("You must enter a number");
			System.out.print("Power of Angel ? ");
			sc.next();
		}
		int power=sc.nextInt();
		//while the angel's power number is negative or 0,or larger than board's size we are requiring a positive number
		while ((power<=0)||(power>boardsize)) {
			System.out.println("The Angel's power must be a positive number and smaller than the board's size");
			power=sc.nextInt();
		}
		//creating our angel and demon
		Demon demon=new Demon (0,0);
		Angel angel=new Angel ((int)(boardsize/2+1),(int)(boardsize/2+1),power);
		System.out.println("Begin the game...");
		//variants for angel or demon win
		boolean angelwon=false,demonwon=false;
		//the x,y coordinates the user is going to give
		int angelx,angely,demonx,demony;
		//the previous angel position
		Angel previousangel=new Angel(angel);
		//placing angel to the board
		board.moveAngel(angel, previousangel);
		board.print();
		//checking if angel won
		angelwon=angel.winAngel(boardsize);
		while ((!angelwon)&&(!demonwon)) {
			//getting coordinates for angel and demon.If any of the coordinates is negative we terminate the program.If any of the coordinates
			//are out of bounds to our board or there are out of the Angel's range we require again the corresponding values, otherwise we continue playing
			angel.requireXcoordinate();
			//we are checking if the user gave a number
			while (!sc.hasNextInt()) {
				System.out.println("You must enter a number");
				angel.requireXcoordinate();
				sc.next();
			}
			angelx=sc.nextInt();
			negative(angelx);
			angel.requireYcoordinate();
			//we are checking if the user gave a number
			while (!sc.hasNextInt()) {
				System.out.println("You must enter a number");
				angel.requireYcoordinate();
				sc.next();
			}
			angely=sc.nextInt();
			negative(angely);
			System.out.println("x = "+angelx+" y = "+angely);
			while ((angelx<=0)||(angely>boardsize)||(angely<=0)||(angelx>boardsize)||(!angel.inRange(angelx, angely))
					||(board.returnBlock(angelx, angely)=='B')) {
				System.out.println("Error: inaccessible square.");
				angel.requireXcoordinate();
				//we are checking if the user gave a number
				while (!sc.hasNextInt()) {
					System.out.println("You must enter a number");
					angel.requireXcoordinate();
					sc.next();
				}
				angelx=sc.nextInt();
				negative(angelx);
				angel.requireYcoordinate();
				//we are checking if the user gave a number
				while (!sc.hasNextInt()) {
					System.out.println("You must enter a number");
					angel.requireYcoordinate();
					sc.next();
				}
				angely=sc.nextInt();
				negative(angely);
				System.out.println("x = "+angelx+" y = "+angely);
			}
			angel.setX(angelx);
			angel.setY(angely);
			//we are moving angel
			board.moveAngel(angel, previousangel);
			previousangel.setX(angel.getX());
			previousangel.setY(angel.getY());
			board.print();
			//checking if angel won
			angelwon=angel.winAngel(boardsize);
			if (angelwon)
				break;
			//demon coordinates
			demon.requireXcoordinate();
			//we are checking if the user gave a number
			while (!sc.hasNextInt()) {
				System.out.println("You must enter a number");
				demon.requireXcoordinate();
				sc.next();
			}
			demonx=sc.nextInt();
			negative(demonx);
			demon.requireYcoordinate();
			//we are checking if the user gave a number
			while (!sc.hasNextInt()) {
				System.out.println("You must enter a number");
				demon.requireYcoordinate();
				sc.next();
			}
			demony=sc.nextInt();
			negative(demony);
			demon.setX(demonx);
			demon.setY(demony);
			System.out.println(demon);
			if ((demonx<0)||(demony<0))
				System.exit(0);
			//analyzing demon's coordinates,if there are out of baord's size,equal to the current angel position or they correspond to another
			//blocked block we require to get other coordinates
			while ((demonx<=0)||(demony>boardsize)||(demony<=0)||
					(demonx>boardsize)||(board.returnBlock(demonx, demony)!='+')) {
				System.out.println("Error: inaccessible square.");
				demon.requireXcoordinate();
				//we are checking if the user gave a number
				while (!sc.hasNextInt()) {
					System.out.println("You must enter a number");
					demon.requireXcoordinate();
					sc.next();
				}
				demonx=sc.nextInt();
				negative(demonx);
				demon.requireYcoordinate();
				//we are checking if the user gave a number
				while (!sc.hasNextInt()) {
					System.out.println("You must enter a number");
					demon.requireYcoordinate();
					sc.next();
				}
				demony=sc.nextInt();
				negative(demony);
				demon.setX(demonx);
				demon.setY(demony);
				System.out.println(demon);
			}
			demon.setX(demonx);
			demon.setY(demony);
			//we are blocking a block
			board.moveDemon(demon);
			board.print();
			//our check for coordinates is done
			//checking if angel is trapped==demon won
			demonwon=board.trappedAngel(angel);
		}
		if (angelwon)
			System.out.println("Angel wins.");
		else if (demonwon)
			System.out.println("Demon wins.");
		System.out.println("End of the game.");
		sc.close();
	}
	/**
	 * In this main we going to implement class job, calling the play function for the Angel and Demon game. 
	 * Also it's telling us that this is a stand alone game and not chosen over a category.
	 * 
	 * @param args does not contain anything
	 */
	public static void main(String args[]) {
		System.out.println("The Angel game.");
		System.out.println("This is a stand alone game of user VS user.");
		play();
	}
}
