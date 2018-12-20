package com.hitg.advent.challenges;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day01 extends AbstractDay implements DayChallenge {

	/**
	 * Public Constructor.
	 * 
	 * @param inputFile used on challenge.
	 */
	public Day01(Path inputFile) {
		super(inputFile);
	}

	@Override
	public void printChallengeName() {
		System.out.println("--- Day 1: Chronal Calibration ---");
	}

	@Override
	public void printFirstChallenge() {
		System.out.println("Part One:");
		System.out.println("Starting with a frequency of zero, what is the resulting "
				+ "frequency after all of the changes in frequency have been applied?");
	}

	@Override
	public void printInput() {
		System.out.println("Input:");
		super.printInput();
	}

	@Override
	public void printFirstResult() {
		int result = this.calculateResultingFrequency();
		System.out.println("Your puzzle answer was " + result + ".");

	}

	@Override
	public void printSecondChallenge() {
		System.out.println("Part Two:");
		System.out.println("What is the first frequency your device reaches twice?");
	}

	@Override
	public void printSecondResult() {
		int result = this.calculateFrequencyReachesTwice();
		System.out.println("Your puzzle answer was " + result + ".");

	}	
	
	public int calculateResultingFrequency() {
		int frequency = 0;
		try (Scanner sc = new Scanner(this.inputFile)) {
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				frequency += Integer.parseInt(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return frequency;
	}

	public int calculateFrequencyReachesTwice() {
		Set<Integer> frequencySet = new HashSet<>();
		boolean twice = false;
		int frequency = 0;
		frequencySet.add(frequency);
		while (!twice) {
			try (Scanner sc = new Scanner(this.inputFile)) {
				while (sc.hasNextLine()) {
					String line = sc.nextLine();
					frequency += Integer.parseInt(line);
					twice = !frequencySet.add(frequency);
					if (twice) {
						return frequency;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return frequency;
	}
}
