package book;

import java.io.Serializable;

public class BookCopy implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1406628116479563299L;
	int id;
	boolean availability = true;

	public BookCopy(int id, boolean availability) {
		this.id = id;
		this.availability = availability;
	}

	public int getId() {
		return id;
	}

	public boolean getAvailability() {
		return availability;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
}
