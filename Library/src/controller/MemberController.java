package controller;

import java.util.List;

import data.LibraryMemberDatastore;
import person.LibraryMember;

public class MemberController {
	private static LibraryMemberDatastore datastore = new LibraryMemberDatastore();
	private static List<LibraryMember> members = datastore.getList();

	public static LibraryMember getMember(int memberId){
		for(LibraryMember mem: members){
			if(mem.getMemberId() == memberId){
				return mem;
			}
		}
		return null;
	}
	
	public static int getNewId() {
		members = getList();
		if (members.isEmpty()) {
			return 1;
		} else {
			return members.get(members.size() - 1).getMemberId() + 1;
		}
	}

	public static void add(LibraryMember libraryMember) {
		datastore.add(libraryMember);
	}

	public static void update(LibraryMember libraryMember) {
		datastore.update(libraryMember);
	}

	public static void delete(int memberId) {
		datastore.delete(memberId);
	}

	public static List<LibraryMember> getList() {
		return datastore.getList();
	}

	public static boolean doesMemberExist(int memberId) {
		for (LibraryMember member : members) {
			if (member.getMemberId() == memberId) {
				return true;
			}
		}

		return false;
	}

}
