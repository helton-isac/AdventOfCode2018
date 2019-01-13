package com.hitg.advent;

import java.nio.file.Path;

import com.hitg.advent.challenges.*;
import com.hitg.advent.challenges.DayChallenge;

public class Main {

	public static void main(String[] args) {
		System.out.println("- Advent of Code 2018 -");
		System.out.println();

		executeChallenge(new Day01(Path.of("res\\input_day01.txt")));
		executeChallenge(new Day02(Path.of("res\\input_day02.txt")));
		executeChallenge(new Day03(Path.of("res\\input_day03.txt")));
		executeChallenge(new Day04(Path.of("res\\input_day04.txt")));
		executeChallenge(new Day05(Path.of("res\\input_day05.txt")));
		executeChallenge(new Day06(Path.of("res\\input_day06.txt")));
		executeChallenge(new Day07(Path.of("res\\input_day07.txt")));
		executeChallenge(new Day08(Path.of("res\\input_day08.txt")));
		executeChallenge(new Day09(Path.of("res\\input_day09.txt")));
		executeChallenge(new Day10(Path.of("res\\input_day10.txt")));
		executeChallenge(new Day11(Path.of("res\\input_day11.txt")));
		executeChallenge(new Day12(Path.of("res\\input_day12.txt")));
		executeChallenge(new Day13(Path.of("res\\input_day13.txt")));
		executeChallenge(new Day14(Path.of("res\\input_day14.txt")));
		executeChallenge(new Day15(Path.of("res\\input_day15.txt")));
		executeChallenge(new Day16(Path.of("res\\input_day16.txt")));
		executeChallenge(new Day17(Path.of("res\\input_day17.txt")));
		executeChallenge(new Day18(Path.of("res\\input_day18.txt")));
		executeChallenge(new Day19(Path.of("res\\input_day19.txt")));
		executeChallenge(new Day20(Path.of("res\\input_day20.txt")));
		executeChallenge(new Day21(Path.of("res\\input_day21.txt")));
		executeChallenge(new Day22(Path.of("res\\input_day22.txt")));
		executeChallenge(new Day23(Path.of("res\\input_day23.txt")));
		executeChallenge(new Day24(Path.of("res\\input_day24.txt")));
		executeChallenge(new Day25(Path.of("res\\input_day25.txt")));
	}

	private static void executeChallenge(DayChallenge challenge) {
		System.out.println("----------------------------------------------");
		System.out.println("----------------------------------------------");
		challenge.printChallengeName();
		challenge.printInput();
		System.out.println("Solving Challenges (Part 1 and Part 2)...");
		long startTime = System.currentTimeMillis();
		challenge.solveChallenge();
		long estimatedTime = System.currentTimeMillis() - startTime;		
		System.out.println("Challenges solved in " + estimatedTime + " milliseconds");
		challenge.printFirstChallenge();
		challenge.printFirstResult();
		challenge.printSecondChallenge();
		challenge.printSecondResult();
		System.out.println();
	}

}
