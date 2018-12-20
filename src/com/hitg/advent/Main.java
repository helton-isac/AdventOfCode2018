package com.hitg.advent;

import java.nio.file.Path;

import com.hitg.advent.challenges.Day01;
import com.hitg.advent.challenges.Day02;
import com.hitg.advent.challenges.Day03;
import com.hitg.advent.challenges.Day04;
import com.hitg.advent.challenges.DayChallenge;

public class Main {

	public static void main(String[] args) {
		System.out.println("- Advent of Code 2018 -");
		System.out.println();
		

		executeChallenge(new Day01(Path.of("res\\input_day01.txt")));
		executeChallenge(new Day02(Path.of("res\\input_day02.txt")));
		executeChallenge(new Day03(Path.of("res\\input_day03.txt")));
		executeChallenge(new Day04(Path.of("res\\input_day04.txt")));
	}
	
	private static void executeChallenge(DayChallenge challenge) {
		System.out.println("----------------------------------------------");
		System.out.println("----------------------------------------------");
		challenge.printChallengeName();
		challenge.printInput();
		challenge.printFirstChallenge();
		challenge.printFirstResult();
		challenge.printSecondChallenge();
		challenge.printSecondResult();
		System.out.println();
	}

}
