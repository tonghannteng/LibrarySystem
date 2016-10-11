package data;

import java.util.List;

import person.Author;
import person.LibraryMember;
import person.Profile;
import person.User;

public class PersonDatastore {
	private static List<User> users;
	private static List<LibraryMember> libraryMembers;

	public static User login(String username, String password) {
		return null;
	}

	public static void addMember(LibraryMember member) {

	}

	public static void updateMember(int id, Profile profile) {

	}

	public static void deleteMember(int memberId) {
		// also handle the case: user is still borrowing some books, not return
		// yet
	}

	public static List<LibraryMember> getMembers() {
		return libraryMembers;
	}

	public static LibraryMember getMember(int memberId) {
		return null;
	}

	public static void addAuthor(Author author) {

	}

	public static void updateAuthor(Profile profile, String credentials,
			String biography) {

	}

	public static void deleteAuthor(int id) {

	}

	public static List<Author> getAuthors() {
		return null;
	}

	public static List<User> getUsers() {
		return users;
	}

	public static User getUser(String username) {
		return null;
	}

	public static void addUser(User user) {

	}

	public static void updateUser(String username, Profile profile) {

	}

	public static void deleteUser(String username) {

	}
}