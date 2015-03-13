// William Thing
// March 10, 2015
//
// This class represents the card game Black Jack
// where the player is against the dealer and tries to beat
// them by having a stronger set of cards limited to 21.
// Contains a subclass BlackJackHand which represents a
// dealers/persons current hand in the game.

// Not quite finished yet.. Still playable but some very minor flaws

import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

public class BlackJack extends DeckOfCards {
	private int numOfPlayers;
	
	public BlackJack() {
		this(1);	
	}
	
	public BlackJack(int numOfPlayers) {
		super();
		this.numOfPlayers = numOfPlayers;
	}
	
	//	post:	plays a round of Black Jack with x # of players and a dealer
	public void playARound() {
		super.shuffle();			// shuffles deck for more randomness
		Scanner console = new Scanner(System.in);		// read players request
		Queue<BJHand> round = new LinkedList<BJHand>();	// keeps ordering of players
		BJHand dealer = new BJHand("dealer");
		iniateHand(dealer);
		for (int i = 1; i <= numOfPlayers; i++) {
			String player = "player" + i;
			BJHand temp = new BJHand(player);
			iniateHand(temp);
			round.add(temp);
		}
		System.out.println("Dealer has " + dealer.getHandVal());
		for (int i = 0; i < numOfPlayers; i++) {
			BJHand current = round.remove();
			String request = "";
			do {
				System.out.println("Dealer: " + current.name + " do you wish to hit? You are at " + current.getHandVal());
				request = console.next();
				if (request.equalsIgnoreCase("yes")) {
					current.dealt(super.draw());
				}
			} while (request.equalsIgnoreCase("yes") && current.getHandVal() < 21);
			round.add(current);		// add player back into queue
		}
		int dealerVal = dealerHand(dealer);
		System.out.println("Dealer has " + dealerVal);
		for (int i = 0; i < numOfPlayers; i++) {
			BJHand current = round.remove();
			int currentVal = current.getHandVal();
			System.out.print("Dealer: " + current.getName() + " you have " + currentVal);
			if (dealerVal > 21) {
				System.out.println(" you win! Dealer bust!");
			} else if (currentVal <= 21 && currentVal > dealerVal) {
				System.out.println(" you win!");
			} else {
				System.out.println(" you lose!");
			}
			System.out.println("round has ended.");
		}
	}
	
	private int dealerHand(BJHand dealer) {
		int dealerVal = dealer.getHandVal();
		while (dealerVal <= 17) {
			int cardVal = super.draw().getValue();
			if (cardVal > 10) {
				cardVal = 10;
			}
			dealerVal += cardVal;
		}
		return dealerVal;
	}
	
	
	
	//	post: initiates given player's Black Jack hand with two cards
	private void iniateHand(BJHand current) {
		current.dealt(super.draw());
		current.dealt(super.draw());
	}

	
	/*	
	 * post:	subclass that represents a players hand in BlackJack
	 */
	private class BJHand {
		private String name;
		private int handVal;
		private Stack<Card> hand;
		
		public BJHand(String name) {	this.name = name; hand = new Stack<Card>();	}
		
		public void dealt(Card add) {
			if (canAdd()) {
				int temp = add.getValue();
				if (temp == 1 && handVal == 10) {
					handVal = 21;
				} else if (temp > 10) {
					handVal += 10;
				} else {
					handVal += temp;
				}
				System.out.println("Dealer: Your card is " + add);
				twentyOne();
				hand.push(add);
			} else {
				if (handVal == 21) {
					System.out.println("Dealer: You already have 21!");
				} else {
					System.out.println("Dealer: You already have Bust!");
				}
			}
			
		}
		
		public int getHandVal() {	return handVal;	}
		
		public String getName() {	return name;	}
		
		private void twentyOne() {
			if (handVal == 21) {
				System.out.println("Dealer: Congrats your at 21!");
			} else if (handVal > 21) {
				System.out.println("Dealer: Oh dear... You Bust!");
			}
		}
		
		public boolean canAdd() {
			return handVal < 21;
		}
	}
	
}
