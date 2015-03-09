import java.util.Arrays;

public class changeDispenser {

   public static final int[] COINS = {25, 10, 5, 1};
   public static final String[] COINWORDS = {"quarter", "dime", "nickel", "cent"};

   public static void main(String[] args) {
      int[] change = new int[4];
      changeBack(change, 109);
      System.out.println(Arrays.toString(change));
   }
   
   public static void changeBack(int[] a, int change) {
      for (int i = 0; i < COINS.length; i++) {
         if (change >= COINS[i]) {
            a[i] = change / COINS[i];
            change = change % COINS[i];
         }
      }
   }
}