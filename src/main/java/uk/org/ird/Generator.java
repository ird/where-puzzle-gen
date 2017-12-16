package uk.org.ird;

import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.Charset;
import java.io.BufferedReader;

public class Generator {
	public static ArrayList<String> buildArrayFromFile(String inputFile) throws IOException {
		ArrayList<String> res = new ArrayList<>();
		Charset cs = Charset.forName("US-ASCII");
		BufferedReader reader = Files.newBufferedReader(Paths.get(inputFile), cs);
		String line = null;
		while((line = reader.readLine()) != null) {
			res.add(line);
		}
		return res;
	}
	public static void main(String[] args) {
		ArrayList<String> dexterVictims;
		ArrayList<String> songs;
		try {
			dexterVictims = buildArrayFromFile("dex/victims.txt");
			songs = buildArrayFromFile("dex/songs.txt");
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
			return;
		}
		for(String s : dexterVictims) {
			System.out.println(s);
		}
		for(String s: songs) {
			System.out.println(s);
		}
		//Solution dexterWherePuzzle = new Solution(dexterVictims, songs);
		//System.out.println(dexterWherePuzzle.solve());
	}
}