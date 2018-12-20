package com.hitg.advent.challenges;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Day02 extends AbstractDay implements DayChallenge {

	/**
	 * Public Constructor.
	 * 
	 * @param inputFile used on challenge.
	 */
	public Day02(Path inputFile) {
		super(inputFile);
	}
	
	@Override
	public void printChallengeName() {
		System.out.println("--- Day 2: Inventory Management System ---");
	}

	@Override
	public void printFirstChallenge() {
		System.out.println("Part One:");
		System.out.println("What is the checksum for your list of box IDs?");
	}

	@Override
	public void printInput() {
		System.out.println("Input:");
		super.printInput();
	}

	@Override
	public void printFirstResult() {
		int result = this.calculateCheckSum();
		System.out.println("Your puzzle answer was " + result + ".");

	}

	@Override
	public void printSecondChallenge() {
		System.out.println("Part Two:");
		System.out.println("What letters are common between the two correct box IDs?");
	}

	@Override
	public void printSecondResult() {
		String result = this.findBoxes();
		System.out.println("Your puzzle answer was " + result + ".");
	}
	
	public int calculateCheckSum() {
		int twoCharsOcurrence = 0;
		int threeCharsOcurrence = 0;
		try (Scanner sc = new Scanner(this.inputFile)) {
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				Map<Character, Integer> hashChars = new HashMap<>();
				for (Character c : line.toCharArray()) {
					if (hashChars.containsKey(c)) {
						hashChars.put(c, hashChars.get(c) + 1);
					} else {
						hashChars.put(c, 1);
					}
				}

				boolean hasDuplicateChars = false;
				boolean hasThreeTimesChars = false;
				for (Character c : hashChars.keySet()) {
					int countCharsOcurrence = hashChars.get(c);
					switch (countCharsOcurrence) {
					case 2:
						hasDuplicateChars = true;
						break;
					case 3:
						hasThreeTimesChars = true;
						break;
					default:
						break;
					}
				}
				if (hasDuplicateChars) {
					twoCharsOcurrence++;
				}
				if (hasThreeTimesChars) {
					threeCharsOcurrence++;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		int checkSum = twoCharsOcurrence * threeCharsOcurrence;
		return checkSum;
	}

	public String findBoxes() {
		Set<String> allCombinations = new HashSet<>();
		String result = "";
		try (Scanner sc = new Scanner(this.inputFile)) {
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				for (int i = 0; i < line.length(); i++) {
					String combination = line.substring(0, i) +"_"+ line.substring(i + 1, line.length());
					if (!allCombinations.add(combination)) {
						result = combination.replace("_", "");
						break;
					}
				}
				if(!"".equals(result)) {
					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
