package com.hitg.advent.challenges;

import java.nio.file.Path;

public class Day04 extends AbstractDay implements DayChallenge {

	/**
	 * Public Constructor.
	 * 
	 * @param inputFile used on challenge.
	 */
	public Day04(Path inputFile) {
		super(inputFile);
		this.solveChallenge();
	}

	@Override
	public void printChallengeName() {
		System.out.println("--- Day 4: Repose Record ---");
	}

	@Override
	public void printFirstChallenge() {
		System.out.println("Part One:");
		System.out.println("What is the ID of the guard you chose multiplied by the minute you chose?");
	}

	@Override
	public void printInput() {
		System.out.println("Input:");
		super.printInput();
	}

	@Override
	public void printFirstResult() {
		System.out.println("Your puzzle answer was " + "NULL" + ".");
	}

	@Override
	public void printSecondChallenge() {
		System.out.println("Part Two:");
		System.out.println("UNKNOW");
	}

	@Override
	public void printSecondResult() {
		System.out.println("Your puzzle answer was " + "NULL" + ".");
	}

	public void solveChallenge() {

	}

}
