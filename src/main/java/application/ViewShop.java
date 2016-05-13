package application;

import fpt.com.Product;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ViewShop extends HBox {
	private ListView<Product> products;
	private VBox vBox;
	private Label nameLabel, priceLabel, countlabel;
	private TextField nameField, priceField, countField;
	private HBox buttonRow;
	private Button add, delete;
	private ControllerShop cs;

	public ViewShop() {

		products = new ListView<Product>();
		vBox = new VBox();
		nameLabel = new Label("Name:");
		priceLabel = new Label("Price:");
		countlabel = new Label("Count:");
		nameField = new TextField();
		priceField = new TextField();
		countField = new TextField();
		buttonRow = new HBox();
		add = new Button("Add");
		delete = new Button("Delete");

		this.getChildren().addAll(products, vBox);
		vBox.getChildren().addAll(nameLabel, nameField, priceLabel, priceField, countlabel, countField, buttonRow);
		buttonRow.getChildren().addAll(add, delete);

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
				cs.add();
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
	}

	public void bindData(ModelShop model) {
		products.setItems(model);
	}

	public String getNameField() {
		return nameField.getText();
	}

	public Double getPriceField() {
		return Double.parseDouble(priceField.getText());
	}

	public Integer getCountField() {
		return Integer.parseInt(countField.getText());
	}

	public ControllerShop getCs() {
		return cs;
	}

	public void setCs(ControllerShop cs) {
		this.cs = cs;
	}

}
