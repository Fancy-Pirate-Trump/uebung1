package gui;

import application.ProductList;
import fpt.com.Product;
import fpt.com.SerializableStrategy;
import javafx.collections.ModifiableObservableListBase;
import serialization.IDGenerator;
import serialization.IDOverflowException;

public class ModelShop extends ModifiableObservableListBase<fpt.com.Product> {

	ProductList productList = new ProductList();
	IDGenerator idGenerator = new IDGenerator();
	SerializableStrategy strategy;

	@Override
	public Product get(int index) {
		return productList.get(index);
	}

	@Override
	public int size() {
		return productList.size();
	}

	@Override
	protected void doAdd(int index, Product element) {
		try{
			element.setId(idGenerator.nextID());
		} catch(IDOverflowException e) {
			System.out.println("Anzahl Produkte überschreitet Anzahl IDs");
			e.printStackTrace();
		}

		productList.add(index, element);
	}

	@Override
	protected Product doSet(int index, Product element) {
		return productList.set(index, element);
	}

	@Override
	protected Product doRemove(int index) {
		return productList.remove(index);
	}

	public void setSerializableStrategy(SerializableStrategy strategy){
		this.strategy = strategy;
	}

	public void save() {
		System.out.println("Strategie: " + strategy);

	}

	public void load() {
		// TODO Auto-generated method stub

	}

}
