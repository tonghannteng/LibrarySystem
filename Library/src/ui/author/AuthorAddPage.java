package ui.author;

import controller.AuthorController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import person.Address;
import person.Author;

public class AuthorAddPage extends AuthorDetailsPage {
	public AuthorAddPage(AuthorPage authorPage) {
		super("Add new author", authorPage);
		addOKHandler();
	}

	private void addOKHandler() {
		registerOKHandler(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (validate()) {
					AuthorController.add(new Author(
							AuthorController.getNewId(), firstName.getText(),
							lastName.getText(),
							new Address(street.getText(), city.getText(),
									state.getText(), zip.getText(),
									country.getText()),
							phone.getText(), credentials.getText(),
							biography.getText()));

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
}
