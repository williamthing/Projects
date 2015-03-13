// William Thing
// 2/25/2015
//
// This class is a deck of playing cards that include
// options such as shuffling the current deck, picking
// the top card of the stack, and start over and print.

import java.util.Random;

public class DeckOfCards {
	private Card[] deckOfCards;
	private int topCard;
	
	//	post:	constructs a deck of playing cards
	public DeckOfCards() {
		deckOfCards = new Card[52];
		topCard = 0;
		for (int i = 0; i <  13; i++) {
			for (int j = 0; j < 4; j++) {
				deckOfCards[i * 4 + j] = new Card(i+1, j);
			}
		}
		shuffle();
	}
	
	//	post:	returns the top card from the deck
	public Card draw() {
		topCard++;
		if (topCard > deckOfCards.length) {
			System.out.println("need to shuffle deck...");
			throw new IllegalArgumentException();
		}
		return deckOfCards[topCard-1];
	}
	
	//	post:	shuffles and rearranges current cards in the deck
	public void shuffle() {
		Random r = new Random();
	    for (int i = topCard; i < deckOfCards.length; i++) {
	    	int index1 = r.nextInt(deckOfCards.length - topCard) + topCard;
	        int index2 = r.nextInt(deckOfCards.length - topCard) + topCard;
	        Card temp = deckOfCards[index2];
	        deckOfCards[index2] = deckOfCards[index1];
	        deckOfCards[index1] = temp;
	    }
	}
	
	//	post:	re-shuffles the entire deck
	public void startOver() {
		topCard = 0;
		shuffle();
	}
	
	//	post:	prints the remaining cards in the deck.
	public void printDeck() {
		for (int i = topCard; i < deckOfCards.length; i++) {
			System.out.println(deckOfCards[i]);
		}
	}
}
