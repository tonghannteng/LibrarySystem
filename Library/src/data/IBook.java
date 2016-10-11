package data;

import java.util.List;

import book.Book;

public interface IBook {
	
	public void addBook(Book book);
	public void update(Book book);
	public void delete(String id);
	public List<Book> getList();
}
