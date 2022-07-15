import java.util.Scanner;

/**
 * This is the automated Angel Demon game.
 * 
 * In this class an Angel Demon game is being conducted, but this time the two 
 * players are the computer it self.In this class an automated game object
 * is constructed and via that object we are able to move angel and demon 
 * and see how the game is handled by the computer.
 * 
 * @author Giorgos Evangelou
 * @since 10/3/2019
 * @version 1.0
 *
 */
public class ADGComputervComputer {
	/**
	 * This is a method where the automated game is being held.
	 * 
	 * @throws InterruptedException this is an exception that sleep throws when another thread interrupts
	 *  the current thread while sleep is active.
	 */
	public static void play() throws InterruptedException {
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
		AutoAngelGame adgame=new AutoAngelGame(boardsize,(int)(boardsize/2+1),(int)(boardsize/2+1),power);
		System.out.println("Begin the game...");
		//variants for angel or demon win
		boolean angelwon=adgame.winAngel();
		boolean demonwon=false;
		adgame.print();
		boolean angelplays=true;
		//while either angel or demon won we continue playing
		while ((!angelwon)&&(!demonwon)) {
			if (angelplays) {
				angelplays=false;
				adgame.playAngel();
				angelwon=adgame.winAngel();
			}
			else {
				angelplays=true;
				adgame.playDemon();
				demonwon=adgame.winDemon();
			}
			adgame.print();
			Thread.sleep(1000);
		}
		if (angelwon)
			System.out.println("Angel wins.");
		else if (demonwon)
			System.out.println("Demon wins.");
		System.out.println("End of the game.");
		sc.close();
	}
	/**
	 * Begins the Angel and Demon game. 
	 * Also it's telling us that this is a stand alone game and not chosen over a category.
	 * 
	 * @param args does not contain anything
	 * @throws InterruptedException this is an exception that sleep throws when another thread interrupts
	 *  the current thread while sleep is active.
	 */
	public static void main(String args[]) throws InterruptedException {		
		System.out.println("The Angel game.");
		System.out.println("This is a stand alone game for computer vs computer.");
		play();
	}
}
