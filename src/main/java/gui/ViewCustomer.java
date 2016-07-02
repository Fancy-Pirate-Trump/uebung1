package gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ViewCustomer extends VBox{
	private HBox split;
	private TableView table;
	private TableColumn nameCol;
	private TableColumn priceCol;
	private TableColumn quantCol;
	private ListView list;
	private Button buy;
	private Label timeLabel;
	private ControllerCustomer cc;
	private String time = "";

	public ViewCustomer(){
		super();
		split = new HBox();
		table = new TableView();
		nameCol = new TableColumn("Name");
		priceCol = new TableColumn("Price");
		quantCol = new TableColumn("BuyCount");
		list = new ListView();
		buy = new Button("Buy");
		timeLabel = new Label(time);

		this.getChildren().add(split);
		split.getChildren().add(list);
		split.getChildren().add(table);
		
		this.getChildren().add(timeLabel);
		this.getChildren().add(buy);
		table.setEditable(true);
		table.getColumns().addAll(nameCol, priceCol, quantCol);
	}

	public void setCc(ControllerCustomer cc) {
		this.cc = cc;
	}
	public void setTime(String time){
		this.time = time;
	}
}
