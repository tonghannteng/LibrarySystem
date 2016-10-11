package data;

import java.util.List;

import book.WaitingEntry;

public interface IWaiting {
	public void delete(String bookISBN, int memberId);

	public List<WaitingEntry> getList();

	void add(String bookISBN, int memberId);

}
