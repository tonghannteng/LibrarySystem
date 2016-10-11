package ui.member;

import com.jfoenix.controls.JFXTextField;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import ui.DetailsPage;
import ui.IGoBack;

public class MemberDetailsPage extends DetailsPage {

	// Profile
	protected JFXTextField txtFirstname = new JFXTextField();
	protected JFXTextField txtLastname = new JFXTextField();
	protected JFXTextField txtPhone = new JFXTextField();
	// Address
	protected JFXTextField txtStreet = new JFXTextField();
	protected JFXTextField txtCity = new JFXTextField();
	protected JFXTextField txtState = new JFXTextField();
	protected JFXTextField txtZip = new JFXTextField();
	protected JFXTextField txtCountry = new JFXTextField();

	public MemberDetailsPage(String title, IGoBack parentPage) {
		super(title, parentPage);
		buildUI();
	}

	protected void buildUI() {
		GridPane fields = new GridPane();
		fields.setHgap(10);
		fields.setVgap(10);

		fields.add(new Label("First name"), 0, 0);
		fields.add(txtFirstname, 1, 0);
		fields.add(new Label("Last name"), 0, 1);
		fields.add(txtLastname, 1, 1);
		fields.add(new Label("Street"), 0, 2);
		fields.add(txtStreet, 1, 2);
		fields.add(new Label("City"), 0, 3);
		fields.add(txtCity, 1, 3);
		fields.add(new Label("State"), 0, 4);
		fields.add(txtState, 1, 4);
		fields.add(new Label("Zip code"), 0, 5);
		fields.add(txtZip, 1, 5);
		fields.add(new Label("Country"), 0, 6);
		fields.add(txtCountry, 1, 6);
		fields.add(new Label("Phone"), 0, 7);
		fields.add(txtPhone, 1, 7);

		fieldPane.getChildren().add(fields);
		fieldPane.setAlignment(Pos.CENTER);
	}

	protected boolean validate() {
		// member should have at least first or last name
		if (txtFirstname.getText().isEmpty()
				&& txtLastname.getText().isEmpty()) {
			setMessage("Should have at least first or last name.");
			return false;
		}

		// state should be at least 2 characters
		String value = txtState.getText();
		if (!value.isEmpty() && value.length() < 2) {
			setMessage("State should be at least 2 characters.");
			return false;
		}

		// zip code should be 5-digit number
		value = txtZip.getText();
		if (!value.isEmpty()) {
			try {
				Integer.parseInt(value);
			} catch (NumberFormatException e) {
				setMessage("Zip code should be digit number.");
				return false;
			}
			if (value.length() != 5) {
				setMessage("Zip code should be 5-digit number.");
				return false;
			}
		}

		return true;
	}

	protected void clearAllFields() {

		// Profile
		txtFirstname.clear();
		txtLastname.clear();
		txtPhone.clear();
		// Address
		txtStreet.clear();
		txtCity.clear();
		txtState.clear();
		txtZip.clear();
		txtCountry.clear();
	}
}
