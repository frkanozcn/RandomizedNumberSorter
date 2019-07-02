package sorters;

import java.util.List;

public class MergeSort {

	public MergeSort() {
		// TODO Auto-generated constructor stub
	}
	
	private void sort(List<Integer> list) {
		if (isEmptyOrNull(list)) {
			return;
		}
		sortHelper(list, 0, list.size());
	}
	
	private void sortHelper(List<Integer> list, int f, int l) {
		
	}
	
	private static boolean isEmptyOrNull(List<Integer> list) {
		return list == null || list.size() == 0;
	}

}
