package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumberGenerator {
	private static RandomNumberGenerator instance = new RandomNumberGenerator();

	private RandomNumberGenerator() {
	}

	public static RandomNumberGenerator getInstance() {
		return instance;
	}

	/**
	 * For the purpose of this project, lowerBound is 1 (inclusive), upperBound is 1000000 (exclusive)
	 * 
	 * @param sizeOfList
	 * @param lowerBound
	 * @param upperBound
	 * @return
	 * @author Furkan Ozcan
	 */
	public List<Integer> createRandomList(int sizeOfList, int lowerBound, int upperBound) {
		List<Integer> integerList = new ArrayList<Integer>();
		Random random = new Random();
		for (int index = 0; index < sizeOfList; index++) {
			// nextInt's lower bound is 0 by default.
			// To make it compatible with all lower bounds, following is implemented:
			// random.nextInt gives lower bound of 0.
			// Whole expression gives lower bound of lowerBound.
			// random.nextInt gives upper bound of upperBound-lowerBound. Whole expression gives upperBound.
			Integer integer = random.nextInt(upperBound - lowerBound) + lowerBound;
			integerList.add(integer);
		}
		return integerList;
	}

}
