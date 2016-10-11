package data;

import java.util.List;

import person.User;

public class UserDatastore extends DataAccessBase implements IUser{
	@Override
	public void delete(String username) {
		List<User> allUser = getAllItems();
		boolean deleted = false;
		int i = 0;
		while (!deleted && i < allUser.size()) {
			if (allUser.get(i).getUsername().equals(username)) {
				allUser.remove(i);
				save(allUser);
				deleted = true;
			}
			i++;
		}
	}
	@Override
	public void add(User currentUser) {
		List<User> allUser = getAllItems();
		allUser.add(currentUser);
		save(allUser);
		
	}
	@Override
	public void update(User currentUser) {
		List<User> allUser = getAllItems();
		for (int i = 0; i < allUser.size(); i++) {
			if(allUser.get(i).getUsername().equals(currentUser.getUsername())){
				allUser.remove(i);
				allUser.add(i, currentUser);
				save(allUser);
			}
		}		
	}
	//Get all users
	@Override
	public List<User> getList() {
		List<User> allUser = getAllItems();
		return allUser;
	}
}
