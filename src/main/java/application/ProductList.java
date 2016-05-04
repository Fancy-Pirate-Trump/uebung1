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
		return super.iterator();
	}

	@Override
	public boolean add(Product e) {
		return super.add(e);
	}

	@Override
	public boolean delete(Product product) {
		return super.remove(product);
	}

	@Override
	public int size() {
		return super.size();
	}

	@Override
	public Product findProductById(long id) {
		for(Product i: this){
			if(i.getId() == id){
				return i;
			}
		}
		return null;
	}

	@Override
	public Product findProductByName(String name) {
		for(Product i: this){
			if(i.getName().equals(name)){
				return i;
			}
		}
		return null;
	}

}
