// William Thing
// 2/25/2015
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays; // testing

public class Poker {
	private DeckOfCards deck;
	private Card[] pool = new Card[5];
	private Queue<Hand> players;
	private int[] shapeCount = new int[4];
	
	private final String[] shapes = {"heart", "diamond", "club", "spade"};
	
	// post:	Constructs a game of Texas Hold'em! for heads up 1 vs 1
	public Poker() {
		this(2);
	}
	
	//	post: Constructs a game of Texas Hold'em! with given number of players
	public Poker(int players) {
		deck = new DeckOfCards();
		this.players = new LinkedList<Hand>();
		for (int i = 0; i < players; i++) {
			// draws two cards for a player
			Hand newPlayer = new Hand(deck.draw(), deck.draw());
			this.players.add(newPlayer); // adds new player to ring
		}
	}
	
	//	post:	draws prints the flop
	public void flop() {
		int count = 0;
		while (count < 3) {
			Card temp = deck.draw();
			for (int i = 0; i < shapes.length; i++) {
				if (temp.getShape().equals(shapes[i])) {
					shapeCount[i]++;
				}
			}
			pool[count] = temp;
			count++;
		}
		System.out.println();
		System.out.println("The flop is:");
		for (int i = 0; i < count; i++) {
			System.out.println(pool[i]);
		}
		System.out.println(Arrays.toString(shapeCount));
	}

	//	post:	draws prints the turn
	public void turn() {
		Card temp = deck.draw();
		pool[3] = temp;
		for (int i = 0; i < shapes.length; i++) {
			if (temp.getShape().equals(shapes[i])) {
				shapeCount[i]++;
			}
		}
		System.out.println();
		System.out.println("The turn is:");
		for (int i = 0; i < 4; i++) {
			System.out.println(pool[i]);
		}
		System.out.println(Arrays.toString(shapeCount));
	}

	//	post:	draws prints the river
	public void river() {
		Card temp = deck.draw();
		pool[4] = temp;
		for (int i = 0; i < shapes.length; i++) {
			if (temp.getShape().equals(shapes[i])) {
				shapeCount[i]++;
			}
		}
		System.out.println();
		System.out.println("The river is:");
		for (int i = 0; i < pool.length; i++) {
			System.out.println(pool[i]);
		}
		System.out.println(Arrays.toString(shapeCount)); // debugging test
	}
	
	//	post: prints the hand of all the players (for testing and debugging)
	public void printHands() {
		int size = players.size();
		for (int i = 0; i < size; i++) {
			Hand temp = players.remove();
			System.out.println("\nPlayer " + (i+1) + ":");
			temp.showHand();
			players.add(temp);
		}
	}
	
	
	public void winner() {
		
	}
	
}
