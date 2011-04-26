package main.client;

import com.google.gwt.user.client.ui.Composite;
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
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.CellPanel;

//CHART STUFF
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.Selection;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.events.SelectHandler;
import com.google.gwt.visualization.client.visualizations.AreaChart;
//import com.google.gwt.visualization.client.visualizations.AreaChart.Options;
import com.google.gwt.visualization.client.visualizations.ColumnChart;
import com.google.gwt.visualization.client.visualizations.ColumnChart.Options;
//CHART STUFF

@SuppressWarnings("deprecation")
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
	
	private static class AllSuggs
	{
		private final String suggestion;
		private final String department;
		private final String division;
		private final String createdAt;
		private final String updatedAt;
		
		public AllSuggs(String sugg, String dep, String div, String ca, String ua)
		{
			suggestion = sugg;
			department = dep;
			division = div;
			createdAt = ca;
			updatedAt = ua;
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
    VerticalPanel adminButtonBoxPanel = new VerticalPanel();
    VerticalPanel adminButtonPanel = new VerticalPanel();
    Button newUserButton = new Button("New User");
    Button viewUsersButton = new Button("View Users");
    HTML displayUsers = new HTML();
    String displayUsersURL = "http://localhost:3000/users/index";
    VerticalPanel usersCellTablePanel = new VerticalPanel();
        
    VerticalPanel newUserBoxPanel = new VerticalPanel();
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
	VerticalPanel newSuggPanel = new VerticalPanel();
	Button newSuggSubmitButton =  new Button("Submit");
	VerticalPanel suggButtonBoxPanel = new VerticalPanel();
    VerticalPanel suggButtonPanel = new VerticalPanel();
    //CellPanel suggButtonPanel;
    Button makeSuggButton = new Button("Make A Suggestion");
    TextArea newSuggArea = new TextArea();
    
    VerticalPanel editOwnSuggPanel = new VerticalPanel();
    Button editOwnSuggButton = new Button("Edit Your Suggestion");
    ListBox editOwnSuggListBox = new ListBox();
    TextArea editOwnSuggArea = new TextArea();
    Button editOwnSuggSubmitButton = new Button("Submit");
    Button chooseEditSuggButton = new Button("Submit");
    JsArray<ChooseSugg> chooseEditSuggArray;
    String currentSuggText = "";
    String currentSuggID = "";
    Button editDivSuggButton = new Button("Edit Division Suggestions");
    
    VerticalPanel viewAllSuggPanel = new VerticalPanel();
    Button viewAllSuggButton = new Button("View Division Suggestions");
    JsArray<ChooseSugg> allSuggArray;
    ArrayList<AllSuggs> allSuggs = new ArrayList<AllSuggs>();
    SimplePager suggsCellTablePager = new SimplePager();
	CellTable<AllSuggs> suggsCellTable = new CellTable<AllSuggs>(allSuggs.size());
    
    HorizontalPanel surveyPanel = new HorizontalPanel();
    VerticalPanel surveyButtonBoxPanel = new VerticalPanel();
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
	VerticalPanel displaySurveySuggPanel = new VerticalPanel();
	Label displaySurveySuggLabel = new Label("");
	Label chosenOptionLabel = new Label("");
	HorizontalPanel currentOptionPanel = new HorizontalPanel(); 
	HTML currentOptionLabel = new HTML("<b>Current Choice: </b>");
	VerticalPanel displaySurveyQuestionPanel = new VerticalPanel();
	Label displaySurveyQuestionLabel = new Label("");
	HorizontalPanel displaySurveyOptionPanel = new HorizontalPanel();
	Button stronglyAgreeButton = new Button("Strongly Agree");
	Button agreeButton = new Button("Agree");
	Button neutralButton = new Button("Neutral");
	Button disagreeButton = new Button("Disagree");
	Button stronglyDisagreeButton = new Button("Strongly Disagree");
	Button displaySurveyNextButton = new Button("Next");
	Button displaySurveySubmitButton = new Button("Submit Survey");
	Button noQuestionSubmitButton = new Button("Submit Survey");
	String initialChosenOption = "";
	String takenSurveyID = "";
	int displaySurveyArrayIndex = 0;
	String[][] surveyResultArray = new String[20][20];
	int surveyResultArrayIndex = 0;
	int prevSurvIndex = 0;
	//final Button toGetOptionButton = new Button();
	
	//CHOOSE SURVEY CHART STUFF
	Button surveyChartsButton = new Button("View survey results");
	Button chooseChartSubmitButton = new Button("Submit");
	VerticalPanel chooseChartPanel = new VerticalPanel();
	ListBox chooseChartListBox = new ListBox();
	JsArray<ChooseSurvey> chooseChartDataArray;	
	//CHOOSE SURVEY CHART STUFF
	
	
	
	//CHART STUFF
	VerticalPanel chartPanel = new VerticalPanel(); 
	HorizontalPanel displayChartPanel = new HorizontalPanel();
	//FlowPanel displayChartPanel = new FlowPanel();
	VerticalPanel chartSuggPanel = new VerticalPanel();
	DisplaySurvey chartData = null;
	Label chartSuggLabel = new Label("");
	JsArray<DisplaySurvey> chartDataArray;
	//CHART STUFF
	
	String userType = "";	
	
	HorizontalPanel displayUsersPanel = new HorizontalPanel();
	VerticalPanel displayUsersButtonBoxPanel = new VerticalPanel();
	VerticalPanel displayUsersButtonPanel = new VerticalPanel();
	Button viewDivUsers = new Button("View Division Users");
	Button viewDepUsers = new Button("View Department Users");
	VerticalPanel displayUsersCellTable = new VerticalPanel();
	
	VerticalPanel divSuggsChartPanel = new VerticalPanel();
	Button divSuggsChartButton = new Button("Number Of Suggestions");
	
	
	
		        
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
        loginPanel.setWidth("100%");
        loginPanel.setCellHorizontalAlignment(loginInputPanel, HasHorizontalAlignment.ALIGN_CENTER);
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
		noQuestionSubmitButton.addClickHandler(this);
		stronglyAgreeButton.addClickHandler(this);	
		agreeButton.addClickHandler(this);	
		neutralButton.addClickHandler(this);
		disagreeButton.addClickHandler(this);
		stronglyDisagreeButton.addClickHandler(this);
		surveyChartsButton.addClickHandler(this);
		chooseChartSubmitButton.addClickHandler(this);
		makeSuggButton.addClickHandler(this);
		newSuggSubmitButton.addClickHandler(this);
		editOwnSuggButton.addClickHandler(this);
		editOwnSuggSubmitButton.addClickHandler(this);
		chooseEditSuggButton.addClickHandler(this);
		editDivSuggButton.addClickHandler(this);
		viewAllSuggButton.addClickHandler(this);
		viewDivUsers.addClickHandler(this);
		viewDepUsers.addClickHandler(this);
		divSuggsChartButton.addClickHandler(this);
		
		//toGetOptionButton.addClickHandler(this);
		//textarea.setCharacterWidth(50);
		//textarea.setVisibleLines(25);
				
		adminButtonBoxPanel.add(adminButtonPanel);
		adminButtonBoxPanel.setStyleName("adminButtonBoxPanel");
		adminButtonPanel.add(newUserButton);
		adminButtonPanel.add(viewUsersButton);
		adminPanel.add(adminButtonBoxPanel);
		adminPanel.setSpacing(7);
							
		newUserBoxPanel.add(newUserPanel);
		newUserBoxPanel.setStyleName("newUserBoxPanel");
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
				
		suggButtonBoxPanel.add(suggButtonPanel);
		suggButtonBoxPanel.setStyleName("suggButtonBoxPanel");
		suggButtonPanel.setSpacing(7);
		suggButtonPanel.add(makeSuggButton);
		suggButtonPanel.add(editOwnSuggButton);
		suggButtonPanel.add(editDivSuggButton);
		suggButtonPanel.add(viewAllSuggButton);
		suggButtonPanel.add(divSuggsChartButton);
		suggPanel.add(suggButtonBoxPanel);
		newSuggPanel.add(newSuggArea);
		newSuggPanel.add(newSuggSubmitButton);
		newSuggPanel.setWidth("400px");
		newSuggPanel.setCellHorizontalAlignment(newSuggArea,HasHorizontalAlignment.ALIGN_CENTER);
		newSuggPanel.setCellHorizontalAlignment(newSuggSubmitButton,HasHorizontalAlignment.ALIGN_CENTER);
		
		viewAllSuggPanel.add(suggsCellTablePager);
		viewAllSuggPanel.add(suggsCellTable);
		
		tabPanel.add(suggPanel, "Suggestions");
		
		surveyPanel.add(surveyButtonBoxPanel);
		surveyButtonBoxPanel.add(surveyButtonPanel);
		surveyButtonBoxPanel.setStyleName("surveyButtonBoxPanel");
		surveyButtonPanel.setSpacing(7);
		surveyButtonPanel.add(createSurveyButton);
		surveyButtonPanel.add(takeSurveyButton);
		surveyButtonPanel.add(surveyChartsButton);
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
		chooseSurveyPanel.setWidth("400px");
		chooseSurveyPanel.add(chooseSurveySubmitButton);
		chooseSurveyPanel.setCellHorizontalAlignment(chooseSurveyListBox, HasHorizontalAlignment.ALIGN_CENTER);
		chooseSurveyPanel.setCellHorizontalAlignment(chooseSurveySubmitButton, HasHorizontalAlignment.ALIGN_CENTER);
		tabPanel.add(surveyPanel, "Surveys");
		//tabPanel.addSelectionHandler(new SelectionHandler() {
		//	public void onSelection(SelectionEven event) {
		//		if(userType ==
		//	}	
		//}
		
		//displaySurveyPanel.setWidth("400px");
		displaySurveyPanel.add(displaySurveySuggPanel);
		displaySurveySuggPanel.add(displaySurveySuggLabel);
		displaySurveySuggPanel.setHeight("75px");
		displaySurveySuggPanel.setWidth("400px");
		displaySurveySuggPanel.setCellHorizontalAlignment(displaySurveySuggLabel, HasHorizontalAlignment.ALIGN_CENTER);
		displaySurveyPanel.add(displaySurveyQuestionPanel);
		displaySurveyPanel.add(currentOptionPanel);
		displaySurveyQuestionPanel.add(displaySurveyQuestionLabel);
		displaySurveyQuestionPanel.setHeight("50px");
		displaySurveyQuestionPanel.setCellHorizontalAlignment(displaySurveyQuestionLabel, HasHorizontalAlignment.ALIGN_CENTER);
		displaySurveyOptionPanel.setHeight("40px");
		displaySurveyPanel.add(displaySurveyOptionPanel);
		displaySurveyPanel.setCellHorizontalAlignment(displaySurveySuggPanel, HasHorizontalAlignment.ALIGN_CENTER);
		displaySurveyPanel.setCellHorizontalAlignment(displaySurveyQuestionLabel, HasHorizontalAlignment.ALIGN_CENTER);
		displaySurveyPanel.setCellHorizontalAlignment(displaySurveyOptionPanel, HasHorizontalAlignment.ALIGN_CENTER);
		displaySurveyPanel.setCellHorizontalAlignment(currentOptionPanel, HasHorizontalAlignment.ALIGN_CENTER);
		displaySurveyPanel.setCellHorizontalAlignment(displaySurveyQuestionPanel, HasHorizontalAlignment.ALIGN_CENTER);
		currentOptionPanel.add(currentOptionLabel);
				
		chooseChartPanel.add(chooseChartListBox);
		chooseChartPanel.add(chooseChartSubmitButton);
		chooseChartPanel.setWidth("400px");
		chooseChartPanel.setCellHorizontalAlignment(chooseChartSubmitButton,HasHorizontalAlignment.ALIGN_CENTER);
		chooseChartPanel.setCellHorizontalAlignment(chooseChartListBox,HasHorizontalAlignment.ALIGN_CENTER);
		
		
		chartPanel.add(chartSuggPanel);
		chartPanel.setWidth("500px");
		chartPanel.setCellHorizontalAlignment(displayChartPanel,HasHorizontalAlignment.ALIGN_CENTER);
		chartPanel.setCellHorizontalAlignment(chartSuggPanel,HasHorizontalAlignment.ALIGN_CENTER);
		chartPanel.add(displayChartPanel);
		//displayChartPanel.setWidth("1000px");
		chartSuggPanel.add(chartSuggLabel);
		chartSuggPanel.setHeight("75px");
		chartSuggPanel.setCellHorizontalAlignment(chartSuggLabel,HasHorizontalAlignment.ALIGN_CENTER);
		
		displayUsersButtonBoxPanel.add(displayUsersButtonPanel);
		displayUsersButtonBoxPanel.setStyleName("usersBoxPanel");
		displayUsersButtonPanel.setSpacing(7);
		displayUsersButtonPanel.add(viewDivUsers);
		displayUsersButtonPanel.add(viewDepUsers);
		displayUsersPanel.add(displayUsersButtonBoxPanel);
		displayUsersPanel.add(displayUsersCellTable);
		
		tabPanel.add(displayUsersPanel, "Division Users");		
		RootPanel.get().add(loginPanel);
		//RootPanel.get().add(tabPanel);
						
					
	}
			
	private String makePendingSurveyHTML()
	{
		pendingSurveyHTML = "<table border='1'><tr><th>PENDING SURVEY</th></tr><tr><th>Title</th></tr>";
		if (surveyTitle == null){
				
		}
		else {
			pendingSurveyHTML += "<tr><td>" + surveyTitle + "</td></tr>";	
		}
		
		pendingSurveyHTML +=  "<tr><th>Suggestion</th></tr><tr><td>" + surveySugg + "</td></tr><tr><th>Questions and Options</th></tr>";
		
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
					if (j == 1) {
						//pendingSurveyHTML += "<td>" + surveyArray[i][j] + "</td>";
						pendingSurveyHTML += "<td>" + surveyArray[i][j];
					}
					else {
						pendingSurveyHTML += " &nbsp<b>|</b>&nbsp  " + surveyArray[i][j];
					}
			    }				
			}
			pendingSurveyHTML += "</td>";
			pendingSurveyHTML += "</tr>";	
		}
		return pendingSurveyHTML;
	}
	
	//public void onSelection(SelectionEvent event)
	//{
	
	//}
	
	
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
			adminPanel.add(adminButtonBoxPanel);
			adminButtonBoxPanel.add(adminButtonPanel);
			//adminPanel.add(newUserPanel);
			adminPanel.add(newUserBoxPanel);
			newUserBoxPanel.add(newUserPanel);
			//adminPanel.add(usersCellTablePanel);
			//adminPanel.add(displayUsers);
			//getRequest(displayUsersURL);
			//users.clear();
			//table = new CellTable<UserInfo>(users.size());
			//usersCellTablePager.setDisplay(table);
			//adminPanel.add(usersCellTablePager);
			//adminPanel.add(table);
			//usersCellTablePanel.clear();
			//usersCellTablePanel.add(usersCellTablePager);
			//usersCellTablePanel.add(table);
			//getRequest("http://localhost:3000/users/index.json");
					
			//adminPanel.add(size);  Used To check size of ArrayList
			
		}
		
		else if (source == viewUsersButton) {
		    adminPanel.clear();
		    adminPanel.add(adminButtonBoxPanel);
		    adminButtonBoxPanel.add(adminButtonPanel);
		    adminPanel.add(usersCellTablePanel);
			//adminPanel.add(displayUsers);
			//getRequest(displayUsersURL);
			users.clear();
			table = new CellTable<UserInfo>(users.size());
			usersCellTablePager.setDisplay(table);
			usersCellTablePanel.clear();
			usersCellTablePanel.add(usersCellTablePager);
			usersCellTablePanel.add(table);
			//adminPanel.add(usersCellTablePager);
			//adminPanel.add(table);
			getRequest("http://localhost:3000/users/index.json");
			
			
		}
		
		else if (source == newUserSubmitButton) {
			adminPanel.clear();
			adminPanel.add(adminButtonBoxPanel);
			adminButtonBoxPanel.add(adminButtonPanel);
			adminPanel.add(newUserBoxPanel);
			newUserBoxPanel.add(newUserPanel);
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
			//users.clear();
			//table = new CellTable<UserInfo>();
			//usersCellTablePager.setDisplay(table);
			//adminPanel.add(usersCellTablePanel);
			//usersCellTablePanel.clear();
			//usersCellTablePanel.add(usersCellTablePager);
			//usersCellTablePanel.add(table);
			String url = "http://localhost:3000/users/create";
			postRequest(url,encData);
			//getRequest("http://localhost:3000/users/index.json");
		}
		
		else if (source == createSurveyButton) {
			surveyPanel.clear();
			surveyPanel.add(surveyButtonBoxPanel);
			chooseSuggArrayIndex = 0;
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
			String suggestion = sugg.getSuggestion();
			while(suggestion.equals("")) {
				chooseSuggArrayIndex++;
				sugg = chooseSuggArray.get(chooseSuggArrayIndex);
				suggestion = sugg.getSuggestion();
			}
			sugg = chooseSuggArray.get(chooseSuggArrayIndex);
			surveySuggLabel.setText(sugg.getSuggestion());
			surveySuggIDHidden.setValue(sugg.getSuggestionID());
			//tempArray = chooseSuggArray.get(chooseSuggArrayIndex);	
		}
		
		else if (source == chooseSuggBackButton) {
			if (chooseSuggArrayIndex > 0) {
				chooseSuggArrayIndex--;
			}
			ChooseSugg sugg = chooseSuggArray.get(chooseSuggArrayIndex);
			String suggestion = sugg.getSuggestion();
			while(suggestion.equals("")) {
				chooseSuggArrayIndex--;
				sugg = chooseSuggArray.get(chooseSuggArrayIndex);
				suggestion = sugg.getSuggestion();
			}
			sugg = chooseSuggArray.get(chooseSuggArrayIndex);
			surveySuggLabel.setText(sugg.getSuggestion());
			surveySuggIDHidden.setValue(sugg.getSuggestionID());
		}
		
		else if (source == chooseSuggSelectButton) {
			surveySugg = surveySuggLabel.getText();
			surveySuggID = surveySuggIDHidden.getValue();
			surveyPanel.clear();
			surveyPanel.add(surveyButtonBoxPanel);
			surveyButtonBoxPanel.add(surveyButtonPanel);
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
				surveyPanel.add(surveyButtonBoxPanel);
				surveyButtonBoxPanel.add(surveyButtonPanel);
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
			surveyPanel.add(surveyButtonBoxPanel);
			surveyButtonBoxPanel.add(surveyButtonPanel);
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
			surveyPanel.add(surveyButtonBoxPanel);
			surveyButtonBoxPanel.add(surveyButtonPanel);
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
			surveyArrayIndex1 = 0;
			surveyArrayIndex2 = 0;
			for (int i=0;i<surveyArray.length;i++) {
				for (int j=0;j<surveyArray[i].length;j++) {
					surveyArray[i][j] = null;
				}
			}
			Label surveyCreated = new Label("Survey Has Been Created");
			surveyPanel.clear();
			surveyPanel.add(surveyButtonBoxPanel);
			pendingSurvey.setHTML(makePendingSurveyHTML());
			surveyPanel.add(surveyCreated);
			//Window.alert(xmlSurvey);
			String url = "http://localhost:3000/sugg_surveys/createSurvey";
			postRequest(url, encData);
		}
		
		else if (source == takeSurveyButton) {
			surveyPanel.clear();
			surveyPanel.add(surveyButtonBoxPanel);
			surveyButtonBoxPanel.add(surveyButtonPanel);
			surveyPanel.add(chooseSurveyPanel);
			chooseSurveyListBox.clear();
			getRequest("http://localhost:3000/sugg_surveys/chooseSurvey.json");
		}
		
		else if (source == chooseSurveySubmitButton) {
			int index = chooseSurveyListBox.getSelectedIndex();
			String surveyText = chooseSurveyListBox.getItemText(index);
			if (surveyText.contains("(TAKEN)")) {
				Window.alert("You have already taken this survey");
			}
			else {
				surveyPanel.clear();
				surveyPanel.add(surveyButtonBoxPanel);
				surveyPanel.add(displaySurveyPanel);
				displaySurveyOptionPanel.clear();
				displaySurveyPanel.remove(displaySurveySubmitButton);
				displaySurveyQuestionLabel.setText("");
				chosenOptionLabel.setText("");
				currentOptionLabel.setHTML("<b>Current Choice: </b>");
				displaySurveyOptionPanel.add(stronglyAgreeButton);
				displaySurveyOptionPanel.add(agreeButton);
				displaySurveyOptionPanel.add(neutralButton);
				displaySurveyOptionPanel.add(disagreeButton);
				displaySurveyOptionPanel.add(stronglyDisagreeButton);
				displaySurveyPanel.add(displaySurveyNextButton);
				displaySurveyPanel.setCellHorizontalAlignment(displaySurveyNextButton, HasHorizontalAlignment.ALIGN_CENTER);
				displaySurveyArrayIndex = 0;
				prevSurvIndex = 0;
				surveyResultArrayIndex = 0;
				index = chooseSurveyListBox.getSelectedIndex();
				String value = chooseSurveyListBox.getValue(index);
				//Window.alert(value);
				surveyPanel.add(displaySurveyPanel);
				getRequest("http://localhost:3000/sugg_surveys/takeSurvey/"+value+".json");
				//String encData = URL.encode("survey_id")+"=";
				//  encData += URL.encode(value);
				//postRequest("http://localhost:3000/sugg_surveys/takeSurvey/3.json",encData);
			}
		}
		
		else if (source == stronglyAgreeButton) {
			initialChosenOption = "strongly_agree";
			currentOptionLabel.setHTML("<b>Current Choice: </b>Strongly Agree");
		}
		else if (source == agreeButton) {
			initialChosenOption = "agree";
			currentOptionLabel.setHTML("<b>Current Choice: </b>Agree"); 
		}
		else if (source == neutralButton) {
			initialChosenOption = "neutral";
			currentOptionLabel.setHTML("<b>Current Choice: </b>Neutral");
		}
		else if (source == disagreeButton) {
			initialChosenOption = "disagree";
			currentOptionLabel.setHTML("<b>Current Choice: </b>Disagree");
		}
		else if (source == stronglyDisagreeButton) {
			initialChosenOption = "strongly_disagree";
			currentOptionLabel.setHTML("<b>Current Choice: </b>Strongly Disagree");
		}
		else if (source == noQuestionSubmitButton) {
			if (initialChosenOption.equals("")) {
				Window.alert("Select An Option First");
			}
			else {
				Window.alert("Survey Has Been Submitted");
				surveyPanel.clear();
				surveyPanel.add(surveyButtonBoxPanel);
				String encData = URL.encode("survey_id")+"=";
				encData += URL.encode(takenSurveyID)+"&";
				encData += URL.encode("initial_option")+"=";
				encData += URL.encode(initialChosenOption);
				postRequest("http://localhost:3000/sugg_surveys/surveyResult",encData);		
			}
		}
		else if (source == displaySurveyNextButton) {
			String chosenOption = chosenOptionLabel.getText(); 
			
			if (chosenOption.equals("default") || initialChosenOption.equals("")) {
				Window.alert("Select an option first");
			}
			else {
				displaySurveyOptionPanel.clear();
				DisplaySurvey survey = null;
				currentOptionLabel.setHTML("<b>Current Choice: </b>");
				survey = displaySurveyArray.get(displaySurveyArrayIndex);
				DisplaySurvey prevSurv = null;
				if (!chosenOption.equals("")) {
					prevSurv = displaySurveyArray.get(displaySurveyArrayIndex - 1);
					surveyResultArray[prevSurvIndex][0] = prevSurv.getQuestionID();	
				}		
				displaySurveyQuestionLabel.setText(survey.getQuestion());
				for (int i = displaySurveyArrayIndex; i < displaySurveyArray.length(); i++) {
					String lastQuestion = displaySurveyQuestionLabel.getText();
					DisplaySurvey survey2 = null;
					survey2 = displaySurveyArray.get(i);	
					String currentQuestion = survey2.getQuestion();
					final String option = survey2.getOption();
					if (currentQuestion.equals(lastQuestion)) {
						displaySurveyOptionPanel.add(new Button(option,new ClickHandler() {
							public void onClick(ClickEvent event) {
								chosenOptionLabel.setText(option);
								currentOptionLabel.setHTML("<b>Current Choice: </b>"+option);
							//Window.alert(chosenOptionLabel.getText());
								
							}
						}));
						displaySurveyArrayIndex = i + 1;
					}
				}
				surveyResultArray[surveyResultArrayIndex][1] = chosenOptionLabel.getText(); 
				//Window.alert(""+surveyResultArray[surveyResultArrayIndex][0]+","+surveyResultArray[surveyResultArrayIndex][1]);
				if (!chosenOption.equals("")) {
					surveyResultArrayIndex++;
					prevSurvIndex++;
				}
							
				if (displaySurveyArrayIndex >= displaySurveyArray.length()) {
					displaySurveyPanel.remove(displaySurveyNextButton);
					displaySurveyPanel.add(displaySurveySubmitButton);
					displaySurveyPanel.setCellHorizontalAlignment(displaySurveySubmitButton, HasHorizontalAlignment.ALIGN_CENTER);
				}
				chosenOptionLabel.setText("default");
			}
			
		}
		
		else if (source == displaySurveySubmitButton) {
			
			DisplaySurvey prevSurv = null;
			//if (chosenOption.equals("")) {
			prevSurv = displaySurveyArray.get(displaySurveyArrayIndex - 1);
			surveyResultArray[prevSurvIndex][0] = prevSurv.getQuestionID();
			
			//surveyResultArray[surveyResultArrayIndex][0] = prevSurv.getQuestionID();
			currentOptionLabel.setHTML("<h4>Your Choice: ");	
			//}
			//prevSurv = displaySurveyArray.get(prevSurvIndex);
			//surveyResultArray[prevSurvIndex][0] = prevSurv.getQuestionID();
			surveyResultArray[prevSurvIndex][1] = chosenOptionLabel.getText(); 
			surveyPanel.clear();
			surveyPanel.add(surveyButtonBoxPanel);
			Window.alert("Survey Has Been Submitted");
			String xmlSurvey = "\"<?xml version=\"1.0\" encoding=\"UTF-8\"?><arrays type=\"array\">"; 
				for (int i=0;i<surveyResultArray.length;i++) {
					if (surveyResultArray[i][0] != null) {
						xmlSurvey += "<array type=\"array\"> <array>" + surveyResultArray [i][0] + "</array>"+
							"<array>"+surveyResultArray[i][1]+"</array>";
						xmlSurvey += "</array>";
					}
					
				}
				xmlSurvey += "</arrays>";
			String encData = URL.encode("survey_id")+"="+
				URL.encode(takenSurveyID)+"&";
			encData += URL.encode("initial_option")+"="+
				URL.encode(initialChosenOption)+"&";
			encData += URL.encode("results_array")+"="+
				URL.encode(xmlSurvey);
			postRequest("http://localhost:3000/sugg_surveys/surveyResult",encData);
		}
		
		else if(source == chooseChartSubmitButton) {
			int index = chooseChartListBox.getSelectedIndex();
			String id = chooseChartListBox.getValue(index);
			surveyPanel.clear();
			
			surveyPanel.add(surveyButtonBoxPanel);
			surveyPanel.add(chartPanel);
			getRequest("http://localhost:3000/sugg_surveys/chartData/"+id+".json");
			
			
		}
		
		//START CHARTS
		else if(source == surveyChartsButton) {
			surveyPanel.clear();
			surveyPanel.add(surveyButtonBoxPanel);
			surveyButtonBoxPanel.add(surveyButtonPanel);
			displayChartPanel.clear();
			chooseChartListBox.clear();
			surveyPanel.add(chooseChartPanel);
			getRequest("http://localhost:3000/sugg_surveys/allSurveys.json");
			//Window.alert("Results Are Ready");
		}		
		//END CHARTS
		
		else if(source == makeSuggButton) {
			suggPanel.clear();
			newSuggArea.setText("");
			suggPanel.add(suggButtonBoxPanel);
			suggPanel.add(newSuggPanel);
		}
		
		else if(source == newSuggSubmitButton) {
			String sugg = newSuggArea.getText();
			if (sugg.equals("")) {
				Window.alert("Enter A Suggestion First");
			}
			else {
				suggPanel.clear();
				newSuggArea.setText("");
				suggPanel.add(suggButtonBoxPanel);
				String encData = URL.encode("suggestion")+"="+
					URL.encode(sugg);
				postRequest("http://localhost:3000/user_suggestions/create", encData);
			}
		}
		
		else if(source == editOwnSuggButton) {
			suggPanel.clear();
			editOwnSuggPanel.clear();
			editOwnSuggListBox.clear();
			suggPanel.add(suggButtonBoxPanel);
			suggPanel.add(editOwnSuggPanel);
			editOwnSuggPanel.add(editOwnSuggListBox);
			editOwnSuggPanel.add(chooseEditSuggButton);
			editOwnSuggPanel.setWidth("400px");
			editOwnSuggPanel.setCellHorizontalAlignment(editOwnSuggListBox, HasHorizontalAlignment.ALIGN_CENTER);
			editOwnSuggPanel.setCellHorizontalAlignment(chooseEditSuggButton, HasHorizontalAlignment.ALIGN_CENTER);
			getRequest("http://localhost:3000/user_suggestions/chooseSuggestion/"+"editOwn"+".json");
			
		}
		
		else if (source == chooseEditSuggButton) {
			int index = editOwnSuggListBox.getSelectedIndex();
			currentSuggText = editOwnSuggListBox.getItemText(index);
			currentSuggID = editOwnSuggListBox.getValue(index);
			editOwnSuggPanel.clear();
			editOwnSuggPanel.add(editOwnSuggArea);
			editOwnSuggPanel.add(editOwnSuggSubmitButton);
			editOwnSuggPanel.setCellHorizontalAlignment(editOwnSuggArea, HasHorizontalAlignment.ALIGN_CENTER);
			editOwnSuggPanel.setCellHorizontalAlignment(editOwnSuggSubmitButton, HasHorizontalAlignment.ALIGN_CENTER);
			editOwnSuggArea.setText(currentSuggText);
		}
		
		else if (source == editOwnSuggSubmitButton) {
			String newSugg = editOwnSuggArea.getText();
			String suggID = currentSuggID;
			editOwnSuggArea.setText("");
			currentSuggText = "";
			currentSuggID = "";
			suggPanel.clear();
			suggPanel.add(suggButtonBoxPanel);
			String encData = URL.encode("suggestionid")+"="+
				URL.encode(suggID)+"&";
			encData += URL.encode("suggestion")+"="+
				URL.encode(newSugg);
			postRequest("http://localhost:3000/suggestions/update", encData);
		}
		
		else if (source == editDivSuggButton) {
			suggPanel.clear();
			editOwnSuggPanel.clear();
			editOwnSuggListBox.clear();
			suggPanel.add(suggButtonBoxPanel);
			suggPanel.add(editOwnSuggPanel);
			editOwnSuggPanel.add(editOwnSuggListBox);
			editOwnSuggPanel.add(chooseEditSuggButton);
			editOwnSuggPanel.setWidth("400px");
			editOwnSuggPanel.setCellHorizontalAlignment(editOwnSuggListBox, HasHorizontalAlignment.ALIGN_CENTER);
			editOwnSuggPanel.setCellHorizontalAlignment(chooseEditSuggButton, HasHorizontalAlignment.ALIGN_CENTER);
			getRequest("http://localhost:3000/user_suggestions/chooseSuggestion/"+"editDiv"+".json");	
		}
		
		else if (source == viewAllSuggButton) {
			suggPanel.clear();
			allSuggs.clear();
			viewAllSuggPanel.clear();
			suggsCellTable = new CellTable<AllSuggs>(allSuggs.size());
			suggsCellTablePager.setDisplay(suggsCellTable);
			viewAllSuggPanel.add(suggsCellTablePager);
			viewAllSuggPanel.add(suggsCellTable);
			suggPanel.add(suggButtonBoxPanel);
			suggPanel.add(viewAllSuggPanel);
			getRequest("http://localhost:3000/user_suggestions/allSuggestions.json");
		}
		
		else if (source == viewDivUsers) {
			displayUsersPanel.clear();
			displayUsersPanel.add(displayUsersButtonBoxPanel);
			displayUsersPanel.add(displayUsersCellTable);
			users.clear();
			table = new CellTable<UserInfo>(users.size());
			usersCellTablePager.setDisplay(table);
			//displayUsersPanel.add(usersCellTablePager);
			//displayUsersPanel.add(table);
			displayUsersCellTable.clear();
			displayUsersCellTable.add(usersCellTablePager);
			displayUsersCellTable.add(table);
			getRequest("http://localhost:3000/users/index/"+"div"+".json");
		}
		
		else if (source == viewDepUsers) {
			displayUsersPanel.clear();
			displayUsersPanel.add(displayUsersButtonBoxPanel);
			displayUsersPanel.add(displayUsersCellTable);
			users.clear();
			table = new CellTable<UserInfo>(users.size());
			usersCellTablePager.setDisplay(table);
			//displayUsersPanel.add(usersCellTablePager);
			//displayUsersPanel.add(table);
			displayUsersCellTable.clear();
			displayUsersCellTable.add(usersCellTablePager);
			displayUsersCellTable.add(table);
			getRequest("http://localhost:3000/users/index/"+"dep"+".json");	
		}
		
		else if (source == divSuggsChartButton) {
			suggPanel.clear();
			suggPanel.add(suggButtonBoxPanel);
			divSuggsChartPanel.clear();
			suggPanel.add(divSuggsChartPanel);
			getRequest("http://localhost:3000/user_suggestions/numSuggsByDiv.json");
		}
	}
	
	private Options createOptions(String title)
	{
		Options options = Options.create();
		options.setWidth(400);
		options.setHeight(240);
		options.set3D(true);
		options.setTitle(title);
		return options;
	}
	
	
	private AbstractDataTable createTable(int i)
	{
		//DisplaySurvey chartData = null;
		DisplaySurvey data = null;
		data = chartDataArray.get(i);
		String lastQuestion = data.getQuestion();
		int row = 0;
		DataTable initialData = DataTable.create();
		initialData.addColumn(ColumnType.STRING,"Option");
		initialData.addColumn(ColumnType.NUMBER,"Times Chosen");
		for (int j = i; j < chartDataArray.length();j++) {
			DisplaySurvey chartData = chartDataArray.get(j);
			String currentQuestion = chartData.getQuestion();
						
			if (currentQuestion.equals(lastQuestion)) {
				String option = chartData.getOption();
				String timesChosenStr = chartData.getTimesChosen();
				int timesChosen = Integer.parseInt(timesChosenStr);
				initialData.addRow();
				initialData.setValue(row, 0, option);
				initialData.setValue(row, 1, timesChosen);	
				row++;
			}	
		}
		return initialData;	
	}
	
	private Options initialCreateOptions()
	{
		Options initialOptions = Options.create();
		initialOptions.setWidth(400);
		initialOptions.setHeight(240);
		initialOptions.set3D(true);
		initialOptions.setTitle("General Rating of the Suggestion");
		return initialOptions;
	}
	
	
	private AbstractDataTable initialCreateTable()
	{
		//DisplaySurvey chartData = null;
		DisplaySurvey data = null;
		data = chartDataArray.get(0);
		String sugg = "hi";
		DataTable initialData = DataTable.create();
		initialData.addColumn(ColumnType.STRING,"Option");
		initialData.addColumn(ColumnType.NUMBER,"Times Chosen");
		initialData.addRows(5);
		initialData.setValue(0,0,"Strongly Agree");
		initialData.setValue(0,1,data.getStronglyAgree());
		initialData.setValue(1,0,"Agree");
		initialData.setValue(1,1,data.getAgree());
		initialData.setValue(2,0,"Neutral");
		initialData.setValue(2,1,data.getNeutral());
		initialData.setValue(3,0,"Disagree");
		initialData.setValue(3,1,data.getDisagree());
		initialData.setValue(4,0,"Strongly Disagree");
		initialData.setValue(4,1,data.getStronglyDisagree());
		return initialData;	
	}
	
	private Options createDivSuggsOptions()
	{
		Options options = Options.create();
		options.setWidth(400);
		options.setHeight(240);
		options.set3D(true);
		options.setTitle("Number Of Suggestions By Division");
		return options;	
	}
	
	private AbstractDataTable createDivSuggsTable() 
	{
		DataTable chartData = DataTable.create();
		chartData.addColumn(ColumnType.STRING, "Division");
		chartData.addColumn(ColumnType.NUMBER, "Number of Suggestions");
		ChooseSugg data = null;
		data = chooseEditSuggArray.get(0);
		String division = data.getDiv();
		int row = 0;
		int suggCount = 0;
		for (int i = 0; i < chooseEditSuggArray.length(); i++) {
			data = chooseEditSuggArray.get(i);
			String div = data.getDiv();
			if (div.equals(division)) {
				suggCount++;	
			}
			else {
				chartData.addRow();
				chartData.setValue(row, 0, division);
				chartData.setValue(row, 1, suggCount);
				row++;
				suggCount = 1;
				division = div;	
			}	
		}
		chartData.addRow();
		chartData.setValue(row, 0, division);
		chartData.setValue(row, 1, suggCount);
		return chartData;
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
										
					String resp = response.getText();
					//Window.alert(resp);
					
					if (resp.contains("gwt_sugg")) {
						//Window.alert(resp);
						chooseSuggArray = getSurveySuggArray(resp);
						ChooseSugg sugg = chooseSuggArray.get(chooseSuggArrayIndex);
				        surveySuggLabel.setText(sugg.getSuggestion());
				        surveySuggIDHidden.setValue(sugg.getSuggestionID());
					}
					
					else if (resp.contains("json_num_of_suggs_by_div")) {
						String newResp = resp.replace(",\"json_num_of_suggs_by_div\"]", "]");
						chooseEditSuggArray = getSurveySuggArray(newResp);
						Runnable onLoadCallback = new Runnable()
						{
							public void run()
							{
								ColumnChart divSuggsChart = new ColumnChart(createDivSuggsTable(),createDivSuggsOptions());
								divSuggsChartPanel.add(divSuggsChart);
							}
						};
						VisualizationUtils.loadVisualizationApi(onLoadCallback, AreaChart.PACKAGE);
					}
															
					else if (resp.contains("json_choose_sugg_edit")) {
						String newResp = resp.replace(",\"json_choose_sugg_edit\"]", "]");
						chooseEditSuggArray = getSurveySuggArray(newResp);
						for (int i = 0; i < chooseEditSuggArray.length(); i++) {
							ChooseSugg sugg = chooseEditSuggArray.get(i);
							String suggid = sugg.getSuggestionID();
							String suggestion = sugg.getSuggestion();
							editOwnSuggListBox.addItem(suggestion, suggid); 
						}
					}
					
					else if(resp.contains("json_all_surveys")) {
						String newResp = resp.replace(",\"json_all_surveys\"]", "]");
						chooseChartDataArray = getChooseSurveyArray(newResp);
						for (int i = 0; i < chooseChartDataArray.length(); i++) {
							ChooseSurvey survey = chooseChartDataArray.get(i);
							String id = survey.getSurveyID();
							String title = survey.getTitle();
							chooseChartListBox.addItem(title, id);
						}
					}
					
					else if(resp.contains("json_chart_data")) {
						String newResp = resp.replace(",\"json_chart_data\"]", "]");
						//Window.alert(newResp);
						chartDataArray = getDisplaySurveyArray(newResp);
						DisplaySurvey chartData = chartDataArray.get(0);
						//Window.alert(newResp);
						chartSuggLabel.setText(chartData.getSuggestion());					
						Runnable onLoadCallback = new Runnable()
						{
							public void run()
							{
								
								//AreaChart initialChart = new AreaChart(initialCreateTable(),initialCreateOptions());
								ColumnChart initialChart = new ColumnChart(initialCreateTable(),initialCreateOptions());
								//chartPanel.add(initialChart);
								displayChartPanel.add(initialChart);
								
								DisplaySurvey chartData = chartDataArray.get(0);
								String chartTitle = chartData.getQuestion();
								//Window.alert(chartTitle);
								if (chartTitle != null) {
									for (int i = 0;i < chartDataArray.length();i++) {
										DisplaySurvey cData = chartDataArray.get(i);
										String question = cData.getQuestion();
										if (i == 0) {
											//displayChartPanel.add(new AreaChart(createTable(i),createOptions(chartTitle)));
											displayChartPanel.add(new ColumnChart(createTable(i),createOptions(chartTitle)));
										}
										if (!question.equals(chartTitle)) {
											chartTitle = question;
											//displayChartPanel.add(new AreaChart(createTable(i),createOptions(chartTitle)));
											displayChartPanel.add(new ColumnChart(createTable(i),createOptions(chartTitle)));
										}
									}				
													
								}
								//for (int i = 0;i < chartDataArray.length();i++) {
								//	chartDataArray.get(i) = "";
								//}
							}
						};
						VisualizationUtils.loadVisualizationApi(onLoadCallback, AreaChart.PACKAGE);
						
					}
					
					
					else if (resp.contains("json_user_sugg_by_div")) {
						String newResp = resp.replace(",\"json_user_sugg_by_div\"]", "]");
						chooseSuggArray = getSurveySuggArray(newResp);
						ChooseSugg sugg = chooseSuggArray.get(chooseSuggArrayIndex);
						String suggestion = sugg.getSuggestion();
						while(suggestion.equals("")) {
							chooseSuggArrayIndex++;
							sugg = chooseSuggArray.get(chooseSuggArrayIndex);
							suggestion = sugg.getSuggestion();
						}
						sugg = chooseSuggArray.get(chooseSuggArrayIndex);
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
							//displaySurveyPanel.add(displaySurveySubmitButton);
							displaySurveyPanel.add(noQuestionSubmitButton);
							displaySurveyPanel.setCellHorizontalAlignment(noQuestionSubmitButton, HasHorizontalAlignment.ALIGN_CENTER);
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
					
					else if (resp.contains("json_all_suggs_for_celltable")) {
						String newResp = resp.replace(",\"json_all_suggs_for_celltable\"]", "]");
						//Window.alert("hi");
						allSuggArray = getSurveySuggArray(newResp);
						ChooseSugg sugg = null;
						for (int i = 0; i < allSuggArray.length(); i++) {
							sugg = allSuggArray.get(i);
							allSuggs.add(new AllSuggs(sugg.getSuggestion(),sugg.getDep(),
								sugg.getDiv(),sugg.getCreatedAt(),sugg.getUpdatedAt()));
						}
						TextColumn<AllSuggs> suggColumn = new TextColumn<AllSuggs>()
						{
							@Override
							public String getValue(AllSuggs suggs)
							{
								return suggs.suggestion;
							} 
						};
						TextColumn<AllSuggs> deptColumn = new TextColumn<AllSuggs>()
						{
							@Override
							public String getValue(AllSuggs suggs)
							{
								return suggs.department;
							} 
						};
						
						TextColumn<AllSuggs> divColumn = new TextColumn<AllSuggs>()
						{
							@Override
							public String getValue(AllSuggs suggs)
							{
								return suggs.division;
							} 
						};	
						TextColumn<AllSuggs> createdAtColumn = new TextColumn<AllSuggs>()
						{
							@Override
							public String getValue(AllSuggs suggs)
							{
								return suggs.createdAt;
							} 
						};	
						TextColumn<AllSuggs> updatedAtColumn = new TextColumn<AllSuggs>()
						{
							@Override
							public String getValue(AllSuggs suggs)
							{
								return suggs.updatedAt;
							} 
						};
						suggsCellTable.setPageSize(5);
						suggsCellTable.addColumn(suggColumn, "Suggestion");
						suggsCellTable.addColumn(deptColumn, "Department");
						suggsCellTable.addColumn(divColumn, "Division");
						suggsCellTable.addColumn(createdAtColumn, "Created At");
						suggsCellTable.addColumn(updatedAtColumn, "Updated At");
						AsyncDataProvider<AllSuggs> suggProvider = new AsyncDataProvider<AllSuggs>() {
						      @Override
						      protected void onRangeChanged(HasData<AllSuggs> display) {
						        int start = display.getVisibleRange().getStart();
						        int end = start + display.getVisibleRange().getLength();
						        end = end >= allSuggs.size() ? allSuggs.size() : end;
						        List<AllSuggs> sub = allSuggs.subList(start, end);
						        updateRowData(start, sub);
						      }
						    };
						suggProvider.addDataDisplay(suggsCellTable);
						suggProvider.updateRowCount(allSuggs.size(), true);	
						viewAllSuggPanel.add(suggsCellTable);								
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
						//adminPanel.add(table);
						
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
					
					if (resp.contains("logged_in_no")) {
						loginUsernameBox.setText("");
						loginPasswordBox.setText("");
					}
					else if (resp.contains("user_type_is_divChair")) {
						String newResp = resp.replace("user_type_is_", "");
						RootPanel.get().clear();
						RootPanel.get().add(tabPanel);
						userType = newResp;
						//Window.alert(userType);
						tabPanel.remove(adminPanel);
						
					}
					else if (resp.contains("user_type_is_admin")) {
						String newResp = resp.replace("user_type_is_","");
						userType = newResp;
						RootPanel.get().clear();
						RootPanel.get().add(tabPanel);
						tabPanel.clear();
						tabPanel.add(adminPanel, "Admin");
										
					}
					
					else if (resp.contains("user_type_is_normal")) {
						String newResp = resp.replace("user_type_is_","");
						userType = newResp;	
						RootPanel.get().clear();
						RootPanel.get().add(tabPanel);
						tabPanel.remove(adminPanel);
						suggButtonPanel.remove(editDivSuggButton);
						surveyButtonPanel.remove(createSurveyButton);
					}
								
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

