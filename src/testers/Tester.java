package testers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import utils.ThreadManager;

public class Tester {
	private static final int NUMBER_OF_ITERATIONS = 10;
	private static final String ENTER_SORT_TYPE = "Please enter sort type: Insertion (I), Merge (M), Selection(S): ";
	private static final String INSERTION = "I";
	private static final String MERGE = "M";
	private static final String SELECTION = "S";

	public Tester() {
	}

	public static void main(String[] args) throws Throwable {

		String sortType = getSortTypeFromConsole();

		System.out.println(sortType);

		/*
		for (int instance = 0; instance < NUMBER_OF_ITERATIONS; instance++) {
			ThreadManager threadManager = ThreadManager.getInstance(instance, sortType);
			threadManager.start();
		}
		*/
		
		ThreadManager threadManager = ThreadManager.getInstance(NUMBER_OF_ITERATIONS, sortType);
		
		threadManager.start();

		// TODO: collect all HashMap's from all sorted files
	}

	private static String getSortTypeFromConsole() throws IOException {
		String sortType = "";

		while (!isSortTypeValid(sortType, INSERTION, MERGE, SELECTION)) {
			InputStreamReader inputStream = new InputStreamReader(System.in);
			BufferedReader bufferedReader = new BufferedReader(inputStream);
			System.out.print(ENTER_SORT_TYPE);
			sortType = bufferedReader.readLine();
		}

		return sortType.toUpperCase();
	}

	private static boolean isSortTypeValid(String sortType, String... sortList) {
		int length = sortList.length;
		if (sortType == null)
			return false;
		for (int i = 0; i < length; i++) {
			if (sortType.equalsIgnoreCase(sortList[i]))
				return true;
		}
		return false;
	}

}