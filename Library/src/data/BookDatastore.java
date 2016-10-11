package data;

import java.util.List;
import book.Book;
import book.BookCopy;

public class BookDatastore extends DataAccessBase implements IBook {
	@Override
	public void addBook(Book book) {
		List<Book> allBooks = getAllItems();
		allBooks.add(book);
		save(allBooks);
	}

	@Override
	public void update(Book book) {
		List<Book> allBooks = getAllItems();

		boolean updated = false;
		int i = 0;
		while (!updated && i < allBooks.size()) {
			if (allBooks.get(i).getISBN().equals(book.getISBN())) {
				allBooks.remove(i);
				allBooks.add(i, book);
				save(allBooks);
				updated = true;
			}
			i++;
		}
	}

	@Override
	public void delete(String isbn) {
		// TODO Auto-generated method stub
		List<Book> allBooks = getAllItems();

		boolean deleted = false;
		int i = 0;
		while (!deleted && i < allBooks.size()) {
			if (allBooks.get(i).getISBN().equals(isbn)) {
				allBooks.remove(i);
				save(allBooks);
				deleted = true;
			}
			i++;
		}

	}

	@Override
	public List<Book> getList() {
		// TODO Auto-generated method stub
		List<Book> allBooks = getAllItems();
		return allBooks;
	}

}