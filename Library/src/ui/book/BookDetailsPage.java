package ui.book;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import ui.DetailsPage;

public class BookDetailsPage extends DetailsPage {
	protected JFXTextField isbn = new JFXTextField();
	protected JFXTextField bookTitle = new JFXTextField();
	protected JFXTextField maxCheckLength = new JFXTextField();
	protected JFXTextField numCopy = new JFXTextField();
	protected JFXTextField author = new JFXTextField();
	GridPane fields;

	public BookDetailsPage(String title, BookPage bookPage) {
		super(title, bookPage);
		buildUI();
	}

	protected void buildUI() {
		fields = new GridPane();
		fields.setHgap(10);
		fields.setVgap(10);

		fields.add(new Label("ISBN"), 0, 0);
		fields.add(isbn, 1, 0);
		fields.add(new Label("Book Title"), 0, 1);
		fields.add(bookTitle, 1, 1);
		fields.add(new Label("Max Checkout Length"), 0, 2);
		fields.add(maxCheckLength, 1, 2);
		fields.add(new Label("No. of Copies"), 0, 3);
		fields.add(numCopy, 1, 3);
		fields.add(new Label("Author"), 0, 4);
		fields.add(author, 1, 4);

		fieldPane.getChildren().add(fields);
		fieldPane.setAlignment(Pos.CENTER);
	}

	protected boolean validate() {
		// author should have at least first or last name
		if (isbn.getText().isEmpty() && bookTitle.getText().isEmpty()) {
			setMessage("All name fields are empty");
			return false;
		}
		return true;
	}

	protected void addPlusButton(JFXButton button) {
		fields.add(button, 2, 3);
		numCopy.setEditable(false);
	}
}
