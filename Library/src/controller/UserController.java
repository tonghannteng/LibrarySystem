package controller;

import java.util.List;

import data.UserDatastore;
import person.User;
import person.User.Authorization;

public class UserController {
	private static UserDatastore datastore = new UserDatastore();

	public static void add(User user) {
		datastore.add(user);
	}

	public static void update(User user) {
		datastore.update(user);
	}

	public static void delete(String username) {
		datastore.delete(username);
	}

	public static List<User> getList() {
		return datastore.getList();
	}

	public static Authorization getAuthorization(String username,
			String password) {
		List<User> users = datastore.getList();

		for (User user : users) {
			if (user.getUsername().equals(username)
					&& user.getPassword().equals(password)) {
				return user.getAuthorization();
			}
		}

		return null;
	}
}
