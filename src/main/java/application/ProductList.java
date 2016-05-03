package application;

import java.util.ArrayList;
import java.util.Iterator;

import fpt.com.Product;

public class ProductList extends ArrayList<Product> implements fpt.com.ProductList {

	/**
	 *
	 */
	private static final long serialVersionUID = 5149552401884277840L;


	@Override
	public Iterator<Product> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Product e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Product product) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Product findProductById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product findProductByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
