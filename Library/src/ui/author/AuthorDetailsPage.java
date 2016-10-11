package ui.author;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import ui.DetailsPage;

public class AuthorDetailsPage extends DetailsPage {
	protected JFXTextField firstName = new JFXTextField();
	protected JFXTextField lastName = new JFXTextField();
	protected JFXTextField street = new JFXTextField();
	protected JFXTextField city = new JFXTextField();
	protected JFXTextField state = new JFXTextField();
	protected JFXTextField zip = new JFXTextField();
	protected JFXTextField country = new JFXTextField();
	protected JFXTextField phone = new JFXTextField();
	protected JFXTextArea credentials = new JFXTextArea();
	protected JFXTextArea biography = new JFXTextArea();

	public AuthorDetailsPage(String title, AuthorPage authorPage) {
		super(title, authorPage);
		buildUI();
	}

	protected void buildUI() {
		GridPane fields = new GridPane();
		fields.setHgap(10);
		fields.setVgap(10);

		fields.add(new Label("First name"), 0, 0);
		fields.add(firstName, 1, 0);
		fields.add(new Label("Last name"), 0, 1);
		fields.add(lastName, 1, 1);
		fields.add(new Label("Street"), 0, 2);
		fields.add(street, 1, 2);
		fields.add(new Label("City"), 0, 3);
		fields.add(city, 1, 3);
		fields.add(new Label("State"), 0, 4);
		fields.add(state, 1, 4);
		fields.add(new Label("Zip code"), 0, 5);
		fields.add(zip, 1, 5);
		fields.add(new Label("Country"), 0, 6);
		fields.add(country, 1, 6);
		fields.add(new Label("Phone"), 0, 7);
		fields.add(phone, 1, 7);
		fields.add(new Label("Credentials"), 0, 8);
		fields.add(credentials, 1, 8);
		fields.add(new Label("Biography"), 0, 9);
		fields.add(biography, 1, 9);

		fieldPane.getChildren().add(fields);
		fieldPane.setAlignment(Pos.CENTER);
	}

	protected boolean validate() {
		// author should have at least first or last name
		if (firstName.getText().isEmpty() && lastName.getText().isEmpty()) {
			setMessage("All name fields are empty");
			return false;
		}

		// state should be at least 2 characters
		String value = state.getText();
		if (!value.isEmpty() && value.length() < 2) {
			setMessage("State should be at least 2 characters");
			return false;
		}

		value = zip.getText();
		if (!value.isEmpty()) {
			try {
				Integer.parseInt(value);
			} catch (NumberFormatException e) {
				setMessage("Zip code should be 5-digit number");
				return false;
			}
			if (value.length() != 5) {
				setMessage("Zip code should be 5-digit number");
				return false;
			}
		}

		return true;
	}
}
