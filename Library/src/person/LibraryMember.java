package person;

import java.io.Serializable;


public class LibraryMember implements Serializable {
	private static final long serialVersionUID = 178810179059230872L;
	private int memberId;
	private Profile profile;

	public LibraryMember(Profile profile, int memberId) {
		this.profile = profile;
		this.memberId = memberId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getFirstName() {
		return profile.getFirstName();
	}

	public void setFirstName(String firstName) {
		profile.setFirstName(firstName);
	}

	public String getLastName() {
		return profile.getLastName();
	}

	public void setLastName(String lastName) {
		profile.setLastName(lastName);
	}

	public String getStreet() {
		return profile.getAddress().getStreet();
	}

	public String getCity() {
		return profile.getAddress().getCity();
	}

	public String getState() {
		return profile.getAddress().getState();
	}

	public String getZip() {
		return profile.getAddress().getZip();
	}

	public String getCountry() {
		return profile.getAddress().getCountry();
	}

	public String getPhone() {
		return profile.getPhone();
	}
}
