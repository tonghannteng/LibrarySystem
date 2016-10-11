package ui.checkout;

import java.util.Optional;

import com.jfoenix.controls.JFXTextField;

import book.CheckoutEntry;
import controller.CheckoutController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import ui.ILoadTable;
import ui.IRefreshContent;
import ui.TableViewPage;

public class ReturnPage extends TableViewPage implements ILoadTable {

	private Button returnButton = new Button("Return");
	private JFXTextField memberID = new JFXTextField();
	private JFXTextField ISBN = new JFXTextField();

	//private ILoadTable loadTable;
	public ReturnPage(IRefreshContent mainPage) {
		super("Return book", mainPage);
		buildSearchPane();
		buildTableUI();
		addFilterHandlers();
		addHandlers();
		
		registerLoadTableFunction(this);
	}

	public void buildSearchPane() {
		GridPane fieldPane = new GridPane();
		fieldPane.setVgap(10);
		fieldPane.setHgap(10);

		fieldPane.add(new Label("Member ID"), 0, 0);
		fieldPane.add(memberID, 1, 0);
		fieldPane.add(new Label("ISBN"), 2, 0);
		fieldPane.add(ISBN, 3, 0);
		fieldPane.add(returnButton, 6, 0);
		returnButton.setDisable(true);
		returnButton.getStyleClass().add("button-raised");

		getSearchPane().getChildren().add(fieldPane);
	}

	public class SelectItemListener implements ChangeListener<Object> {
		@Override
		public void changed(ObservableValue<? extends Object> observable, Object oldSelection, Object newSelection) {
			returnButton.setDisable(false);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void addHandlers() {
		
		returnButton.setOnAction(new EventHandler<ActionEvent>() {
			@SuppressWarnings("rawtypes")
			@Override
			public void handle(ActionEvent event) {
				TableView table = getTableViewControl();
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation");
				alert.setContentText("Are you sure you want to return this book?");
				Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
				if (result.get() == javafx.scene.control.ButtonType.OK) {
					Object selectedObject = table.getSelectionModel().getSelectedItem();
					if (selectedObject != null) {
						CheckoutEntry entry = (CheckoutEntry) selectedObject;
						CheckoutController.deleteCheckoutEntry(entry.getISBN(), entry.getCopyId());						
						loadTable();
					}
				}
				//move focus to the first record
		        table.requestFocus();                       // Get the focus
		        table.getSelectionModel().selectFirst();    // select first item in TableView model
		        table.getFocusModel().focus(0);             // set the focus on the first element
			}
		});

		getTableViewControl().getSelectionModel().selectedItemProperty().addListener(new SelectItemListener());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void loadTable() {
		ObservableList<CheckoutEntry> data = FXCollections.observableArrayList(CheckoutController.getList());
		getTableViewControl().setItems(data);
	}

	@SuppressWarnings("unchecked")
	public void buildTableUI() {
		TableColumn<CheckoutEntry, String> memberIdColumn = new TableColumn<CheckoutEntry, String>("Member ID");
		TableColumn<CheckoutEntry, String> firstNameColumn = new TableColumn<CheckoutEntry, String>("FirstName");
		TableColumn<CheckoutEntry, String> lastNameColumn = new TableColumn<CheckoutEntry, String>("LastName");
		TableColumn<CheckoutEntry, String> isbnColumn = new TableColumn<CheckoutEntry, String>("ISBN");
		TableColumn<CheckoutEntry, String> bookTitleColumn = new TableColumn<CheckoutEntry, String>("Book Title");
		TableColumn<CheckoutEntry, String> checkedoutDateColumn = new TableColumn<CheckoutEntry, String>(
				"Checked out Date");
		TableColumn<CheckoutEntry, String> dueDateColumn = new TableColumn<CheckoutEntry, String>("DueDate");

		memberIdColumn.setCellValueFactory(new PropertyValueFactory<CheckoutEntry, String>("MemberId"));
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<CheckoutEntry, String>("FirstName"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<CheckoutEntry, String>("LastName"));
		isbnColumn.setCellValueFactory(new PropertyValueFactory<CheckoutEntry, String>("ISBN"));
		bookTitleColumn.setCellValueFactory(new PropertyValueFactory<CheckoutEntry, String>("BookTitle"));
		checkedoutDateColumn.setCellValueFactory(new PropertyValueFactory<CheckoutEntry, String>("DateOfCheckout"));
		dueDateColumn.setCellValueFactory(new PropertyValueFactory<CheckoutEntry, String>("DueDate"));

		getTableViewControl().setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		getTableViewControl().getColumns().addAll(memberIdColumn, firstNameColumn, lastNameColumn, isbnColumn,
				bookTitleColumn, checkedoutDateColumn, dueDateColumn);
	}

	private void addFilterHandlers() {
		memberID.textProperty().addListener(new FilterListener());
		ISBN.textProperty().addListener(new FilterListener());
	}

	class FilterListener implements ChangeListener<String> {
		@SuppressWarnings("unchecked")
		@Override
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			FilteredList<CheckoutEntry> filteredData = new FilteredList<>(
					FXCollections.observableArrayList(CheckoutController.getList()), p -> true);
			filteredData.setPredicate(entry -> {
				// filtered by Member ID
				String value = memberID.getText().trim();
				if (!value.isEmpty() && !Integer.toString(entry.getMemberId()).contains(value)) {
					return false;
				}
				// filtered by ISBN
				value = ISBN.getText().trim();
				if (!value.isEmpty() && !entry.getISBN().contains(value)) {
					return false;
				}
				return true;
			});

			getTableViewControl().setItems(filteredData);
		}
	}

	@Override
	public void buildUI() {
		super.buildUI();
		removeAddButton();
		removeEditButton();
		removeDeleteButton();
	}
}
