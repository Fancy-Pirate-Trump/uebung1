package application;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.*;

import org.apache.openjpa.persistence.*;
import org.apache.openjpa.persistence.jdbc.*;


@Entity()
@Table(name = "products")
public class Product implements fpt.com.Product, java.io.Externalizable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "products_SEQ ")
	private long id;

	@Persistent
	@Strategy ("fpt.com.db.StringPropertyValueHandler")
	private SimpleStringProperty name = new SimpleStringProperty();

	@Persistent
	@Strategy ("fpt.com.db.DoublePropertyValueHandler")
	private SimpleDoubleProperty price = new SimpleDoubleProperty();

	@Persistent
	@Strategy ("fpt.com.db.IntegerPropertyValueHandler")
	private SimpleIntegerProperty quantity = new SimpleIntegerProperty();


	private static final long serialVersionUID = 1546282835533874184L;

	public Product(){

	}

	public Product(String name, double price, int quantity){
		setPrice(price);
		setName(name);
		setQuantity(quantity);
	}

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
		return price.get();
	}

	@Override
	public void setPrice(double price) {
		this.price.set(price);
	}

	@Override
	public int getQuantity() {
		return quantity.get();
	}

	@Override
	public void setQuantity(int quantity) {
		this.quantity.set(quantity);
	}

	@Override
	public String getName() {
		return name.get();
	}

	@Override
	public void setName(String name) {
		this.name.set(name);
	}

	@Override
	public SimpleStringProperty nameProperty() {
		return name;
	}

	@Override
	public SimpleDoubleProperty priceProperty() {
		return price;
	}

	@Override
	public SimpleIntegerProperty quantityProperty() {
		return quantity;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeLong(getId());
		out.writeObject(getName());
		out.writeDouble(getPrice());
		out.writeInt(getQuantity());


	}

	public void writeObject(ObjectOutput out) throws IOException{
		writeExternal(out);
	}

	public void readObject(ObjectInput in) throws IOException, ClassNotFoundException{
		readExternal(in);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		setId(in.readLong());
		setName((String)in.readObject());
		setPrice(in.readDouble());
		setQuantity(in.readInt());

	}

	@Override
	public Product clone(){
		Product p = null;
		try {
			p = (Product) super.clone();
			p.quantity = new SimpleIntegerProperty();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

}
