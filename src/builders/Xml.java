package builders;

public class Xml {
	private Integer occurence;
	private Integer number;
	private Integer rank;

	public Xml() {
	}

	public Xml(Integer occurence, Integer number, Integer rank) {
		this.occurence = occurence;
		this.number = number;
		this.rank = rank;
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
	
	public static class XmlBuilder {
		private Integer occurence;
		private Integer number;
		private Integer rank;

		public XmlBuilder() {
		}
		
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
		
		public Xml build() {
			return new Xml(occurence, number, rank);
		}
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
