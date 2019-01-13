package com.hitg.advent.challenges;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day06 extends AbstractDay implements DayChallenge {

	private int puzzleAnswerPart1 = 0;
	private int puzzleAnswerPart2 = 0;

	public Day06(Path inputFile) {
		super(inputFile);
	}

	@Override
	public void printChallengeName() {
		System.out.println("--- Day 6: Chronal Coordinates ---");
	}

	@Override
	public void printFirstChallenge() {
		System.out.println("Part One:");
		System.out.println("What is the size of the largest area that isn't infinite?");
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
		System.out.println(
				"What is the size of the region containing all locations which have a total distance to all given coordinates of less than 10000?");
	}

	@Override
	public void printSecondResult() {
		System.out.println("Your puzzle answer was " + puzzleAnswerPart2 + ".");

	}

	List<Point> pointsList = new ArrayList<>();
	int planSizeX = 0;
	int planSizeY = 0;
	int[][] matrix = null;

	@Override
	public void solveChallenge() {
		try (Scanner sc = new Scanner(this.inputFile)) {
			while (sc.hasNextLine()) {
				String pointString = sc.nextLine();
				String[] auxArray = pointString.split(", ");
				int x = Integer.parseInt(auxArray[0]);
				int y = Integer.parseInt(auxArray[1]);
				pointsList.add(new Point(x, y));
				planSizeX = Math.max(x, planSizeX);
				planSizeY = Math.max(y, planSizeY);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		matrix = new int[planSizeY + 1][planSizeX + 1];

		this.solvePart1();
		this.solvePart2();
	}

	private void solvePart1() {
		for (int y = 0; y < matrix.length; y++) {
			for (int x = 0; x < matrix[y].length; x++) {
				Point closestPoint = getClosestPoint(new Point(x, y), this.pointsList);
				if (closestPoint != null) {
					closestPoint.incrementAreaSize();
					if (x <= 1 || x >= planSizeX || y <= 1 || y >= planSizeY + 1) {
						closestPoint.setHasInfinitePaths(true);
					}
				}
			}
		}

		int localLargestAreaSize = 0;
		for (Point point : pointsList) {
			if (!point.hasInfinitePaths()) {
				localLargestAreaSize = Math.max(localLargestAreaSize, point.getAreaSize());
			}
		}
		this.puzzleAnswerPart1 = localLargestAreaSize;
	}

	private void solvePart2() {
		int regionSize = 0;
		int totalDistance = 10000;

		for (int y = 0; y < matrix.length; y++) {
			for (int x = 0; x < matrix[y].length; x++) {
				Point p = new Point(x, y);
				int sumManhattanDistances = 0;
				for (Point point : pointsList) {
					int manhattanDistance = calculateManhattanDistance(p, point);
					sumManhattanDistances += manhattanDistance;

					if (sumManhattanDistances >= totalDistance) {
						break;
					}
				}
				if (sumManhattanDistances < totalDistance) {
					regionSize++;
				}
			}
		}

		this.puzzleAnswerPart2 = regionSize;
	}

	/**
	 * Gets the point with the shortest ManhattanDistance or null if there are more
	 * than one.
	 * 
	 * @param p      Reference Point.
	 * @param points Points to look.
	 * @return Returns the nearest point or null if there are more than one.
	 */
	private Point getClosestPoint(Point p, List<Point> points) {

		int shortestManhattanDistance = Integer.MAX_VALUE;
		Point closestPoint = null;

		Map<Integer, Integer> map = new HashMap<>();
		for (Point point : points) {
			int manhattanDistance = calculateManhattanDistance(point, p);
			if (manhattanDistance == 0) {
				return point;
			}
			if (manhattanDistance <= shortestManhattanDistance) {
				shortestManhattanDistance = manhattanDistance;
				closestPoint = point;
				if (map.containsKey(shortestManhattanDistance)) {
					map.put(shortestManhattanDistance, map.get(shortestManhattanDistance) + 1);
				} else {
					map.put(shortestManhattanDistance, 1);
				}
			}
		}

		return map.get(shortestManhattanDistance) == 1 ? closestPoint : null;
	}

	/**
	 * Calculates the Manhattan Distance between two points.
	 * 
	 * @param a Point a.
	 * @param b Point b.
	 * @return returns the Manhattan Distance between the point a and b.
	 *         |a.x-b.x|+|a.y-b.y|
	 */
	private int calculateManhattanDistance(Point a, Point b) {
		return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
	}

	/**
	 * Class Point.
	 */
	class Point {
		private int x;
		private int y;
		private boolean hasInfinitePaths = false;
		private int areaSize = 0;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public void setHasInfinitePaths(boolean hasInfinitePaths) {
			this.hasInfinitePaths = hasInfinitePaths;
		}

		public boolean hasInfinitePaths() {
			return this.hasInfinitePaths;
		}

		public void incrementAreaSize() {
			this.areaSize++;
		}

		public int getAreaSize() {
			return areaSize;
		}
	}
}
