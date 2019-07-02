package testers;

import java.util.List;

import sorters.InsertionSort;
import sorters.MergeSort;
import sorters.SelectionSort;
import utils.RandomNumberGenerator;
import utils.ThreadManager;

public class Tester {
	private static final int NUMBER_OF_ITERATIONS = 10;

	public Tester() {
	}

	public static void main(String[] args) throws Throwable {
		// TODO: Edit ThreadManager to be appropriate for Singleton
		
		/*
		for (int iteration = 0; iteration < NUMBER_OF_ITERATIONS; iteration++) {
			ThreadManager threadManager = new ThreadManager(iteration+1);
			threadManager.start();
		}
		*/
		
		
		/*
		MergeSort ms = new MergeSort();
		*/
		
		
		RandomNumberGenerator instanceRandomNumberGenerator = RandomNumberGenerator.getInstance();
		List<Integer> randomList = instanceRandomNumberGenerator.createRandomList(10, 1, 10);
		
		
		/*
		SelectionSort ss = new SelectionSort();
		//System.out.println(randomList.toString());
		long ssStart = System.currentTimeMillis();
		List<Integer> sortedList = ss.sort(randomList);
		long ssFinish = System.currentTimeMillis();
		System.out.println("ss execution: " + (ssFinish - ssStart));
		//System.out.println(sortedList.toString());
		*/
		
		/*
		InsertionSort is = new InsertionSort();
		//System.out.println(randomList.toString());
		long ssStart = System.currentTimeMillis();
		List<Integer> sortedList = is.sort(randomList);
		long ssFinish = System.currentTimeMillis();
		System.out.println("ss execution: " + (ssFinish - ssStart));
		//System.out.println(sortedList.toString());
		*/
	}

}