// William Thing
// 2/25/2015
public class Hand {
	private Card[] hand = new Card[2];
	
	public Hand(Card one, Card two) {
		hand[0] = one;
		hand[1] = two;
	}
	
	public void showHand() {
		for (int i = 0; i < hand.length; i++) {
			System.out.println(hand[i]);
		}
	}

}
