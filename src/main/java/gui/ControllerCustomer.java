package gui;

import application.Order;

public class ControllerCustomer {

	ViewCustomer vc;
	ModelCustomer mc;

	public void link(ViewCustomer vc, ModelCustomer mc, ModelShop ms) {
		vc.setCc(this);
		this.mc = mc;
		this.vc = vc;
		vc.bindData(ms);

		new Thread(new TimeGetter(vc)).start();;
	}


	public void buy(Order order){
		mc.buy(order);
	}


}

