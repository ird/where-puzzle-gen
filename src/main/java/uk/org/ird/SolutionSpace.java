package uk.org.ird;

import java.util.LinkedList;
import java.util.HashMap;
import java.util.ArrayList;

public class SolutionSpace {
	private LinkedList<String> answer;
	private HashMap<Integer, LinkedList<String>> solutionMap;
	private int nextIndex = 0;
	/**
	 * Initialises a SolutionSpace and builds the solutionMap from two ArrayLists
	 * @param orderList An array of items which dictate the order of the solution in the puzzle
	 * @param inputStrings An array of strings which may be used to make the solution
	 */
	public SolutionSpace(ArrayList<String> orderList, ArrayList<String> inputStrings) {
		answer = new LinkedList<>();
		solutionMap = new HashMap<>();
		int i = 0;
		for(String item : orderList) {
			LinkedList<String> matches = new LinkedList<>();
			for(String input : inputStrings) {
				if(input.contains(item)){
					matches.add(input);
				}
			}
			solutionMap.put(i, matches);
			i++;	
		}
	}
	public void findPuzzlesFor(String word) {
		System.out.println("Looking for " + word);
		while(true) {
			int col = findFirstAndRemove(word.charAt(0));
			if(findAnswer(word.substring(1), col)) {
				System.out.println("\n" + answer + " at col " + col);
			} else {
				if(nextIndex == solutionMap.keySet().size()-1) {
					System.out.println("No more solutions found");
					break;
				}
			}
			nextIndex = 0;
			answer.clear();
		}
	}
	/**
	 * Finds the first occurance of 'first', removes the entry (so subsequent searches don't use the same start point)
	 * then returns the column in which it was found
	 * @param first
	 * @return the column in which the first letter of the desired word is found
	 */
	private int findFirstAndRemove(char first) { 
		int i = 0;
		for(Integer k : solutionMap.keySet()) {
			for(String s: solutionMap.get(k)) {
				int index = s.indexOf(first);
				if(index != -1) {
					answer.add(s);
					nextIndex = i + 1;
					solutionMap.get(k).remove(s);
					return index;
				}
			}
			i++;
		}
		return 0;
	}
	/**
	 * Search the solutionMap in order to find desired letters. Add the
	 * strings they appear in to the answer list.
	 * @param word
	 * @param col the column of each string to look in
	 * @return true when the word is complete, false if a solution can't be found
	 */
	private boolean findAnswer(String word, int col) {
		for(int k = nextIndex; k < solutionMap.keySet().size(); k++) {
			for(String s: solutionMap.get(k)) {
				if(word.equals(""))
						return true;
				char nextLetter = word.charAt(0);
				if(s.length() <= col)
					continue;
				if(s.charAt(col) == nextLetter) {
					answer.add(s);
					word = word.substring(1);
					break;
				}
			}
		}
		return false;
	}
	public String toString() {
		return answer.toString();
	}
	public void enumerate() {
		for(Integer k : solutionMap.keySet()) {
			System.out.println("Index " + k);
			System.out.println(solutionMap.get(k));
		}
	}
}
