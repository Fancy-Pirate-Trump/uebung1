package application;
import fpt.com.Product;
public class Order extends ProductList implements fpt.com.Order {

	private static final long serialVersionUID = -6631413930636044757L;

	@Override
	public double getSum() {
		double sum = 0;
		for (Product p : this) {
			sum += p.getPrice()*p.getQuantity();
		}
		return sum;
	}

	@Override
	public int getQuantity() {
		int quant = 0;
		for (Product p : this) {
			quant+=p.getQuantity();
		}
		return quant;
	}
	
	@Override
	public String toString(){
		String s = "";
		s += "================================================================================\n";
		for(Product p : this){
			s += p.getName() + "\t" + p.getPrice() + "\t"+ p.getQuantity() + "\n" ;
		}
		s += "================================================================================\n";
		s += "Gesamtanzahl: " + getQuantity() + "\n" + "Gesamtwert: " + getSum();
		return s;
	}

}
