package sorters;

public class SorterFactory {

	private static final Sort INSERTION = new InsertionSort();
	private static final Sort MERGE = new MergeSort();
	private static final Sort SELECTION = new SelectionSort();

	private enum Sorters {

		I(INSERTION), M(MERGE), S(SELECTION);

		private final Sort sorter;

		private Sorters(Sort sorter) {
			this.sorter = sorter;
		}

		public Sort getValue() {
			return this.sorter;
		}
	}

	public Sort getSortMethod(String sortType) {
		for (Sorters sorter : Sorters.values()) {
			if (sorter.name().equalsIgnoreCase(sortType)) {
				return sorter.getValue();
			}
		}
		return null;
	}
}
