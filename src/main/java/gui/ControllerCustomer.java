package gui;

import javafx.application.Platform;

public class ControllerCustomer {

	ViewCustomer vc;
	ModelCustomer mc;

	public void link(ViewCustomer vc, ModelCustomer mc) {
		vc.setCc(this);
		this.mc = mc;
		this.vc = vc;

		new Thread(new TimeGetter(vc)).start();;
	}


}

