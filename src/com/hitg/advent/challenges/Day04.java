package com.hitg.advent.challenges;

import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day04 extends AbstractDay implements DayChallenge {

	int chosenGuardStrategy1 = -1;
	int chosenMinuteStrategy1 = -1;
	int chosenGuardStrategy2 = -1;
	int chosenMinuteStrategy2 = -1;
	int minuteMostUsedToSleepAmmountStrategy2 = -1;

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
		System.out.println("Strategy 1: - What is the ID of the guard you chose multiplied by the minute you chose?");
	}

	@Override
	public void printInput() {
		System.out.println("Input:");
		super.printInput();
	}

	@Override
	public void printFirstResult() {
		System.out.println("Your puzzle answer was " + (chosenGuardStrategy1 * chosenMinuteStrategy1) + ".");
	}

	@Override
	public void printSecondChallenge() {
		System.out.println("Part Two:");
		System.out.println("Strategy 2: What is the ID of the guard you chose multiplied by the minute you chose?");
	}

	@Override
	public void printSecondResult() {
		System.out.println("Your puzzle answer was " + (chosenGuardStrategy2 * chosenMinuteStrategy2) + ".");
	}

	class GuardInfo {
		Date date;
		String hour;
		String action;
	}

	Map<Integer, Integer> guardsXSleepTime = new HashMap<>();
	Map<Integer, Set<GuardInfo>> guardsXGuardsInfo = new HashMap<>();

	Set<GuardInfo> guardsInformation = new TreeSet<>(new Comparator<GuardInfo>() {

		@Override
		public int compare(GuardInfo o1, GuardInfo o2) {
			return o1.date.compareTo(o2.date);
		}

	});

	@Override
	public void solveChallenge() {
		String patternString = "\\[(\\S*)\\s(\\S*)\\]\\s(.*)";
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher;
		try (Scanner sc = new Scanner(this.inputFile)) {
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				matcher = pattern.matcher(line);

				while (matcher.find()) {
					GuardInfo guardInfo = new GuardInfo();
					try {
						guardInfo.date = new SimpleDateFormat("yyyy-MM-dd hh:mm")
								.parse(matcher.group(1) + " " + matcher.group(2));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					guardInfo.hour = matcher.group(2);
					guardInfo.action = matcher.group(3);
					guardsInformation.add(guardInfo);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		patternString = "\\#(\\S*)\\s";
		pattern = Pattern.compile(patternString);

		int currentGuardId = -1;
		int minuteSleep = -1;
		int maxTimeSleeping = -1;
		int guardOwnerMaxTimeSleeping = -1;
		for (GuardInfo info : guardsInformation) {
			if ("falls asleep".equals(info.action)) {
				minuteSleep = Integer.parseInt(info.hour.split(":")[1]);
			} else if ("wakes up".equals(info.action)) {
				guardsXSleepTime.put(currentGuardId, guardsXSleepTime.get(currentGuardId)
						+ (Integer.parseInt(info.hour.split(":")[1]) - minuteSleep));
				if (maxTimeSleeping < guardsXSleepTime.get(currentGuardId)) {
					maxTimeSleeping = guardsXSleepTime.get(currentGuardId);
					guardOwnerMaxTimeSleeping = currentGuardId;
				}
			} else {
				matcher = pattern.matcher(info.action);
				while (matcher.find()) {
					currentGuardId = Integer.parseInt(matcher.group(1));
				}
				if (!guardsXSleepTime.containsKey(currentGuardId)) {
					guardsXSleepTime.put(currentGuardId, 0);
				}
			}
			if (!guardsXGuardsInfo.containsKey(currentGuardId)) {
				guardsXGuardsInfo.put(currentGuardId, new TreeSet<>(new Comparator<GuardInfo>() {

					@Override
					public int compare(GuardInfo o1, GuardInfo o2) {
						return o1.date.compareTo(o2.date);
					}

				}));
			}
			guardsXGuardsInfo.get(currentGuardId).add(info);
		}

		for (Set<GuardInfo> guardsInfo : guardsXGuardsInfo.values()) {
			Map<Integer, Integer> minuteXsleep = new HashMap<>();
			int minuteMostUsedToSleep = -1;
			int minuteMostUsedToSleepAmmount = -1;
			for (GuardInfo info : guardsInfo) {
				if ("falls asleep".equals(info.action)) {
					minuteSleep = Integer.parseInt(info.hour.split(":")[1]);
				} else if ("wakes up".equals(info.action)) {
					int wakeUpMinute = Integer.parseInt(info.hour.split(":")[1]);
					for (int i = minuteSleep; i < wakeUpMinute; i++) {
						if (!minuteXsleep.containsKey(i)) {
							minuteXsleep.put(i, 0);
						}
						minuteXsleep.put(i, minuteXsleep.get(i) + 1);
						if (minuteMostUsedToSleepAmmount < minuteXsleep.get(i)) {
							minuteMostUsedToSleepAmmount = minuteXsleep.get(i);
							minuteMostUsedToSleep = i;
						}
					}
				} else {
					matcher = pattern.matcher(info.action);
					while (matcher.find()) {
						currentGuardId = Integer.parseInt(matcher.group(1));
					}
				}
			}
			if (minuteMostUsedToSleepAmmountStrategy2 < minuteMostUsedToSleepAmmount) {
				minuteMostUsedToSleepAmmountStrategy2 = minuteMostUsedToSleepAmmount;
				chosenGuardStrategy2 = currentGuardId;
				chosenMinuteStrategy2 = minuteMostUsedToSleep;
			}
			if (currentGuardId == guardOwnerMaxTimeSleeping) {
				chosenGuardStrategy1 = guardOwnerMaxTimeSleeping;
				chosenMinuteStrategy1 = minuteMostUsedToSleep;
			}
		}

	}

}
