package testers;

import thread.ThreadManager;

public class Tester {
	private static final int NUMBER_OF_ITERATIONS = 10;

	public Tester() {
	}

	public static void main(String[] args) throws Throwable {
		for (int iteration = 0; iteration < NUMBER_OF_ITERATIONS; iteration++) {
			ThreadManager threadManager = new ThreadManager(iteration+1);
			threadManager.start();
		}
	}

}