// William Thing
// Google Games
// Fun?!

import java.util.*;
import java.io.*;

public class PokemonShiritori {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner reader = new Scanner(new File("pokemon"));
		Map<Character, List<String>> pokemonMap = new HashMap<Character, List<String>>();
      int count = 0; // counter for debugging
		while (reader.hasNextLine()) {
			String thePokemon = reader.nextLine();
			Character letter = thePokemon.charAt(0);
			if (!pokemonMap.containsKey(letter)) {
				List<String> tempList = new ArrayList<String>();	// creates new list
            pokemonMap.put(letter, tempList);
			}
         pokemonMap.get(letter).add(thePokemon);
         //count++;
		}
      findShiritori(pokemonMap);
      //System.out.println(count);
	}
   
   public static void findShiritori(Map<Character, List<String>> map) {
      // go through every pokemon possible
      Stack<Stack<String>> answers = new Stack<Stack<String>>();             // holds all possible answers
      for (Character letter: map.keySet()) {
         for (String pokemon: map.get(letter)) {
            Stack<String> temp = new Stack<String>();          // temp variable to hold possible answers
            Set<String> seenPokemon = new HashSet<String>();   // set to make sure no duplicates are added
            temp.push(pokemon);
            seenPokemon.add(pokemon);                          // adds first pokemon
            Character lastLetter = pokemon.charAt(pokemon.length()-1);
            findShiritori(map, temp, seenPokemon, lastLetter, answers);
         }
      }
      int max = 0;
      String theAnswer = "";
      while (!answers.isEmpty()) {
         
         Stack<String> possAns = answers.pop();   // a possible answer
         int size = possAns.size();                // size of possible answer
         String firstPokemon = "";                 // track first pokemon
         while (!possAns.isEmpty()) {
            firstPokemon = possAns.pop();
         }
         String line = size + " " + firstPokemon;
         System.out.println(line);
         Scanner lineScan = new Scanner(line);
         int temp = lineScan.nextInt();
         if (temp > max) {
            theAnswer = line;
            max = temp;
         }
      }
      System.out.println(theAnswer);                                                                                  
   }   
    
	private static void findShiritori(Map<Character, List<String>> map, Stack<String> s,
                                             Set<String> seen, Character c, Stack<Stack<String>> answers) {
      // base case
      if (!map.containsKey(c) || seen.containsAll(map.get(c))) {
         answers.add(s);
      } else {
         for (String pokemon: map.get(c)) {
            if (!seen.contains(pokemon)) {
               seen.add(pokemon);
               s.push(pokemon);
               Character lastLetter = pokemon.charAt(pokemon.length()-1);
               findShiritori(map, s, seen, lastLetter, answers);
               // backtracking
               seen.remove(pokemon);
               s.pop();
            }
         }
      }
	}


}