package data;

import java.util.List;

import person.Author;
import person.Profile;

public interface IAuthor {
	public void add(Author author);
	public void update(Author author);
	public void delete(int id);
	public List<Author> getList();
}
