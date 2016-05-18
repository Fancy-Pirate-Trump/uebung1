package gui;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import application.ProductList;
import fpt.com.Product;
import fpt.com.SerializableStrategy;
import javafx.collections.ModifiableObservableListBase;
import serialization.IDGenerator;
import serialization.IDOverflowException;

public class ModelShop extends ModifiableObservableListBase<fpt.com.Product> {

	ProductList productList = new ProductList();
	IDGenerator idGenerator = new IDGenerator(1,999999);
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

	@SuppressWarnings("null")
	public void load() {
		FileInputStream input = null;
		FileOutputStream output = null;
		try{
			strategy.open(input, output);
			System.out.println("fip nach open" + input);
			System.out.println("fop nach open" + output);
			while(add(strategy.readObject())){
				;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				strategy.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void save() {
		FileOutputStream output = null;
		try{
			strategy.open((OutputStream)output);

			for(Product product : this){
				strategy.writeObject(product);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				strategy.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
