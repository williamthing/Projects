//	William Thing
// 2/25/2015
//
//	This class represents one of fifty two cards
//	in a deck.

public class Card implements Comparable<Card> {
	private int value;
	private String shape;
	
	private final String[] shapes = {"heart", "diamond", "club", "spade"};
	
	public Card(int value, int shape) {
		this.value = value;
		this.shape = shapes[shape];
	}
	
	//	post:	returns value of this card
	public int getValue() { return value; }
	
	//	post:	returns the shape corresponding to this card
	public String getShape() { return shape; }
	
	//	post:	returns string version of this card
	public String toString() {
		String card = " of " + shape;
		if (value == 1) {
			card = "ace" + card;
		} else if (value == 11) {
			card = "jack" + card;
		} else if (value == 12) {
			card = "queen" + card;
		} else if (value == 13) {
			card = "king" + card;
		} else {
			card = value + card;
		}
		return card;
	}
	
	// post:	takes another card to compare, returns negative if
	//			this card is larger value than other, 0 if same,
	//			and positive if lower.
	public int compareTo(Card other) {
		int thisCard = this.value;
		int otherCard =	other.value;
		if (thisCard == 1) {
			thisCard = 14;
		}
		if (otherCard == 1) {
			otherCard = 14;
		}
		return -(thisCard - otherCard);
	}
	
}

