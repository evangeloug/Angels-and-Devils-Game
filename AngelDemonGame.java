import java.util.Scanner;

/**
 * This class is for playing Angel and Demon Game.
 * 
 * This is a game where in a board(like a chess board) layer an angel and demon are playing to win.Angel tries to 
 * reach an edge of the board and demon tries to surround angel with blocked locks or to block every edge on the board.
 * Angel has a power where indicates him the blocks he is able to move and demon can move anywhere.In this class we
 * are giving the user the option either to play versus another user or watching the computer playing with it's self.
 * 
 * @author Giorgos Evangelou
 * @since 5/3/2019
 * @version 1.0
 *
 */
public class AngelDemonGame {

	/**
	 * This main prints the menu for the user to choose if he want to play or watch the computer play 
	 * and call the corresponding class to held the game he wants.
	 * 
	 * @param args contains nothing
	 * @throws InterruptedException this is an exception that sleep throws when another thread interrupts
	 *  the current thread while sleep is active.
	 */
	public static void main(String args[])throws InterruptedException {
		System.out.println("Welcome to the Angel game.");
		System.out.println("Choose a way to play : ");
		System.out.println("1 - You play versus another user.\n2 - Computer plays versus computer");
		System.out.print("Enter a category : ");
		Scanner sc = new Scanner(System.in);
		while (!sc.hasNextInt()) {
			System.out.println("You must enter a number");
			System.out.print("Enter a category : ");
			sc.next();
		}
		int play=sc.nextInt();
		while ((play!=1)&&(play!=2)) {
			System.out.println("You must enter number 1 or 2.");
			while (!sc.hasNextInt()) {
				System.out.println("You must enter a number");
				System.out.print("Enter a category : ");
				sc.next();
			}
			play=sc.nextInt();
		}
		if (play==1)
			ADGUservUser.play();
		else
			ADGComputervComputer.play();
		sc.close();
	}
}
