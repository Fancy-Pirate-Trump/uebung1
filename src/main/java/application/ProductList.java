package application;

import java.util.ArrayList;
import fpt.com.Product;

public class ProductList extends ArrayList<Product> implements fpt.com.ProductList {

	/**
	 *
	 */
	private static final long serialVersionUID = 5149552401884277840L;

	@Override
	public boolean delete(Product product) {
		return super.remove(product);
	}

	@Override
	public Product findProductById(long id) {
		for (Product i : this) {
			if (i.getId() == id) {
				return i;
			}
		}
		return null;
	}

	@Override
	public Product findProductByName(String name) {
		for (Product i : this) {
			if (i.getName().equals(name)) {
				return i;
			}
		}
		return null;
	}

}
