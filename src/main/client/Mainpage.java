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
	String surveyTitle = "";
	String[] surveyArray = new String[30];
	int surveyArrayIndex1 = 0;
	int surveyArrayIndex2 = 0;
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
    HorizontalPanel surveyButtonRow = new HorizontalPanel();
    Button submitSurveyButton = new Button("Submit Survey");
    Button addQuestionButton = new Button("Add A Question");
    //VerticalPanel pendingSurvey = new VerticalPanel();
    //HorizontalPanel pendingSurveyChoices = new HorizontalPanel();
    HTML pendingSurvey = new HTML();
        
	public void onModuleLoad()
	{
        //okButton.addClickHandler(this);
		newUserButton.addClickHandler(this);
		newUserSubmitButton.addClickHandler(this);
		viewUsersButton.addClickHandler(this);
		createSurveyButton.addClickHandler(this);
		submitSurveyButton.addClickHandler(this);
		
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
								
		tabPanel.add(adminPanel, "Admin");
		
		suggButtonPanel.add(makeSuggButton);
		suggPanel.add(suggButtonPanel);
		tabPanel.add(suggPanel, "Suggestions");
		
		surveyPanel.add(surveyButtonPanel);
		surveyButtonPanel.add(createSurveyButton);
		newSurveyPanel.add(surveyTitleRow);
		newSurveyPanel.add(surveyButtonRow);
		surveyButtonRow.add(submitSurveyButton);
		surveyButtonRow.add(addQuestionButton);
		surveyTitleRow.add(surveyTitleLabel);
		surveyTitleRow.add(surveyTitleBox);
		tabPanel.add(surveyPanel, "Surveys");
		
		RootPanel.get().add(tabPanel);
		getRequest(displayUsersURL);
		//String url = "http://localhost:3000/pages/welcome";
		//getRequest(url);
		
	}
	
	public void onClick(ClickEvent event)
	{
		
		Object source = event.getSource();
		if (source == newUserButton) {
			adminPanel.clear();
			adminPanel.add(adminButtonPanel);
			adminPanel.add(newUserPanel);
			adminPanel.add(displayUsers);
			
		}
		
		else if (source == viewUsersButton) {
		    adminPanel.clear();
		    adminPanel.add(adminButtonPanel);
			adminPanel.add(displayUsers);
			//String url = "http://localhost:3000/users/index";
			//getRequest(url);
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
		
		else if (source == submitSurveyButton) {
			surveyTitle = surveyTitleBox.getText();
			String encData = URL.encode("survey_title") + "=" +
				URL.encode(surveyTitle) + "&";
			surveyTitleBox.setText("");
			pendingSurvey.setHTML("<table border='1'><tr>"+surveyTitle+"</tr><tr>  </tr>  </table>");
			//postRequest("http://localhost:3000/sugg_surveys/create", surveyTitle);	
		}
		
		/*
		if (source == okButton) {
			String encData = URL.encode("user_name") + "=" +
				URL.encode(usernameBox.getText()) + "&";
			encData += URL.encode("password") + "=" +
				URL.encode(passwordBox.getText()) + "&";
            String url = "http://localhost:3000/pages/login";
			postRequest(url,encData);
		}
		*/
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
					
					getRequest(displayUsersURL);
					//displayUsers.setUrl(displayUsersURL);
					//String url1 = "http://localhost:3000/pages/welcome";
					//getRequest(url1);
				
				}
			});
		}
		catch (final Exception e) {
			Window.alert(e.getMessage());
		}
	} // end postRequst

}
