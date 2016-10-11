package ui.book;

import com.jfoenix.controls.JFXTextField;
import book.Book;
import controller.BookController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import ui.IDelete;
import ui.ILoadTable;
import ui.IRefreshContent;
import ui.TableViewPage;

public class BookPage extends TableViewPage implements ILoadTable, IDelete {
	
	private BookAddPage bookAddPage = new BookAddPage(this);
	private BookEditPage bookEditPage = new BookEditPage(this);
	
	private JFXTextField searchISBN = new JFXTextField();
	private JFXTextField searchTitle = new JFXTextField();
	private JFXTextField searchmaxLength = new JFXTextField();
	private JFXTextField searchNumberCopy = new JFXTextField();

	public BookPage(IRefreshContent mainPage) {
		super("BOOK", mainPage);
		registerAddPage(bookAddPage);
		registerEditPage(bookEditPage);
		buildSearchPane();
		buildTableUI();
		addFilterHandlers();
		
		registerLoadTableFunction(this);
		registerDeleteRecordFunction(this);
		// TODO Auto-generated constructor stub
	}
	
	public void buildSearchPane() {
		GridPane fieldPane = new GridPane();
		fieldPane.setVgap(10);
		fieldPane.setHgap(10);
		
		fieldPane.add(new Label("ISBN"), 0, 0);
		fieldPane.add(searchISBN, 1, 0);
		fieldPane.add(new Label("Title"), 2, 0);
		fieldPane.add(searchTitle, 3, 0);
		fieldPane.add(new Label("Max Length"), 4, 0);
		fieldPane.add(searchmaxLength, 5, 0);
		fieldPane.add(new Label("Number of Copy"), 6, 0);
		fieldPane.add(searchNumberCopy, 7, 0);

		getSearchPane().getChildren().add(fieldPane);

	}
	

	@SuppressWarnings("unchecked")
	@Override
	public void loadTable() {
		ObservableList<Book> data = FXCollections.observableArrayList(BookController.getList());
		getTableViewControl().setItems(data);
	}

	@SuppressWarnings("unchecked")
	public void buildTableUI() {
		TableColumn<Book, String> isbnColumn = new TableColumn<Book, String>("ISBN");
		TableColumn<Book, String> titleColumn = new TableColumn<Book, String>("Title");
		TableColumn<Book, String> maxLengthColumn = new TableColumn<Book, String>("Max Length");
		TableColumn<Book, String> numberOfCopyColumn = new TableColumn<Book, String>("NumberOfCopy");

		isbnColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("ISBN"));
		titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("Title"));
		maxLengthColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("MaxCheckoutLength"));
		numberOfCopyColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("NumOfCopy"));

		getTableViewControl().setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		getTableViewControl().getColumns().addAll(isbnColumn, titleColumn, maxLengthColumn, numberOfCopyColumn);
	}

	private void addFilterHandlers() {
		searchISBN.textProperty().addListener(new FilterListener());
		searchTitle.textProperty().addListener(new FilterListener());
		searchmaxLength.textProperty().addListener(new FilterListener());
		searchNumberCopy.textProperty().addListener(new FilterListener());
	}
	
	class FilterListener implements ChangeListener<String> {
		@SuppressWarnings("unchecked")
		@Override
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			FilteredList<Book> filteredData = new FilteredList<>(
					FXCollections.observableArrayList(BookController.getList()), p -> true);
			filteredData.setPredicate(book -> {
				String value;

				value = searchISBN.getText();
				if (!value.isEmpty() && !book.getISBN().contains(value)) {
					return false;
				}
				
				value = searchTitle.getText();
				if (!value.isEmpty() && !book.getTitle().contains(value)) {
					return false;
				}

				return true;
			});

			getTableViewControl().setItems(filteredData);
		}
	}

	@Override
	public void delete(Object object) {
		BookController.deleteBook(((Book) object).getISBN());
	}

}
