package ui.book;

import com.jfoenix.controls.JFXButton;

import book.Book;
import controller.BookController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BookEditPage extends BookDetailsPage {

	private Book book;
	private JFXButton plusButton = new JFXButton("+");

	public BookEditPage(BookPage bookPage) {
		super("Edit book information", bookPage);
		addOKHandler();
		addPlusButton(plusButton);
		addPlusHandler();
	}

	private void addOKHandler() {
		registerOKHandler(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (validate()) {

					BookController.updateBook(
							new Book(isbn.getText(), bookTitle.getText(),
									Integer.parseInt(maxCheckLength.getText()),
									Integer.parseInt(numCopy.getText())));

					isbn.clear();
					bookTitle.clear();
					maxCheckLength.clear();
					numCopy.clear();

					parentPage.goBack();
				}
			}
		});
	}

	@Override
	public void loadSelectedObject(Object selectedObject) {
		book = (Book) selectedObject;

		isbn.setText(book.getISBN());
		bookTitle.setText(book.getTitle());
		maxCheckLength.setText(Integer.toString(book.getMaxCheckoutLength()));
		numCopy.setText(Integer.toString(book.getNumOfCopy()));
	}

	private void addPlusHandler() {
		plusButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				BookController.addBookCopy(isbn.getText());
				numCopy.setText(Integer.toString(
						BookController.getBook(isbn.getText()).getNumOfCopy()));
			}
		});
	}
}
