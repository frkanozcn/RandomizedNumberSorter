package sorters;

import java.util.HashMap;
import java.util.List;

public class MergeSort implements Sort {

	public MergeSort() {
	}

	public List<Integer> sort(List<Integer> list) {
		if (isEmptyOrNull(list)) {
			return list;
		}

		int size = list.size();

		int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			array[i] = list.get(i);
		}

		sortHelper(array, 0, list.size() - 1);

		for (int i = 0; i < size; i++) {
			list.set(i, array[i]);
		}

		return list;
	}

	private void sortHelper(int[] array, int f, int l) {
		if (f < l) { // list can still be divided
			int m = (f + l) / 2; // middle index
			sortHelper(array, f, m);
			sortHelper(array, m + 1, l);
			mergeSortedLists(array, f, m, l);
		}
	}

	private void mergeSortedLists(int[] array, int f, int m, int l) {
		int size1 = m - f + 1;
		int size2 = l - m;
		int[] a1 = new int[size1];
		int[] a2 = new int[size2];

		for (int i = 0; i < size1; i++) {
			a1[i] = array[i + f]; // from f to (m-f+1)-1+f = m
		}

		for (int i = 0; i < size2; i++) {
			a2[i] = array[i + m + 1]; // from m+1 to (l-m)-1+m+1 = l
		}

		int i1 = 0; // current a1 index
		int i2 = 0; // current a2 index
		int i = f; // current array index (start point f)

		while (i1 < size1 && i2 < size2) {
			if (a1[i1] <= a2[i2]) {
				array[i] = a1[i1];
				i1++;
			} else {
				array[i] = a2[i2];
				i2++;
			}
			i++;
		}

		while (i1 < size1) { // there may be elements left after comparing, just copy them into the main
								// array
			array[i] = a1[i1];
			i++;
			i1++;
		}

		while (i2 < size2) { // same case as above, this time for the second half of the array
			array[i] = a2[i2];
			i++;
			i2++;
		}

	}

	private static boolean isEmptyOrNull(List<Integer> list) {
		return list == null || list.size() == 0;
	}

	@Override
	public HashMap<Integer, Integer> sort(HashMap<Integer, Integer> mapRandom) {
		// TODO Auto-generated method stub
		return null;
	}
}
