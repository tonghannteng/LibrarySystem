package person;

import java.io.Serializable;

final public class Author implements Serializable {
	private static final long serialVersionUID = 1L;

	private Profile profile;
	private int id;
	private String credentials;
	private String biography;

	public Author(int id, String firstName, String lastName, Address address,
			String phone, String credentials, String biography) {
		this.id = id;
		this.profile = new Profile(firstName, lastName, address, phone);
		this.credentials = credentials;
		this.biography = biography;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCredentials() {
		return credentials;
	}

	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
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

	public Address getAddress() {
		return profile.getAddress();
	}
}
