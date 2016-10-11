package ui.book;

import book.Book;
import controller.BookController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BookAddPage extends BookDetailsPage {

	public BookAddPage(BookPage bookPage) {
		super("Add new book", bookPage);
		addOKHandler();
	}

	private void addOKHandler() {
		registerOKHandler(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (validate()) {

					BookController.addBook(
							new Book(isbn.getText(), bookTitle.getText(), Integer.parseInt(maxCheckLength.getText()), Integer.parseInt(numCopy.getText())));

					isbn.clear();
					bookTitle.clear();
					maxCheckLength.clear();
					numCopy.clear();

					parentPage.goBack();
				}
			}
		});
	}

}
