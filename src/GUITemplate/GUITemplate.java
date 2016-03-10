package GUITemplate;


import connectivity.AddUser;
import connectivity.Login;
import entity.User;
import gui.AdminUser;
import gui.ChangeUserInfo;
import gui.QuizmakerGUI;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GUITemplate extends Application{
	private AddUser addUser;
	private Login logIn = new Login();
	private BooleanProperty isLoggedIn = new SimpleBooleanProperty(false);
	private BooleanProperty isAdmin = new SimpleBooleanProperty(false);
	private BorderPane root;
	private ChangeUserInfo userInfo;
	private QuizmakerGUI qMakerGUI;
	private User user = null;
	

                
    	Button btn1, btn2, btn3,btn4,btn5,btn6, btn7, btn8;
    	Label lbl;
    	Label lbl2;
    	
    	AnchorPane centerPane;
    	boolean writeLine = false;
    	
    	double clickX1;
    	double clickY1;

    	Stage primaryStage;
    	
    	public static void main(String[] args) {
    		launch(args);
    	}
    	
    	@Override
    	public void start(Stage primaryStage) {
    		//primaryStage.getIcons().add(new Image("/Newton.png"));	
    		primaryStage.setTitle("Newton test tool 0.5 Alpha");
    		this.primaryStage = primaryStage;
    		
    		
    		
    		primaryStage.setOnCloseRequest(e -> {
    			e.consume();
    			Platfom();
    		});	

  		
    		//Menyer topp
    		
    			
    		
    	    Menu viewMenu = new Menu("_View");
    	    /*
    	    ToggleGroup tGroup = new ToggleGroup();
    	    RadioMenuItem mysqlItem = new RadioMenuItem("MySQL");
    	    mysqlItem.setToggleGroup(tGroup);
    		
    		RadioMenuItem showCountdown = new RadioMenuItem("Javafx");
    	    showCountdown.setToggleGroup(tGroup);
    	    showCountdown.setSelected(true);
    		 
    	    viewMenu.getItems().addAll(mysqlItem, showCountdown,
    	    		new SeparatorMenuItem());
    	    */
    	    
    	    CheckMenuItem cssMenuItem = new CheckMenuItem("Show remaining time");
    	    cssMenuItem.setSelected(true);
    	    viewMenu.getItems().add(cssMenuItem);
    	   
    	    
    		
    		
    		

    		
    		//Vertikal box till v�nster med knappar och funktioner
    		
    		VBox leftMenu = new VBox(20);
    		leftMenu.setStyle("-fx-background-color: #F47920;");
    		leftMenu.setPadding(new Insets(10, 10, 10, 10));
    			/*
    			btn1 = new Button();
    			btn1.setLayoutX(22.0);
    			btn1.setLayoutY(50.0);
    			btn1.setText(" View tests ");
    			double b = 15;
    			btn1.setShape(new Circle(b));
    			btn1.setMinSize(5*b, 3*b);
    			btn1.setMaxSize(5*b, 3*b);
    			//btn1.setOnAction(this);
    			VBox.setMargin(btn1, new Insets(0,0,0,3));
    			*/
    			
    			btn2 = new Button();
    			btn2.setLayoutX(20.0);
    			btn2.setLayoutY(110.0);
    			btn2.setText(" Login ");
    			double a = 10;
    			btn2.setShape(new Rectangle(a, a));
    			btn2.setMinSize(9*a, 3*a);
    			btn2.setMaxSize(9*a, 3*a);
    			btn2.setStyle("-fx-font: 12 Myriad; -fx-base: #F47920;");
    			DropShadow shadow = new DropShadow();
    			//btn2.setOnAction(this);
    			VBox.setMargin(btn2, new Insets(100,0,0,10));
    			
    			//Adding the shadow when the mouse cursor is on
    			btn2.addEventHandler(MouseEvent.MOUSE_ENTERED, 
    			    new EventHandler<MouseEvent>() {
    			        @Override public void handle(MouseEvent e) {
    			            btn2.setEffect(shadow);
    			        }
    			});
    			//Removing the shadow when the mouse cursor is off
    			btn2.addEventHandler(MouseEvent.MOUSE_EXITED, 
    			    new EventHandler<MouseEvent>() {
    			        @Override public void handle(MouseEvent e) {
    			            btn2.setEffect(null);
    			        }
    			});
    			
    			btn3 = new Button();
    			btn3.setLayoutX(20.0);
    			btn3.setLayoutY(110.0);
    			btn3.setText(" Update ");
    			double ae = 10;
    			btn3.setShape(new Rectangle(ae, ae));
    			btn3.setMinSize(9*ae, 3*ae);
    			btn3.setMaxSize(9*ae, 3*ae);
    			btn3.setStyle("-fx-font: 12 Myriad; -fx-base: #F47920;");
    			DropShadow shadow1 = new DropShadow();
    			//btn2.setOnAction(this);
    			VBox.setMargin(btn3, new Insets(0,0,100,10));
    			
    			
    			btn3.addEventHandler(MouseEvent.MOUSE_ENTERED, 
    			    new EventHandler<MouseEvent>() {
    			        @Override public void handle(MouseEvent e) {
    			            btn3.setEffect(shadow1);
    			        }
    			});
    			
    			btn3.addEventHandler(MouseEvent.MOUSE_EXITED, 
    			    new EventHandler<MouseEvent>() {
    			        @Override public void handle(MouseEvent e) {
    			            btn3.setEffect(null);
    			        }
    			});
    			
    			btn4 = new Button();
    			btn4.setLayoutX(30.0);
    			btn4.setLayoutY(235.0);
    			btn4.setText(" START ");
    			double r = 30;
    			btn4.setShape(new Circle(r));
    			btn4.setMinSize(2*r, 2*r);
    			btn4.setMaxSize(2*r, 2*r);
    			btn4.setStyle("-fx-font: 12 Myriad; -fx-base: #F47920; -fx-font-weight: bold");
    			
    			DropShadow shadow2 = new DropShadow();
    			btn4.addEventHandler(MouseEvent.MOUSE_ENTERED, 
    			    new EventHandler<MouseEvent>() {
    			        @Override public void handle(MouseEvent e) {
    			            btn4.setEffect(shadow2);
    			        }
    			});
    			btn4.addEventHandler(MouseEvent.MOUSE_EXITED, 
    			    new EventHandler<MouseEvent>() {
    			        @Override public void handle(MouseEvent e) {
    			            btn4.setEffect(null);
    			        }
    			});
    			//btn4.setOnAction(this);
    			
    			VBox.setMargin(btn4, new Insets(0,0,0,25));
    			
    			leftMenu.getChildren().addAll( btn2, btn3, btn4);
    			
    			
    			
    			
    			
    			
    			//CenterPane
    			
    			centerPane = new AnchorPane();
    			centerPane.setStyle("-fx-border-color: #000000;");
    			centerPane.getChildren().addAll();
    			
    			
    			
    			
    			//Horizontal box i nedre kant
    			
    			HBox bottomMenu = new HBox();
    			bottomMenu.setStyle("-fx-background-color: #F47920;");
    			bottomMenu.setPadding(new Insets(10, 10, 10, 10));
    			/*
    			Image img1 = new Image(this.getClass().getResourceAsStream("/exit.png"));   			
    			btn8 = new Button("Exit", new ImageView(img1));
    			btn8.setLayoutX(20.0);
    			btn8.setLayoutY(550.0);
    			HBox.setMargin(btn8, new Insets(0,0,5,5));
    			btn8.setOnAction(e -> {
    				boolean result1 = ConfirmPane.display("Avsluta Programmet", "Vill du avsluta programmet?");
    				if (result1 == true) {
    					System.exit(0);
    				}
    			})*/
    			
    			lbl = new Label("Time remaining: 00:00:00");
    			lbl.setLayoutX(20.0);
    			lbl.setLayoutY(100.0);
    			double ad = 10;
    			lbl.setShape(new Rectangle(ad, ad));
    			lbl.setMinSize(14*ad, 3*ad);
    			lbl.setMaxSize(14*ad, 3*ad);
    			lbl.setStyle("-fx-border-color: #000000;" +  
    					   "-fx-background-color: #F47920;" + 
    					   "-fx-text-fill: #000000");
    			lbl.setAlignment(Pos.CENTER);
    			lbl.setFont(Font.font("Myriad", 12));
    			HBox.setMargin(lbl, new Insets(2,0,0,90));
    		    /*
    			lbl.setOnMouseEntered(new EventHandler<MouseEvent>() {
    		        @Override
    		        public void handle(MouseEvent e) {
    		          lbl.setVisible(true);
    		        }
    		      });
    		      lbl.setOnMouseExited(new EventHandler<MouseEvent>() {
    		        @Override
    		        public void handle(MouseEvent e) {
    		          lbl.setVisible(false);
    		        }
    		      });
				*/


    			
    			lbl2 = new Label(" Question 00/00 ");
    			lbl2.setLayoutX(20.0);
    			lbl2.setLayoutY(110.0);
    			double ac = 10;
    			lbl2.setShape(new Rectangle(ac, ac));
    			lbl2.setMinSize(10*ac, 3*ac);
    			lbl2.setMaxSize(10*ac, 3*ac);
    			lbl2.setStyle("-fx-border-color: #000000;" +  
    					   "-fx-background-color: #F47920;" + 
    					   "-fx-text-fill: #000000");
    			lbl2.setAlignment(Pos.CENTER);
    			lbl2.setFont(Font.font("Myriad", 12));
    			HBox.setMargin(lbl2, new Insets(2,0,0,3));
    			
    			btn5 = new Button();
    			btn5.setLayoutX(20.0);
    			btn5.setLayoutY(110.0);
    			btn5.setText(" Back ");
    			double aa = 10;
    			btn5.setShape(new Rectangle(aa, aa));
    			btn5.setMinSize(8*aa, 3*aa);
    			btn5.setMaxSize(8*aa, 3*aa);
    			btn5.setStyle("-fx-font: 12 Myriad; -fx-base: #F47920; -fx-font-weight: bold");
    			HBox.setMargin(btn5, new Insets(2,0,0,160));
    			DropShadow shadow3 = new DropShadow();
    			btn5.addEventHandler(MouseEvent.MOUSE_ENTERED, 
    			    new EventHandler<MouseEvent>() {
    			        @Override public void handle(MouseEvent e) {
    			            btn5.setEffect(shadow3);
    			        }
    			});
    			btn5.addEventHandler(MouseEvent.MOUSE_EXITED, 
    			    new EventHandler<MouseEvent>() {
    			        @Override public void handle(MouseEvent e) {
    			            btn5.setEffect(null);
    			        }
    			});
    			//btn5.setOnAction(this)
    			
    			btn6 = new Button();
    			btn6.setLayoutX(20.0);
    			btn6.setLayoutY(110.0);
    			btn6.setText(" Next ");
    			double ab = 10;
    			btn6.setShape(new Rectangle(ab, ab));
    			btn6.setMinSize(8*ab, 3*ab);
    			btn6.setMaxSize(8*ab, 3*ab);
    			btn6.setStyle("-fx-font: 12 Myriad; -fx-base: #F47920; -fx-font-weight: bold");
    			HBox.setMargin(btn6, new Insets(2,0,0,90));
    			DropShadow shadow4 = new DropShadow();
    			btn6.addEventHandler(MouseEvent.MOUSE_ENTERED, 
    			    new EventHandler<MouseEvent>() {
    			        @Override public void handle(MouseEvent e) {
    			            btn6.setEffect(shadow4);
    			        }
    			});
    			btn6.addEventHandler(MouseEvent.MOUSE_EXITED, 
    			    new EventHandler<MouseEvent>() {
    			        @Override public void handle(MouseEvent e) {
    			            btn6.setEffect(null);
    			        }
    			});
    			//btn6.setOnAction(this)
    			
    			
    			bottomMenu.getChildren().addAll(btn5, lbl, lbl2, btn6);
    			
    			//Scen och Pane inställningar
    			
    			BorderPane borderPane = new BorderPane();
    			borderPane.setTop(initMenu());
    			borderPane.setLeft(leftMenu);
    			borderPane.setCenter(centerPane);
    			borderPane.setBottom(bottomMenu);
    			//borderPane.setPrefSize(800,600);
    			
    			Scene scene = new Scene(borderPane, 800, 600, Color.rgb(56, 56, 56));
    			primaryStage.setScene(scene);
    			primaryStage.show();
    			
    	}
    	private void Platfom() {
    		Boolean answer = ConfirmPane.display("Exit program", "Do you want to exit the program?");
    		if(answer)
    				Platform.exit();
    	}
    	public MenuBar initMenu() {
    		BooleanBinding canAdd = isAdmin.and(isLoggedIn);
    		MenuBar menu = new MenuBar();
    		Menu mnuFile = new Menu("File");
    		MenuItem mnuLogIn = new MenuItem("Log in");
    		MenuItem mnuLogOut = new MenuItem("Log out");
    		menu.setStyle("-fx-background-color: #F47920;");
    		MenuItem mnuExit = new MenuItem("Exit");
    		mnuExit.setOnAction(exitAction -> {
    			System.exit(0);
    		});
    		
    		
    		MenuItem createQuest = new MenuItem("Create Test");
    		createQuest.setOnAction(createQ -> {
    			qMakerGUI = new QuizmakerGUI();
    			centerPane.getChildren().clear();
    			centerPane.getChildren().addAll(qMakerGUI.showPane());
    		});
    		
    		mnuFile.getItems().addAll(mnuLogIn, mnuLogOut, new SeparatorMenuItem(), mnuExit);
    		try{
    		primaryStage.setTitle("Guest");
    		}catch(Exception e){
    			System.out.println(e);
    		}
    		mnuLogIn.setOnAction(logInAction -> {
    			if (!isLoggedIn.get()) {
    				showLogin();
    			}
    		});
    		mnuLogIn.disableProperty().bind(isLoggedIn);
    		
    		mnuLogOut.setOnAction(logOutAction -> {
    			primaryStage.setTitle("Not logged in");
    			centerPane.getChildren().clear();
    			isLoggedIn.set(false);
    			isAdmin.set(false);
    		});
    		mnuLogOut.disableProperty().bind(isLoggedIn.not());
    		
    		Menu mnuAdmin = new Menu("Admin");
    		MenuItem adminUsers = new MenuItem("Administer Users");
    		adminUsers.setOnAction(action -> {
    			AdminUser userAdmin = new AdminUser();
    			centerPane.getChildren().clear();
    			centerPane.getChildren().addAll(userAdmin.showPane(root));
    		});
    		mnuAdmin.disableProperty().bind(canAdd.not());
    		mnuAdmin.getItems().addAll(adminUsers, createQuest);
    		
    		Menu mnuStudent = new Menu("Student");
    		mnuStudent.disableProperty().bind(isLoggedIn.not());
    		MenuItem mnuChangeUser = new MenuItem("Change user info");
    		mnuChangeUser.setOnAction(change -> {
    			centerPane.getChildren().clear();
    			userInfo = new ChangeUserInfo(user);			
    			centerPane.getChildren().addAll(userInfo.showPane());
    		});
    		mnuStudent.getItems().add(mnuChangeUser);
    		
    		menu.getMenus().addAll(mnuFile, mnuAdmin, mnuStudent);
    		
    		
    		
    		return menu;
    	}
    	
    	public void showLogin() {
    		Stage logInStage = new Stage();
    		StackPane logInPane = new StackPane();
    		Scene logInScene = new Scene(logInPane, 400, 200);
    		logInStage.setScene(logInScene);
    		logInStage.initStyle(StageStyle.UNDECORATED);
    		logInStage.show();
    		
    		
    		Text loginTitle = new Text("Log in");
    		loginTitle.setFont(Font.font(50));
    		HBox title = new HBox();
    		title.getChildren().add(loginTitle);
    		title.setAlignment(Pos.CENTER);
    		TextField txtUserName = new TextField();
    		loginTitle.requestFocus();
    		txtUserName.setPromptText("Username");
    		txtUserName.setMaxWidth(200);
    		PasswordField txtPassword = new PasswordField();
    		txtPassword.setMaxWidth(200);
    		txtPassword.setPromptText("Password");
    		Text wrongLogin = new Text("Wrong username/password");
    		wrongLogin.setFont(Font.font(30));
    		wrongLogin.setVisible(false);
    		
    		VBox loginColumn = new VBox();
    		loginColumn.setAlignment(Pos.CENTER);
    		loginColumn.getChildren().addAll(title,txtUserName, txtPassword, wrongLogin);
    		logInPane.getChildren().addAll(loginColumn);
    		
    		
    		txtPassword.setOnAction(e -> {
    			user = logIn.login(txtUserName.getText(), txtPassword.getText());
    			if (user != null) {
    				if (logIn.getAccountType().equals("Admin")) {
    					primaryStage.setTitle("Logged in as " + logIn.getName() + " - Admin");
    					isAdmin.set(true);
    				} else {
    					primaryStage.setTitle("Logged in as " + logIn.getName() + " - Student");
    				}
    				wrongLogin.setVisible(false);
    				logInStage.close();
    				isLoggedIn.set(true);
    			} else {
    				wrongLogin.setVisible(true);
    			}
    		});
    		
    	}
    	
    	public void addUser() {
    		ObservableList<String> accountType = FXCollections.observableArrayList();
    		accountType.add("Admin");
    		accountType.add("Student");
    		
    		Stage addUserStage = new Stage();
    		StackPane userPane = new StackPane();
    		Scene addUserScene = new Scene(userPane, 400, 250);
    		addUserStage.setScene(addUserScene);
    		addUserStage.initStyle(StageStyle.UNDECORATED);
    		addUserStage.toFront();
    		centerPane.getChildren().add(userPane);
    		
    		Text titleText = new Text("Add user account");
    		titleText.setFont(Font.font(30));
    		HBox titleBox = new HBox();
    		titleBox.setAlignment(Pos.CENTER);
    		titleBox.getChildren().add(titleText);
    		
    		TextField txtUserName = new TextField();
    		txtUserName.setPromptText("Username");
    		txtUserName.setMaxWidth(200);
    		PasswordField txtPassword = new PasswordField();
    		txtPassword.setMaxWidth(200);
    		txtPassword.setPromptText("Password");
    		PasswordField confirmPassword = new PasswordField();
    		confirmPassword.setPromptText("Confirm password");
    		confirmPassword.setMaxWidth(200);
    		ComboBox cmbAccountType = new ComboBox();
    		cmbAccountType.setItems(accountType);
    		cmbAccountType.setPromptText("Account type");
    		
    		TextField txtEmail = new TextField();
    		txtEmail.setMaxWidth(200);
    		txtEmail.setPromptText("E-mail");
    		
    		VBox enterBox = new VBox();
    		enterBox.getChildren().addAll(txtUserName, txtPassword, confirmPassword, txtEmail, cmbAccountType);
    		
    		Button okButton = new Button("OK");
    		Button cancelButton = new Button("Cancel");
    		HBox buttons = new HBox();
    		okButton.requestFocus();
    		buttons.getChildren().addAll(okButton, cancelButton);
    		
    		cancelButton.setOnAction(cancel -> {
    			addUserStage.close();
    		});
    		
    		okButton.setOnAction(ok -> {
    			Alert error = new Alert(AlertType.ERROR);
    			if (!(confirmPassword.getText().equals(txtPassword.getText()))) {
    				error.setTitle("Password mismatch");
    				error.setHeaderText("The passwords must be the same");
    				error.showAndWait();
    			} 
    			else if (cmbAccountType.getValue()==null) {
    				error.setTitle("No accounttype chosen");
    				error.setHeaderText("You must choose an account type");
    				error.showAndWait();
    			}
    			else if(!(txtEmail.getText().contains("@"))) {
    				error.setTitle("Incorrect e-mail");
    				error.setHeaderText("You have not entered a valid e-mail");
    				error.showAndWait();
    			}
    			else {
    				if (addUser.addUser(txtUserName.getText(), txtPassword.getText(), cmbAccountType.getValue().toString(), txtEmail.getText())) {
    					error.setTitle("User added");
    					error.setHeaderText("User " + txtUserName.getText() + " added");
    					error.showAndWait();
    				} else {
    					error.setTitle("User exists");
    					error.setHeaderText("User " + txtUserName.getText() + " already exists");
    					error.showAndWait();
    				}
    				addUserStage.close();
    			}
    		});
    		
    		VBox addUserColumn = new VBox();
    		addUserColumn.getChildren().addAll(titleBox, enterBox, buttons);
    		userPane.getChildren().add(addUserColumn);
    	}
}