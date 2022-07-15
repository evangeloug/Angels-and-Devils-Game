import static org.junit.Assert.*;

import org.junit.*;

/**
 * This is a junit to test the the automated Angel Demon
 * game.We are testing it's methods.
 * 
 * @author Giorgos Evangelou
 * @since 15/3/2019
 * @version 1.0
 */
public class AutoAngelGameTest {

	public static void initializationError() {	
	}
	@BeforeClass
	public static void beforeClass() {
		System.out.println("AUTOANGELGAME CLASS IS GOING TO BE TESTED");
	}
	@AfterClass
	public static void afterClass() {
		System.out.println("AUTOANGELGAME IS  TESTED");
	}
	@Before
	public  void beforeMethod() {
		System.out.println("A METHOD IS GOING TO BE TESTED");
	}
	@After
	public  void afterMethod() {
		System.out.println("A METHOD IS  TESTED");
	}
	/**
	 * This method checks if the angel can move through it's method
	 * and if he can win via playing automated through the method.
	 */
	@Test 
	public void testPlayAngel() {
		System.out.println("Testing testPlayAngel method");
		AutoAngelGame adgame=new AutoAngelGame(8,(int)(8/2+1),(int)(8/2+1),1);
		adgame.playAngel();
		//checking if angel moved from his first position
		assertNotEquals(adgame.returnBlock((int)(8/2+1),(int)(8/2+1)),'A');
		//we are moving an angel to an edge to test if he won
		while (!adgame.winAngel()) {
			adgame.playAngel();
		}
		assertTrue(adgame.winAngel());
	}
	
	/**
	 * This method tests demon playing by getting the Demon as a point
	 * on our board.This method moves demon for the user vs user
	 * game.
	 */
	@Test
	public void testMoveDemon() {
		System.out.println("Testing testMoveDemon method");
		//testing movement for the non automated version of the game
		ADGameBoard board=new ADGameBoard(8);
		//by creating demon we are blocking the first block of the board
		Demon demon=new Demon(1,1);
		//the block in the position we want to block but we did not block
		//yet has the symbol +(free block) rather than B(blocked block)
		assertNotEquals(board.returnBlock(demon.getX(), demon.getY()),'B');
		assertEquals(board.returnBlock(demon.getX(), demon.getY()),'+');
		//checking if the blocks after a demon movement are marked
		//with the symbol B which means blocked
		board.moveDemon(demon);
		assertEquals(board.returnBlock(demon.getX(), demon.getY()),'B');
		demon.setX(5);
		demon.setY(4);
		board.moveDemon(demon);
		assertEquals(board.returnBlock(demon.getX(), demon.getY()),'B');
		//blocking an edge
		demon.setX(8);
		demon.setY(8);
		board.moveDemon(demon);
		assertEquals(board.returnBlock(demon.getX(), demon.getY()),'B');
	}
	/**
	 * This method tests angel playing by getting the angel as a point
	 * on our board.This method moves angel for the user vs user
	 * game.
	 */
	@Test
	public void testMoveAngel() {
		System.out.println("Testing testMoveAngel method");
		//testing movement for the non automated version of the game
		ADGameBoard board=new ADGameBoard(8);
		//creating an angel,with power=1, in the middle of the board
		Angel angel=new Angel ((int)(8/2+1),(int)(8/2+1),1);
		//the previous angel position
		Angel previousangel=new Angel(angel);
		board.moveAngel(angel, previousangel);
		assertEquals(board.returnBlock(angel.getX(),angel.getY()),'A');
		//moving angel and testing if his current block has A symbol,which
		//indicates for angel and if his previous position has + symbol
		//which means it's free
		angel.setX(6);
		angel.setY(7);
		board.moveAngel(angel, previousangel);
		assertEquals(board.returnBlock(angel.getX(),angel.getY()),'A');
		assertEquals(board.returnBlock(previousangel.getX(),previousangel.getY()),'+');
		previousangel.setX(angel.getX());
		previousangel.setX(angel.getX());
		angel.setX(2);
		angel.setY(3);
		board.moveAngel(angel, previousangel);
		assertEquals(board.returnBlock(angel.getX(),angel.getY()),'A');
		assertEquals(board.returnBlock(previousangel.getX(),previousangel.getY()),'+');
	}
	/**
	 * This method checks if angel can win in different situations(different board size)
	 */
	@Test 
	public  void testWinAngel() {
		System.out.println("Testing testWinAngel method");
		AutoAngelGame adgame=new AutoAngelGame(8,(int)(8/2+1),(int)(8/2+1),1);
		//angel did not win in his first position with an 8x8 dimension board with power 1
		assertFalse(adgame.winAngel());
		adgame.playAngel();
		//angel did not win by his first move on an 8x8 board with power 1
		assertFalse(adgame.winAngel());
		//we are moving an angel to an edge to test if he won
		while (!adgame.winAngel()) {
			adgame.playAngel();
		}
		assertTrue(adgame.winAngel());
		AutoAngelGame adgame2=new AutoAngelGame(2,(int)(2/2+1),(int)(2/2+1),1);	
		//angel wins in his first position with an 2x2 dimension board
		assertTrue(adgame2.winAngel());
		AutoAngelGame adgame3=new AutoAngelGame(1,(int)(1/2+1),(int)(1/2+1),1);	
		//angel wins in his first position with an 1x1 dimension board
		assertTrue(adgame3.winAngel());
	}

	/**
	 * This method checks if demon can win by blocking the squares
	 * around angel in order to make it impossible for him to move.
	 * Also it checks if demon doesn't win unless he does the above.
	 */
	@Test
	public void testWinDemon() {
		System.out.println("Testing testWinDemon method");
		AutoAngelGame adgame=new AutoAngelGame(6,(int)(6/2+1),(int)(6/2+1),1);
		adgame.playDemon();
		//demon did not win with his first move on the board
		assertFalse(adgame.winDemon());
		//while holding angel still and blocking the squares around him
		//demon must win after blocking angel's power available blocks
		//in a way angel cannot move
		for (int i=1;i<=7;i++) 
			adgame.playDemon();
		assertTrue(adgame.winDemon());
		AutoAngelGame adgame2=new AutoAngelGame(10,(int)(10/2+1),(int)(10/2+1),1);
		for (int i=1;i<=36;i++) 
			adgame2.playDemon();
		//in a larger board we are holding angel still and moving demon to block
		//every edge on the board.He must win after blocking every possible exit for
		//angel
		assertTrue(adgame2.winDemon());
		}
	/**
	 * This method test if the demon can move properly via it's
	 * automated method.Also checks if the demon can block Angel
	 * if we are keeping angel at the same block on different
	 * size boards.
	 */
	@Test
	public void testPlayDemon() {
		System.out.println("Testing testPlayDemon method");
		AutoAngelGame adgame=new AutoAngelGame(6,(int)(6/2+1),(int)(6/2+1),1);
		//we are blocking a block and testing if the block has the B character
		adgame.moveDemon(new Demon(1,1));
		assertEquals(adgame.returnBlock(1, 1),'B');
		//in order to test if demon can win with his movement because this
		//is his target,we are calling testWinDemon method which is already
		//implemented in order to test this
		testWinDemon();
	}

	/**
	 * This method checks if angel is trapped which is equals to
	 * demon win.
	 */
	@Test
	public void testTrappedAngel() {
		System.out.println("Testing testTrappedAngel method");
		AutoAngelGame adgame=new AutoAngelGame(6,(int)(6/2+1),(int)(6/2+1),1);
		//angel is not trapped in his creation block
		assertFalse(adgame.trappedAngel(new Angel((int)(6/2+1),(int)(6/2+1),1)));
		//we are testing if the angel is trapped-if demon won- by
		//calling the TestWinDemon method.Demon wins equals with Angel trapped
		testWinDemon();
	}

	/**
	 * Checking if coordinates are out of our board size.
	 */
	@Test
	public void testOutBounds() {
		System.out.println("Testing testOutBounds method");
		AutoAngelGame adgame=new AutoAngelGame(6,(int)(6/2+1),(int)(6/2+1),1);
		//testing if a coordinate of a block is out of our board's bounds
		assertTrue(adgame.outBounds(8));
		assertTrue(adgame.outBounds(10));
		//checking if a coordinate is inside our board's size
		assertFalse(adgame.outBounds(3));
		//if an edge is inside the board
		assertTrue(adgame.outBounds(6));
	}

	/**
	 * Checking if the content of a block changes correctly.
	 */
	@Test
	public void testChangeContent() {
		System.out.println("Testing testChangeContent method");
		AutoAngelGame adgame=new AutoAngelGame(6,(int)(6/2+1),(int)(6/2+1),1);
		//changing the content of some blocks and checking if it's actually changed
		adgame.changeContent(2, 2, 'A');
		assertEquals(adgame.returnBlock(2, 2),'A');
		assertNotEquals(adgame.returnBlock(2, 2),'+');
		//before changing the value of a block we check if the block
		//does not have that character already filled in
		assertNotEquals(adgame.returnBlock(6, 6),'A');
		assertEquals(adgame.returnBlock(6, 6),'+');
		adgame.changeContent(6, 6, 'A');
		assertEquals(adgame.returnBlock(6, 6),'A');
	}
	/**
	 * Checking if the content of a block is the correct by returning it.
	 */
	@Test
	public void testReturnBlock() {
		System.out.println("Testing testReturnBlock method");
		AutoAngelGame adgame=new AutoAngelGame(6,(int)(6/2+1),(int)(6/2+1),1);
		adgame.changeContent(2, 2, 'A');
		assertEquals(adgame.returnBlock(2, 2),'A');
		assertNotEquals(adgame.returnBlock(2, 2),'+');
		assertEquals(adgame.returnBlock(6, 6),'+');
	}

}
