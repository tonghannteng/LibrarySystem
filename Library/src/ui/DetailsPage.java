package ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DetailsPage {
	protected IGoBack parentPage;

	private Label title = new Label();
	private Label message = new Label("");
	protected GridPane fieldPane = new GridPane();
	private JFXButton okButton = new JFXButton("OK");
	private JFXButton cancelButton = new JFXButton("Cancel");

	private HBox buttons = new HBox(10);
	private StackPane contentPane = new StackPane();
	private VBox mainPane = new VBox(20);

	public DetailsPage(String title, IGoBack parentPage) {
		this.parentPage = parentPage;
		this.title.setText(title);
		buildUI();

		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				refreshContentPane();
			}
		});
	}

	private void buildUI() {
		contentPane.setPadding(new Insets(10, 10, 10, 10));
		contentPane.getChildren().add(mainPane);

		title.setFont(new Font(15));
		mainPane.getChildren().add(title);

		buttons.setAlignment(Pos.CENTER);
		buttons.getChildren().addAll(okButton, cancelButton);
		okButton.setButtonType(ButtonType.RAISED);
		okButton.getStyleClass().add("button-raised");
		cancelButton.setButtonType(ButtonType.RAISED);
		cancelButton.getStyleClass().add("button-raised");

		message.setTextFill(Color.RED);

		mainPane.getChildren().add(getFieldPane());
		mainPane.getChildren().add(message);
		mainPane.getChildren().add(buttons);
	}

	public StackPane getContentPane() {
		return contentPane;
	}

	public GridPane getFieldPane() {
		return fieldPane;
	}

	protected void setMessage(String textMesssage) {
		message.setText(textMesssage);
	}

	protected void registerOKHandler(EventHandler<ActionEvent> handler) {
		okButton.setOnAction(handler);
	}

	protected void registerCancelHandler(EventHandler<ActionEvent> handler) {
		cancelButton.setOnAction(handler);
	}

	protected void refreshContentPane() {
		parentPage.goBack();
	}

	protected void loadSelectedObject(Object selectedObject) {
	}
}
