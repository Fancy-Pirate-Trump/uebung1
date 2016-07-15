package gui;

import application.Order;

public class ControllerCustomer {

	ViewCustomer vc;
	ModelCustomer mc;
	public ViewShop vs;

	public void link(ViewCustomer vc, ModelCustomer mc, ModelShop ms, ViewShop vs) {
		vc.setCc(this);
		this.mc = mc;
		this.vc = vc;
		this.vs = vs;
		vc.bindData(ms);

		new Thread(new TimeGetter(vc)).start();;
	}


	public void buy(Order order){
		mc.buy(order);
	}


}

