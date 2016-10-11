package person;

import java.io.Serializable;

public class Profile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8510834509311405207L;
	private String firstName;
	private String lastName;
	private String phone;
	private Address address;
	
	public Profile(){};
	
	public Profile(String firstName, String lastName, Address address, String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
}
