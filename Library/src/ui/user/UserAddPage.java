package ui.user;

import controller.UserController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import person.Address;
import person.Profile;
import person.User;
import person.User.Authorization;

public class UserAddPage extends UserDetailsPage{
	public UserAddPage(UserPage userPage) {
		super("Add new user", userPage);
		addOKHandler();
	}

	private void addOKHandler() {
		registerOKHandler(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (validate()) {
					//Address(String street, String city, String state, String zip, String country)
					Address address = new Address(txtStreet.getText().trim(), txtCity.getText().trim(), 
							txtState.getText().trim(), txtZip.getText().trim(), txtCountry.getText().trim());
					//Profile(String firstName, String lastName, Address address, String phone)				
					Profile profile = new Profile(txtFirstname.getText().trim(), txtLastname.getText().trim(),
							address, txtPhone.getText().trim());
					//User(Profile profile, String username, String password, Authorization authorization)
					String username = txtUsername.getText().trim();
					String password = txtPassword.getText().trim();
					Authorization authorization = Authorization.valueOf(cbAuthorization.getValue());
					User user = new User(profile, username, password, authorization);
					UserController.add(user);
					//clear all fields
					clearAllFields();
					parentPage.goBack();
				}
			}
		});
	}
}
