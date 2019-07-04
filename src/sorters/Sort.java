package sorters;

import java.util.HashMap;
import java.util.List;

import builders.Xml;

public interface Sort {
	public HashMap<Integer, Integer> sort(HashMap<Integer, Integer> mapRandom);
	
	public List<Xml> sortXml(List<Xml> xmlList);
}
