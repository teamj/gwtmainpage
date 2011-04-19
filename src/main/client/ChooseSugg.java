package main.client;
import com.google.gwt.core.client.JavaScriptObject;

class ChooseSugg extends JavaScriptObject
{
	protected ChooseSugg()
	{}
	
	public final native String getSuggestion()
	/*-{
	  return this.user_suggestion.suggestion;
	}-*/;
	
	public final native String getSuggestionID()
	/*-{
	  return this.user_suggestion.suggestionid;
	}-*/;
}
