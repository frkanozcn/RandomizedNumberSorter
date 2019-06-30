package thread;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThreadManager implements Runnable {
	private Thread thread;
	private String threadName;
	private Integer threadNumber;
	
	private static final Integer RANDOM_BOUND = 1000000;
	private static final Integer NUMBER_PER_FILE = 100000;
	private static final String FILE_NAME_PREFIX = "C:\\Users\\Furkan Özcan\\Desktop\\XmlResults\\xmlResult";
	private static final String FILE_NAME_XML = ".xml";
	
	public ThreadManager(Integer threadNumber) {
		this.threadNumber = threadNumber;
		this.threadName = "thread-" + threadNumber.toString();
	}

	/** Thread related functions */
	@Override
	public void run() {
		mainRun(threadNumber);
	}
	
	public void start() {
		this.thread = new Thread(this, threadName);
		this.thread.start();
	}
	/** Thread related functions */
	
	/** MISC */
	private static String createXml(List<Integer> numbers) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder = stringBuilder.append("<sayiList>");
		stringBuilder = stringBuilder.append("\n");
		int sizeOfNumbers = numbers != null ? numbers.size() : 0;
		for (int i = 0; i<sizeOfNumbers; i++) {
			stringBuilder = stringBuilder.append("<sayi sira=\"");
			stringBuilder = stringBuilder.append(i+1);
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
	
	private static void print(String inputString) {
		System.out.println(inputString);
	}
	
	private static void mainRun(Integer iteration) {
		Random random = new Random();
		List<Integer> randomNumberList = new ArrayList<Integer>();
		for (int i = 0; i < NUMBER_PER_FILE; i++) {
			Integer nextInt = random.nextInt(RANDOM_BOUND);
			randomNumberList.add(nextInt);
		}
		int sizeOfRandomNumberList = randomNumberList.size();
		print("Calling createXml for iteration " + iteration.toString() + "...\n");
		long startTime = System.currentTimeMillis();
		String xmlResult = createXml(randomNumberList);
		//print(xmlResult);
		long finishTime = System.currentTimeMillis();
		Long timeElapsed = finishTime - startTime;
		print("createXml finished");
		print("Time elapsed = " + timeElapsed + "ms");
		String completeFileName = FILE_NAME_PREFIX;
		completeFileName = completeFileName.concat(iteration.toString());
		completeFileName = completeFileName.concat(FILE_NAME_XML);
		writeToFile(completeFileName, xmlResult);
	}
	/** MISC */
}
