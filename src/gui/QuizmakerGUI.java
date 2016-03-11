package gui;


import java.awt.event.MouseEvent;
import java.beans.EventHandler;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import entity.Answers;
import entity.Question;
import entity.QuestionType;
import entity.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class QuizmakerGUI {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	
	private ObservableList<Integer> pointObList ;
	private ObservableList<String> questTypeObList ;
	private ObservableList<String> testList = FXCollections.observableArrayList();
	private ObservableList<String> questList = FXCollections.observableArrayList();
 	private Test test;
 	//private Test tempTest;
	private QuestionType questType = new QuestionType();
	private Question quest;
	private Answers answer;
	private int points;
	private int correctAnswerIndex;
	private String questionName;	
	private ListView<String> listViewQuest = new ListView<String>();
	private TextArea questFeedback = new TextArea();
	private TextArea writeQuestionTxt = new TextArea();
	private TextArea writeAnswerTxt = new TextArea();
	private TextField answer1 = new TextField();
	private TextField answer2 = new TextField();
	private TextField answer3 = new TextField();
	private TextField answer4 = new TextField();
	private TextField startTestTxt = new TextField();
	private TextField stopTestTxt = new TextField();
	private TextField setTitleTxt = new TextField();
	private TextField setTitleTxtQ = new TextField();
	private RadioButton radBut1 = new RadioButton();
	private RadioButton radBut2 = new RadioButton();
	private RadioButton radBut3 = new RadioButton();
	private RadioButton radBut4 = new RadioButton();
	private CheckBox feedbackCheck = new CheckBox();
	private ToggleGroup butGroup = new ToggleGroup();
	private Button newQuestBut = new Button("New Question");	
	private Button saveQuestBut = new Button("Save Question");
	private Button newTestBut = new Button("New Test");	
	private Button saveTestBut = new Button("Save Test");
	private Button editTestBut = new Button("Edit Test");
	private Button okBut = new Button("OK");
	private ComboBox<String> questCmbBox = new ComboBox<String>();
	private ComboBox<String> testCmbBox = new ComboBox<String>();
	private ComboBox<String> setTypeCmbBox = new ComboBox<String>();
	private ComboBox<Integer> pointCmbBox = new ComboBox<Integer>();
	private VBox fieldsVBox = new VBox();
	private VBox radButsVBox = new VBox();	
	private VBox butsAndCmbQuest = new VBox();
	private VBox butsAndCmbTest = new VBox();
	private VBox componentsVBox = new VBox();	
	private VBox startLblVBox = new VBox();
	private VBox stopLblVBox = new VBox();
	private VBox setTitleVBox = new VBox();
	private HBox popUpHBox = new HBox();
	private HBox newQuestHBox = new HBox();
	private HBox fieldsAndRadsHBox = new HBox();
	private HBox butsAndtxtAreaHBox = new HBox();	
	private Label startLbl = new Label("Test start");
	private Label stopLbl = new Label("Test stop");
	private Scene popUpScene;
	private Scene popUpScene2;
	private Stage popUpStage = new Stage();
	private Stage popUpStage2 = new Stage();
	private BorderPane popUpPaneNewT = new BorderPane();
	private BorderPane popUpPaneNewQ = new BorderPane();
	
	public QuizmakerGUI(){	}
	
	public QuizmakerGUI(Test test) {
		this.test = test;		
	}
	
	public BorderPane showPane(){
		
		emf = Persistence.createEntityManagerFactory("Testverktyg");
		em = emf.createEntityManager();
		
		List <Test> tempLista = (List <Test>) em.createQuery("select t from Test t").getResultList();
		testCmbBox.setPromptText("Select test");
		testCmbBox.setItems(testList);		
		for( int i = 0; i < tempLista.size(); i++){			
			testList.add(tempLista.get(i).getTestName());			
		}
		
		//Skapar lista och combobox till po�ngs�ttare
		pointObList = FXCollections.observableArrayList(1, 2, 3, 4, 5);
		pointCmbBox = new ComboBox<Integer>(pointObList);
		//L�gger in tv� typer av olika fr�gor
		questTypeObList = FXCollections.observableArrayList("Radiobuttons", "Fritext");
		setTypeCmbBox = new ComboBox<String>(questTypeObList);
								
	    //Graphiccomponents to the questionmaker	
		BorderPane root = new BorderPane();
		questFeedback.setVisible(false);
		
		fieldsVBox.getChildren().addAll(answer1, answer2, answer3, answer4, questFeedback);
		answer1.setPromptText("Answer one");
		answer2.setPromptText("Answer two");
		answer3.setPromptText("Answer three");
		answer4.setPromptText("Answer four");
		writeQuestionTxt.setPromptText("Write question here");
		questFeedback.setPromptText("Write student feedback here");
		writeAnswerTxt.setPromptText("Write answer here");
		setTitleTxt.setPromptText("Test Name");
		setTitleTxtQ.setPromptText("Question Name");
		fieldsVBox.setPrefWidth(480.0);
		//Organizing my radiobuttons
		radBut1.setToggleGroup(butGroup);
		radBut2.setToggleGroup(butGroup);
		radBut3.setToggleGroup(butGroup);
		radBut4.setToggleGroup(butGroup);		
		radButsVBox.getChildren().addAll(radBut1, radBut2, radBut3, radBut4, feedbackCheck);		
		// Button VBox right of textarea		
		butsAndCmbTest.getChildren().addAll(newTestBut, editTestBut, testCmbBox);
		// Button VBox right of textarea
		butsAndCmbQuest.getChildren().addAll(newQuestBut, saveQuestBut);
		//HBox right of textarea where u type in a question
		butsAndtxtAreaHBox.getChildren().addAll(writeQuestionTxt, butsAndCmbQuest, butsAndCmbTest);
		butsAndtxtAreaHBox.setSpacing(20);
		//RadioButtons and textfields where u write the answers HBox
		fieldsAndRadsHBox.getChildren().addAll(radButsVBox, fieldsVBox, listViewQuest);		
		radButsVBox.setSpacing(10);
		//Add my HBoxes to a big VBox
		componentsVBox.getChildren().addAll(butsAndtxtAreaHBox, fieldsAndRadsHBox);
		componentsVBox.setSpacing(80);
		//Left side VBox Popup NewTest
		startLblVBox.getChildren().addAll(setTitleTxt, startLbl, startTestTxt);
		//Left side VBox popup NewQuest
		setTitleVBox.getChildren().addAll(setTitleTxtQ);
		//Right side VBox NewTest
		stopTestTxt.setPromptText("YY/MM/DD-00:00");
		startTestTxt.setPromptText("YY/MM/DD-00:00");
		stopLblVBox.getChildren().addAll(stopLbl, stopTestTxt, saveTestBut);
		//HBox for VBoxes NewTest popup
		popUpHBox.getChildren().addAll(startLblVBox, stopLblVBox);
		//HBox for VBox and cmbBox in newquest popup
		setTypeCmbBox.setPromptText("Question type");
		pointCmbBox.setPromptText("Point value");
		newQuestHBox.getChildren().addAll(setTitleVBox, setTypeCmbBox, pointCmbBox, okBut);
		//Two borderPanes for popups
		popUpPaneNewQ.setTop(newQuestHBox);
		popUpPaneNewT.setTop(popUpHBox);
		//Two scenes for popups
		popUpScene2 = new Scene(popUpPaneNewQ, 400, 50);
		popUpScene = new Scene(popUpPaneNewT, 300, 200);
		//Set the scenes for both popups
		popUpStage.setScene(popUpScene);
		popUpStage2.setScene(popUpScene2);
		
		
		
		root.setMargin(componentsVBox, new Insets(12,12,12,12));
		root.setCenter(componentsVBox);
		Scene scene = new Scene(root, 1000, 600);
		
		//Changes the interface of the question input
		setTypeCmbBox.setOnAction(event -> {
			if(setTypeCmbBox.getValue().equals("Essay")){
				fieldsAndRadsHBox.getChildren().clear();
				fieldsAndRadsHBox.getChildren().addAll(writeAnswerTxt, listViewQuest);
			}
			if(setTypeCmbBox.getValue().equals("4-Choice")){
				fieldsAndRadsHBox.getChildren().clear();
				fieldsAndRadsHBox.getChildren().addAll(radButsVBox, fieldsVBox, listViewQuest);
			}
		});
		
		testCmbBox.setOnAction(event -> {
			
			listViewQuest.getItems().clear();
			//Gets the selected string from combobox and insert into a test variable
			test = tempLista.get(testCmbBox.getSelectionModel().getSelectedIndex());
			//Query for getting the question according by the testname
			Test tempTest = (Test) em.createQuery("select t from Test t where t.testName ='" + testCmbBox.getValue()+"'").getSingleResult();
			
			for (Question q : tempTest.getQuestions()){
				questList.add(q.getQuestionTitle());
			}			
			
			listViewQuest.setItems(questList);
			
		});
		
		radButsVBox.setOnMouseClicked(event -> {
			if(radBut1.isSelected()){
				correctAnswerIndex = 0;
			}
			if(radBut2.isSelected()){
				correctAnswerIndex = 1;
			}
			if(radBut3.isSelected()){
				correctAnswerIndex = 2;
			}
			if(radBut4.isSelected()){
				correctAnswerIndex = 3;
			}			
		});
		
		//Save the question to a test in the database
		saveQuestBut.setOnAction(event -> {
			em.getTransaction().begin();
			points = pointCmbBox.getValue();
			quest.setPoints(points);
			quest.setDirectFeedback(feedbackCheck.isSelected());
			questionName = setTitleTxtQ.getText();
			quest.setQuestionTitle(questionName);
			quest.setQuestionText(writeQuestionTxt.getText());
			quest.setQuestionType(questType);
			questType.addQuestion(quest);			
			test.addQuestion(quest);			
			answer = new Answers(quest);											
			answer.setCorrectAnswer(correctAnswerIndex);
			answer.addAnswer(answer1.getText());
			answer.addAnswer(answer2.getText());
			answer.addAnswer(answer3.getText());
			answer.addAnswer(answer4.getText());
			em.persist(answer);
			em.persist(quest);
			em.getTransaction().commit();
		});
		
	/*listViewQuest.setOnMouseClicked(event -> {
			
			listViewQuest.getSelectionModel().getSelectedItems();
			
			answer1.setText("");
			answer2.setText("");
			answer3.setText("");
			answer4.setText("");
			
		});*/
		
		
		setTypeCmbBox.setOnAction(event -> {
			em.getTransaction().begin();
			//TODO
			if(setTypeCmbBox.getValue().equals("Radiobuttons")){				
				questType.setQuestionType("Radiobuttons");				
			}else if (setTypeCmbBox.getValue().equals("Fritext")){
				questType.setQuestionType("Fritext");				
			}
			
			em.persist(questType);
			em.getTransaction().commit();
		});
		
		//Save the test to the database
		saveTestBut.setOnAction(event -> {
			test = new Test();
			em.getTransaction().begin();
			test.setTestName(setTitleTxt.getText());
			setTitleTxt.setText("");
			test.setTestStart(startTestTxt.getText());
			startTestTxt.setText("");
			test.setTestEnd(stopTestTxt.getText());
			stopTestTxt.setText("");
			popUpStage.close();			
			em.persist(test);			
			em.getTransaction().commit();
		});
		
		newTestBut.setOnAction(event -> {			
			popUpStage.showAndWait();			
		});
		
		okBut.setOnAction(event -> {
			popUpStage2.close();
		});
		
		newQuestBut.setOnAction(event -> {
			quest = new Question(points, true, null, null, test, null);			
			popUpStage2.showAndWait();	
		});
		
		feedbackCheck.setOnAction(event -> {			
			if(feedbackCheck.isSelected()){
				questFeedback.setVisible(true);
				
			}
			else{
				questFeedback.setVisible(false);}
				
		});
		
		
		return root;		
	}		
}