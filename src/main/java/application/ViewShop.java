package application;

import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import fpt.com.Product;

public class ViewShop {
	
	VBox root = new VBox();
	
	private ListView<Product> products = new ListView<Product>();
	public void bindData(ModelShop model) {
		products.setItems(model);
	}
	

	public ViewShop(){
	products.setCellFactory(e->
	{
		  ListCell<Product> cell = new ListCell<Product>() {
		    @Override protected void updateItem(Product myObject, boolean b) {
		      super.updateItem(myObject, myObject == null || b);
			  if (myObject != null) {
			    setText(myObject.getName() + " | " + myObject.getPrice()+ " €  | " + myObject.getQuantity());
		      } else {
		        setText("");
		      }
		    }
		  };
		  return cell;
		});
	root.getChildren().addAll(products);
	root.setVisible(true);
	}
	
	public Node getRoot(){
		return root;
	}
	
}
