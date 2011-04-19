package main.client;
import com.google.gwt.core.client.JavaScriptObject;

class ChooseSurvey extends JavaScriptObject
{
	protected ChooseSurvey()
	{}
	
	public final native String getTitle()
	/*-{
	  return this.sugg_survey.survey_title;
	}-*/;
	
	public final native String getSurveyID()
	/*-{
	  return this.sugg_survey.id;
	}-*/;
}