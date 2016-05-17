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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ViewShop extends HBox {
	private ListView<Product> products;
	private VBox vBox;
	private Label nameLabel, priceLabel, countlabel;
	private TextField nameField, priceField, countField;
	private HBox buttonRow, optionRow;
	private Button add, delete, save, load;
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
		delete = new Button("Delete");
		save = new Button("Save");
		load = new Button("Load");
		optionRow = new HBox();
		strategySelection = new ComboBox<String>();
		borderPane = new BorderPane();

		borderPane.setTop(optionRow);
		borderPane.setLeft(products);
		borderPane.setRight(vBox);
		this.getChildren().addAll(borderPane);
		vBox.getChildren().addAll(nameLabel, nameField, priceLabel, priceField, countlabel, countField, buttonRow);
		buttonRow.getChildren().addAll(add, delete);
		optionRow.getChildren().addAll( save, load, strategySelection);

		ObservableList<String> strategies = FXCollections.observableArrayList("Binary", "XML", "XStream");
		strategySelection.setItems(strategies);

		products.setCellFactory(e -> {
			ListCell<Product> cell = new ListCell<Product>() {
				@Override
				protected void updateItem(Product myObject, boolean b) {
					super.updateItem(myObject, myObject == null || b);
					if (myObject != null) {
						setText(myObject.getName() + " | " + myObject.getPrice() + " €  | " + myObject.getQuantity());
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
				catch (Exception e){};
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

		save.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				cs.save();
			}

		});

		load.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				cs.load();
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
