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
	private long savedId;

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

	/* Für JDBC übergibt die Methode die Anzal der Produkte und die
	 * höchste Id, damit ein Zähler läuft und nur die Produkte
	 * hinzugefügt werden, die vorher eingegeben wurden
	 */
	public void load() {
		File file = new File(strategy.getFilename());
		try {
			Product temp = null;
			FileInputStream fis = new FileInputStream(file);
			strategy.open(fis, null);
			strategy.giveValue(size(), savedId);

			//solange das Produkt aus readObject nicht null ist wird hinzugefügt

			while((temp = strategy.readObject()) != null){
				add(temp);
				};
			strategy.close();
		} catch (Exception e) {}
	}

	/*Für JDBC speichert hier die Methode sowohl die Anzahl der Produkte,
	 * als auch die höchste Id, damit man die Liste vorher leeren kann.
	 */
	public void save() {
		File file = new File(strategy.getFilename());
		try {
			FileOutputStream fos = new FileOutputStream(file);
			strategy.open(null, fos);
			for(Product prod: this) strategy.writeObject(prod);
			savedId = this.saveId(strategy.giveId());
			strategy.close();
		} catch(IOException e){
			e.printStackTrace();
		}

	}

	/*speichert die Id aus der Strategy. Musste damit es allgemein bleibt(strategy.//)
	 * in jede Strategy die Methode einbauen
	 */
	public long saveId(long id){
		long savedId = id;
		return savedId;
	}

}
