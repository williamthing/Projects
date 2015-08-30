// William Thing
// Jan 27, 2015
//
// Prints out an the exact change in US currency:
// quarters, dimes, nickels, and cents
// of a given $x.xx amount. 

import java.util.Arrays;

public class changeDispenser {

   public static final int[] COINS = {25, 10, 5, 1};
   public static final String[] COINWORDS = {"quarter", "dime", "nickel", "cent"};

   public static void main(String[] args) {
      int[] change = new int[4];
      int[] change2 = new int[4];
      changeBack(change, 109);
      int n = convertDollarsToCents(1.09);
      changeBack(change2, n);
      printChange(change);
      printChange(change2);
      
      //System.out.println(Arrays.toString(change));  test
      //System.out.println(Arrays.toString(change2)); test
   }
   
   // pre:  takes an empty array and $ amount
   // post: calculates the given $ amount into number
   //       of coins that should be dispensed indicated in
   //       given array.  
   public static void changeBack(int[] a, int change) {
      for (int i = 0; i < COINS.length; i++) {
         if (change >= COINS[i]) {
            a[i] = change / COINS[i];
            change = change % COINS[i];
         }
      }
   }
   
   // post: converts given $ amount into cents
   public static int convertDollarsToCents(double money) {
	   int dollars = (int) (money * 100);
      return dollars;
   }
  
   // post: prints amount of change
   public static void printChange(int[] a) {
      System.out.print("Your change is ");
      for (int i = 0; i < a.length; i++) {
         if (a[i] != 0) {
            System.out.print(a[i] + " " + COINWORDS[i] + " ");
         }
      }
      System.out.println();
  }
   
}
