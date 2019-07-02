package builders;

public class Xml {
	private int occurence;
	private Integer number;
	private Integer rank;

	public Xml(XmlBuilder xmlBuilder) {
		this.occurence = xmlBuilder.occurence;
		this.number = xmlBuilder.number;
		this.rank = xmlBuilder.rank;
	}
	
	public static class XmlBuilder {
		private int occurence;
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
		
	}

}
