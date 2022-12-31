import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Your implementations of the Boyer Moore string searching algorithm.
 */
public class PatternMatching {
    /**
     * Boyer Moore algorithm that relies on a last occurrence table.
     * This algorithm Works better with large alphabets.
     *
     * Make sure to implement the buildLastTable() method, which will be
     * used in this boyerMoore() method.
     *
     * Note: You may find the getOrDefault() method from Java's Map class useful.
     *
     * You may assume that the passed in pattern, text, and comparator will not be null.
     *
     * @param pattern    The pattern you are searching for in a body of text.
     * @param text       The body of text where you search for the pattern.
     * @param comparator You MUST use this to check if characters are equal.
     * @return           List containing the starting index for each match found.
     */
    public static List<Integer> boyerMoore(CharSequence pattern, CharSequence text, CharacterComparator comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
    	Map<Character, Integer> lastTable = buildLastTable(pattern);
    	List<Integer> matchIndices = new ArrayList<>();
    	int textIndex = 0;
    	
    	while(textIndex <= text.length() - pattern.length()) {	// outerloop: move from left to right on the text
    		int patternIndex = pattern.length() - 1;	// innerloop: move from right to left on the pattern
    		while(patternIndex >= 0 && comparator.compare(text.charAt(textIndex + patternIndex), pattern.charAt(patternIndex)) == 0){	// if the chars at textIndex and patternIndex match, keep comparing
    			patternIndex--;
    		}
    		if(patternIndex == -1) {
    			matchIndices.add(textIndex);	// all the chars match so a match has been found, add it to the indices list and shift the pattern by 1
    			textIndex++;
    			continue;
    		}
    		
    		int lastOccurrence = lastTable.getOrDefault(text.charAt(textIndex + patternIndex), -1);
    		if(lastOccurrence == -1) {
    			textIndex = textIndex + patternIndex + 1;	// if the mismatched char is not found in the last occurrence table, the rest of the pattern can be skipped
    		} else if (lastOccurrence >= patternIndex){
    			textIndex++;	// if the mismatched char has already been compared, shift the pattern by 1
    		} else {
    			int shift = patternIndex - lastOccurrence;	// shift the pattern to align the last occurrence
    			textIndex += shift;
    		}
    	}
    	return matchIndices;    	
    }

    /**
     * Builds the last occurrence table that will be used to run the Boyer Moore algorithm.
     *
     * Note that each char x will have an entry at table.get(x).
     * Each entry should be the last index of x where x is a particular
     * character in your pattern.
     * If x is not in the pattern, then the table will not contain the key x,
     * and you will have to check for that in your Boyer Moore implementation.
     *
     * Ex. pattern = octocat
     *
     * table.get(o) = 3
     * table.get(c) = 4
     * table.get(t) = 6
     * table.get(a) = 5
     * table.get(everything else) = null, which you will interpret in
     * Boyer-Moore as -1
     *
     * If the pattern is empty, return an empty map.
     * You may assume that the passed in pattern will not be null.
     *
     * @param pattern A pattern you are building last table for.
     * @return A Map with keys of all of the characters in the pattern mapping
     *         to their last occurrence in the pattern.
     */
    public static Map<Character, Integer> buildLastTable(CharSequence pattern) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
    	Map<Character, Integer> lastTable = new HashMap<>();
    	for(int i = 0; i < pattern.length(); i++) {
    		lastTable.put(pattern.charAt(i), i);
    	}
    	return lastTable;    	
    }
    
    /**
     * Main method to test the Boyer-Moore pattern matching algorithm
     * @param args
     */
    public static void main(String[] args) {
    	CharSequence pattern = "happy";
    	CharSequence text = "happy birthday to the happiest dude in the happy world";
    	CharacterComparator comparator = new CharacterComparator();
    	System.out.println(boyerMoore(pattern, text, comparator));
    	
    	CharSequence pattern2 = "abacab";
    	CharSequence text2 = "abacbabadcabacabd";
    	System.out.println(boyerMoore(pattern2, text2, comparator));
    	
    	CharSequence pattern3 = "nonsense";
    	CharSequence text3 = "happy birthday to the happiest dude in the happy world";
    	System.out.println(boyerMoore(pattern3, text3, comparator));
    	
    	CharSequence pattern4 = "AA";
    	CharSequence text4 = "AAAAAAAA";
    	System.out.println(boyerMoore(pattern4, text4, comparator));
    	
    	CharSequence pattern5 = "ABAB";
    	CharSequence text5 = "BABABABA";
    	System.out.println(boyerMoore(pattern5, text5, comparator));
    	
    }
}