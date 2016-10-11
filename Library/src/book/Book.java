package book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import controller.BookController;
import person.Author;

public class Book implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4028788337471650315L;
	private String title;
	private String ISBN;
	private List<Author> authorList;
	private List<BookCopy> bookCopies = new ArrayList<BookCopy>(); // each copy
																	// has its
																	// own
																	// availability

	private int maxCheckoutLength;

	public Book(String ISBN, String title, int maxCheckoutLength,
			int numOfCopy) {
		this.ISBN = ISBN;
		this.title = title;
		this.maxCheckoutLength = maxCheckoutLength;
		setNumOfCopy(numOfCopy);
	}

	public int getMaxCheckoutLength() {
		return maxCheckoutLength;
	}

	public void setMaxCheckoutLength(int maxCheckoutLength) {
		this.maxCheckoutLength = maxCheckoutLength;
	}

	public int getNumOfCopy() {
		return bookCopies.size();
	}

	public void setNumOfCopy(int numOfCopy) {
		for (int i = 0; i < numOfCopy; i++) {
			bookCopies.add(new BookCopy(i, true));
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public List<Author> getAuthorList() {
		return authorList;
	}

	public void setAuthorList(List<Author> authorList) {
		this.authorList = authorList;
	}

	public List<BookCopy> getBookCopies() {
		return bookCopies;
	}

	public void setBookCopyAvailability(int bookCopyId, boolean availability) {
		for (BookCopy bookCopy : bookCopies) {
			if (bookCopy.getId() == bookCopyId) {
				bookCopy.setAvailability(availability);
			}
		}
		BookController.updateBook(this);
	}

	public void addBookCopy() {
		BookCopy bookCopy = new BookCopy(getNewBookCopyId(), true);
		bookCopies.add(bookCopy);
		BookController.updateBook(this);
	}

	private int getNewBookCopyId() {
		if (bookCopies.isEmpty()) {
			return 1;
		} else {
			return bookCopies.get(bookCopies.size() - 1).getId() + 1;
		}
	}
}
