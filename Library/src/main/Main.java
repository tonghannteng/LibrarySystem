package main;

import com.jfoenix.controls.JFXButton;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import person.User.Authorization;
import ui.user.UserPage;
import ui.waitingqueue.WaitingQueuePage;
import ui.IRefreshContent;
import ui.author.AuthorPage;
import ui.book.BookPage;
import ui.checkout.CheckoutPage;
import ui.checkout.ReturnPage;
import ui.member.MemberPage;

public class Main extends Application implements IRefreshContent {
	private final int MENU_WIDTH = 100;
	private JFXButton users, members, books, authors, checkout, bookReturn,
			waitingQueue;
	private StackPane contentPane;
	private HBox mainPane;
	private VBox menuPane;

	UserPage userPage = new UserPage(this);
	MemberPage memberPage = new MemberPage(this);
	BookPage bookPage = new BookPage(this);
	AuthorPage authorPage = new AuthorPage(this);
	CheckoutPage checkoutPage = new CheckoutPage(this);
	ReturnPage returnPage = new ReturnPage(this);
	WaitingQueuePage waitingQueuePage = new WaitingQueuePage(this);
	Login loginDialog = new Login();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		userPage.buildUI();
		memberPage.buildUI();
		bookPage.buildUI();
		authorPage.buildUI();
		checkoutPage.buildUI();
		returnPage.buildUI();
		waitingQueuePage.buildUI();

		String css = getClass().getResource("main.css").toExternalForm();
		Scene primaryScene = new Scene(buildUI());
		primaryScene.getStylesheets().add(css);

		primaryStage.setTitle("Library");
		primaryStage.setMaximized(true);
		primaryStage.setResizable(true);
		primaryStage.setScene(primaryScene);
		addHandlers();

		Authorization userAuthorization = loginDialog.show();
		if (userAuthorization != null) {

			if (userAuthorization != Authorization.ADMIN
					&& userAuthorization != Authorization.BOTH) {
				menuPane.getChildren().remove(0);
			}

			if (userAuthorization != Authorization.LIBRARIAN
					&& userAuthorization != Authorization.BOTH) {
				menuPane.getChildren().remove(1);
			}

			primaryStage.show();
		}
	}

	private StackPane buildUI() {
		contentPane = new StackPane();
		contentPane.setMinSize(700, 300);

		mainPane = new HBox();
		mainPane.getChildren().add(buildMenu());
		mainPane.getChildren().add(contentPane);

		StackPane root = new StackPane();
		root.setPadding(new Insets(10, 10, 10, 10));
		root.getChildren().add(mainPane);

		return root;
	}

	private VBox buildMenu() {
		menuPane = new VBox();
		menuPane.setPadding(new Insets(10, 10, 10, 10));
		menuPane.setStyle("-fx-background-color: rgb(127,152,204);");

		menuPane.getChildren().add(buildAdminMenu());
		menuPane.getChildren().add(buildLibrarianMenu());

		return menuPane;
	}

	private TitledPane buildAdminMenu() {
		users = new JFXButton("Users");
		members = new JFXButton("Members");
		books = new JFXButton("Books");
		authors = new JFXButton("Authors");

		users.setMinWidth(MENU_WIDTH);
		members.setMinWidth(MENU_WIDTH);
		books.setMinWidth(MENU_WIDTH);
		authors.setMinWidth(MENU_WIDTH);

		VBox adminMenu = new VBox(10);
		adminMenu.getChildren().add(users);
		adminMenu.getChildren().add(members);
		adminMenu.getChildren().add(books);
		adminMenu.getChildren().add(authors);

		TitledPane adminPane = new TitledPane("Administrator", adminMenu);
		adminPane.setExpanded(true);

		return adminPane;
	}

	private TitledPane buildLibrarianMenu() {
		checkout = new JFXButton("Checkout");
		bookReturn = new JFXButton("Return");
		waitingQueue = new JFXButton("Waiting queue");

		checkout.setMinWidth(MENU_WIDTH);
		bookReturn.setMinWidth(MENU_WIDTH);
		waitingQueue.setMinWidth(MENU_WIDTH);

		VBox librarianMenu = new VBox(10);
		librarianMenu.getChildren().add(checkout);
		librarianMenu.getChildren().add(bookReturn);
		librarianMenu.getChildren().add(waitingQueue);

		TitledPane librarianPane = new TitledPane("Librarian", librarianMenu);
		librarianPane.setExpanded(true);

		return librarianPane;
	}

	private void addHandlers() {
		AddUserHandler();
		AddMemberHandler();
		AddBookHandler();
		AddAuthorHandler();
		AddCheckoutHandler();
		AddReturnHandler();
		AddWaitingQueueHandler();
	}

	private void AddUserHandler() {
		users.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				userPage.loadTable();
				refreshContentPane(userPage.getContentPane());
			}
		});
	}

	private void AddMemberHandler() {
		members.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				memberPage.loadTable();
				refreshContentPane(memberPage.getContentPane());
			}
		});
	}

	private void AddBookHandler() {
		books.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				bookPage.loadTable();
				refreshContentPane(bookPage.getContentPane());
			}
		});
	}

	private void AddAuthorHandler() {
		authors.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				authorPage.loadTable();
				refreshContentPane(authorPage.getContentPane());
			}
		});
	}

	private void AddCheckoutHandler() {
		checkout.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				checkoutPage.loadTable();
				refreshContentPane(checkoutPage.getContentPane());
			}
		});
	}

	private void AddReturnHandler() {
		bookReturn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				returnPage.loadTable();
				refreshContentPane(returnPage.getContentPane());
			}
		});
	}

	private void AddWaitingQueueHandler() {
		waitingQueue.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				waitingQueuePage.loadTable();
				refreshContentPane(waitingQueuePage.getContentPane());
			}
		});
	}

	@Override
	public void refreshContentPane(StackPane contentPane) {
		mainPane.getChildren().remove(1);
		mainPane.getChildren().add(contentPane);
	}
}
