package main.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.core.client.JsArray;
import java.util.ArrayList;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import java.util.List;


public class Mainpage implements EntryPoint, ClickHandler
{
	
	private static class UserInfo
	{
		private final String department;
		private final String division;
		private final String first_name;
		private final String last_name;	
		
		public UserInfo(String dep, String div, String fn, String ln)
		{
			department = dep;
			division = div;
			first_name = fn;
			last_name = ln;
		} 
	}
	
	ArrayList<UserInfo> users = new ArrayList<UserInfo>();
	JsArray<User> jsonData;
	
	HorizontalPanel loginPanel = new HorizontalPanel();
	VerticalPanel loginInputPanel = new VerticalPanel();
	HorizontalPanel loginRow1 = new HorizontalPanel();
	HorizontalPanel loginRow2 = new HorizontalPanel();
	Label loginUsernameLabel = new Label("Username");
	Label loginPasswordLabel = new Label("Password");
	PasswordTextBox loginPasswordBox = new PasswordTextBox();
	TextBox loginUsernameBox = new TextBox();
	Button loginSubmitButton = new Button("Submit");
	
	TextArea trialArea = new TextArea();
	
	
	//VerticalPanel mainPanel = new VerticalPanel();
	TabPanel tabPanel = new TabPanel();  
	
    //Frame displayUsers = new Frame("http://localhost:3000/users/index");
        
    HorizontalPanel adminPanel = new HorizontalPanel();
    VerticalPanel adminButtonPanel = new VerticalPanel();
    Button newUserButton = new Button("New User");
    Button viewUsersButton = new Button("View Users");
    HTML displayUsers = new HTML();
    String displayUsersURL = "http://localhost:3000/users/index";
        
    VerticalPanel newUserPanel = new VerticalPanel();
    Button newUserSubmitButton = new Button("Submit");
        
	HorizontalPanel row1 = new HorizontalPanel();
	HorizontalPanel row2 = new HorizontalPanel();
	HorizontalPanel row3 = new HorizontalPanel();
	HorizontalPanel row4 = new HorizontalPanel();
	HorizontalPanel row5 = new HorizontalPanel();
	HorizontalPanel row6 = new HorizontalPanel();
		
	TextBox firstNameBox = new TextBox();
	TextBox lastNameBox = new TextBox();
	TextBox userNameBox = new TextBox();
	PasswordTextBox passwordBox = new PasswordTextBox();
	TextBox divisionBox = new TextBox();
	TextBox departmentBox = new TextBox();
	
	Label fnLabel = new Label("First Name: ");
	Label lnLabel = new Label("Last Name: ");
	Label unLabel = new Label("User Name: ");
	Label pwLabel = new Label("Password: ");
	Label divLabel = new Label("Division: ");
	Label depLabel = new Label("Department: ");
	
	HorizontalPanel suggPanel = new HorizontalPanel();
    VerticalPanel suggButtonPanel = new VerticalPanel();
    Button makeSuggButton = new Button("Make A Suggestion");
    
    HorizontalPanel surveyPanel = new HorizontalPanel();
    VerticalPanel surveyButtonPanel = new VerticalPanel();
    Button createSurveyButton = new Button("Create A Survey");
    Button takeSurveyButton = new Button("Take A Survey");
    VerticalPanel surveySuggPanel = new VerticalPanel();
    Label surveySuggLabel = new Label("Sample Suggestion");
    VerticalPanel suggContainer = new VerticalPanel();
    HorizontalPanel surveySuggButtons = new HorizontalPanel();
    Button chooseSuggNextButton = new Button("Next");
    Button chooseSuggBackButton = new Button("Back");
    Button chooseSuggSelectButton = new Button("Select For Survey");
    Hidden surveySuggIDHidden = new Hidden();
    VerticalPanel newSurveyPanel = new VerticalPanel();
    Label surveyTitleLabel = new Label("Title");
    TextBox surveyTitleBox = new TextBox();
    HorizontalPanel surveyTitleRow = new HorizontalPanel();
    Button newSurveyNextButton1 = new Button("Next");
    Button newSurveyNextButton2 = new Button("Next");
    Button submitSurveyButton = new Button("Finish Survey");
    Button addQuestionButton = new Button("Add Another Question");
    Button addOptionButton = new Button("Submit Option");
    VerticalPanel surveyQuestionPanel = new VerticalPanel();
    Label surveyQuestionLabel = new Label("Enter Your Question");
    TextArea surveyQuestionArea = new TextArea();
    VerticalPanel surveyOptionsPanel = new VerticalPanel();
    Label surveyOptionsLabel = new Label("Create The Option");
    TextBox surveyOptionsBox = new TextBox();
    HorizontalPanel surveyButtonRow = new HorizontalPanel();
    //VerticalPanel pendingSurvey = new VerticalPanel();
    //HorizontalPanel pendingSurveyChoices = new HorizontalPanel();
    HTML pendingSurvey = new HTML();
    String surveyTitle = "";
    String surveySuggestion = "";
	String[][] surveyArray = new String[10][10];
	int surveyArrayIndex1 = 0;
	int surveyArrayIndex2 = 0;
	String pendingSurveyHTML = "";
	SimplePager usersCellTablePager = new SimplePager();
	CellTable<UserInfo> table = new CellTable<UserInfo>(users.size());
	//TextBox size = new TextBox();  Used to check ArrayList and JsArray size
	String surveySugg = "";
	String surveySuggID = "";
	int chooseSuggArrayIndex = 0;
	JsArray<ChooseSugg> chooseSuggArray;
	JsArray<ChooseSurvey> chooseSurveyArray;
	JsArray<DisplaySurvey> displaySurveyArray;
	
	VerticalPanel chooseSurveyPanel = new VerticalPanel();
	ListBox chooseSurveyListBox = new ListBox();
	Button chooseSurveySubmitButton = new Button("Submit");
	
	VerticalPanel displaySurveyPanel = new VerticalPanel();
	Label displaySurveySuggLabel = new Label("");
	Label chosenOptionLabel = new Label("");
	Label displaySurveyQuestionLabel = new Label("");
	HorizontalPanel displaySurveyOptionPanel = new HorizontalPanel();
	Button stronglyAgreeButton = new Button("Strongly Agree");
	Button agreeButton = new Button("Agree");
	Button neutralButton = new Button("Neutral");
	Button disagreeButton = new Button("Disagree");
	Button stronglyDisagreeButton = new Button("Strongly Disagree");
	Button displaySurveyNextButton = new Button("Next");
	Button displaySurveySubmitButton = new Button("Submit Survey");
	String initialChosenOption = "";
	String takenSurveyID = "";
	int displaySurveyArrayIndex = 0;
	String[][] surveyResultArray = new String[20][20];
	int surveyResultArrayIndex = 0;
	int prevSurvIndex = 0;
	//final Button toGetOptionButton = new Button();
	
	
	
	
		        
	public void onModuleLoad()
	{
              
        loginRow1.add(loginUsernameLabel);
        loginRow1.add(loginUsernameBox);
        loginRow2.add(loginPasswordLabel);
        loginRow2.add(loginPasswordBox);
        loginInputPanel.add(loginRow1);
        loginInputPanel.add(loginRow2);
        loginInputPanel.add(loginSubmitButton);
        loginPanel.add(loginInputPanel);
        trialArea.setVisibleLines(25);
        trialArea.setCharacterWidth(50);
        //loginPanel.add(trialArea);   //TTEST TEST TEST
        
        
        newUserButton.addClickHandler(this);
		newUserSubmitButton.addClickHandler(this);
		viewUsersButton.addClickHandler(this);
		createSurveyButton.addClickHandler(this);
		submitSurveyButton.addClickHandler(this);
		newSurveyNextButton1.addClickHandler(this);
		newSurveyNextButton2.addClickHandler(this);
		addQuestionButton.addClickHandler(this);
		addOptionButton.addClickHandler(this);
		loginSubmitButton.addClickHandler(this);
		chooseSuggNextButton.addClickHandler(this);
		chooseSuggBackButton.addClickHandler(this);
		chooseSuggSelectButton.addClickHandler(this);
		takeSurveyButton.addClickHandler(this);
		chooseSurveySubmitButton.addClickHandler(this);
		displaySurveyNextButton.addClickHandler(this);
		displaySurveySubmitButton.addClickHandler(this);
		stronglyAgreeButton.addClickHandler(this);	
		agreeButton.addClickHandler(this);	
		neutralButton.addClickHandler(this);
		disagreeButton.addClickHandler(this);
		stronglyDisagreeButton.addClickHandler(this);
		//toGetOptionButton.addClickHandler(this);
		//textarea.setCharacterWidth(50);
		//textarea.setVisibleLines(25);
				
		adminButtonPanel.add(newUserButton);
		adminButtonPanel.add(viewUsersButton);
		adminPanel.add(adminButtonPanel);
		
		/*  //REFER TO THIS WHEN MAKING SURVEY PAGE
		Label test = new Label("hi");
		String[] myArray = {"hi","bye","good","bad"};
		for (int i=0;i<myArray.length;i++) {
			adminPanel.add(new Button(myArray[i], new ClickHandler() {
				public void onClick(ClickEvent event) {
					//SET THE LABEL TO THE OPTION WHEN THIS BUTTON IS CLICKED		
				}
			}));
		}
		*/
		
		newUserPanel.add(row1);
		row1.add(fnLabel);
		row1.add(firstNameBox);
		newUserPanel.add(row2);
		row2.add(lnLabel);
		row2.add(lastNameBox);
		newUserPanel.add(row3);
		row3.add(unLabel);
		row3.add(userNameBox);
		newUserPanel.add(row4);
		row4.add(pwLabel);
		row4.add(passwordBox);
		newUserPanel.add(row5);
		row5.add(divLabel);
		row5.add(divisionBox);
		newUserPanel.add(row6);
		row6.add(depLabel);
		row6.add(departmentBox);
		newUserPanel.add(newUserSubmitButton);
		tabPanel.add(adminPanel, "Admin");
				
		suggButtonPanel.add(makeSuggButton);
		suggPanel.add(suggButtonPanel);
		tabPanel.add(suggPanel, "Suggestions");
		
		surveyPanel.add(surveyButtonPanel);
		surveyButtonPanel.add(createSurveyButton);
		surveyButtonPanel.add(takeSurveyButton);
		suggContainer.add(surveySuggLabel);
		suggContainer.setHeight("100px");
		surveySuggButtons.add(chooseSuggBackButton);
		surveySuggButtons.add(chooseSuggNextButton);
		surveySuggButtons.add(chooseSuggSelectButton);
		surveySuggPanel.setWidth("400px");
		surveySuggPanel.add(surveySuggIDHidden);
		surveySuggPanel.add(suggContainer);
		surveySuggPanel.add(surveySuggButtons);
		surveySuggPanel.setCellHorizontalAlignment(suggContainer, HasHorizontalAlignment.ALIGN_CENTER);
		surveySuggPanel.setCellHorizontalAlignment(surveySuggButtons, HasHorizontalAlignment.ALIGN_CENTER);
		newSurveyPanel.add(surveyTitleRow);
		newSurveyPanel.add(newSurveyNextButton1);
		newSurveyPanel.setWidth("400px");
		newSurveyPanel.setCellHorizontalAlignment(surveyTitleRow, HasHorizontalAlignment.ALIGN_CENTER);
		newSurveyPanel.setCellHorizontalAlignment(newSurveyNextButton1, HasHorizontalAlignment.ALIGN_CENTER);
		surveyTitleRow.add(surveyTitleLabel);
		surveyTitleRow.add(surveyTitleBox);
		surveyQuestionPanel.setWidth("400px");
		surveyQuestionPanel.add(surveyQuestionLabel);
		surveyQuestionPanel.add(surveyQuestionArea);
		surveyQuestionPanel.add(newSurveyNextButton2);
		surveyQuestionPanel.setCellHorizontalAlignment(surveyQuestionLabel, HasHorizontalAlignment.ALIGN_CENTER);
		surveyQuestionPanel.setCellHorizontalAlignment(surveyQuestionArea, HasHorizontalAlignment.ALIGN_CENTER);
		surveyQuestionPanel.setCellHorizontalAlignment(newSurveyNextButton2, HasHorizontalAlignment.ALIGN_CENTER);
		surveyOptionsPanel.setWidth("400px");
		surveyOptionsPanel.add(surveyOptionsLabel);
		surveyOptionsPanel.add(surveyOptionsBox);
		surveyOptionsPanel.add(surveyButtonRow);
		surveyOptionsPanel.setCellHorizontalAlignment(surveyOptionsLabel, HasHorizontalAlignment.ALIGN_CENTER);
		surveyOptionsPanel.setCellHorizontalAlignment(surveyOptionsBox, HasHorizontalAlignment.ALIGN_CENTER);
		surveyOptionsPanel.setCellHorizontalAlignment(surveyButtonRow, HasHorizontalAlignment.ALIGN_CENTER);
		surveyButtonRow.add(addQuestionButton);
		surveyButtonRow.add(addOptionButton);
		surveyButtonRow.add(submitSurveyButton);
		chooseSurveyPanel.add(chooseSurveyListBox);
		chooseSurveyPanel.add(chooseSurveySubmitButton);
		tabPanel.add(surveyPanel, "Surveys");
		
		displaySurveyPanel.add(displaySurveySuggLabel);
		displaySurveyPanel.add(displaySurveyQuestionLabel);
		displaySurveyPanel.add(displaySurveyOptionPanel);
		
		
		//RootPanel.get().add(loginPanel);
		RootPanel.get().add(tabPanel);
		
		//String url = "http://localhost:3000/pages/welcome";
		//getRequest(url);
		//getRequest("http://localhost:3000/users/index.json");
		
	}
		
	private String makePendingSurveyHTML()
	{
		pendingSurveyHTML = "<table border='1'><tr><th>PENDING SURVEY</th></tr><tr><th>Title:</th></tr>";
		if (surveyTitle == null){
				
		}
		else {
			pendingSurveyHTML += "<tr><td>" + surveyTitle + "</td></tr>";	
		}
		
		pendingSurveyHTML +=  "<tr><th>Suggestion:</th></tr><tr><td>" + surveySugg + "</td></tr><tr><th>Questions and Options:</th></tr>";
		
		for (int i=0;i<surveyArray.length;i++) {
			if (surveyArray[i][0] == null) {
				//DOES NOTHING
			}
			else {
				pendingSurveyHTML += "<tr><td>" + surveyArray[i][0] + "</td></tr><tr>";
			}
			for (int j=1;j<surveyArray[i].length;j++) {
				if (surveyArray[i][j] == null) {
					//DOES NOTHING
				}
				else {
					pendingSurveyHTML += "<td>" + surveyArray[i][j] + "</td>";
			    }				
			}
			pendingSurveyHTML += "</tr>";	
		}
		return pendingSurveyHTML;
	}
	
	
	
	public void onClick(ClickEvent event)
	{
		
		Object source = event.getSource();
		
		if (source == loginSubmitButton) {
			String username = loginUsernameBox.getText();
			String password = loginPasswordBox.getText();
			String encData = URL.encode("username") + "=" +
			  URL.encode(username) + "&";
			encData += URL.encode("password") + "=" +
			  URL.encode(password) + "&";
			String url = "http://localhost:3000/pages/login";
			postRequest(url, encData);
		}
										
		else if (source == newUserButton) {
			adminPanel.clear();
			adminPanel.add(adminButtonPanel);
			adminPanel.add(newUserPanel);
			//adminPanel.add(displayUsers);
			//getRequest(displayUsersURL);
			users.clear();
			table = new CellTable<UserInfo>(users.size());
			usersCellTablePager.setDisplay(table);
			adminPanel.add(usersCellTablePager);
			adminPanel.add(table);
			getRequest("http://localhost:3000/users/index.json");
					
			//adminPanel.add(size);  Used To check size of ArrayList
			
		}
		
		else if (source == viewUsersButton) {
		    adminPanel.clear();
		    adminPanel.add(adminButtonPanel);
			//adminPanel.add(displayUsers);
			//getRequest(displayUsersURL);
			users.clear();
			table = new CellTable<UserInfo>(users.size());
			usersCellTablePager.setDisplay(table);
			adminPanel.add(usersCellTablePager);
			adminPanel.add(table);
			getRequest("http://localhost:3000/users/index.json");
			
			
		}
		
		else if (source == newUserSubmitButton) {
			adminPanel.clear();
			adminPanel.add(adminButtonPanel);
			adminPanel.add(newUserPanel);
			String encData = URL.encode("first_name") + "=" +
				URL.encode(firstNameBox.getText()) + "&";
			encData += URL.encode("last_name") + "=" +
				URL.encode(lastNameBox.getText()) + "&";
			encData += URL.encode("username") + "=" +
				URL.encode(userNameBox.getText()) + "&";
			encData += URL.encode("password") + "=" +
				URL.encode(passwordBox.getText()) + "&";
			encData += URL.encode("division") + "=" +
				URL.encode(divisionBox.getText()) + "&";
			encData += URL.encode("department") + "=" +
				URL.encode(departmentBox.getText()) + "&";
		    firstNameBox.setText(""); 
		    lastNameBox.setText("");
		    userNameBox.setText("");
			passwordBox.setText("");
			divisionBox.setText("");
			departmentBox.setText("");
			users.clear();
			table = new CellTable<UserInfo>();
			usersCellTablePager.setDisplay(table);
			adminPanel.add(usersCellTablePager);
			String url = "http://localhost:3000/users/create";
			postRequest(url,encData);
			getRequest("http://localhost:3000/users/index.json");
		}
		
		else if (source == createSurveyButton) {
			surveyPanel.clear();
			surveyPanel.add(surveyButtonPanel);
			getRequest("http://localhost:3000/user_suggestions/divChairView.json");
			
			surveyPanel.add(surveySuggPanel);
			//surveyPanel.add(newSurveyPanel);
			//surveyPanel.add(pendingSurvey);
			
		}
		
		else if (source == chooseSuggNextButton) {
			if (chooseSuggArrayIndex < (chooseSuggArray.length() - 1)) {
				chooseSuggArrayIndex++;
			}
			ChooseSugg sugg = chooseSuggArray.get(chooseSuggArrayIndex);
			surveySuggLabel.setText(sugg.getSuggestion());
			surveySuggIDHidden.setValue(sugg.getSuggestionID());
			//tempArray = chooseSuggArray.get(chooseSuggArrayIndex);	
		}
		
		else if (source == chooseSuggBackButton) {
			if (chooseSuggArrayIndex > 0) {
				chooseSuggArrayIndex--;
			}
			ChooseSugg sugg = chooseSuggArray.get(chooseSuggArrayIndex);
			surveySuggLabel.setText(sugg.getSuggestion());
			surveySuggIDHidden.setValue(sugg.getSuggestionID());
		}
		
		else if (source == chooseSuggSelectButton) {
			surveySugg = surveySuggLabel.getText();
			surveySuggID = surveySuggIDHidden.getValue();
			surveyPanel.clear();
			surveyPanel.add(surveyButtonPanel);
			surveyPanel.add(newSurveyPanel);
			surveyPanel.add(pendingSurvey);
			pendingSurvey.setHTML(makePendingSurveyHTML());
		}
		
		else if (source == newSurveyNextButton1) {
			if (surveyTitleBox.getText() == "") {
				Window.alert("You must enter a title");
			}
			else {
				surveyTitle = surveyTitleBox.getText();
				surveyTitleBox.setText("");
				surveyPanel.clear();
				surveyPanel.add(surveyButtonPanel);
				surveyPanel.add(surveyQuestionPanel);
				pendingSurvey.setHTML(makePendingSurveyHTML());
				surveyPanel.add(pendingSurvey);
			}
			
		}
				
		else if (source == newSurveyNextButton2) {
			surveyArray[surveyArrayIndex1][surveyArrayIndex2] = surveyQuestionArea.getText();
			surveyQuestionArea.setText("");
			surveyArrayIndex2++;
			surveyPanel.clear();
			surveyPanel.add(surveyButtonPanel);
			surveyPanel.add(surveyOptionsPanel);
			pendingSurvey.setHTML(makePendingSurveyHTML());
			surveyPanel.add(pendingSurvey);
		}
		
		else if (source == addOptionButton) {
			surveyArray[surveyArrayIndex1][surveyArrayIndex2] = surveyOptionsBox.getText();
			surveyArrayIndex2++;
			surveyOptionsBox.setText("");
			pendingSurvey.setHTML(makePendingSurveyHTML());
		}
		
		else if (source == addQuestionButton) {
			surveyPanel.clear();
			surveyPanel.add(surveyButtonPanel);
			surveyPanel.add(surveyQuestionPanel);
			surveyPanel.add(pendingSurvey);
			surveyArrayIndex1++;
			surveyArrayIndex2 = 0;
		}
		
		else if (source == submitSurveyButton) {
			String xmlSurvey = "\"<?xml version=\"1.0\" encoding=\"UTF-8\"?><arrays type=\"array\">"; 
				for (int i=0;i<surveyArray.length;i++) {
					
					if (surveyArray[i][0] != null){
						xmlSurvey += "<array type=\"array\">";
						xmlSurvey +=  "<array>" + surveyArray[i][0] + "</array>"; 
					}
					for (int j=1;j<surveyArray[i].length-1;j++) {
						if (surveyArray[i][j] != null) {
							xmlSurvey += "<array>"+surveyArray[i][j]+"</array>";
						}	
					}
					if (surveyArray[i][0] != null) {
						xmlSurvey += "</array>";
					}
					
				}
				xmlSurvey += "</arrays>";
			String encData = URL.encode("survey_title") + "=" +
				URL.encode(surveyTitle) + "&";
			encData += URL.encode("sugg_id") + "=" +
				URL.encode(surveySuggID) + "&";
		    encData += URL.encode("xml_survey") + "=" +
		    	URL.encode(xmlSurvey);
			surveySugg = null;
			surveySuggID = null;
			surveyTitle = null;
			chooseSuggArrayIndex = 0;
			for (int i=0;i<surveyArray.length;i++) {
				for (int j=0;j<surveyArray[i].length;j++) {
					surveyArray[i][j] = null;
				}
			}
			Label surveyCreated = new Label("Survey Has Been Created");
			surveyPanel.clear();
			surveyPanel.add(surveyButtonPanel);
			pendingSurvey.setHTML(makePendingSurveyHTML());
			surveyPanel.add(surveyCreated);
			Window.alert(xmlSurvey);
			String url = "http://localhost:3000/sugg_surveys/createSurvey";
			postRequest(url, encData);
		}
		
		else if (source == takeSurveyButton) {
			surveyPanel.clear();
			surveyPanel.add(surveyButtonPanel);
			surveyPanel.add(chooseSurveyPanel);
			chooseSurveyListBox.clear();
			getRequest("http://localhost:3000/sugg_surveys/chooseSurvey.json");
		}
		
		else if (source == chooseSurveySubmitButton) {
			surveyPanel.clear();
			surveyPanel.add(surveyButtonPanel);
			surveyPanel.add(displaySurveyPanel);
			displaySurveyOptionPanel.clear();
			displaySurveyPanel.remove(displaySurveySubmitButton);
			displaySurveyQuestionLabel.setText("");
			chosenOptionLabel.setText("");
			displaySurveyOptionPanel.add(stronglyAgreeButton);
			displaySurveyOptionPanel.add(agreeButton);
			displaySurveyOptionPanel.add(neutralButton);
			displaySurveyOptionPanel.add(disagreeButton);
			displaySurveyOptionPanel.add(stronglyDisagreeButton);
			displaySurveyPanel.add(displaySurveyNextButton);
			displaySurveyArrayIndex = 0;
			prevSurvIndex = 0;
			int index = chooseSurveyListBox.getSelectedIndex();
			String value = chooseSurveyListBox.getValue(index);
			Window.alert(value);
			surveyPanel.add(displaySurveyPanel);
			getRequest("http://localhost:3000/sugg_surveys/takeSurvey/"+value+".json");
			//String encData = URL.encode("survey_id")+"=";
			//  encData += URL.encode(value);
			//postRequest("http://localhost:3000/sugg_surveys/takeSurvey/3.json",encData);
		}
		
		else if (source == stronglyAgreeButton) {
			initialChosenOption = "strongly_agree";
		}
		else if (source == agreeButton) {
			initialChosenOption = "agree";
		}
		else if (source == neutralButton) {
			initialChosenOption = "neutral";
		}
		else if (source == disagreeButton) {
			initialChosenOption = "disagree";
		}
		else if (source == stronglyDisagreeButton) {
			initialChosenOption = "strongly_disagree";
			Window.alert(initialChosenOption);
		}
		else if (source == displaySurveyNextButton) {
			//getRequest("http://localhost:3000/sugg_surveys/takeSurvey/"+takenSurveyID+".json");
			
			String chosenOption = chosenOptionLabel.getText(); 
			if (chosenOption.equals("default")) {
				Window.alert("Select an option first");
			}
			else {
				displaySurveyOptionPanel.clear();
				//displaySurveyPanel.add(displaySurveyQuestionLabel);
				DisplaySurvey survey = null;
				survey = displaySurveyArray.get(displaySurveyArrayIndex);
				DisplaySurvey prevSurv = null;
				prevSurv = displaySurveyArray.get(prevSurvIndex);
				surveyResultArray[surveyResultArrayIndex][0] = prevSurv.getQuestionID();
							
				//displaySurveySuggLabel.setText(survey.getSuggestion());
				displaySurveyQuestionLabel.setText(survey.getQuestion());
				for (int i = 0; i < displaySurveyArray.length(); i++) {
					String lastQuestion = displaySurveyQuestionLabel.getText();
					survey = displaySurveyArray.get(i);	
					String currentQuestion = survey.getQuestion();
					final String option = survey.getOption();
					if (currentQuestion.equals(lastQuestion)) {
						displaySurveyOptionPanel.add(new Button(option,new ClickHandler() {
							public void onClick(ClickEvent event) {
								chosenOptionLabel.setText(option);
								//Window.alert(chosenOptionLabel.getText());
								
							}
						}));
						displaySurveyArrayIndex = i + 1;
					}
				}
				surveyResultArray[surveyResultArrayIndex][1] = chosenOptionLabel.getText(); 
				Window.alert(""+surveyResultArray[surveyResultArrayIndex][0]+","+surveyResultArray[surveyResultArrayIndex][1]);
				if (!chosenOption.equals("")) {
					surveyResultArrayIndex++;
					prevSurvIndex++;
				}
							
				if (displaySurveyArrayIndex >= displaySurveyArray.length()) {
					displaySurveyPanel.remove(displaySurveyNextButton);
					displaySurveyPanel.add(displaySurveySubmitButton);
				}
				chosenOptionLabel.setText("default");
			}
			
		}
		
		else if (source == displaySurveySubmitButton) {
			DisplaySurvey prevSurv = null;
			prevSurv = displaySurveyArray.get(prevSurvIndex);
			surveyResultArray[surveyResultArrayIndex][0] = prevSurv.getQuestionID();
			surveyResultArray[surveyResultArrayIndex][1] = chosenOptionLabel.getText(); 
			surveyPanel.clear();
			surveyPanel.add(surveyButtonPanel);
			Window.alert("Survey Submitted");
			String xmlSurvey = "\"<?xml version=\"1.0\" encoding=\"UTF-8\"?><arrays type=\"array\">"; 
				for (int i=0;i<surveyResultArray.length;i++) {
					if (surveyResultArray[i][0] != null) {
						xmlSurvey += "<array type=\"array\"> <array>" + surveyResultArray [i][0] + "</array>"+
							"<array>"+surveyResultArray[i][1]+"</array>";
						xmlSurvey += "</array>";
					}
					
				}
				xmlSurvey += "</arrays>";
				Window.alert(xmlSurvey);
			String encData = URL.encode("survey_id")+"="+
				URL.encode(takenSurveyID)+"&";
			encData += URL.encode("initial_option")+"="+
				URL.encode(initialChosenOption)+"&";
			encData += URL.encode("results_array")+"="+
				URL.encode(xmlSurvey);
			postRequest("http://localhost:3000/sugg_surveys/surveyResult",encData);
		}		
		
	}
    
	private void getRequest(String url)
	{
		final RequestBuilder rb =
			new RequestBuilder(RequestBuilder.GET,url);
		try {
			rb.sendRequest(null, new RequestCallback()
			{
				public void onError(final Request request,
					final Throwable exception)
				{
					Window.alert(exception.getMessage());
				}
				public void onResponseReceived(final Request request,
					final Response response)
				{
					
					//trialArea.setText(resp);
					//loginPanel.add(trialLabel);
					//textarea.setText(response.getText());
					//int length = chooseSuggArray.length(); //TO CHECK LENGTH OF ARRAY
					
					
					String resp = response.getText();
					//Window.alert(resp);
					
					if (resp.contains("gwt_sugg")) {
						//Window.alert(resp);
						chooseSuggArray = getSurveySuggArray(resp);
						ChooseSugg sugg = chooseSuggArray.get(chooseSuggArrayIndex);
				        surveySuggLabel.setText(sugg.getSuggestion());
				        surveySuggIDHidden.setValue(sugg.getSuggestionID());
					}
					
					else if (resp.contains("json_user_sugg_by_div")) {
						String newResp = resp.replace(",\"json_user_sugg_by_div\"]", "]");
						chooseSuggArray = getSurveySuggArray(newResp);
						ChooseSugg sugg = chooseSuggArray.get(chooseSuggArrayIndex);
				        surveySuggLabel.setText(sugg.getSuggestion());
				        surveySuggIDHidden.setValue(sugg.getSuggestionID());	
					}
					
					else if (resp.contains("json_available_surveys")) {
						String newResp = resp.replace(",\"json_available_surveys\"]", "]");
						//Window.alert(newResp);
						chooseSurveyArray = getChooseSurveyArray(newResp);
						ChooseSurvey survey;
						for (int i=0;i<chooseSurveyArray.length();i++) {
							survey = chooseSurveyArray.get(i);
							chooseSurveyListBox.addItem(survey.getTitle(),survey.getSurveyID());
						}
					}
					
					else if (resp.contains("json_display_survey")) {
						String newResp = resp.replace(",\"json_display_survey\"]", "]");
						//Window.alert(newResp);
						displaySurveyArray = getDisplaySurveyArray(newResp);
						DisplaySurvey survey;
						survey = displaySurveyArray.get(0);
						String question = survey.getQuestion();
						if (question == null) {
							displaySurveyPanel.remove(displaySurveyNextButton);
							displaySurveyPanel.add(displaySurveySubmitButton);
						}
						String suggText = survey.getSuggestion();
						displaySurveySuggLabel.setText(suggText);
						takenSurveyID = survey.getSurveyID();
						//if (!initialChosenOption.equals("") ) {
						//	for (int i = -1; i < displaySurveyArray.length(); i++) {
						//		if (i == -1) {
						//		
						//		}
						//	}			
						//}	
						
					}
									
					else if (resp.contains("#display_users_celltable")) {
						String newResp = resp.replace(",\"#display_users_celltable\"]", "]");
						//Window.alert(newResp);
						
						jsonData = getArrayUserData(newResp);
						User user = null;
						for (int i = 0; i < jsonData.length(); i++) {
							user = jsonData.get(i);
							users.add(new UserInfo(user.getDepartment(), user.getDivision(), user.getFirstName(), user.getLastName()));
						}
						//size.setText("size is: " + users.size());  Used to check size of ArrayList
						TextColumn<UserInfo> depColumn = new TextColumn<UserInfo>()
						{
							@Override
							public String getValue(UserInfo user)
							{
								return user.department;
							}
						};
						TextColumn<UserInfo> divColumn = new TextColumn<UserInfo>()
						{
							@Override
							public String getValue(UserInfo user)
							{
								return user.division;
							}
						};
						TextColumn<UserInfo> fnColumn = new TextColumn<UserInfo>()
						{
							@Override
							public String getValue(UserInfo user)
							{
								return user.first_name;
							}
						};
						TextColumn<UserInfo> lnColumn = new TextColumn<UserInfo>()
						{
							@Override
							public String getValue(UserInfo user)
							{
								return user.last_name;
							}
						};
						table.setPageSize(5);
						table.addColumn(depColumn, "Department");
						table.addColumn(divColumn, "Division");
						table.addColumn(fnColumn, "First Name");
						table.addColumn(lnColumn, "Last Name");
						AsyncDataProvider<UserInfo> provider = new AsyncDataProvider<UserInfo>() {
						      @Override
						      protected void onRangeChanged(HasData<UserInfo> display) {
						        int start = display.getVisibleRange().getStart();
						        int end = start + display.getVisibleRange().getLength();
						        end = end >= users.size() ? users.size() : end;
						        List<UserInfo> sub = users.subList(start, end);
						        updateRowData(start, sub);
						      }
						    };
						provider.addDataDisplay(table);
						provider.updateRowCount(users.size(), true);
						//table.setRowCount(users.size(),true);
						//table.setRowData(0,users);
						adminPanel.add(table);
						
					}
					
					
					
					//displayUsers.setHTML(response.getText());
					//trialArea.setText(response.getText());
				}
			});
			
		}
		catch (final Exception e) {
			Window.alert(e.getMessage());
		}
	} // end getRequest


	private void postRequest(String url, String data)
	{
		final RequestBuilder rb =
			new RequestBuilder(RequestBuilder.POST,url);
		rb.setHeader("Content-type",
			"application/x-www-form-urlencoded");
		try {
			rb.sendRequest(data, new RequestCallback()
			{
				public void onError(final Request request,
					final Throwable exception)
				{
					Window.alert(exception.getMessage());
				}
				public void onResponseReceived(final Request request,
					final Response response)
				{
					
					
					String resp = response.getText().trim();
					//Window.alert(resp);
					//int id = Integer.parseInt(resp);
					if (resp.contains("logged_in_yes")) {
						RootPanel.get().clear();
						RootPanel.get().add(tabPanel);
					}
					else if (resp.contains("logged_in_no")) {
						loginUsernameBox.setText("");
						loginPasswordBox.setText("");
					}
									
					
					//displayUsers.setUrl(displayUsersURL);
					//String url1 = "http://localhost:3000/pages/welcome";
					//getRequest(url1);
					//getRequest(displayUsersURL);
				
				}
			});
		}
		catch (final Exception e) {
			Window.alert(e.getMessage());
		}
	} // end postRequst
	private final native JsArray<User> getArrayUserData(String json)
	/*-{
		return eval(json);
	}-*/;
	
	private final native JsArray<ChooseSugg> getSurveySuggArray(String json)
	/*-{
		return eval(json);
	}-*/;
	
	private final native JsArray<ChooseSurvey> getChooseSurveyArray(String json)
	/*-{
		return eval(json);
	}-*/;
	
	private final native JsArray<DisplaySurvey> getDisplaySurveyArray(String json)
	/*-{
		return eval(json);
	}-*/;

}

