package person;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 6514654511819253869L;

	public enum Authorization {
		ADMIN, LIBRARIAN, BOTH
	};

	private Profile profile;
	private String username;
	private String password;
	private Authorization authorization;

	public User(Profile profile, String username, String password,
			Authorization authorization) {
		this.username = username;
		this.password = password;
		this.profile = profile;
		this.authorization = authorization;
	}

	public Authorization getAuthorization() {
		return authorization;
	}

	public void setAuthorization(Authorization authorization) {
		this.authorization = authorization;
	}

	public String getAuthorizationValue() {
		return authorization.toString();
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
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
