package gui;

import application.Order;
import application.Product;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewCustomer extends VBox {
	private HBox split;
	private ListView<Product> table;
	private TableColumn nameCol;
	private TableColumn priceCol;
	private TableColumn quantCol;
	private ListView<fpt.com.Product> list;
	private Button buy, enter, select;
	private Label timeLabel, passwordLabel, nameLabel;
	private ControllerCustomer cc;
	private Stage loginStage;
	private Scene loginScene;
	private VBox loginBox, login;
	private BorderPane borderPane;
	private TextField name;
	private PasswordField password;
	private Order order;

	public ViewCustomer() {
		super();
		split = new HBox();
		table = new ListView();
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
		password = new PasswordField();
		password.setPromptText("Password");
		name = new TextField();
		name.setPromptText("Username");
		passwordLabel = new Label("Password");
		nameLabel = new Label("Name");
		enter = new Button("Enter");
		select = new Button("Select");
		order = new Order();

		login.getChildren().addAll(nameLabel, name, passwordLabel, password, enter);
		borderPane.setCenter(login);
		loginBox.getChildren().add(borderPane);

		this.getChildren().add(split);

		split.getChildren().add(list);
		split.getChildren().add(table);

		this.getChildren().add(timeLabel);

		this.getChildren().add(buy);
		this.getChildren().add(select);
		// mache VBox unten
		table.setEditable(true);
		// table.getColumns().addAll(nameCol, priceCol, quantCol);

		buy.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				loginStage.setScene(loginScene);
				loginStage.show();
			}

		});
		enter.setOnAction((s) -> {
			order.setName(name.getText());
			order.setPassword(password.getText());
			cc.buy(order);
			loginStage.close();
			order=new Order();
			table.getItems().removeAll();
			table.refresh();
		});

		select.setOnAction((s) -> {
			Product listProduct = (Product) list.getSelectionModel().getSelectedItem();
			if (listProduct.getQuantity() >= 1) {
				Product tableProduct = new Product(listProduct.getName(), listProduct.getPrice(), 1);
				order.add(tableProduct);
				table.getItems().add(tableProduct);
				// Verändere Quantity wenn es das schon gibt
				listProduct.setQuantity(listProduct.getQuantity() - 1);
				list.refresh();
				cc.vs.products.refresh();
			}

		});

		list.setCellFactory(e -> {
			ListCell<fpt.com.Product> cell = new ListCell<fpt.com.Product>() {
				@Override
				protected void updateItem(fpt.com.Product myObject, boolean b) {
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

		table.setCellFactory(e -> {
			ListCell<Product> cell = new ListCell<Product>() {
				@Override
				protected void updateItem(Product myObject, boolean b) {
					super.updateItem(myObject, myObject == null || b);
					if (myObject != null) {
						setText(myObject.getName() + " | " + myObject.getPrice() + " €");
					} else {
						setText("");
					}
				}
			};

			cell.setId("cell");
			return cell;
		});

	}

	public void setCc(ControllerCustomer cc) {
		this.cc = cc;
	}

	public void setTime(String time) {
		timeLabel.setText(time);
	}

	public void bindData(ModelShop model) {
		list.setItems(model);
	}
}
