package data;

import java.util.List;

import person.Author;

public class AuthorDatastore extends DataAccessBase implements IAuthor {
	@Override
	public void add(Author author) {
		List<Author> allAuthors = getAllItems();
		allAuthors.add(author);
		save(allAuthors);
	}

	@Override
	public void update(Author author) {
		List<Author> allAuthors = getAllItems();

		boolean updated = false;
		int i = 0;
		while (!updated && i < allAuthors.size()) {
			if (allAuthors.get(i).getId() == author.getId()) {
				allAuthors.remove(i);
				allAuthors.add(i, author);
				save(allAuthors);
				updated = true;
			}
			i++;
		}
	}

	@Override
	public void delete(int id) {
		List<Author> allAuthors = getAllItems();

		boolean deleted = false;
		int i = 0;
		while (!deleted && i < allAuthors.size()) {
			if (allAuthors.get(i).getId() == id) {
				allAuthors.remove(i);
				save(allAuthors);
				deleted = true;
			}
			i++;
		}
	}

	@Override
	public List<Author> getList() {
		List<Author> allAuthors = getAllItems();
		return allAuthors;
	}
}
