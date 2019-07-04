package builders;

public class Xml {
	private Integer occurence;
	private Integer number;
	private Integer rank;

	public Xml() {
	}

	/**
	 * This may be completely unnecessary
	 * 
	 * @param xmlBuilder
	 */
	public Xml(XmlBuilder xmlBuilder) {
		Xml xmlInstance = xml(xmlBuilder);
		this.occurence = xmlInstance.getOccurence();
		this.number = xmlInstance.getNumber();
		this.rank = xmlInstance.getRank();
	}

	/**
	 * This may be completely unnecessary as well
	 * 
	 * @param xmlBuilder
	 * @return
	 */
	private Xml xml(XmlBuilder xmlBuilder) {
		return xmlBuilder.buildXml();
	}

	public Integer getOccurence() {
		return this.occurence;
	}

	public Integer getNumber() {
		return number;
	}

	public Integer getRank() {
		return rank;
	}
	
	public int compareTo(Xml anotherXml) {
		if (this.occurence.compareTo(anotherXml.getOccurence()) == 0) { // if occurence is equal, lower number comes earlier in the list
			if (this.number.compareTo(anotherXml.getNumber()) < 0) {
				return -1;
			} else {
				return 1;
			}
		} else if (this.occurence.compareTo(anotherXml.getOccurence()) < 0) {
			return 1; // if occurence is lower, then it should be later in the list
		} else {
			return -1; // if occurence is higher, then it should come earlier in the list
		}
	}

}
