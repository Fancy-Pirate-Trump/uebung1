package gui;

import application.Product;

public class ControllerShop {

	ViewShop vs;
	ModelShop ms;

	public void link(ModelShop ms, ViewShop vs) {
		vs.bindData(ms);
		vs.setCs(this);
		this.vs = vs;
		this.ms = ms;
	}

	public void add() {
		Product product = new Product(vs.getNameField(), vs.getPriceField(), vs.getCountField());
		ms.add(product);

	}

	public void delete(int selected) {
		if (ms.isEmpty() == false) {
			ms.remove(selected);
		}
	}

}
