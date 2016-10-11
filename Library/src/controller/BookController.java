package controller;

import java.util.List;
import book.Book;
import book.BookCopy;
import data.BookDatastore;

public class BookController {

	private static BookDatastore datastore = new BookDatastore();
	private static List<Book> books = datastore.getList();

	public static Book getBook(String ISBN) {
		books = datastore.getList();
		for (Book book : books) {
			if (book.getISBN().equals(ISBN)) {
				return book;
			}
		}

		return null;
	}

	public static void addBook(Book book) {
		datastore.addBook(book);
		books = datastore.getList();
	}

	public static void updateBook(Book book) {
		datastore.update(book);
		books = datastore.getList();
	}

	public static void deleteBook(String ISBN) {
		datastore.delete(ISBN);
		books = datastore.getList();
	}

	public static List<Book> getList() {
		books = datastore.getList();
		return books;
	}

	public static void setBookCopyAvailability(String ISBN, int bookCopyId,
			boolean availability) {
		Book book = getBook(ISBN);
		book.setBookCopyAvailability(bookCopyId, availability);
		updateBook(book);
		books = datastore.getList();
	}

	public static void addBookCopy(String ISBN) {
		Book book = getBook(ISBN);
		book.addBookCopy();
		books = datastore.getList();
	}

	public static int getAvailableBookCopy(String ISBN) {
		Book book = getBook(ISBN);
		List<BookCopy> copies = book.getBookCopies();
		int result = 0;

		for (BookCopy copy : copies) {
			if (copy.getAvailability() == true) {
				result++;
			}
		}

		return result;
	}
}
