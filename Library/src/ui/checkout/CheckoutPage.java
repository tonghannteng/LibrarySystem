package ui.checkout;

import java.util.Optional;

import com.jfoenix.controls.JFXTextField;

import book.CheckoutEntry;
import controller.BookController;
import controller.CheckoutController;
import controller.MemberController;
import controller.WaitingController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import ui.IDelete;
import ui.ILoadTable;
import ui.IRefreshContent;
import ui.TableViewPage;

public class CheckoutPage extends TableViewPage implements ILoadTable, IDelete {

	private Button checkoutButton = new Button("Checkout");
	private JFXTextField memberID = new JFXTextField();
	private JFXTextField ISBN = new JFXTextField();

	public CheckoutPage(IRefreshContent mainPage) {
		super("Check Out", mainPage);
		buildSearchPane();
		buildTableUI();

		registerLoadTableFunction(this);
		registerDeleteRecordFunction(this);
		// TODO Auto-generated constructor stub
	}

	public void buildSearchPane() {
		GridPane fieldPane = new GridPane();
		fieldPane.setVgap(10);
		fieldPane.setHgap(10);

		fieldPane.add(new Label("Member ID"), 0, 0);
		fieldPane.add(memberID, 1, 0);
		fieldPane.add(new Label("ISBN"), 2, 0);
		fieldPane.add(ISBN, 3, 0);
		fieldPane.add(checkoutButton, 6, 0);
		checkoutButton.getStyleClass().add("button-raised");
		checkoutButton.setOnAction(new CheckoutHandler());

		getSearchPane().getChildren().add(fieldPane);

	}

	class CheckoutHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent arg0) {
			if (validate()) {
				CheckoutEntry checkoutEntry = new CheckoutEntry(ISBN.getText(),
						CheckoutController.getAvailableBookCopy(ISBN.getText()),
						Integer.parseInt(memberID.getText()));
				CheckoutController.addCheckoutEntry(checkoutEntry);
				loadTable();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void loadTable() {
		ObservableList<CheckoutEntry> data = FXCollections
				.observableArrayList(CheckoutController.getList());
		getTableViewControl().setItems(data);
	}

	@SuppressWarnings("unchecked")
	public void buildTableUI() {
		TableColumn<CheckoutEntry, String> memberIdColumn = new TableColumn<CheckoutEntry, String>(
				"Member ID");
		TableColumn<CheckoutEntry, String> firstNameColumn = new TableColumn<CheckoutEntry, String>(
				"FirstName");
		TableColumn<CheckoutEntry, String> lastNameColumn = new TableColumn<CheckoutEntry, String>(
				"LastName");
		TableColumn<CheckoutEntry, String> isbnColumn = new TableColumn<CheckoutEntry, String>(
				"ISBN");
		TableColumn<CheckoutEntry, String> bookTitleColumn = new TableColumn<CheckoutEntry, String>(
				"Book Title");
		TableColumn<CheckoutEntry, String> checkedoutDateColumn = new TableColumn<CheckoutEntry, String>(
				"Checked out Date");
		TableColumn<CheckoutEntry, String> dueDateColumn = new TableColumn<CheckoutEntry, String>(
				"DueDate");

		memberIdColumn.setCellValueFactory(
				new PropertyValueFactory<CheckoutEntry, String>("MemberId"));
		firstNameColumn.setCellValueFactory(
				new PropertyValueFactory<CheckoutEntry, String>("FirstName"));
		lastNameColumn.setCellValueFactory(
				new PropertyValueFactory<CheckoutEntry, String>("LastName"));
		isbnColumn.setCellValueFactory(
				new PropertyValueFactory<CheckoutEntry, String>("ISBN"));
		bookTitleColumn.setCellValueFactory(
				new PropertyValueFactory<CheckoutEntry, String>("BookTitle"));
		checkedoutDateColumn.setCellValueFactory(
				new PropertyValueFactory<CheckoutEntry, String>(
						"DateOfCheckout"));
		dueDateColumn.setCellValueFactory(
				new PropertyValueFactory<CheckoutEntry, String>("DueDate"));

		getTableViewControl()
				.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		getTableViewControl().getColumns().addAll(memberIdColumn,
				firstNameColumn, lastNameColumn, isbnColumn, bookTitleColumn,
				checkedoutDateColumn, dueDateColumn);
	}

	private boolean validate() {
		if (BookController.getBook(ISBN.getText()) == null) {
			setMessage("Book doesn't exist");
			return false;
		}

		if (!CheckoutController.isBookCopyAvailable(ISBN.getText())) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Book copy is not available");
			alert.setContentText(
					"Book copy is not available.\nDo you want to be in waiting queue?");
			Optional<javafx.scene.control.ButtonType> result = alert
					.showAndWait();
			if (result.get() == javafx.scene.control.ButtonType.OK) {
				WaitingController.add(ISBN.getText(),
						Integer.parseInt(memberID.getText()));
			}

			return false;
		}

		int memberId;
		try {
			memberId = Integer.parseInt(memberID.getText());
		} catch (NumberFormatException e) {
			setMessage("Member Id is not valid");
			return false;
		}

		if (!MemberController.doesMemberExist(memberId)) {
			setMessage("Member does not exist");
			return false;
		}

		return true;
	}

	@Override
	public void buildUI() {
		super.buildUI();
		removeAddButton();
		removeEditButton();
	}

	@Override
	public void delete(Object object) {
		CheckoutController.deleteCheckoutEntry(
				((CheckoutEntry) object).getISBN(),
				((CheckoutEntry) object).getCopyId());

	}
}
