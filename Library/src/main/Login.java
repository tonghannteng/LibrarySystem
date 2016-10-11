package main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import controller.UserController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import person.User.Authorization;

public class Login {
	JFXTextField username;
	JFXPasswordField password;
	JFXButton loginButton;
	Label message;
	Authorization userAuthorization = null;

	private Stage stage;

	public Login() {
		buildUI();
		addLoginHandler();
	}

	private void buildUI() {
		username = new JFXTextField();
		password = new JFXPasswordField();
		message = new Label();
		loginButton = new JFXButton("Login");

		GridPane fields = new GridPane();
		fields.setHgap(10);
		fields.setVgap(10);

		message.setTextFill(Color.RED);
		fields.add(new Label("Username"), 0, 0);
		fields.add(username, 1, 0);
		fields.add(new Label("Password"), 0, 1);
		fields.add(password, 1, 1);

		VBox mainPane = new VBox(10);
		mainPane.setPadding(new Insets(10, 10, 10, 10));
		mainPane.getChildren().addAll(fields, message, loginButton);
		mainPane.setAlignment(Pos.CENTER);

		Scene primaryScene = new Scene(mainPane);
		stage = new Stage();
		stage.setTitle("Library");
		stage.setResizable(false);
		stage.setScene(primaryScene);
	}

	public Authorization show() {
		stage.showAndWait();
		return userAuthorization;
	}

	private void addLoginHandler() {
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				userAuthorization = UserController.getAuthorization(
						username.getText(), password.getText());
				if (userAuthorization == null) {
					setMessage("Invalid username or password");
				} else {
					stage.close();
				}
			}
		});
	}

	private void setMessage(String textMesssage) {
		message.setText(textMesssage);
	}
}
