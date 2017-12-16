package uk.org.ird;

import java.util.LinkedList;
import java.util.HashMap;
import java.util.ArrayList;

public class SolutionSpace {
	private LinkedList<String> answer;
	private HashMap<Integer, LinkedList<String>> solutionMap;
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
	/**
	 *
	 *
	 *
	 */
	public int findFirstAndRemove(String answer) {
		return 0;
	}
	/**
	 *
	 *
	 *
	 */
	public boolean findAnswer(String answer, int col) {
		return false;
	}
	public String toString() {
		String res = new String();
		for(Integer k : solutionMap.keySet()) {
			res += "Posn " + k.toString() + ": \n";
			res += solutionMap.get(k).toString() + "\n";
		}
		return res;
	}
}
