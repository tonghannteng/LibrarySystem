package controller;

import java.util.List;

import book.Book;
import book.BookCopy;
import book.CheckoutEntry;
import data.CheckInOutDatastore;

public class CheckoutController {

	private static CheckInOutDatastore datastore = new CheckInOutDatastore();
	private static List<CheckoutEntry> checkouts = datastore.getList();

	public static void addCheckoutEntry(CheckoutEntry checkoutEntry) {
		datastore.add(checkoutEntry);
		BookController.setBookCopyAvailability(checkoutEntry.getISBN(), checkoutEntry.getCopyId(), false);
		
		checkouts = datastore.getList();
	}

	public static void deleteCheckoutEntry(String ISBN, int bookCopyId) {
		datastore.delete(ISBN, bookCopyId);
		checkouts = datastore.getList();
	}

	public static List<CheckoutEntry> getList() {
		checkouts = datastore.getList();
		return checkouts;
	}

	public static boolean isBookCopyAvailable(String ISBN) {
		Book book = BookController.getBook(ISBN);
		List<BookCopy> bookCopies = book.getBookCopies();

		for (BookCopy bookCopy : bookCopies) {
			if (bookCopy.getAvailability()) {
				return true;
			}
		}

		return false;
	}

	public static int getAvailableBookCopy(String ISBN) {
		Book book = BookController.getBook(ISBN);
		List<BookCopy> bookCopies = book.getBookCopies();

		for (BookCopy bookCopy : bookCopies) {
			if (bookCopy.getAvailability()) {
				return bookCopy.getId();
			}
		}

		return -1;
	}
}
