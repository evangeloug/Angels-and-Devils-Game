/**
 * This is an object of the automated Angel Demon game.
 * 
 * This class extends from the ADGameBoard class which is the game board of the
 * Angel and Demon game.This class makes possible the game play of the game but
 * with rivals the computer for both angel and demon.There are methods where
 * both angel and demon are moving according to a strategy to increase their
 * possibilities to win the game.Also there are methods which help demon and
 * angel to move by checking the available free blocks on the board.
 * 
 * @author Giorgos Evangelou
 * @since 10/3/2019
 */
public class AutoAngelGame extends ADGameBoard {

	private Angel angel;
	private Angel previousangel;
	private Demon demon;

	/**
	 * This is the constructor of our automated playing class.
	 * 
	 * Constructor is building a board where the game is going to be held on,and
	 * also the angel and demon of our game's board.The board is being built by
	 * calling the constructor of it's superclass(ADGameBoard) and we get starting
	 * positions for angel in order to construct it.Demon is going to move after his
	 * method will get called so we are constructing his outside of the board.We are
	 * moving angel to it's starting position. Also the previousangel object is
	 * being constructed(it contains the previous position of the angel) and at
	 * first he is initialized as a copy of angel.
	 * 
	 * @param size   this is he size of the board we are playing
	 * @param angelx the first x coordinate for angel
	 * @param angely the first y coordinate for angel
	 * @param pow    anglel's power
	 */
	public AutoAngelGame(int size, int angelx, int angely, int pow) {
		super(size);
		angel = new Angel(angelx, angely, pow);
		demon = new Demon(-1, -1);
		previousangel = new Angel(angel);
		moveAngel(angel, previousangel);
	}

	/**
	 * This is a method where angel moves automatically.
	 * 
	 * In order for the angel to move he must follow a strategy.At first he checks
	 * how many of the blocks on the board(points) inside his range to move are
	 * available. If he has available an exit then he exits immediately. If he does
	 * not have one he checks for a block to move on his furthest square inside
	 * his power.If there is a free block there he moves.If he does not have any he
	 * checks for the square below his most further(most further-1 square).If he
	 * finds an available one he moves there otherwise he continues to checks square until
	 * he finds a free block or he reaches the least further square.In case the only
	 * available block is his current position he loses and demon wins.(demon wins
	 * if he made impossible the chances of angel to escape or move)
	 */
	public void playAngel() {
		// free blocks to move inside angel's range
		Point[] blocks = powerAccessible();
		Angel tempprevious = new Angel(previousangel);
		previousangel.setX(angel.getX());
		previousangel.setY(angel.getY());
		// checking if any of the positions angel can move is an exit(board edge)
		// if he can win by moving to that block
		for (int i = 0; i < blocks.length; i++) {
			angel.setX(blocks[i].getX() + 1);
			angel.setY(blocks[i].getY() + 1);
			// if angel can win by moving to an edge then we found his next position
			if (angel.winAngel(board.length)) {
				moveAngel(angel, previousangel);
				break;
			}
		}
		if (!angel.winAngel(board.length)) {
			// if angel can't move to the edge then we restore it's position.
			// the same happens with previous angel as the previous angel becomes
			// the first previous we had
			angel = previousangel;
			previousangel = tempprevious;
			// we are testing if the square on angel's power edge has any free blocks
			// in order to move him to one of them
			blocks = squareAccessible(angel.x - 1 - angel.getPower(), angel.x - 1 + angel.getPower(),
					angel.y - 1 - angel.getPower(), angel.y - 1 + angel.getPower());
			int i = 1;
			// if the square on the edge of angel's power does not have any free blocks
			// we continue testing it's smaller squares one by one until we find a free
			// block
			while (blocks == null) {
				blocks = squareAccessible(angel.x - 1 - angel.getPower() - i, angel.x - 1 + angel.getPower() - i,
						angel.y - 1 - angel.getPower() - i, angel.y - 1 + angel.getPower() - i);
				i++;
			}
			int nextblock = Random(0, blocks.length - 1);
			previousangel.setX(angel.getX());
			previousangel.setY(angel.getY());
			angel.setX(blocks[nextblock].getX() + 1);
			angel.setY(blocks[nextblock].getY() + 1);
			moveAngel(angel, previousangel);
		}
		System.out.println("Angel\n" + angel);
	}

	/**
	 * A method which checks if angel won.
	 * 
	 * @return boolean true if angel won otherwise false
	 */
	public boolean winAngel() {
		return angel.winAngel(board.length);
	}

	/**
	 * A method which checks if demon won(trapped angel=demon win).
	 * 
	 * @return boolean true if demon won otherwise false
	 */
	public boolean winDemon() {
		return trappedAngel(angel);
	}

	// returns a random number between a and b
	private int Random(int a, int b) {
		return a + (int) (Math.random() * (b - a + 1));
	}

	/**
	 * This is a method where demon make his next move.
	 * 
	 * In order for the demon to win the game, he must follow a strategy.At first he
	 * checks if the angel has, in his power range, an exit and blocks it immidietly.After
	 * that he follows his strategy which is a two options strategy.If the size of
	 * the board is small,the angel will escape quickly so he starts blocking blocks
	 * inside angel's range in order to make it impossible for the angel to move.The
	 * second option starts with a big board where angel has to make a lot of moves
	 * in order to escape.If the board is big, demon starts blocking the edges of
	 * the board in order to stop angel from escaping.If he manages to block either
	 * the edges or the surrounding angel blocks,in a way he can't move, the the
	 * demon wins the game.
	 */
	public void playDemon() {
		Point[] blocks = powerAccessible();
		// did demon play
		boolean played = false;
		// if angel has an edge inside his available for him blocks to move
		// the demon blocks it
		for (int i = 0; i < blocks.length; i++) {
			if ((blocks[i].getX() == 0) || (blocks[i].getX() == board.length - 1) || (blocks[i].getX() == 0)
					|| (blocks[i].getX() == board.length - 1)) {
				demon.setX(blocks[i].getX() + 1);
				demon.setY(blocks[i].getY() + 1);
				played = true;
				break;
			}
		}
		if (!played) {
			if (board.length >= 8)
				// we are going to block one of the edges
				blocks = edgesAccessible();
			int blockedbox = Random(0, blocks.length - 1);
			demon.setX(blocks[blockedbox].getX() + 1);
			demon.setY(blocks[blockedbox].getY() + 1);
		}
		moveDemon(demon);
		System.out.println("Demon \n" + demon);
	}

	/**
	 * A method for free spaces to move.
	 * 
	 * This method gets the coordinates of the 4 edges of a square and checks how
	 * many free blocks are in square's perimeter.The free blocks are saved in a
	 * Point array because blocks are point on the game board and they're being
	 * returned. (free blocks have the '+' symbol)
	 * 
	 * @param ystart the y coordinate of the square's beginning
	 * @param yend   the y coordinate of the square's end
	 * @param xstart the x coordinate of the square's beginning
	 * @param xend   the x coordinate of the square's end
	 * @return Point[] the free blocks on the square as points on the board
	 */
	private Point[] squareAccessible(int ystart, int yend, int xstart, int xend) {
		// making a point board which fits all the edges blocks(as points)
		Point[] tempblocks = new Point[4 * (xend - xstart + 1)];
		int freeblocks = 0;
		int tempxstart = xstart;
		int tempystart = ystart;
		for (; xstart <= xend; xstart++)
			if (board[ystart][xstart] == '+') {
				tempblocks[freeblocks] = new Point(ystart, xstart);
				freeblocks++;
			}
		for (; xstart <= xend; xstart++)
			if (board[yend][xstart] == '+') {
				tempblocks[freeblocks] = new Point(yend, xstart);
				freeblocks++;
			}
		for (; ystart <= yend; ystart++)
			if (board[ystart][tempxstart] == '+') {
				tempblocks[freeblocks] = new Point(ystart, tempxstart);
				freeblocks++;
			}
		for (ystart = tempystart; ystart <= yend; ystart++)
			if (board[ystart][xend] == '+') {
				tempblocks[freeblocks] = new Point(ystart, xend);
				freeblocks++;
			}
		if (freeblocks == 0)
			return null;
		Point[] blocks = new Point[freeblocks];
		freeblocks = 0;
		for (; freeblocks < blocks.length; freeblocks++)
			blocks[freeblocks] = tempblocks[freeblocks];
		return blocks;
	}

	/**
	 * Availability in free edges blocks.
	 * 
	 * In this method we are making a point array which represents the free edges'
	 * blocks inside our board.(free blocks have the '+' symbol)Every free edge
	 * block is saved as a point(with it's coordinates) inside the array which will
	 * be returned.
	 * 
	 * @return Point[] an array of free edges' blocks which are points in the board
	 */
	private Point[] edgesAccessible() {
		// making a point board which fits all the edges blocks(as points)
		Point[] tempblocks = new Point[board.length * 4];
		int freeblocks = 0;
		int length = board.length - 1;
		int i = 0;
		for (; i <= length; i++)
			if (board[i][0] == '+') {
				tempblocks[freeblocks] = new Point(i, 0);
				freeblocks++;
			}
		for (i = 0; i <= length; i++)
			if (board[i][length] == '+') {
				tempblocks[freeblocks] = new Point(i, length);
				freeblocks++;
			}
		for (i = 0; i <= length; i++)
			if (board[0][i] == '+') {
				tempblocks[freeblocks] = new Point(0, i);
				freeblocks++;
			}
		for (i = 0; i <= length; i++)
			if (board[length][i] == '+') {
				tempblocks[freeblocks] = new Point(length, i);
				freeblocks++;
			}
		Point[] blocks = new Point[freeblocks];
		for (i = 0; i < blocks.length; i++)
			blocks[i] = tempblocks[i];
		return blocks;
	}

	/**
	 * Availability in free blocks method.
	 * 
	 * This method checks in a specific range which are the free blocks('+' symbol
	 * in the board means free block).In this method we are checking the
	 * availability of blocks inside angel's power.We are storing the free blocks as
	 * point of the board in an array and we return it.
	 * 
	 * @return Point[] an array of free blocks which are points in the board
	 */
	private Point[] powerAccessible() {
		/*
		 * we are going to initialize the beginning and ending of the squares inside
		 * angel's power if angel is closer to an edge than to his further distanced
		 * block we initialize the corresponding start or ending with a board's edge
		 */
		int columnstart = angel.getY() - 1 - angel.getPower();
		int columnend = angel.getY() - 1 + angel.getPower();
		int rowstart = angel.getX() - 1 - angel.getPower();
		int rowend = angel.getX() - 1 + angel.getPower();
		if (outBounds(columnstart))
			columnstart = 0;
		if (outBounds(rowstart))
			rowstart = 0;
		if (outBounds(columnend))
			columnend = board.length - 1;
		if (outBounds(rowend))
			rowend = board.length - 1;
		// we are making a table with all the accessible blocks
		Point[] blocks = null;
		for (; rowstart <= rowend; rowstart++) {
			columnstart = angel.getY() - 1 - angel.getPower();
			if (outBounds(columnstart))
				columnstart = 0;
			for (; columnstart <= columnend; columnstart++) {
				if (board[rowstart][columnstart] == '+') {
					Point[] tempblocks = null;
					if (blocks == null) {
						blocks = new Point[1];
						blocks[0] = new Point(rowstart, columnstart);
					} else {
						tempblocks = new Point[blocks.length + 1];
						for (int i = 0; i < blocks.length; i++)
							tempblocks[i] = blocks[i];
						tempblocks[blocks.length] = new Point(rowstart, columnstart);
						blocks = tempblocks;
					}
				}
			}
		}
		return blocks;
	}
}
