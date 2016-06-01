package gui;

import application.Product;
import serialization.BinaryStrategy;
import serialization.DataBaseStrategy;
import serialization.SerializableStrategyClass;
import serialization.XMLStrategy;
import serialization.XStreamStrategy;

public class ControllerShop {

	ViewShop vs;
	ModelShop ms;

	public void link(ModelShop ms, ViewShop vs) {
		vs.bindData(ms);
		vs.setCs(this);
		this.vs = vs;
		this.ms = ms;
	}

	public void add() throws Exception{
		Product product = new Product(vs.getNameField(), vs.getPriceField(), vs.getCountField());
		ms.add(product);

	}

	public void delete(int selected) {
		if (ms.isEmpty() == false) {
			ms.remove(selected);
		}
	}

	public void save(){
		setModelSerializableStrategy();
		ms.save();
	}

	public void load(){
		setModelSerializableStrategy();
		ms.load();
	}

	private void setModelSerializableStrategy(){
		String strategyName = vs.getSelectedStrategy();

		SerializableStrategyClass strategy = stringToSerializableStrategy(strategyName);
		ms.setSerializableStrategy(strategy);
	}

	private SerializableStrategyClass stringToSerializableStrategy(String s){
		switch(s){
		case "Binary": return new BinaryStrategy();
		case "XML": return new XMLStrategy();
		case "XStream" : return new XStreamStrategy();
		case "DataBase" : return new DataBaseStrategy();
		default: return null;
		}
	}
}
