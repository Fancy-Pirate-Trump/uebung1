package application;

import java.util.ArrayList;
import java.util.Iterator;

import fpt.com.Product;

public class Order extends ArrayList<Product> implements fpt.com.Order {
	/**
	 *
	 */
	private static final long serialVersionUID = -6631413930636044757L;

	@Override
	public Iterator<Product> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Product e) {
		return super.add(e);
	}

	@Override
	public boolean delete(Product p) {
		// TODO ayyyy
		return false;
	}

	@Override
	public int size() {
		return super.size();
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

	@Override
	public double getSum() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getQuantity() {
		// TODO Auto-generated method stub
		return 0;
	}

}
