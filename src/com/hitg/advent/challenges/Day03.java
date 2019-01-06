package com.hitg.advent.challenges;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day03 extends AbstractDay implements DayChallenge {

	private class Claim {
		public int id;
		public int left;
		public int top;
		public int width;
		public int height;
	}
	
	private int squareInchesWithinTwoOrMoreClaims = -1;
	private int idClaimWithoutOverlap = -1;

	/**
	 * Public Constructor.
	 * 
	 * @param inputFile used on challenge.
	 */
	public Day03(Path inputFile) {
		super(inputFile);
	}

	@Override
	public void printChallengeName() {
		System.out.println("--- Day 3: No Matter How You Slice It ---");
	}

	@Override
	public void printFirstChallenge() {
		System.out.println("Part One:");
		System.out.println("If the Elves all proceed with their own plans, "
				+ "none of them will have enough fabric. How many square inches "
				+ "of fabric are within two or more claims?");
	}

	@Override
	public void printInput() {
		System.out.println("Input:");
		super.printInput();
	}

	@Override
	public void printFirstResult() {
		System.out.println("Your puzzle answer was " + squareInchesWithinTwoOrMoreClaims + ".");

	}

	@Override
	public void printSecondChallenge() {
		System.out.println("Part Two:");
		System.out.println("What is the ID of the only claim that doesn't overlap?");
	}

	@Override
	public void printSecondResult() {
		System.out.println("Your puzzle answer was " + idClaimWithoutOverlap + ".");
	}

	private List<Claim> claims = new ArrayList<>();

	private int matrix[][] = new int[1020][1020];

	public void solveChallenge() {
		try (Scanner sc = new Scanner(this.inputFile)) {
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] parse = line.split(" ");
				Claim claim = new Claim();
				claim.id = Integer.parseInt(parse[0].replaceAll("#", ""));
				claim.left = Integer.parseInt(parse[2].split(",")[0]);
				claim.top = Integer.parseInt(parse[2].split(",")[1].replace(":", ""));
				claim.width = Integer.parseInt(parse[3].split("x")[0]);
				claim.height = Integer.parseInt(parse[3].split("x")[1]);
				claims.add(claim);
				fillBox(claim.left, claim.top, claim.width, claim.height);
			}

			squareInchesWithinTwoOrMoreClaims = checkBox();
			this.idClaimWithoutOverlap = findClaimWithoutOverlap();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private int findClaimWithoutOverlap() {
		for (Claim claim : claims) {
			boolean overlap = false;
			for (int i = claim.left; i < (claim.left + claim.width); i++) {
				for (int j = claim.top; j < (claim.top + claim.height); j++) {
					if (matrix[i][j] != 1) {
						overlap = true;
						break;
					}
				}
				if (overlap) {
					break;
				}
			}
			if (!overlap) {
				return claim.id;
			}
		}
		return -1;
	}

	private int checkBox() {
		int count = 0;
		for (int i = 0; i < 1020; i++) {
			for (int j = 0; j < 1020; j++) {
				if (matrix[i][j] > 1) {
					count++;
				}
			}
		}
		return count;
	}

	private void fillBox(int left, int top, int width, int height) {
		for (int i = left; i < (left + width); i++) {
			for (int j = top; j < (top + height); j++) {
				matrix[i][j]++;
			}
		}
	}
}
