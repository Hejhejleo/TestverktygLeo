package gui;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entity.Answers;
import entity.Question;
import entity.StudentAnswer;
import entity.Test;
import entity.User;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DoTest extends Application {
	Stage primaryStage;
	boolean fade;
	String testName;
	int questionNumber = 0;
	Answers answer;
	Boolean goneThrough = false;
	List<Scene> canvases = new ArrayList<>();
	List<Question> questions = new ArrayList<>();
	List<String> testOverviewQ = new ArrayList<>();
	List<String> testOverviewA = new ArrayList<>();
	User user;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Testverktyg");
		EntityManager em = emf.createEntityManager();
		Query test = em.createNamedQuery("get tests");
		testName = "Test i JPA";
		test.setParameter("name", testName);
		System.out.println("---------------:::Hello:::-----------------");
		// get questions from DB
		System.out.println("---------------:::1:::-----------------");
		List<Test> testsList = new ArrayList<>(test.getResultList());
		System.out.println("---------------:::2:::-----------------");
		List<Answers> answersList = new ArrayList<>(em.createQuery("select a from Answers a").getResultList());

		System.out.println("---------------:::3:::-----------------");
		Test currentTest = new Test();
		currentTest = testsList.get(0);
		System.out.println("---------------:::4:::-----------------");

		for (int i = 0; i < currentTest.getQuestions().size(); i++) {
			questions.add(currentTest.getQuestions().get(i));
			System.out.println(questions.get(i).getQuestionText());
		}
		System.out.println("---------------:::5:::-----------------");

		
		//Make array-size to fit test
		for(int i=0;i<questions.size();i++){
			testOverviewA.add(null);
			testOverviewQ.add(null);
			System.out.println(testOverviewA.get(i));
		}
		// RootGrid
		
		

		// Make scenes
		System.out.println("---------------:::6:::-----------------");
		for (int i = 0; i <= (questions.size() - 1); i++) {
			for (int j = 0; j <= (answersList.size() - 1); j++) {
				if (answersList.get(j).getQuestion() == questions.get(i)) {
					answer = answersList.get(j);
				}

			}
			TextArea questionTextArea = new TextArea();
			ToggleGroup group = new ToggleGroup();
			GridPane root = new GridPane();
			System.out.println("---------------:::7:::-----------------");

			Scene scene = new Scene(root, 800, 350);

			// TopLeft Corner
			VBox topLeft = new VBox();
			Text titleText = new Text(questions.get(i).getQuestionTitle());
			Text questionText = new Text(questions.get(i).getQuestionText());

			// TitleStyle
			titleText.setFont(Font.font(20));
			titleText.setFill(Color.BLACK);

			// QuestionStyle
			questionText.setFont(Font.font(12));

			topLeft.getChildren().addAll(titleText, questionText);
			GridPane.setConstraints(topLeft, 0, 0, 2, 1);
			root.getChildren().addAll(topLeft);

			// TopRight Corner
			BorderPane top = new BorderPane();
			HBox topRight = new HBox(10);
			topRight.spacingProperty().set(10);
			
			top.setTop(topRight);
			topRight.setAlignment(Pos.TOP_RIGHT);
			StackPane spoilerHolder = new StackPane();
			Rectangle spoilerTimer = new Rectangle(40, 15, Color.BLACK);
			Rectangle spoilerDetection = new Rectangle(40, 15, Color.TRANSPARENT);
			Text timerText = new Text("time");
			timerText.setFill(Color.WHITE);
			timerText.setStroke(Color.WHITE);
			Label timer = new Label("18:45");
			Label questionNr = new Label((i + 1) + "/" + (questions.size()));
			spoilerHolder.getChildren().addAll(timer, spoilerTimer, timerText);
			spoilerHolder.getChildren().add(spoilerDetection);
			topRight.getChildren().addAll(spoilerHolder, questionNr);
			GridPane.setConstraints(top, 2, 0);
			root.getChildren().addAll(top);

			// BottomRight
			GridPane bottomRight = new GridPane();
			HBox bottomRightButtonHolder = new HBox();
			bottomRightButtonHolder.setAlignment(Pos.BOTTOM_RIGHT);
			Circle buttonBack = new Circle(15);
			Circle buttonNext = new Circle(15);
			Button buttonDone = new Button("Lämna in");
			buttonDone.setVisible(false);
			GridPane.setConstraints(bottomRightButtonHolder, 1, 1);
			GridPane.setConstraints(buttonDone, 0, 1);
			bottomRight.getChildren().addAll(buttonDone, bottomRightButtonHolder);
			bottomRightButtonHolder.getChildren().addAll(buttonBack, buttonNext);

			GridPane.setConstraints(bottomRight, 2, 1);
			root.getChildren().addAll(bottomRight);

			// BottomLeft / BottomCenter
			BorderPane questionHolder = new BorderPane();
			BorderPane answerHolder = new BorderPane();

			// v---------------------Mall för text fråga
			if (questions.get(i).getquestionType().getQuestionType().equals("Fritext")) {
				questionHolder.setCenter(questionTextArea);
				GridPane.setConstraints(questionHolder, 0, 1, 2, 1);
				root.getChildren().addAll(questionHolder);
			}
			// ^---------------------Mall för text fråga

			// v---------------------Mall för radiobutton fråga
			if (questions.get(i).getquestionType().getQuestionType().equals("Radiobuttons")) {
				VBox toggleButtons = new VBox();
				RadioButton button1 = new RadioButton(answer.getAnswerList().get(0));
				button1.setToggleGroup(group);
				RadioButton button2 = new RadioButton(answer.getAnswerList().get(1));
				button2.setToggleGroup(group);
				RadioButton button3 = new RadioButton(answer.getAnswerList().get(2));
				button3.setToggleGroup(group);
				RadioButton button4 = new RadioButton(answer.getAnswerList().get(3));
				button4.setToggleGroup(group);

				toggleButtons.getChildren().addAll(button1, button2, button3, button4);
				answerHolder.setCenter(toggleButtons);
				GridPane.setConstraints(answerHolder, 0, 1, 2, 1);
				root.getChildren().addAll(answerHolder);
	
			}

			// ^---------------------Mall för radiobutton fråg

			// rootGrid
			ColumnConstraints column1 = new ColumnConstraints();
			column1.setPercentWidth(50);
			root.getColumnConstraints().addAll(column1, column1, column1);
			RowConstraints row1 = new RowConstraints();
			row1.setPercentHeight(50);
			root.getRowConstraints().addAll(row1, row1);

			// BottomRightGrid
			RowConstraints row50 = new RowConstraints();
			row50.setPercentHeight(50);
			bottomRight.getColumnConstraints().addAll(column1, column1);
			bottomRight.getRowConstraints().addAll(row50, row50);

			root.setGridLinesVisible(true);
			bottomRight.setGridLinesVisible(true);
			canvases.add(scene);
			

			buttonBack.setOnMouseClicked(event -> {
				testOverviewQ.set(questionNumber, questions.get(questionNumber).getQuestionText());
				
				if(questions.get(questionNumber).getquestionType().getQuestionType().equals("Fritext")){
				testOverviewA.set(questionNumber,questionTextArea.getText());
				}
				if(questions.get(questionNumber).getquestionType().getQuestionType().equals("Radiobuttons")){
					try{
					testOverviewA.set(questionNumber,((RadioButton)(group.getSelectedToggle())).getText());
					}catch(Exception e){
						System.out.println(e);
					}
				}
				changeQuestion("-");
				System.out.println(questionNumber);
			});

			buttonNext.setOnMouseClicked(event -> {
				testOverviewQ.set(questionNumber, questions.get(questionNumber).getQuestionText());
				
				if(questions.get(questionNumber).getquestionType().getQuestionType().equals("Fritext")){
					testOverviewA.set(questionNumber,questionTextArea.getText());
					}
					if(questions.get(questionNumber).getquestionType().getQuestionType().equals("Radiobuttons")){
						try{
						testOverviewA.set(questionNumber,((RadioButton)(group.getSelectedToggle())).getText());
						}
					catch(Exception e){
						System.out.println(e);
					}
					}

				changeQuestion("+");
				System.out.println(questionNumber);
			});

			spoilerDetection.setOnMouseEntered(event -> {
				spoilerTimer.setFill(Color.TRANSPARENT);
				timerText.setFill(Color.TRANSPARENT);
				timerText.setStroke(Color.TRANSPARENT);

			});
			spoilerDetection.setOnMouseExited(event -> {
				spoilerTimer.setFill(Color.BLACK);
				timerText.setFill(Color.WHITE);
				timerText.setStroke(Color.WHITE);

			});


		}
		
		//LastCanvas /START
		
		BorderPane rootLC = new BorderPane();
		Scene lastCanvas = new Scene(rootLC,800,350);
		Button endTest = new Button("End Test");
		rootLC.setCenter(endTest);
		canvases.add(lastCanvas);
		
		//LastCanvas /SLUT
		
		//Overview för provets svar /START
		BorderPane rootOV = new BorderPane();
		Scene sceneOV = new Scene(rootOV, 800, 350);

		Text overviewTitle = new Text("Overview");
		overviewTitle.setFont(Font.font(18));
		TextArea overview = new TextArea();
		overview.setEditable(false);

		HBox rightContainer = new HBox(12);
		BorderPane leftContainer = new BorderPane();
		rightContainer.setPadding(new Insets(0,3000,20,30));
		rightContainer.setAlignment(Pos.BOTTOM_LEFT);
		
		Button yes = new Button("Skicka in");
		Button cancel = new Button("Cancel");
		rightContainer.getChildren().addAll(yes, cancel);
		//leftContainer.getChildren().addAll(overviewTitle,overview);
		leftContainer.setTop(overviewTitle);
		leftContainer.setCenter(overview);

		rootOV.setLeft(leftContainer);
		rootOV.setRight(rightContainer);

		primaryStage.setScene(canvases.get(0));
		primaryStage.show();
		
		cancel.setOnAction(event ->{
			primaryStage.setScene(canvases.get(0));
			questionNumber=0;
			overview.setText("");
			
		});
		endTest.setOnAction(event ->{
		overview.appendText(getAnswersForOverview());
			primaryStage.setScene(sceneOV);
		});
		
		yes.setOnAction(event ->{
			for(int i=0;i<questions.size();i++){
			sendToDatabase(user,questions.get(i),testOverviewA.get(i).toString(),em);
			}
		});
		
		//Overview för provets svar /SLUT

		primaryStage.setScene(canvases.get(questionNumber));
		primaryStage.show();

	}
	

	public static void main(String[] args) {
		launch(args);
	}
	

	public void showDoneButton() {
		for (int i = 0; i <= canvases.size() - 1; i++) {
       
		}
	}
	
	public String getAnswersForOverview(){
		String overview="";
		for(int i=0;i<testOverviewA.size();i++){
			overview+=testOverviewQ.get(i)+": "+testOverviewA.get(i)+"\n";
			
		}
		return overview;
		
		
	}

	public void changeQuestion(String cond) {
		if (questionNumber < 1 & cond == "-") {
			this.questionNumber = canvases.size() - 1;
			primaryStage.setScene(canvases.get(questionNumber));
		} else if (questionNumber >= (canvases.size() - 1) & cond == "+") {
			this.questionNumber = 0;
			primaryStage.setScene(canvases.get(questionNumber));
			goneThrough = true;
			

		} else {
			switch (cond) {
			case "+":
				questionNumber++;
				primaryStage.setScene(canvases.get(questionNumber));
				break;
			case "-":
				questionNumber--;
				primaryStage.setScene(canvases.get(questionNumber));
				break;
			}
		}
	}
	
public void sendToDatabase(User user, Question question, String answer, EntityManager em){
		StudentAnswer studentAnswer = new StudentAnswer();
		studentAnswer.setUser(user);
		studentAnswer.setQuestion(question);
		studentAnswer.setAnswer(answer);
		em.getTransaction().begin();
		em.persist(studentAnswer);
		em.getTransaction().commit();
	}

	public void drawCanvas() {

	}

}
