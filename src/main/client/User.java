package main.client;
import com.google.gwt.core.client.JavaScriptObject;

class User extends JavaScriptObject 
{
	protected User()
	{}
	
	public final native String getDepartment()
	/*-{
	  return this.user.department;
	}-*/;
	
	public final native String getDivision()
	/*-{
	  return this.user.division;
	}-*/;
	
	public final native String getFirstName()
	/*-{
	  return this.user.first_name;
	}-*/;
	
	public final native String getLastName()
	/*-{
	  return this.user.last_name;
	}-*/;
	
}
