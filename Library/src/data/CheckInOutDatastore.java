package data;

import java.util.List;

import book.CheckoutEntry;
import controller.BookController;

public class CheckInOutDatastore extends DataAccessBase implements ICheckOut {

	@Override
	public void add(CheckoutEntry checkoutEntry) {
		// TODO Auto-generated method stub
		List<CheckoutEntry> allCheckouts = getAllItems();
		allCheckouts.add(checkoutEntry);
		save(allCheckouts);

	}

	@Override
	public void delete(String ISBN, int bookCopyId) {
		// TODO Auto-generated method stub
		List<CheckoutEntry> allCheckouts = getAllItems();

		boolean deleted = false;
		int i = 0;
		while (!deleted && i < allCheckouts.size()) {
			if (allCheckouts.get(i).getISBN().equals(ISBN)
					&& allCheckouts.get(i).getCopyId() == bookCopyId) {
				allCheckouts.remove(i);
				save(allCheckouts);
				BookController.getBook(ISBN).setBookCopyAvailability(bookCopyId,
						true);
				deleted = true;
			}
			i++;
		}

	}

	@Override
	public List<CheckoutEntry> getList() {
		// TODO Auto-generated method stub
		List<CheckoutEntry> allCheckouts = getAllItems();
		return allCheckouts;
	}
}