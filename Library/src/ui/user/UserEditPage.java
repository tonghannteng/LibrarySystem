package ui.user;

import controller.UserController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import person.Address;
import person.Profile;
import person.User;
import person.User.Authorization;

public class UserEditPage extends UserDetailsPage {
	private User user;

	public UserEditPage(UserPage userPage) {
		super("Edit User", userPage);
		addOKHandler();
	}

	private void addOKHandler() {
		registerOKHandler(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (validate()) {
					// Address(String street, String city, String state, String
					// zip, String country)
					Address address = new Address(txtStreet.getText().trim(),
							txtCity.getText().trim(), txtState.getText().trim(),
							txtZip.getText().trim(),
							txtCountry.getText().trim());
					// Profile(String firstName, String lastName, Address
					// address, String phone)
					Profile profile = new Profile(txtFirstname.getText().trim(),
							txtLastname.getText().trim(), address,
							txtPhone.getText().trim());
					// User(Profile profile, String username, String password,
					// Authorization authorization)
					String username = txtUsername.getText().trim();
					String password = txtPassword.getText().trim();
					Authorization authorization = Authorization
							.valueOf(cbAuthorization.getValue());
					User user = new User(profile, username, password,
							authorization);
					UserController.update(user);
					// clear all fields
					clearAllFields();
					parentPage.goBack();
				}
			}
		});
	}

	@Override
	public void loadSelectedObject(Object selectedObject) {
		user = (User) selectedObject;

		txtFirstname.setText(user.getFirstName());
		txtLastname.setText(user.getLastName());

		txtStreet.setText(user.getStreet());
		txtCity.setText(user.getCity());
		txtState.setText(user.getState());
		txtZip.setText(user.getZip());
		txtCountry.setText(user.getCountry());
		txtPhone.setText(user.getPhone());
		txtUsername.setText(user.getUsername());
		txtPassword.setText(user.getPassword());
		cbAuthorization.setValue(user.getAuthorizationValue());
	}

}
