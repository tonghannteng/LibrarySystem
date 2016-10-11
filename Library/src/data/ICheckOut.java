package data;

import java.util.List;

import book.CheckoutEntry;

public interface ICheckOut {
	
	public void add(CheckoutEntry checkoutEntry);
	public void delete(String ISBN, int bookCopyId);
//	public void update(String memberid, String ISBN, int bookCopyId);
	public List<CheckoutEntry> getList();

}
