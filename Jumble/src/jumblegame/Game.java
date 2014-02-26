package jumblegame;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main Game class
 * 
 * @author Sarp
 * 
 */
public class Game {

	private final static String DICTIONARY_FOLDER_NAME = "spelldict";
	private static DictionaryHandler dict;

	/*
	 * Main Jumble Game method
	 */
	public static void main(String[] args) {
		// Handle the dictionary
		dict = new DictionaryHandler(DICTIONARY_FOLDER_NAME);

		// For running the program
		boolean runProgram = true;
		while (runProgram) {
			System.out.println("Please enter letters: ");
			Scanner input = new Scanner(System.in);
			while (input.hasNext()) {// handle every word differently
				String wordInput = input.next();
				wordFinder(wordInput.toLowerCase());// Make sure they are lowercase
			}
			input.close();
		}

	}

	/*
	 * Takes String as parameter to try constructing words from combinations of
	 * characters and verifies them
	 */
	private static void wordFinder(String comb) {
		// Array of characters to be used for combinations
		char[] charArr = comb.toCharArray();
		// ArrayList of Strings for String combinations
		ArrayList<String> combinationStr = new ArrayList<String>();
		// Initialize first letter combinations
		for (Character c : charArr) {
			combinationStr.add(c.toString());
		}
		int previousLoc = 0; // previous Last Read Location
		// For all existing amount of letters
		for (int i = 1; i < comb.length(); i++) {
			// For all current existing strings in the array
			int combSize = combinationStr.size();
			// Get the all characters
			for (Character c : charArr) {
				for (int j = previousLoc; j < combSize; j++) {
					// Add them to the combination
					combinationStr.add(combinationStr.get(j) + c);
				}
			}
			previousLoc = combSize;
		}
		for (String word : combinationStr) {
			if (dict.doesWordExist(word)) {
				System.out.println(word + " exists!");
			}
		}
	}

}
