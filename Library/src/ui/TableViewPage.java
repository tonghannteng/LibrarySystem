package ui;

import java.util.Optional;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TableViewPage implements IGoBack {
	private Label title = new Label();
	private StackPane searchPane = new StackPane();
	private StackPane contentPane = new StackPane();
	private Label message = new Label("");
	private JFXButton addButton = new JFXButton("Add");
	private JFXButton editButton = new JFXButton("Edit");
	private JFXButton deleteButton = new JFXButton("Delete");
	private HBox buttons = new HBox(10);
	private VBox mainPane = new VBox(20);

	@SuppressWarnings("rawtypes")
	private TableView table = new TableView();

	private ILoadTable loadTable;
	private IDelete deleteRecord;

	private DetailsPage addPage;
	private DetailsPage editPage;
	private IRefreshContent mainPage;

	public TableViewPage(String title, IRefreshContent mainPage) {
		this.title.setText(title);
		this.mainPage = mainPage;
		addHandlers();
	}

	@SuppressWarnings("unchecked")
	public void buildUI() {
		contentPane.setPadding(new Insets(10, 10, 10, 10));
		contentPane.setMinSize(300, 100);
		contentPane.getChildren().add(mainPane);

		title.setFont(new Font(18));
		mainPane.getChildren().add(title);

		message.setTextFill(Color.RED);

		buttons.getChildren().add(addButton);
		buttons.getChildren().add(editButton);
		buttons.getChildren().add(deleteButton);

		editButton.setDisable(true);
		deleteButton.setDisable(true);
		addButton.setButtonType(ButtonType.RAISED);
		addButton.getStyleClass().add("button-raised");
		editButton.setButtonType(ButtonType.RAISED);
		editButton.getStyleClass().add("button-raised");
		deleteButton.setButtonType(ButtonType.RAISED);
		deleteButton.getStyleClass().add("button-raised");
		buttons.setAlignment(Pos.CENTER);

		mainPane.getChildren().add(searchPane);
		mainPane.getChildren().add(table);
		mainPane.getChildren().add(message);
		mainPane.getChildren().add(buttons);

		loadTable.loadTable();

		table.setColumnResizePolicy((param) -> true);
	}

	public StackPane getContentPane() {
		return contentPane;
	}

	public StackPane getSearchPane() {
		return searchPane;
	}

	protected void setMessage(String textMesssage) {
		message.setText(textMesssage);
	}

	protected void registerAddPage(DetailsPage addPage) {
		this.addPage = addPage;
	}

	protected void registerEditPage(DetailsPage editPage) {
		this.editPage = editPage;
	}

	@SuppressWarnings("unchecked")
	private void addHandlers() {
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mainPage.refreshContentPane(addPage.getContentPane());
			}
		});

		editButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Object selectedObject = table.getSelectionModel()
						.getSelectedItem();
				if (selectedObject != null) {
					editPage.loadSelectedObject(selectedObject);
					mainPage.refreshContentPane(editPage.getContentPane());
				}
			}
		});

		deleteButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation");
				alert.setContentText("Are you sure you want to delete?");
				Optional<javafx.scene.control.ButtonType> result = alert
						.showAndWait();
				if (result.get() == javafx.scene.control.ButtonType.OK) {
					Object selectedObject = table.getSelectionModel()
							.getSelectedItem();
					if (selectedObject != null) {
						deleteRecord.delete(selectedObject);
						loadTable.loadTable();
					}
				}
			}
		});

		table.getSelectionModel().selectedItemProperty()
				.addListener(new SelectItemListener());
	}

	private void refreshContentPane(StackPane contentPane) {
		loadTable.loadTable();
		mainPage.refreshContentPane(contentPane);
	}

	@Override
	public void goBack() {
		refreshContentPane(contentPane);
	}

	@SuppressWarnings("rawtypes")
	protected TableView getTableViewControl() {
		return this.table;
	}

	protected void registerLoadTableFunction(ILoadTable function) {
		loadTable = function;
	}

	protected void registerDeleteRecordFunction(IDelete function) {
		deleteRecord = function;
	}

	class SelectItemListener implements ChangeListener<Object> {
		@Override
		public void changed(ObservableValue<? extends Object> observable,
				Object oldSelection, Object newSelection) {
			editButton.setDisable(false);
			deleteButton.setDisable(false);
		}
	}

	protected void removeAddButton() {
		buttons.getChildren().remove(0);
	}

	protected void removeEditButton() {
		buttons.getChildren().remove(0);
	}

	protected void removeDeleteButton() {
		buttons.getChildren().remove(0);
	}
}
