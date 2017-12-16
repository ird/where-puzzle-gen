package uk.org.ird;

import java.util.ArrayList;
import java.util.EnumSet;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.charset.Charset;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.WRITE;

public class Generator {
	public static ArrayList<String> buildArrayFromFile(String inputFile) throws IOException {
		return buildArrayFromFileFiltered(inputFile, null);
	}
	public static ArrayList<String> buildArrayFromFileFiltered(String inputFile, ArrayList<String> filter) throws IOException {
		ArrayList<String> res = new ArrayList<>();
		Charset cs = Charset.forName("US-ASCII");
		BufferedReader reader = Files.newBufferedReader(Paths.get(inputFile), cs);
		String line = null;
		while((line = reader.readLine()) != null) {
			if(filter != null) {
				for(String f: filter) {
					if(line.contains(f)){
						line = line.replaceAll("[^a-zA-Z!0-9]", "").toUpperCase();
						res.add(line);
						continue;
					}
				}
			} else {
				line = line.replaceAll("[^a-zA-Z!0-9]", "").toUpperCase();
				res.add(line);
			}
		}
		return res;
	}
	public static void songBankPreprocess(String input, String output) throws IOException {
		Charset cs = Charset.forName("Cp1252");
		Charset cs_write = Charset.forName("US-ASCII");
		OpenOption[] options = { CREATE_NEW, APPEND, WRITE };
		BufferedReader reader = Files.newBufferedReader(Paths.get(input), cs);
		BufferedWriter writer = Files.newBufferedWriter(Paths.get(output), cs_write, options);
		String line = null;
		while((line = reader.readLine()) != null) {
			String[] parts = line.split("<SEP>");
			if(parts.length < 4)
				continue;
			line = parts[2] + parts[3];
			line = line.replaceAll("[^a-zA-Z!0-9]", "").toUpperCase();
			writer.write(line, 0, line.length());
			writer.newLine();
		}
	}
	public static void main(String[] args) {
		
		ArrayList<String> dexterVictims;
		ArrayList<String> songs;
		try {
			//songBankPreprocess("dex/song_titles.txt", "dex/songs.txt");
			dexterVictims = buildArrayFromFile("dex/victims.txt");
			songs = buildArrayFromFileFiltered("dex/songs.txt", dexterVictims);
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
		SolutionSpace dexterWherePuzzle = new SolutionSpace(dexterVictims, songs);
		System.out.println(dexterWherePuzzle);
	}
}