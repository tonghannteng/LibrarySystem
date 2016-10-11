package ui.author;

import com.jfoenix.controls.JFXTextField;

import controller.AuthorController;
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
import person.Author;
import ui.IDelete;
import ui.ILoadTable;
import ui.IRefreshContent;
import ui.TableViewPage;

public class AuthorPage extends TableViewPage implements ILoadTable, IDelete {
	private AuthorAddPage authorAddPage = new AuthorAddPage(this);
	private AuthorEditPage authorEditPage = new AuthorEditPage(this);

	private JFXTextField firstName = new JFXTextField();
	private JFXTextField lastName = new JFXTextField();
	private JFXTextField address = new JFXTextField();
	private JFXTextField phone = new JFXTextField();

	public AuthorPage(IRefreshContent mainPage) {
		super("AUTHORS", mainPage);
		registerAddPage(authorAddPage);
		registerEditPage(authorEditPage);
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
		fieldPane.add(new Label("Address"), 4, 0);
		fieldPane.add(address, 5, 0);
		fieldPane.add(new Label("Phone"), 6, 0);
		fieldPane.add(phone, 7, 0);

		getSearchPane().getChildren().add(fieldPane);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void loadTable() {
		ObservableList<Author> data = FXCollections
				.observableArrayList(AuthorController.getList());
		getTableViewControl().setItems(data);
	}

	@SuppressWarnings("unchecked")
	public void buildTableUI() {
		TableColumn<Author, String> idColumn = new TableColumn<Author, String>(
				"Id");
		TableColumn<Author, String> firstNameColumn = new TableColumn<Author, String>(
				"First name");
		TableColumn<Author, String> lastNameColumn = new TableColumn<Author, String>(
				"Last name");
		TableColumn<Author, String> streetColumn = new TableColumn<Author, String>(
				"Street");
		TableColumn<Author, String> cityColumn = new TableColumn<Author, String>(
				"City");
		TableColumn<Author, String> stateColumn = new TableColumn<Author, String>(
				"State");
		TableColumn<Author, String> zipColumn = new TableColumn<Author, String>(
				"Zip");
		TableColumn<Author, String> countryColumn = new TableColumn<Author, String>(
				"Country");
		TableColumn<Author, String> phoneColumn = new TableColumn<Author, String>(
				"Phone");
		TableColumn<Author, String> credentialsColumn = new TableColumn<Author, String>(
				"Credentials");
		TableColumn<Author, String> biographyColumn = new TableColumn<Author, String>(
				"Biography");

		idColumn.setCellValueFactory(
				new PropertyValueFactory<Author, String>("id"));
		firstNameColumn.setCellValueFactory(
				new PropertyValueFactory<Author, String>("firstName"));
		lastNameColumn.setCellValueFactory(
				new PropertyValueFactory<Author, String>("lastName"));
		streetColumn.setCellValueFactory(
				new PropertyValueFactory<Author, String>("street"));
		cityColumn.setCellValueFactory(
				new PropertyValueFactory<Author, String>("city"));
		stateColumn.setCellValueFactory(
				new PropertyValueFactory<Author, String>("state"));
		zipColumn.setCellValueFactory(
				new PropertyValueFactory<Author, String>("zip"));
		countryColumn.setCellValueFactory(
				new PropertyValueFactory<Author, String>("country"));
		phoneColumn.setCellValueFactory(
				new PropertyValueFactory<Author, String>("phone"));
		credentialsColumn.setCellValueFactory(
				new PropertyValueFactory<Author, String>("credentials"));
		biographyColumn.setCellValueFactory(
				new PropertyValueFactory<Author, String>("biography"));

		getTableViewControl()
				.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		getTableViewControl().getColumns().addAll(idColumn, firstNameColumn,
				lastNameColumn, streetColumn, cityColumn, stateColumn,
				zipColumn, countryColumn, phoneColumn, credentialsColumn,
				biographyColumn);
	}

	private void addFilterHandlers() {
		firstName.textProperty().addListener(new FilterListener());
		lastName.textProperty().addListener(new FilterListener());
		address.textProperty().addListener(new FilterListener());
		phone.textProperty().addListener(new FilterListener());
	}

	class FilterListener implements ChangeListener<String> {
		@SuppressWarnings("unchecked")
		@Override
		public void changed(ObservableValue<? extends String> observable,
				String oldValue, String newValue) {
			FilteredList<Author> filteredData = new FilteredList<>(FXCollections
					.observableArrayList(AuthorController.getList()),
					p -> true);
			filteredData.setPredicate(author -> {
				String value;

				// filtered by first name
				value = firstName.getText();
				if (!value.isEmpty()
						&& !author.getFirstName().contains(value)) {
					return false;
				}
				// filtered by last name
				value = lastName.getText();
				if (!value.isEmpty() && !author.getLastName().contains(value)) {
					return false;
				}
				// filtered by address
				value = address.getText();
				String addressString = author.getAddress().toString();
				if (!value.isEmpty() && !addressString.contains(value)) {
					return false;
				}
				// filtered by phone
				value = phone.getText();
				if (!value.isEmpty() && !author.getPhone().contains(value)) {
					return false;
				}

				return true;
			});

			getTableViewControl().setItems(filteredData);
		}
	}

	@Override
	public void delete(Object object) {
		AuthorController.delete(((Author) object).getId());
	}
}
