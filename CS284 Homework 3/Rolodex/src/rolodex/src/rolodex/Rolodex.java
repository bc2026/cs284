package rolodex.src.rolodex;

import java.util.ArrayList;

/**
 * Constructs a new empty Rolodex object.
 * The index is initialized as an array of separators (26 separators).
 * Each separator is initialized with the appropriate letter, null previous and next pointers.
 * The separators are then linked together into a circular doubly linked list.
 */

public class Rolodex {
	private Entry cursor;
	private final Entry[] index;

	Rolodex() {
		index = new Entry[26];

		for (int i = 0; i < index.length; i++) {
			index[i] = new Separator(null, null, (char) (i + 'A'));
		}

		for (int i = 0; i < index.length; i++) {
			if (i == 0) {
				index[i].next = index[i + 1];
				index[i].prev = index[index.length - 1];
			} else if (i == index.length - 1) {
				index[i].next = index[0];
				index[i].prev = index[i - 1];
			} else {
				index[i].next = index[i + 1];
				index[i].prev = index[i - 1];
			}
		}
	}
	/**
	 * Checks if the rolodex contains an entry with the specified name.
	 * @param name the name of the entry to check
	 * @return true if the rolodex contains an entry with the specified name, false otherwise.
	 */
	public boolean contains(String name) {
		int index = Character.toUpperCase(name.charAt(0)) - 'A';

		cursor = this.index[index];
		while (Character.toUpperCase(cursor.getName().charAt(0)) != index + 'A' + 1) {
			if (cursor.getName().equals(name)) {
				return true;
			}
			cursor = cursor.next;
		}

		return false;
	}

	/**
	 * Returns the number of entries in the rolodex.
	 * @return the number of entries in the rolodex
	 */
	public int size() {
		int count = 0;
		initializeCursor();
		nextEntry();
		while (!(cursor.isSeparator() && cursor.getName().charAt(0) == 'A')) {
			if (!cursor.isSeparator()) {
				count++;
			}
			nextEntry();
		}

		return count;
	}



	/**
	 * Returns a list of phone numbers (cells) for entries with the specified name.
	 * @param name the name of the entries to look up
	 * @return a list of phone numbers (cells) for entries with the specified name
	 * @throws IllegalArgumentException if the rolodex does not contain an entry with the specified name
	 */
	public ArrayList<String> lookup(String name) throws IllegalArgumentException {
		ArrayList<String> elements = new ArrayList<>();

		if (!contains(name)) {
			throw new IllegalArgumentException("lookup: name not found");
		}

		initializeCursor();
		while (((Separator) (cursor)).getLetter() != Character.toUpperCase(name.charAt(0))) {
			nextSeparator();
		}

		while (Character.toUpperCase(cursor.getName().charAt(0)) == Character.toUpperCase(name.charAt(0))) {
			if (cursor.getName().equals(name)) {
				elements.add(((Card) (cursor)).getCell());
			}
			nextEntry();
		}

		return elements;
	}


	/**

	 Returns a string representation of the Rolodex object. The string contains
	 each card in the Rolodex, with each card separated by a new line character.
	 @return a string representation of the Rolodex object
	 */
	public String toString() {
		Entry current = index[0];

		StringBuilder builder = new StringBuilder();
		while (current.next != index[0]) {
			builder.append(current.toString()).append("\n");
			current = current.next;
		}
		builder.append(current.toString()).append("\n");
		return builder.toString();
	}


	/**

	 Initializes the cursor to the first separator in the index array.
	 */
	private void initializeCursor() {
		cursor = index[0];
	}


	/**

	 Advances the cursor to the next separator in the linked list.
	 */
	private void nextSeparator() {
		cursor = cursor.next;
	}


	/**

	 Advances the cursor to the next entry in the linked list.
	 */
	private void nextEntry() {
		do {
			cursor = cursor.next;
		} while (cursor.isSeparator());
	}


	/**

	 Adds a card to the Rolodex with the specified name and cell number.
	 @param name the name to add to the Rolodex
	 @param cell the cell number to add to the Rolodex
	 @throws IllegalArgumentException if the card already exists in the Rolodex
	 */
	public void addCard(String name, String cell) throws IllegalArgumentException {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int ind = alphabet.indexOf(Character.toUpperCase(name.charAt(0)));

		cursor = index[ind];

		nextEntry();
		while (Character.toUpperCase(cursor.getName().charAt(0)) == ((Separator)(index[ind])).getLetter()) {
			if (cursor.getName() == name && ((Card)(cursor)).getCell() == cell) {
				throw new IllegalArgumentException("addCard: duplicate entry");
			}
			cursor = cursor.next;
		}

		initializeCursor();
		while (((Separator)(cursor)).getLetter() != Character.toUpperCase(name.charAt(0))) {
			nextSeparator();
		}

		char letter = ((Separator)(cursor)).getLetter();
		while(cursor.next != null && cursor.getName().compareTo(name) < 0 && cursor.next.getName().compareTo(name) < 0 && Character.toUpperCase(cursor.next.getName().charAt(0)) == letter) {
			nextEntry();
		}

		cursor.next = new Card(cursor, cursor.next, name, cell);
		if (cursor.next.next != null) {
			cursor.next.next.prev = cursor.next;
		}
	}

	/**

	 Removes a card from the Rolodex with the specified name and cell number.
	 @param name the name of the card to remove from the Rolodex
	 @param cell the cell number of the card to remove from the Rolodex
	 @throws IllegalArgumentException if the card does not exist in the Rolodex or the
	 cell number does not match any card with the specified name
	 */
	public void removeCard(String name, String cell) throws IllegalArgumentException{
		if (!contains(name)) {
			throw new IllegalArgumentException("removeCard: name does not exist");
		}

		ArrayList<String> elements = new ArrayList<String>();

		initializeCursor();
		while (((Separator)(cursor)).getLetter() != Character.toUpperCase(name.charAt(0))) {
			nextSeparator();
		}

		while (Character.toUpperCase(cursor.getName().charAt(0)) == Character.toUpperCase(name.charAt(0))) {
			if (cursor.getName().equals(name)) {
				elements.add(((Card)(cursor)).getCell());
			}
			nextEntry();
		}

		if (!elements.contains(cell)) {
			throw new IllegalArgumentException("removeCard: cell for that name does not exist");
		}

		initializeCursor();
		while (((Separator)(cursor)).getLetter() != Character.toUpperCase(name.charAt(0))) {
			nextSeparator();
		}

		while(!(cursor.getName().equals(name) && ((Card)(cursor)).getCell().equals(cell))) {
			nextEntry();
		}

		cursor.prev.next = cursor.next;
		if (cursor.next != null) {
			cursor.next.prev = cursor.prev;
		}
	}

	/**

	 Removes all cards from the Rolodex with the specified name.
	 @param name the name of the cards to remove from the Rolodex
	 @throws IllegalArgumentException if the name does not exist in the Rolodex
	 */
	public void removeAllCards(String name) throws IllegalArgumentException{
		if (!contains(name)) {
			throw new IllegalArgumentException("removeAllCards: name does not exist");
		}

		initializeCursor();
		while (((Separator)(cursor)).getLetter() != Character.toUpperCase(name.charAt(0))) {
			nextSeparator();
		}

		char letter = ((Separator)(cursor)).getLetter();
		while(cursor.next != null && Character.toUpperCase(cursor.getName().charAt(0)) == letter) {
			if (cursor.getName().equals(name)) {
				removeCard(cursor.getName(), ((Card)(cursor)).getCell());
			}
			nextEntry();
		}
	}


	/**

	 Returns a string representation of the current entry in the Rolodex.
	 @return a string representation of the current entry in the Rolodex
	 */
	public String currentEntryToString() {
		return cursor.toString();
	}

	/**

	 The main method for the Rolodex program.
	 @param args the command-line arguments for the program
	 */
	public static void main(String[] args) {

		Rolodex r = new Rolodex();


		System.out.println(r);

		r.addCard("Chloe", "123");
		r.addCard("Chad", "23");
		r.addCard("Cris", "3");
		r.addCard("Cris", "4");
		r.addCard("Cris", "5");
		//		r.addCard("Cris", "4");
		r.addCard("Maddie", "23");

		System.out.println(r);

		System.out.println(r.contains("Albert"));

		r.removeAllCards("Cris");

		System.out.println(r);

		r.removeAllCards("Chad");
		r.removeAllCards("Chloe");

		r.addCard("Chloe", "123");
		r.addCard("Chad", "23");
		r.addCard("Cris", "3");
		r.addCard("Cris", "4");

		System.out.println(r);




	}


}
