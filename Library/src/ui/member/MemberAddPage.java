package ui.member;

import controller.MemberController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import person.Address;
import person.LibraryMember;
import person.Profile;

public class MemberAddPage extends MemberDetailsPage {

	public MemberAddPage(MemberPage memberPage) {
		super("Add new library member", memberPage);
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
					int id = MemberController.getNewId();
					MemberController.add(new LibraryMember(profile, id));
					// clear all fields
					clearAllFields();
					parentPage.goBack();
				}
			}
		});
	}

}
