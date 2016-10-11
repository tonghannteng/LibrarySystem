package data;

import java.util.List;

import person.LibraryMember;

public interface ILibraryMember {
	
	public void add(LibraryMember member);

	public List<LibraryMember> getList();

	public void delete(int index);

	public void update(LibraryMember currentMember);
}