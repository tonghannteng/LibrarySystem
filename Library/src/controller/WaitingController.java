package controller;

import java.util.List;

import book.WaitingEntry;
import data.WaitingDatastore;

public class WaitingController {
	private static WaitingDatastore datastore = new WaitingDatastore();
	private static List<WaitingEntry> entries = datastore.getList();

	public static List<WaitingEntry> getList() {
		return entries;
	}

	public static void delete(String bookISBN, int memberId) {
		datastore.delete(bookISBN, memberId);
		entries = datastore.getList();
	}
	
	public static void add(String bookISBN, int memberId) {
		datastore.add(bookISBN, memberId);
		entries = datastore.getList();
	}
}
