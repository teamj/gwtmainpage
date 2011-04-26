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
	
	public final native String getDiv()
	/*-{
	  return this.user_suggestion.division;
	}-*/;
	
	public final native String getDep()
	/*-{
	  return this.user_suggestion.department;
	}-*/;
	
	public final native String getCreatedAt()
	/*-{
	  return this.user_suggestion.created_at;
	}-*/;
	
	public final native String getUpdatedAt()
	/*-{
	  return this.user_suggestion.updated_at;
	}-*/;
}
