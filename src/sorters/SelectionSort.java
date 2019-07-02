package sorters;

import java.util.ArrayList;
import java.util.List;

public class SelectionSort {
	
	public SelectionSort() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Integer> sort(List<Integer> list) {
		List<Integer> resultList = new ArrayList<Integer>();
		if (list == null || list.size() == 0) {
			return resultList;
		}
				
		while (list.size() > 0) {
			int minIndex = 0;
			Integer min = list.get(minIndex);
			for (int i = 1; i < list.size(); i++) {
				Integer curr = list.get(i);
				if (curr.compareTo(min) < 0) {
					min = curr;
					minIndex = i;
				}
			}
			resultList.add(list.remove(minIndex));
		}
		
		return resultList;
	}

}
