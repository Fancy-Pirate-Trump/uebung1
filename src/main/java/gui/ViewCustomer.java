package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewCustomer extends VBox{
	private HBox split;
	private TableView table;
	private TableColumn nameCol;
	private TableColumn priceCol;
	private TableColumn quantCol;
	private ListView list;
	private Button buy, enter;
	private Label timeLabel, passwordLabel, nameLabel;
	private ControllerCustomer cc;
	private Stage loginStage;
	private Scene loginScene;
	private VBox loginBox,login;
	private BorderPane borderPane;
	private TextField password, name;

	public ViewCustomer(){
		super();
		split = new HBox();
		table = new TableView();
		nameCol = new TableColumn("Name");
		priceCol = new TableColumn("Price");
		quantCol = new TableColumn("BuyCount");
		list = new ListView();
		buy = new Button("Buy");
		timeLabel = new Label("jkljklsdfjkl");
		borderPane = new BorderPane();
		loginBox = new VBox();
		loginStage = new Stage();
		loginScene = new Scene(loginBox);
		login = new VBox();
		password = new TextField();
		password.setPromptText("Password");
		name = new TextField();
		name.setPromptText("Username");
		passwordLabel = new Label("Password");
		nameLabel = new Label("Name");
		enter = new Button("Enter");

		login.getChildren().addAll(nameLabel,name,passwordLabel,password, enter);
		borderPane.setCenter(login);
		loginBox.getChildren().add(borderPane);


		this.getChildren().add(split);

		split.getChildren().add(list);
		split.getChildren().add(table);

		list.getItems().add(timeLabel);

		this.getChildren().add(buy);
		table.setEditable(true);
		table.getColumns().addAll(nameCol, priceCol, quantCol);



		buy.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				loginStage.setScene(loginScene);
				loginStage.show();
			}

			});
		enter.setOnAction((s)->{
				boolean success = cc.login(name.getText(),password.getText());
				if(success){
					System.out.println("Blub");
				//cc.buy;
					loginStage.close();
				}
				else{
					password.setText("");
					name.setText("");
				}
		});
	}

	public void setCc(ControllerCustomer cc) {
		this.cc = cc;
	}
	public void setTime(String time){
		 timeLabel.setText(time);
	}
}
