package application;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import fpt.com.Product;

public class ViewShop {
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
	}

}
