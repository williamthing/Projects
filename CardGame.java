// William Thing
// 2/25/2015
//


public class CardGame {

	public static void main(String[] args) {
		Poker texasHoldem = new Poker();
		texasHoldem.printHands();
		texasHoldem.flop();
		texasHoldem.turn();
		texasHoldem.river();
		texasHoldem.printHands();
		
	}
}
