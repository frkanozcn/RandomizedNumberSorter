package builders;

public class XmlBuilder {
	private Integer occurence;
	private Integer number;
	private Integer rank;

	public XmlBuilder setOccurence(int occurence) {
		this.occurence = occurence;
		return this;
	}

	public XmlBuilder setNumber(Integer number) {
		this.number = number;
		return this;
	}

	public XmlBuilder setRank(Integer rank) {
		this.rank = rank;
		return this;
	}

	/*
	public Xml buildXml() {
		return new Xml(this);
	}
	*/

	public Integer getOccurence() {
		return occurence;
	}

	public Integer getNumber() {
		return number;
	}

	public Integer getRank() {
		return rank;
	}

}
