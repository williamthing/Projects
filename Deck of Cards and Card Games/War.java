// William Thing
// March 10, 2015
//
// This program plays the card game War
// where initially two players have even piles from a
// deck of cards and continue to draw a single car each
// round to see who will reap the spoils of War!
// The player who has all the cards will be declared
// the winner.

import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;


public class War extends DeckOfCards {
	private Queue<Card> player1;
	private Queue<Card> player2;
	
	// post:	constructs the game war
	public War() {
		super();
		player1 = new LinkedList<Card>();
		player2 = new LinkedList<Card>();
		for (int i = 0; i < 52; i++) {
			if (i % 2 != 0) {
				player1.add(super.draw());
			} else {
				player2.add(super.draw());
			}
		}
	}
	//	post:	each player will draw a single card from their pile, whoever has the higher	
	//			card will take both cards into their respective piles.
	//			If there is a tiebreaker, draws 3 cards and then another declare of war
	//			to see who wins the tie breaker.
	public int declareWar() {
		if (player1.isEmpty()) {
			System.out.println("Player 2 wins!");
			return 0;
		} else if (player2.isEmpty()) {
			System.out.println("Player 1 wins!");
			return 0;
		} else {
			Card one = player1.remove();
			Card two = player2.remove();
			System.out.println(one);
			System.out.println(two);
			
			if (one.compareTo(two) < 0) {
				player1.add(one);
				player1.add(two);
			} else if (one.compareTo(two) > 0) {
				player2.add(one);
				player2.add(two);
			} else {
				tiebreaker(one, two);
			}
			
			System.out.println(player1);
			System.out.println(player2);
			return 1;
		}
	}
	
	//	pre:	if both players draw the same int value card
	//	post:	follows the rules of the card game War
	//			by drawing 3 cards and then another declare of war
	//			to see who wins the tie breaker.
	private void tiebreaker(Card one, Card two) {
		Stack<Card> tiebreaker = new Stack<Card>();
		boolean isTie = false;
		do {
			if (player1.size() < 4) {
				noMoreCards(player1, player2);
				break;
			} 
			if (player2.size() < 4) {
				noMoreCards(player2, player1);
				break;
			}
			tiebreaker.push(one);
			tiebreaker.push(two);
			System.out.println("One..");
			tiebreaker.push(player1.remove());
			tiebreaker.push(player2.remove());
			System.out.println("Two..");
			tiebreaker.push(player1.remove());
			tiebreaker.push(player2.remove());
			System.out.println("Three..");
			tiebreaker.push(player1.remove());
			tiebreaker.push(player2.remove());
			System.out.println("I declare War!");
			one = player1.remove();
			two = player2.remove();
			System.out.println(one);
			System.out.println(two);
			if (one.compareTo(two) < 0) {
				isTie = false;
				spoilsOfWar(tiebreaker, player1);
				player1.add(one);
				player1.add(two);
			} else if (one.compareTo(two) > 0) {
				isTie = false;
				spoilsOfWar(tiebreaker, player2);
				player2.add(one);
				player2.add(two);
			} else {
				isTie = true;
			}
		} while (isTie);
	}
	
	//	pre:	given player has less than four cards, so can not continue tie breaker.
	//	post:	all cards from player with smaller pile will automatically go to other player's pile
	//			and ends game.
	private void noMoreCards(Queue<Card> loser, Queue<Card> winner) {
		while (!loser.isEmpty()) {
			winner.add(loser.remove());
		}
	}
	
	//	post:	after tiebreaker gives all the cards to the winner
	private void spoilsOfWar(Stack<Card> s, Queue<Card> q) {
		while(!s.isEmpty()) {
			q.add(s.pop());
		}
	}
	
}
