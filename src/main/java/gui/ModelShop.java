package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import application.ProductList;
import fpt.com.Product;
import javafx.collections.ModifiableObservableListBase;

import serialization.SerializableStrategyClass;

public class ModelShop extends ModifiableObservableListBase<fpt.com.Product> {

	ProductList productList = new ProductList();
	SerializableStrategyClass strategy;
	private long sizeOfLastSave;
	private long savedId;
	private long time;

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

	/* F�r JDBC �bergibt die Methode die Anzal der Produkte und die
	 * h�chste Id, damit ein Z�hler l�uft und nur die Produkte
	 * hinzugef�gt werden, die vorher eingegeben wurden
	 */
	public void load() {
		time = System.currentTimeMillis();
		File file = new File(strategy.getFilename());
		try {
			Product temp = null;
			FileInputStream fis = new FileInputStream(file);
			strategy.open(fis, null);
			strategy.setCount(sizeOfLastSave, savedId);

			//solange das Produkt aus readObject nicht null ist wird hinzugef�gt
			while((temp = strategy.readObject()) != null){
				add(temp);
				};
			strategy.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		time = System.currentTimeMillis() - time;
		System.out.println("Das ganze dauerte : " + (float)time/1000 + " Sekunden.");
	}

	/*F�r JDBC speichert hier die Methode sowohl die Anzahl der Produkte,
	 * als auch die h�chste Id, damit man die Liste vorher leeren kann.
	 */
	public void save() {
		time = System.currentTimeMillis();
		File file = new File(strategy.getFilename());
		try {
			FileOutputStream fos = new FileOutputStream(file);
			strategy.open(null, fos);
			for(Product prod: this) strategy.writeObject(prod);
			savedId = strategy.getHighestId();
			sizeOfLastSave = this.size();
			strategy.close();
		} catch(IOException e){
			e.printStackTrace();
		}
		time = System.currentTimeMillis() - time;
		System.out.println("Das ganze dauerte : " + (float)time/1000 + " Sekunden.");
	}
}
