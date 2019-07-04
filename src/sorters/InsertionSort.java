package sorters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import builders.Xml;

public class InsertionSort implements Sort {

	public InsertionSort() {
	}
	
	public List<Xml> sortXml(List<Xml> list) {
		List<Xml> resultList = new ArrayList<Xml>();
		
		if (list == null || list.size() == 0) {
			return resultList;
		}
		
		while (list.size() > 0) {
			Xml curr = list.remove(0);
			boolean isInserted = false;
			for (int i = 0; i < resultList.size(); i++) {
				if (curr.compareTo(resultList.get(i)) < 0) {
					resultList.add(i, curr);
					isInserted = true;
					break;
				}
			}
			if (!isInserted) {
				resultList.add(curr);
			} else {
				continue;
			}
		}

		return resultList;
	}

}
