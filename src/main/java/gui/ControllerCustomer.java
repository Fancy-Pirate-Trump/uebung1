package gui;

import application.Order;

public class ControllerCustomer {

	ViewCustomer vc;
	ModelCustomer mc;

	public void link(ViewCustomer vc, ModelCustomer mc) {
		vc.setCc(this);
		this.mc = mc;
		this.vc = vc;

		new Thread(new TimeGetter(vc)).start();;
	}

	public boolean login(String name, String password){
		return mc.login(name, password);
	}
	
	public void buy(Order order){
		mc.buy(order);
	}


}

