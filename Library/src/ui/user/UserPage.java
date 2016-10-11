package ui.user;

import com.jfoenix.controls.JFXTextField;

import controller.UserController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import person.User;
import ui.IDelete;
import ui.ILoadTable;
import ui.IRefreshContent;
import ui.TableViewPage;

public class UserPage extends TableViewPage implements ILoadTable, IDelete {
	private UserAddPage userAddPage = new UserAddPage(this);
	private UserEditPage userEditPage = new UserEditPage(this);

	private JFXTextField username = new JFXTextField();
	private JFXTextField firstname = new JFXTextField();

	// Define for Authorization combobox
	public static String[] getAuthorizations() {
		User.Authorization[] values = User.Authorization.values();
		String[] names = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			names[i] = values[i].name();
		}
		return names;
	}

	String[] authorizations = getAuthorizations();
	ObservableList<String> options = FXCollections.observableArrayList(authorizations);
	final ComboBox<String> cbAuthorization = new ComboBox<String>(options);

	public UserPage(IRefreshContent mainPage) {
		super("Users", mainPage);
		registerAddPage(userAddPage);
		registerEditPage(userEditPage);
		buildSearchPane();
		buildTableUI();
		addFilterHandlers();
		registerLoadTableFunction(this);
		registerDeleteRecordFunction(this);
	}

	public void buildSearchPane() {

		GridPane fieldPane = new GridPane();
		fieldPane.setVgap(10);
		fieldPane.setHgap(10);

		fieldPane.add(new Label("Firstname"), 0, 0);
		fieldPane.add(firstname, 1, 0);
		fieldPane.add(new Label("User name"), 2, 0);
		fieldPane.add(username, 3, 0);
		fieldPane.add(new Label("Authorization"), 4, 0);
		fieldPane.add(cbAuthorization, 5, 0);

		getSearchPane().getChildren().add(fieldPane);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void loadTable() {
		ObservableList<User> data = FXCollections.observableArrayList(UserController.getList());
		getTableViewControl().setItems(data);
	}

	@SuppressWarnings("unchecked")
	public void buildTableUI() {
		TableColumn<User, String> firstNameColumn = new TableColumn<User, String>("First name");
		TableColumn<User, String> lastNameColumn = new TableColumn<User, String>("Last name");
		TableColumn<User, String> streetColumn = new TableColumn<User, String>("Street");
		TableColumn<User, String> cityColumn = new TableColumn<User, String>("City");
		TableColumn<User, String> stateColumn = new TableColumn<User, String>("State");
		TableColumn<User, String> zipColumn = new TableColumn<User, String>("Zip");
		TableColumn<User, String> countryColumn = new TableColumn<User, String>("Country");
		TableColumn<User, String> phoneColumn = new TableColumn<User, String>("Phone");
		TableColumn<User, String> usernameColumn = new TableColumn<User, String>("Username");
		TableColumn<User, String> authorizationColumn = new TableColumn<User, String>("Authorization");

		firstNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
		streetColumn.setCellValueFactory(new PropertyValueFactory<User, String>("street"));
		cityColumn.setCellValueFactory(new PropertyValueFactory<User, String>("city"));
		stateColumn.setCellValueFactory(new PropertyValueFactory<User, String>("state"));
		zipColumn.setCellValueFactory(new PropertyValueFactory<User, String>("zip"));
		countryColumn.setCellValueFactory(new PropertyValueFactory<User, String>("country"));
		phoneColumn.setCellValueFactory(new PropertyValueFactory<User, String>("phone"));
		usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		authorizationColumn.setCellValueFactory(new PropertyValueFactory<User, String>("AuthorizationValue"));

		getTableViewControl().setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		getTableViewControl().getColumns().addAll(firstNameColumn, lastNameColumn, streetColumn, cityColumn,
				stateColumn, zipColumn, countryColumn, phoneColumn, usernameColumn, authorizationColumn);
	}

	private void addFilterHandlers() {
		username.textProperty().addListener(new FilterListener());
		firstname.textProperty().addListener(new FilterListener());
		cbAuthorization.valueProperty().addListener(new FilterListener());
	}

	class FilterListener implements ChangeListener<String> {
		@SuppressWarnings("unchecked")
		@Override
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			FilteredList<User> filteredData = new FilteredList<>(
					FXCollections.observableArrayList(UserController.getList()), p -> true);
			filteredData.setPredicate(user -> {
				String value;
				
				// filtered by Firstname
				value = firstname.getText().trim();
				if (!value.isEmpty() && !user.getFirstName().contains(value)) {
					return false;
				}
				// filtered by User name
				value = username.getText().trim();
				if (!value.isEmpty() && !user.getUsername().contains(value)) {
					return false;
				}
				// filtered by Authorization
				value = cbAuthorization.getValue();
				if (!value.isEmpty() && !user.getAuthorizationValue().equals(value)) {
					return false;
				}
				return true;
			});

			getTableViewControl().setItems(filteredData);
		}
	}
	@Override
	public void delete(Object object) {
		UserController.delete(((User) object).getUsername());
	}
}
