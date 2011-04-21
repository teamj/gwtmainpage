package main.client;
import com.google.gwt.core.client.JavaScriptObject;

class DisplaySurvey extends JavaScriptObject
{
	protected DisplaySurvey()
	{}
	
	public final native String getSuggestion()
	/*-{
	  return this.take_survey.suggestion;
	}-*/;
	
	public final native String getStronglyAgree()
	/*-{
	  return this.take_survey.strongly_agree;
	}-*/;
	
	public final native String getAgree()
	/*-{
	  return this.take_survey.agree;
	}-*/;
	
	public final native String getNeutral()
	/*-{
	  return this.take_survey.neutral;
	}-*/;
	
	public final native String getDisagree()
	/*-{
	  return this.take_survey.disagree;
	}-*/;
	
	public final native String getStronglyDisagree()
	/*-{
	  return this.take_survey.strongly_disagree;
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
	
	public final native String getTimesChosen()
	/*-{
	  return this.take_survey.times_chosen;
	}-*/;
}