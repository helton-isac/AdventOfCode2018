package com.hitg.advent.challenges;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Day15 extends AbstractDay implements DayChallenge {

	String puzzleAnswerPart1 = "";
	String puzzleAnswerPart2 = "";

	public Day15(Path inputFile) {
		super(inputFile);
	}

	@Override
	public void printChallengeName() {
		System.out.println("--- Day 15: Unknown ---");
	}

	@Override
	public void printFirstChallenge() {
		System.out.println("Part One:");
		System.out.println("Unknown");
	}

	@Override
	public void printInput() {
		System.out.println("Input:");
		super.printInput();
	}

	@Override
	public void printFirstResult() {
		System.out.println("Your puzzle answer was " + puzzleAnswerPart1 + ".");
	}

	@Override
	public void printSecondChallenge() {
		System.out.println("Part Two:");
		System.out.println("Unknown");
	}

	@Override
	public void printSecondResult() {
		System.out.println("Your puzzle answer was " + puzzleAnswerPart2 + ".");

	}

	@Override
	public void solveChallenge() {
		try (Scanner sc = new Scanner(this.inputFile)) {
			while (sc.hasNextLine()) {
				sc.nextLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.solvePart1();
		this.solvePart2();
	}

	private void solvePart1() {
		this.puzzleAnswerPart1 = "Unknown";
	}

	private void solvePart2() {
		this.puzzleAnswerPart2 = "Unknown";
	}

}
