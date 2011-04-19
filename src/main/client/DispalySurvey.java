package main.client;
import com.google.gwt.core.client.JavaScriptObject;

class DispalySurvey extends JavaScriptObject
{
	protected DispalySurvey()
	{}
	
	public final native String getSuggestion()
	/*-{
	  return this.take_survey.suggestion;
	}-*/;
	
	public final native String getSurveyID()
	/*-{
	  return this.take_survey.surveyid;
	}-*/;
	
	public final native String getQuestion()
	/*-{
	  return this.take_survey.question_text;
	}-*/;
	
	public final native String getQuestionID()
	/*-{
	  return this.take_survey.questionid;
	}-*/;
	
	public final native String getOption()
	/*-{
	  return this.take_survey.option_text;
	}-*/;
}
