package gui;

public class ControllerCustomer {
	
	ViewCustomer vc;
	ModelCustomer mc;

	public void link(ViewCustomer vc, ModelCustomer mc) {
		vc.setCc(this);
		this.vc = vc;
	}
	public void startService(){
		mc.startDateService();
	}
}
