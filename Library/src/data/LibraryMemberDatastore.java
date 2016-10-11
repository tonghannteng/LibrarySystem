package data;

import java.util.List;

import person.LibraryMember;

public class LibraryMemberDatastore extends DataAccessBase implements ILibraryMember{
	
	@Override
	public void delete(int memberID) {
		List<LibraryMember> allLibraryMember = getAllItems();
		boolean deleted = false;
		int i = 0;
		while (!deleted && i < allLibraryMember.size()) {
			if (allLibraryMember.get(i).getMemberId() == memberID) {
				allLibraryMember.remove(i);
				save(allLibraryMember);
				deleted = true;
			}
			i++;
		}
	}
	@Override
	public void add(LibraryMember currentLibraryMember) {
		List<LibraryMember> allLibraryMember = getAllItems();
		allLibraryMember.add(currentLibraryMember);
		save(allLibraryMember);
		
	}
	@Override
	public void update(LibraryMember currentLibraryMember) {
		List<LibraryMember> allLibraryMember = getAllItems();
		for (int i = 0; i < allLibraryMember.size(); i++) {
			if(allLibraryMember.get(i).getMemberId() == currentLibraryMember.getMemberId()){
				allLibraryMember.remove(i);
				allLibraryMember.add(i, currentLibraryMember);
				save(allLibraryMember);
			}
		}		
	}
	
	//Get all users
	@Override
	public List<LibraryMember> getList() {
		List<LibraryMember> allLibraryMember = getAllItems();
		return allLibraryMember;
	}

	
}