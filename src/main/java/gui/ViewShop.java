package gui;

import com.sun.prism.paint.Color;

import chat.StandaloneClient;
import fpt.com.Product;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ViewShop extends HBox {
	public ListView<Product> products;
	private VBox vBox;
	private Label nameLabel, priceLabel, countlabel, volumeSliderLabel;
	private TextField nameField, priceField, countField;
	private HBox buttonRow, optionRow;
	private Button add, delete, save, load, clear, chat;
	private ComboBox strategySelection;
	private BorderPane borderPane;
	private Slider volumeSlider;
	private ControllerShop cs;
	private MediaPlayer mediaPlayer;


	public ViewShop() {

		products = new ListView<Product>();
		products.setId("products");

		vBox = new VBox(2);
		vBox.setPadding(new Insets(0,-8,-9,2));

		Media m = new Media("http://a.pomf.cat/naozry.mp3");
		mediaPlayer = new MediaPlayer(m);
		mediaPlayer.setVolume(0);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();

		Image gif = new Image("http://a.pomf.cat/ykeqzq.gif");
		ImageView view = new ImageView(gif);
		view.setFitHeight(250);
		view.setFitWidth(250);
		view.setSmooth(true);
		view.setCache(true);

		nameLabel = new Label(" Name:");
		nameLabel.setId("font");

		priceLabel = new Label(" Price:");
		priceLabel.setId("font");

		countlabel = new Label(" Count:");
		countlabel.setId("font");

		nameField = new TextField();
		nameField.setPromptText("Produktname eingeben");
		priceField = new DoubleTextField();
		priceField.setPromptText("Preis eingeben");
		countField = new NumberTextField();
		countField.setPromptText("Anzahl eingeben");
		buttonRow = new HBox(2);
		optionRow = new HBox(2);
		optionRow.setPrefHeight(36);
		borderPane = new BorderPane();

		add = new Button("Add");
		add.setId("font-button");

		delete = new Button("Delete");
		delete.setId("font-button");

		save = new Button("Save");
		save.setId("font-button");

		load = new Button("Load");
		load.setId("font-button");

		clear = new Button("Clear");
		clear.setId("font-button");
		
		chat = new Button("Open Chat");
		chat.setId("font-button");

		volumeSlider = new Slider();
		volumeSlider.setId("font-slider");
		volumeSlider.setOrientation(Orientation.HORIZONTAL);
		volumeSliderLabel = new Label("Volume");
		volumeSliderLabel.setId("font-volume");
		volumeSlider.setShowTickLabels(true);
		volumeSlider.setShowTickMarks(true);

		strategySelection = new ComboBox<String>();
		strategySelection.setPromptText("Strategie");
		strategySelection.setId("font-optionRow");

		borderPane.setTop(optionRow);
		borderPane.setLeft(products);
		borderPane.setRight(vBox);
		this.getChildren().addAll(borderPane);
		vBox.getChildren().addAll(nameLabel, nameField, priceLabel, priceField, countlabel, countField, buttonRow, view);
		buttonRow.getChildren().addAll(add, delete, clear);
		optionRow.getChildren().addAll( save, load, strategySelection, volumeSliderLabel, volumeSlider, chat);

		ObservableList<String> strategies = FXCollections.observableArrayList("Binary", "XML", "XStream", "DataBase", "JPAStrategy");
		strategySelection.setItems(strategies);

		products.setCellFactory(e -> {
			ListCell<Product> cell = new ListCell<Product>() {
				@Override
				protected void updateItem(Product myObject, boolean b) {
					super.updateItem(myObject, myObject == null || b);
					if (myObject != null) {
						setText(myObject.getName() + " | " + myObject.getPrice() + " € | " + myObject.getQuantity());
					} else {
						setText("");
					}
				}
			};

			cell.setId("cell");
			return cell;
		});

		add.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try{
				cs.add();
				}
				catch (Exception e){
					System.out.println("Vergessen etwas einzutragen?");
				};
			}

		});

		delete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				int selected = products.getSelectionModel().getSelectedIndex();
				if (selected > -1) {
					cs.delete(selected);
				}
			}

		});

		clear.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				int size = products.getItems().size();
				for(int i = size-1; i >= 0; i--){
					cs.delete(i);
				}
			}
		});

		save.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try{
					cs.save();
				} catch(Exception e){
					System.out.println("Nichts zum Speichern vorhanden");
				}
			}

		});

		load.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try{
					cs.load();
				} catch(Exception e){
					System.out.println("Nichts zum Laden vorhanden");
				}
			}

		});

		nameField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			   @Override
			   public void handle(KeyEvent t) {
			       if (t.getCode() == KeyCode.ENTER) {
			    	        if(priceField.getText().isEmpty()) priceField.requestFocus();
			    	        else if(countField.getText().isEmpty()) countField.requestFocus();

				    	    if(!countField.getText().isEmpty()&&!priceField.getText().isEmpty()&&!nameField.getText().isEmpty())
				    	   		add.fire();
			       }
			       if(t.getCode() == KeyCode.UP) countField.requestFocus();
			       if(t.getCode() == KeyCode.DOWN) priceField.requestFocus();
			   }
		});

		priceField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			   @Override
			   public void handle(KeyEvent t) {
			       if (t.getCode() == KeyCode.ENTER) {
		    	   		if(countField.getText().isEmpty()) countField.requestFocus();
		    	   		else if(nameField.getText().isEmpty()) nameField.requestFocus();

			    	    if(!countField.getText().isEmpty()&&!priceField.getText().isEmpty()&&!nameField.getText().isEmpty())
		    	   		add.fire();
			       }
			       if(t.getCode() == KeyCode.UP) nameField.requestFocus();
			       if(t.getCode() == KeyCode.DOWN) countField.requestFocus();
			   }
		});

		countField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			   @Override
			   public void handle(KeyEvent t) {
			       if (t.getCode() == KeyCode.ENTER) {
		    	   		if(nameField.getText().isEmpty()) nameField.requestFocus();
		    	   		else if(priceField.getText().isEmpty()) priceField.requestFocus();

			    	    if(!countField.getText().isEmpty()&&!priceField.getText().isEmpty()&&!nameField.getText().isEmpty())
		    	   		add.fire();
			       }
			       if(t.getCode() == KeyCode.UP) priceField.requestFocus();
			       if(t.getCode() == KeyCode.DOWN) nameField.requestFocus();
			   }
		});

		volumeSlider.setValue(mediaPlayer.getVolume()*100);
		volumeSlider.valueProperty().addListener(new InvalidationListener(){

			@Override
			public void invalidated(Observable observable) {
				// TODO Auto-generated method stub
				mediaPlayer.setVolume(volumeSlider.getValue()/100);
			}

		});
		
		chat.setOnAction((f)->{
			new Thread(new StandaloneClient()).start();
		});

	}

	public void bindData(ModelShop model) {
		products.setItems(model);
	}

	public String getNameField() throws Exception{
		return nameField.getText();
	}

	public Double getPriceField() throws Exception{
		return Double.parseDouble(priceField.getText());
	}

	public Integer getCountField() throws Exception{
		return Integer.parseInt(countField.getText());
	}

	public ControllerShop getCs() {
		return cs;
	}

	public void setCs(ControllerShop cs) {
		this.cs = cs;
	}

	public String getSelectedStrategy(){
		return (String) strategySelection.getValue();
	}

}
