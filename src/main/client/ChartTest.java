package main.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.Selection;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.events.SelectHandler;
import com.google.gwt.visualization.client.visualizations.AreaChart;
import
com.google.gwt.visualization.client.visualizations.AreaChart.Options;
/**
* Entry point classes define <code>onModuleLoad()</code>.
*/
@SuppressWarnings("deprecation")
public class ChartTest implements EntryPoint {
	VerticalPanel questionPanel = new VerticalPanel();
	public void onModuleLoad()
	{
		Label q1 = new Label("1. Rate ability on word processing");
		Label q2 = new Label("2. Rate ability on databases");
		Label q3 = new Label("3. Rate ability on using e-mail");
		Label q4 = new Label("4. Rate ability on spreadsheets");
		questionPanel.add(q1);
		questionPanel.add(q2);
		questionPanel.add(q3);
		questionPanel.add(q4);
		RootPanel.get().add(questionPanel);
		Runnable onLoadCallback = new Runnable()
		{
			public void run()
			{
				Panel panel = RootPanel.get();
				AreaChart chart =
					new AreaChart(createTable(),createOptions());
				panel.add(chart);
			}
		};
		VisualizationUtils.loadVisualizationApi(
				onLoadCallback, AreaChart.PACKAGE);
	}
	private Options createOptions()
	{
		Options options = Options.create();
		options.setWidth(400);
		options.setHeight(240);
		options.setTitle("Knowledge Survey");
		return options;
	}
	private AbstractDataTable createTable()
	{
		DataTable data = DataTable.create();
		data.addColumn(ColumnType.STRING,"Question");
		data.addColumn(ColumnType.NUMBER,"Post");
		data.addColumn(ColumnType.NUMBER,"Pre");
		data.addRows(4);
		data.setValue(0, 0, "1");
		data.setValue(0,1,4.2);
		data.setValue(0,2,3.9);
		data.setValue(1, 0, "2");
		data.setValue(1,1,1.8);
		data.setValue(1,2,2);
		data.setValue(2,0,"3");
		data.setValue(2,1,4);
		data.setValue(2,2,3.5);
		data.setValue(3,0,"4");
		data.setValue(3,1,4.1);
		data.setValue(3,2,2.6);
		return data;
	} // createTable() ends
}
	