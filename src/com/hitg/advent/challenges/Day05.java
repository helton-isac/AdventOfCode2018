package com.hitg.advent.challenges;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Day05 extends AbstractDay implements DayChallenge {

	public Day05(Path inputFile) {
		super(inputFile);
	}

	@Override
	public void printChallengeName() {
		System.out.println("--- Day 5: Alchemical Reduction ---");
	}

	@Override
	public void printFirstChallenge() {
		System.out.println("Part One:");
		System.out.println("How many units remain after fully reacting the polymer you scanned?");
	}

	@Override
	public void printInput() {
		System.out.println("Input:");
		try (Scanner sc = new Scanner(inputFile)) {
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (line.length() > 20) {
					System.out.println(line.substring(0, 20) + "...");
				} else {
					System.out.println(line);
				}
			}
			if (sc.hasNextLine()) {
				System.out.println("...");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void printFirstResult() {
		System.out.println("Your puzzle answer was " + part1Solution + ".");
		// Não é 11950
	}

	@Override
	public void printSecondChallenge() {
		System.out.println("Part Two:");
		System.out.println(
				"What is the length of the shortest polymer you can produce by removing all units of exactly one type and fully reacting the result?");
	}

	@Override
	public void printSecondResult() {
		System.out.println("Your puzzle answer was " + part2Solution + ".");

	}

	String polymerString = "";
	int part1Solution = -1;
	int part2Solution = -1;

	@Override
	public void solveChallenge() {
		try (Scanner sc = new Scanner(this.inputFile)) {
			while (sc.hasNextLine()) {
				polymerString = sc.nextLine();
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.solvePart1();
		this.solvePart2();
	}

	private void solvePart1() {
		part1Solution = sizeAfterReaction(polymerString);
	}

	private void solvePart2() {

		int shortestPolymer = Integer.MAX_VALUE;

		char A = 'A';
		char Z = 'Z';
		
		for (int i = A; i <= Z; i++) {
			String replacedString = polymerString.replaceAll(Character.toString(i), "")
					.replaceAll(Character.toString(Character.toLowerCase(i)), "");
			int size = sizeAfterReaction(replacedString);
			shortestPolymer = Math.min(size, shortestPolymer);
		}
		part2Solution = shortestPolymer;

	}

	private int sizeAfterReaction(String polymerStringToReact) {
		Deque<Character> polymerReaction = new ArrayDeque<>();
		for (Character c : polymerStringToReact.toCharArray()) {
			Character peekUnit = polymerReaction.peek();
			if (peekUnit == null || Character.toLowerCase(peekUnit) != Character.toLowerCase(c)) {
				polymerReaction.push(c);
			} else {
				if ((Character.isUpperCase(peekUnit) && Character.isLowerCase(c))
						|| (Character.isUpperCase(c) && Character.isLowerCase(peekUnit))) {
					polymerReaction.pop();
				} else {
					polymerReaction.push(c);
				}
			}
		}
		return polymerReaction.size();
	}

}
