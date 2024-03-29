package rolodex.src.rolodex;


public abstract class Entry {
	protected Entry prev;
	protected Entry next;
	
	public Entry(Entry prev, Entry next) {
		super();
		this.prev = prev;
		this.next = next;
	}
	
	public abstract Boolean isSeparator();
	
	public abstract int size();
	
	public abstract String getName();

	public int getIndex() {
		int index = 0;
		Entry current = this;
		while (current.prev != null) {
			index++;
			current = current.prev;
		}
		return index;
	}



}
