package testers;

import thread.ThreadManager;

public class Tester {

	private static final Integer RANDOM_BOUND = 1000000;
	private static final Integer NUMBER_PER_FILE = 10;
	private static final int NUMBER_OF_ITERATIONS = 10;
	private static final String FILE_NAME_PREFIX = "C:\\Users\\Furkan Özcan\\Desktop\\XmlResults\\xmlResult";
	private static final String FILE_NAME_XML = ".xml";

	public Tester() {
	}

	public static void main(String[] args) throws Throwable {
		for (int iteration = 0; iteration < NUMBER_OF_ITERATIONS; iteration++) {
			ThreadManager threadManager = new ThreadManager(iteration+1);
			threadManager.start();
		}
	}

}