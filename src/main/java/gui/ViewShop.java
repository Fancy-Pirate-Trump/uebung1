package gui;

import fpt.com.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ViewShop extends HBox {
	private ListView<Product> products;
	private VBox vBox;
	private Label nameLabel, priceLabel, countlabel;
	private TextField nameField, priceField, countField;
	private HBox buttonRow, optionRow;
	private Button add, delete, save, load, clear;
	private ComboBox strategySelection;
	private BorderPane borderPane;
	private ControllerShop cs;

	public ViewShop() {

		products = new ListView<Product>();
		vBox = new VBox();
		nameLabel = new Label("Name:");
		priceLabel = new Label("Price:");
		countlabel = new Label("Count:");
		nameField = new TextField();
		priceField = new DoubleTextField();
		countField = new NumberTextField();
		buttonRow = new HBox();
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
		optionRow = new HBox();
		strategySelection = new ComboBox<String>();
		strategySelection.setId("font-optionRow");
		borderPane = new BorderPane();

		borderPane.setTop(optionRow);
		borderPane.setLeft(products);
		borderPane.setRight(vBox);
		this.getChildren().addAll(borderPane);
		vBox.getChildren().addAll(nameLabel, nameField, priceLabel, priceField, countlabel, countField, buttonRow);
		buttonRow.getChildren().addAll(add, delete, clear);
		optionRow.getChildren().addAll( save, load, strategySelection);

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
			   }
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
