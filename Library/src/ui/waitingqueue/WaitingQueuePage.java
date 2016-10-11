package ui.waitingqueue;

import com.jfoenix.controls.JFXTextField;

import book.WaitingEntry;
import controller.WaitingController;
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

public class WaitingQueuePage extends TableViewPage
		implements ILoadTable, IDelete {

	private JFXTextField firstName = new JFXTextField();
	private JFXTextField lastName = new JFXTextField();
	private JFXTextField bookISBN = new JFXTextField();
	private JFXTextField bookTitle = new JFXTextField();

	public WaitingQueuePage(IRefreshContent mainPage) {
		super("WAITING QUEUE", mainPage);
		buildSearchPane();
		buildTableUI();
		addFilterHandlers();

		registerLoadTableFunction(this);
		registerDeleteRecordFunction(this);
	}

	private void buildSearchPane() {
		GridPane fieldPane = new GridPane();
		fieldPane.setVgap(10);
		fieldPane.setHgap(10);

		fieldPane.add(new Label("First name"), 0, 0);
		fieldPane.add(firstName, 1, 0);
		fieldPane.add(new Label("Last name"), 2, 0);
		fieldPane.add(lastName, 3, 0);
		fieldPane.add(new Label("Book ISBN"), 4, 0);
		fieldPane.add(bookISBN, 5, 0);
		fieldPane.add(new Label("Book title"), 6, 0);
		fieldPane.add(bookTitle, 7, 0);

		getSearchPane().getChildren().add(fieldPane);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void loadTable() {
		ObservableList<WaitingEntry> data = FXCollections
				.observableArrayList(WaitingController.getList());
		getTableViewControl().setItems(data);
	}

	@SuppressWarnings("unchecked")
	public void buildTableUI() {
		TableColumn<WaitingEntry, String> memberIdColumn = new TableColumn<WaitingEntry, String>(
				"Member Id");
		TableColumn<WaitingEntry, String> firstNameColumn = new TableColumn<WaitingEntry, String>(
				"First name");
		TableColumn<WaitingEntry, String> lastNameColumn = new TableColumn<WaitingEntry, String>(
				"Last name");
		TableColumn<WaitingEntry, String> bookISBNColumn = new TableColumn<WaitingEntry, String>(
				"ISBN");
		TableColumn<WaitingEntry, String> bookTitleColumn = new TableColumn<WaitingEntry, String>(
				"Title");

		memberIdColumn.setCellValueFactory(
				new PropertyValueFactory<WaitingEntry, String>("MemberId"));
		firstNameColumn.setCellValueFactory(
				new PropertyValueFactory<WaitingEntry, String>("Firstname"));
		lastNameColumn.setCellValueFactory(
				new PropertyValueFactory<WaitingEntry, String>("Lastname"));
		bookISBNColumn.setCellValueFactory(
				new PropertyValueFactory<WaitingEntry, String>("ISBN"));
		bookTitleColumn.setCellValueFactory(
				new PropertyValueFactory<WaitingEntry, String>("BookTitle"));

		getTableViewControl()
				.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		getTableViewControl().getColumns().addAll(memberIdColumn,
				firstNameColumn, lastNameColumn, bookISBNColumn,
				bookTitleColumn);
	}

	private void addFilterHandlers() {
		firstName.textProperty().addListener(new FilterListener());
		lastName.textProperty().addListener(new FilterListener());
		bookISBN.textProperty().addListener(new FilterListener());
		bookTitle.textProperty().addListener(new FilterListener());
	}

	class FilterListener implements ChangeListener<String> {
		@SuppressWarnings("unchecked")
		@Override
		public void changed(ObservableValue<? extends String> observable,
				String oldValue, String newValue) {
			FilteredList<WaitingEntry> filteredData = new FilteredList<>(
					FXCollections.observableArrayList(
							WaitingController.getList()),
					p -> true);
			filteredData.setPredicate(waitingEntry -> {
				String value;

				// filtered by first name
				value = firstName.getText();
				if (!value.isEmpty()
						&& !waitingEntry.getFirstname().contains(value)) {
					return false;
				}
				// filtered by last name
				value = lastName.getText();
				if (!value.isEmpty()
						&& !waitingEntry.getLastname().contains(value)) {
					return false;
				}
				// filtered by ISBN
				value = bookISBN.getText();
				if (!value.isEmpty()
						&& !waitingEntry.getISBN().contains(value)) {
					return false;
				}
				// filtered by book title
				value = bookTitle.getText();
				if (!value.isEmpty()
						&& !waitingEntry.getBookTitle().contains(value)) {
					return false;
				}

				return true;
			});

			getTableViewControl().setItems(filteredData);
		}
	}

	@Override
	public void delete(Object object) {
		WaitingEntry waitingEntry = (WaitingEntry) object;
		WaitingController.delete(waitingEntry.getISBN(),
				waitingEntry.getMemberId());
	}

	@Override
	public void buildUI() {
		super.buildUI();
		removeAddButton();
		removeEditButton();
	}
}
