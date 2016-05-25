package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import application.ProductList;
import fpt.com.Product;
import fpt.com.SerializableStrategy;
import javafx.collections.ModifiableObservableListBase;
import serialization.IDGenerator;
import serialization.IDOverflowException;
import serialization.SerializableStrategyClass;

public class ModelShop extends ModifiableObservableListBase<fpt.com.Product> {

	ProductList productList = new ProductList();
	SerializableStrategyClass strategy;

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

	public void setSerializableStrategy(SerializableStrategyClass strategy){
		this.strategy = strategy;
	}

	public void load() {
		File file = new File(strategy.getFilename());
		try {
			FileInputStream fis = new FileInputStream(file);
			strategy.open(fis, null);
			while(add(strategy.readObject()));
			strategy.close();
		} catch (Exception e) {}
	}

	public void save() {
		File file = new File(strategy.getFilename());
		try {
			FileOutputStream fos = new FileOutputStream(file);
			strategy.open(null, fos);
			for(Product prod: this) strategy.writeObject(prod);;
			strategy.close();
		} catch (Exception e) {}

	}

}
