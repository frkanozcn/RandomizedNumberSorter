package sorters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import builders.Xml;

public class SelectionSort implements Sort {

	public SelectionSort() {
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
	
	@Override
	public List<Xml> sortXml(List<Xml> list) {
		List<Xml> resultList = new ArrayList<Xml>();
		if (list == null || list.size() == 0) {
			return resultList;
		}

		while (list.size() > 0) {
			int minIndex = 0;
			Xml min = list.get(minIndex);
			for (int i = 1; i < list.size(); i++) {
				Xml curr = list.get(i);
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
