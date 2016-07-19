package chat;

import java.rmi.RemoteException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StandaloneClient implements Runnable{
	static Scanner scanner;
	private ChatClient client;
	private HBox chat;
	private BorderPane BPane;
	private VBox login, writeBoard;
	private Label nameLabel, username, writeLabel;
	private Button enter, sendText;
	private TextField name, writeField;
	private ListView<String> chatMessages;
	
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		chat = new HBox();
		BPane = new BorderPane();
		login = new VBox();
		name = new TextField();
		username = new Label("Username");
		writeLabel = new Label("Hier schreiben :");
		writeField = new TextField();
		sendText =  new Button("Send Text");
		writeBoard = new VBox();
		
		writeBoard.getChildren().addAll(writeLabel, writeField, sendText);
		
		name.setPromptText("Name hier eintragen");
		nameLabel = new Label("Enter name:");
		enter = new Button("Login");
		
		chatMessages = new ListView<String>();
		
		BPane.setTop(username);
		BPane.setCenter(chatMessages);
		BPane.setBottom(writeBoard);
		
		login.getChildren().addAll(nameLabel, name, enter);
		
		chat.getChildren().add(BPane);
		
		
		Scene sceneLogin = new Scene(login);
		primaryStage.setScene(sceneLogin);
		
		Scene sceneChat = new Scene(chat);
		
		Stage secondaryStage = new Stage();
		secondaryStage.setScene(sceneChat);
		
		primaryStage.show();
		
		sendText.setOnAction((e)->{

			try {
				client.sendToServer(writeField.getText());
				chatMessages.setItems(client.getMessages());
			} catch (RemoteException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		});
		
		enter.setOnAction((e)->{
			String localName = name.getText();
			if(!localName.isEmpty()){
				primaryStage.close();
				secondaryStage.show();
				
					client = null;
				try {
					client = new ChatClient(localName);
				} catch (RemoteException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				username.setText(localName);
				
			}
		});
	}
	
	public void run() {
		try {
			start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
