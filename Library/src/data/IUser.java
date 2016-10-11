package data;

import java.util.List;

import person.User;

public interface IUser {

	public void delete(String username);

	public void add(User currentUser);

	public void update(User currentUser);

	public List<User> getList();

}
