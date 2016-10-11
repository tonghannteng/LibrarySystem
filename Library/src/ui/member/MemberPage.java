package ui.member;

import java.util.List;

import com.jfoenix.controls.JFXTextField;

import book.CheckoutEntry;
import controller.CheckoutController;
import controller.MemberController;
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
import person.LibraryMember;
import ui.IDelete;
import ui.ILoadTable;
import ui.IRefreshContent;
import ui.TableViewPage;

public class MemberPage extends TableViewPage implements ILoadTable, IDelete {

	private MemberAddPage memberAddPage = new MemberAddPage(this);
	private MemberEditPage memberEditPage = new MemberEditPage(this);

	private JFXTextField firstname = new JFXTextField();

	public MemberPage(IRefreshContent mainPage) {
		super("Library Member Management", mainPage);
		registerAddPage(memberAddPage);
		registerEditPage(memberEditPage);
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

		getSearchPane().getChildren().add(fieldPane);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void loadTable() {
		ObservableList<LibraryMember> data = FXCollections
				.observableArrayList(MemberController.getList());
		getTableViewControl().setItems(data);
	}

	@SuppressWarnings("unchecked")
	public void buildTableUI() {
		TableColumn<LibraryMember, String> memberidColumn = new TableColumn<LibraryMember, String>(
				"MemberId");
		TableColumn<LibraryMember, String> firstNameColumn = new TableColumn<LibraryMember, String>(
				"First name");
		TableColumn<LibraryMember, String> lastNameColumn = new TableColumn<LibraryMember, String>(
				"Last name");
		TableColumn<LibraryMember, String> streetColumn = new TableColumn<LibraryMember, String>(
				"Street");
		TableColumn<LibraryMember, String> cityColumn = new TableColumn<LibraryMember, String>(
				"City");
		TableColumn<LibraryMember, String> stateColumn = new TableColumn<LibraryMember, String>(
				"State");
		TableColumn<LibraryMember, String> zipColumn = new TableColumn<LibraryMember, String>(
				"Zip");
		TableColumn<LibraryMember, String> countryColumn = new TableColumn<LibraryMember, String>(
				"Country");
		TableColumn<LibraryMember, String> phoneColumn = new TableColumn<LibraryMember, String>(
				"Phone");

		memberidColumn.setCellValueFactory(
				new PropertyValueFactory<LibraryMember, String>("MemberId"));
		firstNameColumn.setCellValueFactory(
				new PropertyValueFactory<LibraryMember, String>("firstName"));
		lastNameColumn.setCellValueFactory(
				new PropertyValueFactory<LibraryMember, String>("lastName"));
		streetColumn.setCellValueFactory(
				new PropertyValueFactory<LibraryMember, String>("street"));
		cityColumn.setCellValueFactory(
				new PropertyValueFactory<LibraryMember, String>("city"));
		stateColumn.setCellValueFactory(
				new PropertyValueFactory<LibraryMember, String>("state"));
		zipColumn.setCellValueFactory(
				new PropertyValueFactory<LibraryMember, String>("zip"));
		countryColumn.setCellValueFactory(
				new PropertyValueFactory<LibraryMember, String>("country"));
		phoneColumn.setCellValueFactory(
				new PropertyValueFactory<LibraryMember, String>("phone"));

		getTableViewControl()
				.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		getTableViewControl().getColumns().addAll(memberidColumn,
				firstNameColumn, lastNameColumn, streetColumn, cityColumn,
				stateColumn, zipColumn, countryColumn, phoneColumn);
	}

	private void addFilterHandlers() {
		firstname.textProperty().addListener(new FilterListener());
	}

	class FilterListener implements ChangeListener<String> {
		@SuppressWarnings("unchecked")
		@Override
		public void changed(ObservableValue<? extends String> observable,
				String oldValue, String newValue) {
			FilteredList<LibraryMember> filteredData = new FilteredList<>(
					FXCollections.observableArrayList(
							MemberController.getList()),
					p -> true);
			filteredData.setPredicate(member -> {
				// filtered by Firstname
				String value = firstname.getText().trim();
				if (!value.isEmpty()
						&& !member.getFirstName().contains(value)) {
					return false;
				}
				return true;
			});

			getTableViewControl().setItems(filteredData);
		}
	}

	@Override
	public void delete(Object object) {
		LibraryMember member = (LibraryMember) object;
		List<CheckoutEntry> checkoutEntries = CheckoutController.getList();
		// if there is book which is borrowing by Library member
		if (checkoutEntries.size() != 0) {
			for (int i = 0; i < checkoutEntries.size(); i++) {
				if (checkoutEntries.get(i).getMemberId() == (member)
						.getMemberId()) {
					setMessage(
							"This member is borrowing book, so cannot delete.");
					return;
				} else {
					MemberController.delete((member).getMemberId());
				}
			}
		} else {
			MemberController.delete((member).getMemberId());
		}
	}
}
