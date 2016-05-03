package application;

import javafx.beans.value.ObservableValue;

public class Product implements fpt.com.Product {
	private long id;
	private double price;
	private String name;
	private int quantity;


	/**
	 *
	 */
	private static final long serialVersionUID = 1546282835533874184L;

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int getQuantity() {
		return quantity;
	}

	@Override
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public ObservableValue<String> nameProperty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObservableValue<Number> priceProperty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObservableValue<Number> quantityProperty() {
		// TODO Auto-generated method stub
		return null;
	}

}
