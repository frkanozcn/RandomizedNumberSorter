package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import builders.Xml;
import builders.XmlBuilder;
import sorters.SorterFactory;

public class ThreadManager implements Runnable {
	private Thread thread;
	private String threadName;
	private Integer threadNumber;
	private String sortType;
	private static ThreadManager instance;

	private static final Integer RANDOM_BOUND = 10000000;
	private static final Integer NUMBER_PER_FILE = 100000;
	private static final String FILE_NAME_PREFIX = System.getProperty("user.home") + "/Desktop/XmlResults/xmlResult";
	private static final String FILE_NAME_XML = ".xml";

	private ThreadManager(Integer threadNumber, String sortType) {
		this.threadNumber = threadNumber;
		this.sortType = sortType;
		this.threadName = "thread-" + threadNumber.toString();
	}

	/** Thread related functions */
	@Override
	public void run() {
		mainRun(threadNumber, sortType);
	}

	/**
	 * Creates random Integer list
	 * 
	 * @param iteration
	 */
	private static void mainRun(Integer iteration, String sortType) {
		// create random number list
		RandomNumberGenerator randomNumberGenerator = RandomNumberGenerator.getInstance();
		List<Integer> randomList = randomNumberGenerator.createRandomList(NUMBER_PER_FILE, 0, RANDOM_BOUND);

		// create unsorted xml
		String xmlResult = createXmlRandomList(randomList);

		// create file name
		String completeFileName = FILE_NAME_PREFIX;
		completeFileName = completeFileName.concat(iteration.toString());
		completeFileName = completeFileName.concat(FILE_NAME_XML);

		// writeToFile
		writeToFile(completeFileName, xmlResult);

		// random list to hash map with <number, occurence> pairs
		HashMap<Integer, Integer> mapRandom = new HashMap<>(NUMBER_PER_FILE);
		mapRandom = createHashMapFromRandomList(randomList);

		// sort random list
		List<Integer> sortedList = sort(mapRandom, sortType);
		
		// write sortedList to file

	}

	public void start() {
		this.thread = new Thread(this, threadName);
		this.thread.start();
	}

	/** Thread related functions */

	/** Instance */
	public static ThreadManager getInstance(int threadNumber, String sortType) {
		instance = new ThreadManager(threadNumber, sortType);
		return instance;
	}

	/** Instance */

	/** MISC */
	private static String createXmlRandomList(List<Integer> numbers) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder = stringBuilder.append("<sayiList>");
		stringBuilder = stringBuilder.append("\n");
		int sizeOfNumbers = numbers != null ? numbers.size() : 0;
		for (int i = 0; i < sizeOfNumbers; i++) {
			stringBuilder = stringBuilder.append("<sayi sira=\"");
			stringBuilder = stringBuilder.append(i + 1);
			stringBuilder = stringBuilder.append("\">");
			stringBuilder = stringBuilder.append(numbers.get(i).toString());
			stringBuilder = stringBuilder.append("</sayi>");
			stringBuilder = stringBuilder.append("\n");
		}
		stringBuilder = stringBuilder.append("</sayiList>");
		return stringBuilder.toString();
	}

	private static void writeToFile(String fileName, String result) {
		File file = new File(fileName);
		try {
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(result);
			fileWriter.close();
		} catch (IOException e) {
			print(e.toString());
		}
	}

	private static HashMap<Integer, Integer> createHashMapFromRandomList(List<Integer> randomList) {
		// TODO Auto-generated method stub
		return null;
	}

	private static List<Integer> sort(HashMap<Integer, Integer> mapRandom, String sortType) {
		SorterFactory sf = new SorterFactory();
		return null;
	}

	private static void print(String inputString) {
		System.out.println(inputString);
	}

	/** MISC */
}
