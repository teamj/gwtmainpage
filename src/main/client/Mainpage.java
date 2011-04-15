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



public class Mainpage implements EntryPoint, ClickHandler
{
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
	String[][] surveyArray = new String[10][10];
	int surveyArrayIndex1 = 0;
	int surveyArrayIndex2 = 0;
	String pendingSurveyHTML = "";
        
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
        //getRequest("http://localhost:3000/users/sample");
        
        //okButton.addClickHandler(this);
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
		//textarea.setCharacterWidth(50);
		//textarea.setVisibleLines(25);
				
		adminButtonPanel.add(newUserButton);
		adminButtonPanel.add(viewUsersButton);
		adminPanel.add(adminButtonPanel);
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
		//tabPanel.add(adminPanel, "Admin");
		
		suggButtonPanel.add(makeSuggButton);
		suggPanel.add(suggButtonPanel);
		tabPanel.add(suggPanel, "Suggestions");
		
		surveyPanel.add(surveyButtonPanel);
		surveyButtonPanel.add(createSurveyButton);
		newSurveyPanel.add(surveyTitleRow);
		newSurveyPanel.add(newSurveyNextButton1);
		surveyTitleRow.add(surveyTitleLabel);
		surveyTitleRow.add(surveyTitleBox);
		surveyQuestionPanel.add(surveyQuestionLabel);
		surveyQuestionPanel.add(surveyQuestionArea);
		surveyQuestionPanel.add(newSurveyNextButton2);
		surveyOptionsPanel.add(surveyOptionsLabel);
		surveyOptionsPanel.add(surveyOptionsBox);
		surveyOptionsPanel.add(surveyButtonRow);
		surveyButtonRow.add(addQuestionButton);
		surveyButtonRow.add(addOptionButton);
		surveyButtonRow.add(submitSurveyButton);
		tabPanel.add(surveyPanel, "Surveys");
		
		RootPanel.get().add(loginPanel);
		
		//RootPanel.get().add(tabPanel);
		//String url = "http://localhost:3000/pages/welcome";
		//getRequest(url);
		
	}
	
	public String makePendingSurveyHTML()
	{
		pendingSurveyHTML = "<table border='1'><tr><th>PENDING SURVEY</th></tr><tr><th>Title:</th></tr>";
		if (surveyTitle == null){
				
		}
		else {
			pendingSurveyHTML += "<tr><td>" + surveyTitle + "</td></tr>";	
		}
		
		pendingSurveyHTML +=  "<tr><th>Suggestion:</th></tr><tr><th>Questions and Options:</th></tr>";
		
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
			adminPanel.add(displayUsers);
			getRequest(displayUsersURL);
			
		}
		
		else if (source == viewUsersButton) {
		    adminPanel.clear();
		    adminPanel.add(adminButtonPanel);
			adminPanel.add(displayUsers);
			getRequest(displayUsersURL);
		}
		
		else if (source == newUserSubmitButton) {
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
			String url = "http://localhost:3000/users/create";
			postRequest(url,encData);
		}
		
		else if (source == createSurveyButton) {
			surveyPanel.clear();
			surveyPanel.add(surveyButtonPanel);
			surveyPanel.add(newSurveyPanel);
			surveyPanel.add(pendingSurvey);
		}
		
		else if (source == newSurveyNextButton1) {
			surveyTitle = surveyTitleBox.getText();
			surveyTitleBox.setText("");
			surveyPanel.clear();
			surveyPanel.add(surveyButtonPanel);
			surveyPanel.add(surveyQuestionPanel);
			pendingSurvey.setHTML(makePendingSurveyHTML());
			surveyPanel.add(pendingSurvey);
			
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
			//PASS ARRAY TO RAILS FIRST
			surveyTitle = null;
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
					displayUsers.setHTML(response.getText());
										
					//textarea.setText(response.getText());
					
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
					int id = Integer.parseInt(resp);
					if (id > 0) {
						RootPanel.get().clear();
						RootPanel.get().add(tabPanel);
					}
					else {
						loginUsernameBox.setText("");
						loginPasswordBox.setText("");
					}
					
					//displayUsers.setUrl(displayUsersURL);
					//String url1 = "http://localhost:3000/pages/welcome";
					//getRequest(url1);
					
					
					getRequest(displayUsersURL);
				
				}
			});
		}
		catch (final Exception e) {
			Window.alert(e.getMessage());
		}
	} // end postRequst

}

