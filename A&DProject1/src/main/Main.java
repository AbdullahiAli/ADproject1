package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		try {
			System.setIn(new FileInputStream(new File("extra_4.in")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Scanner stdin = new Scanner(System.in);
		ArrayList<ArrayList> pairs = new ArrayList<ArrayList>();
		ArrayList<Integer> metadata = lineToInt(stdin.nextLine());
		int nrEdges = metadata.get(0);
		int count = 0;
		while (count < nrEdges) {
			pairs.add(lineToInt(stdin.nextLine()));
			count++;
		}
		stdin.close();
		Graph graph = new Graph(metadata, pairs);
		Algorithm algorithm = new Algorithm(graph);
		algorithm.run();

	}

	public static ArrayList<Integer> lineToInt(String line) {
		ArrayList<Integer> data = new ArrayList<Integer>();
		String[] split = line.split("\\s");
		for (String element : split) {
			data.add(Integer.parseInt(element));
		}
		return data;

	}
}
