package com.hitg.advent.challenges;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class AbstractDay {

	private static int MAX_LINES_TO_PRINT_INPUT = 5;

	/**
	 * Input file used on challenge.
	 */
	protected Path inputFile;

	/**
	 * Public Constructor.
	 * 
	 * @param inputFile used on challenge.
	 */
	public AbstractDay(Path inputFile) {
		this.inputFile = inputFile;
	}

	protected void printInput() {
		int lineNumber = 1;
		try (Scanner sc = new Scanner(inputFile)) {
			while (sc.hasNextLine() && lineNumber <= MAX_LINES_TO_PRINT_INPUT) {
				System.out.println(sc.nextLine());
				lineNumber++;
			}
			if(sc.hasNextLine()) {
				System.out.println("...");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
