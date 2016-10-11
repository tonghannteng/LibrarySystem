package ui.author;

import controller.AuthorController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import person.Address;
import person.Author;

public class AuthorEditPage extends AuthorDetailsPage {
	private Author author;

	public AuthorEditPage(AuthorPage authorPage) {
		super("Edit author information", authorPage);
		addOKHandler();
	}

	private void addOKHandler() {
		registerOKHandler(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (validate()) {
					AuthorController.update(author.getId(),
							firstName.getText(), lastName.getText(),
							new Address(street.getText(), city.getText(),
									state.getText(), zip.getText(),
									country.getText()),
							phone.getText(), credentials.getText(),
							biography.getText());

					firstName.clear();
					lastName.clear();
					street.clear();
					city.clear();
					state.clear();
					zip.clear();
					country.clear();
					phone.clear();
					credentials.clear();
					biography.clear();

					parentPage.goBack();
				}
			}
		});
	}

	@Override
	public void loadSelectedObject(Object selectedObject) {
		author = (Author) selectedObject;

		firstName.setText(author.getFirstName());
		lastName.setText(author.getLastName());
		street.setText(author.getStreet());
		city.setText(author.getCity());
		state.setText(author.getState());
		zip.setText(author.getZip());
		country.setText(author.getCountry());
		phone.setText(author.getPhone());
		credentials.setText(author.getCredentials());
		biography.setText(author.getBiography());
	}
}
