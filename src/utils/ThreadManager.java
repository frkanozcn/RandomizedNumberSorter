package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

import builders.Xml;
import sorters.SorterFactory;

public class ThreadManager implements Runnable {
	private List<ThreadInfo> threadInfoList;
	private Integer threadNumber;
	private String sortType;
	private static Integer totalInstance; // equals to 
	private static ThreadManager instance;

	private static final Integer RANDOM_BOUND = 1000000;
	private static final Integer NUMBER_PER_FILE = 10000;
	private static final String FILE_DIRECTORY = System.getProperty("user.home") + "/Desktop/XmlResultsFurkanOzcan/";
	private static final String FILE_NAME_PREFIX = "xmlResult";
	private static final String FILE_NAME_PREFIX_SORTED = "xmlResultSorted";
	private static final String FILE_NAME_PREFIX_SORTED_FINAL = "xmlResultSortedFinal";
	private static final String FILE_NAME_XML = ".xml";

	private ThreadManager(Integer threadNumber, String sortType) {
		List<ThreadInfo> threadInfoList = new ArrayList<ThreadInfo>();
		for (int i = 0; i < threadNumber; i++) {
			String threadName = "thread-" + threadNumber.toString();
			Thread thread = new Thread(this, threadName);
			ThreadInfo threadInfo = new ThreadInfo(i, thread);
			threadInfoList.add(threadInfo);
		}
		this.threadInfoList = threadInfoList;
		this.sortType = sortType;
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
		long startTime = System.currentTimeMillis();
		// create random number list
		RandomNumberGenerator randomNumberGenerator = RandomNumberGenerator.getInstance();
		List<Integer> randomList = randomNumberGenerator.createRandomList(NUMBER_PER_FILE, 0, RANDOM_BOUND);

		// create unsorted xml
		String xmlResult = createXmlRandomList(randomList);

		// writeToFile
		writeToFile(xmlResult, iteration, false);

		// random list to Xml list
		List<Xml> xmlList = createXmlListFromHashMappedRandomList(randomList);
		
		// sort random list
		List<Xml> sortedXmlList = sort(xmlList, sortType);

		// TODO: write sortedList to file
		String xmlResultSorted = createXmlSortedList(sortedXmlList);

		// writeToFile
		writeToFile(xmlResultSorted, iteration, true);
		long finishTime = System.currentTimeMillis();
		System.out.println("Execution time for thread-" + iteration + ": " + (finishTime - startTime));

	}

	public void start() throws InterruptedException, IOException {
		for (ThreadInfo threadInfo : this.threadInfoList) {
			this.threadNumber = threadInfo.getThreadNumber();
			threadInfo.getThread().start();
			threadInfo.getThread().sleep(100);
		}
		
		for (ThreadInfo threadInfo : this.threadInfoList) {
			threadInfo.getThread().join();
		}
		
		handleAfterSort();
	}

	/** Thread related functions 
	 * @throws IOException */

	private void handleAfterSort() throws IOException {
		int totalInstance = this.totalInstance;
		HashMap<Integer, Integer> wholeMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < totalInstance; i++) {
			Integer index = i;
			StringBuilder sbFileName = new StringBuilder();
			sbFileName = sbFileName.append(FILE_DIRECTORY);
			sbFileName = sbFileName.append(FILE_NAME_PREFIX_SORTED);
			sbFileName = sbFileName.append(index.toString());
			sbFileName = sbFileName.append(FILE_NAME_XML);
			String fileName = sbFileName.toString();
			File file = new File(fileName);
			InputStream is = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = br.readLine(); // this is "<sortedList>", just skip
			Integer currLine = 0;
			line = br.readLine();
			currLine++;
			while (line != null && !line.equalsIgnoreCase("</sortedList>")) {
				int lineLength = line.length();
				int substringStart = 21 + currLine.toString().length();
				String substring_1 = line.substring(substringStart, lineLength);
				int indexOfSayacEnd = substring_1.indexOf("\"");
				String occurence = substring_1.substring(0, indexOfSayacEnd);
				int indexOfSayiEnd = substring_1.indexOf("<");
				String number = substring_1.substring(occurence.length() + 2, indexOfSayiEnd);
				Integer occurenceInteger = Integer.valueOf(occurence);
				Integer numberInteger = Integer.valueOf(number);
				if (wholeMap.containsKey(numberInteger)) {
					Integer currOccurence = wholeMap.get(numberInteger);
					wholeMap.put(numberInteger, currOccurence + occurenceInteger);
				} else {
					wholeMap.put(numberInteger, occurenceInteger);
				}
				line = br.readLine();
				currLine++;
			}
		}
		List<Xml> xmlList = createXmlListFromHashMap(wholeMap);
		List<Xml> sortedXmlList = sort(xmlList, sortType);
		String xmlResult = createXmlSortedList(sortedXmlList);
		writeToFile(xmlResult);
	}

	/** Instance */
	public static ThreadManager getInstance(int threadNumber, String sortType) {
		instance = new ThreadManager(threadNumber, sortType);
		totalInstance = threadNumber; // this is for after sort
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
	
	private static String createXmlSortedList(List<Xml> sortedXmlList) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder = stringBuilder.append("<sortedList>");
		stringBuilder = stringBuilder.append("\n");
		int sizeOfXmlList = sortedXmlList != null ? sortedXmlList.size() : 0;
		for (int i = 0; i < sizeOfXmlList; i++) {
			stringBuilder = stringBuilder.append("<sayi sira=\"");
			stringBuilder = stringBuilder.append(i + 1);
			stringBuilder = stringBuilder.append("\" sayac=\"");
			stringBuilder = stringBuilder.append(sortedXmlList.get(i).getOccurence());
			stringBuilder = stringBuilder.append("\">");
			stringBuilder = stringBuilder.append(sortedXmlList.get(i).getNumber());
			stringBuilder = stringBuilder.append("</sayi>");
			stringBuilder = stringBuilder.append("\n");
		}
		stringBuilder = stringBuilder.append("</sortedList>");
		return stringBuilder.toString();
	}

	private static void writeToFile(String result, Integer iteration, boolean isSorted) {
		String directoryName = FILE_DIRECTORY;
		File fileDirectory = new File(directoryName);
		if (!fileDirectory.exists())
			fileDirectory.mkdirs();
		String fileNameType = isSorted ? FILE_NAME_PREFIX_SORTED : FILE_NAME_PREFIX;
		String extension = iteration.toString() + FILE_NAME_XML;
		
		String fileName = directoryName + fileNameType + extension;
		
		File file = new File(fileName);
		try {
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(result);
			fileWriter.close();
		} catch (IOException e) {
			print(e.toString());
		}
	}
	
	private static void writeToFile(String result) {
		String directoryName = FILE_DIRECTORY;
		File fileDirectory = new File(directoryName);
		if (!fileDirectory.exists())
			fileDirectory.mkdirs();
		String fileNameType = FILE_NAME_PREFIX_SORTED_FINAL;
		String extension = FILE_NAME_XML;
		
		String fileName = directoryName + fileNameType + extension;
		
		File file = new File(fileName);
		try {
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(result);
			fileWriter.close();
		} catch (IOException e) {
			print(e.toString());
		}
	}
	
	private static List<Xml> createXmlListFromRandomList(List<Integer> randomList) {
		List<Xml> resultList = new ArrayList<Xml>();
		for (Integer integer : randomList) {
			int foundIndex = isInXmlList(integer, resultList);
			if (foundIndex > -1) {
				Integer occurence = resultList.get(foundIndex).getOccurence();
				Xml xml = new Xml.XmlBuilder().setNumber(integer).setOccurence(occurence + 1).build();
				resultList.set(foundIndex, xml);
			} else {
				Xml xml = new Xml.XmlBuilder().setNumber(integer).setOccurence(1).build();
				resultList.add(xml);
			}
		}
		return resultList;
	}
	
	private static List<Xml> createXmlListFromHashMappedRandomList(List<Integer> randomList) {
		HashMap<Integer, Integer> resultMap = new HashMap<>();
		for (Integer integer : randomList) {
			if (resultMap.containsKey(integer)) {
				Integer occurence = resultMap.get(integer) + 1;
				resultMap.put(integer, occurence);
			} else {
				resultMap.put(integer, 1);
			}
		}
		
		List<Xml> xmlList = new ArrayList<Xml>();
		Set<Entry<Integer,Integer>> entrySet = resultMap.entrySet();
		Iterator<Entry<Integer, Integer>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Entry<Integer, Integer> next = iterator.next();
			Integer key = next.getKey();
			Integer value = next.getValue();
			Xml xml = new Xml.XmlBuilder().setNumber(key).setOccurence(value).build();
			xmlList.add(xml);
		}
		return xmlList;
	}
	
	private static List<Xml> createXmlListFromHashMap(HashMap<Integer, Integer> hashMap) {
		List<Xml> xmlList = new ArrayList<Xml>();
		Set<Entry<Integer,Integer>> entrySet = hashMap.entrySet();
		Iterator<Entry<Integer, Integer>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Entry<Integer, Integer> next = iterator.next();
			Integer key = next.getKey();
			Integer value = next.getValue();
			Xml xml = new Xml.XmlBuilder().setNumber(key).setOccurence(value).build();
			xmlList.add(xml);
		}
		return xmlList;
	}
	
	/**
	 * 
	 * @param integer
	 * @param xmlList
	 * @returns the index of found location. Default (not found) is -1
	 */
	private static int isInXmlList(Integer integer, List<Xml> xmlList) {
		if (xmlList == null || xmlList.isEmpty())
			return -1;

		for (int i = 0; i < xmlList.size(); i++) {
			if (xmlList.get(i).getNumber().compareTo(integer) == 0)
				return i;
		}
		return -1;
	}
	
	private static List<Xml> sort(List<Xml> xmlList, String sortType) {
		SorterFactory sf = new SorterFactory();
		return sf.getSortMethod(sortType).sortXml(xmlList);
	}

	private static void print(String inputString) {
		System.out.println(inputString);
	}

	/** MISC */
}
