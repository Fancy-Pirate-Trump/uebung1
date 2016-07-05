package gui;

public class ControllerCustomer {
	
	ViewCustomer vc;
	ModelCustomer mc;

	public void link(ViewCustomer vc, ModelCustomer mc) {
		vc.setCc(this);
		this.mc = mc;
		this.vc = vc;
	}

	public void startService() {
		while(true){
		String time = mc.startDateService();
//		vc.setTime(time);
		System.out.println(time);
		}
	}
}

