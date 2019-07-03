package builders;

import java.util.Arrays;

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

}
