package controller;

import java.util.List;

import data.AuthorDatastore;
import person.Address;
import person.Author;

public class AuthorController {
	private static AuthorDatastore datastore = new AuthorDatastore();
	private static List<Author> authors = datastore.getList();

	public static int getNewId() {
		authors = getList();
		if (authors.isEmpty()) {
			return 1;
		} else {
			return authors.get(authors.size() - 1).getId() + 1;
		}
	}

	public static void add(Author author) {
		datastore.add(author);
		authors = datastore.getList();
	}

	public static void update(int id, String firstName, String lastName,
			Address address, String phone, String credentials,
			String biography) {
		datastore.update(new Author(id, firstName, lastName, address, phone,
				credentials, biography));
		authors = datastore.getList();
	}

	public static void delete(int id) {
		datastore.delete(id);
		authors = datastore.getList();
	}

	public static List<Author> getList() {
		return authors;
	}
}
