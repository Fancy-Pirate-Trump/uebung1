package serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import fpt.com.Product;
import fpt.com.SerializableStrategy;

public class BinaryStrategy extends SerializableStrategyClass {
	InputStream input;
	OutputStream output;

	@Override
	public Product readObject() throws IOException, ClassNotFoundException {
		ObjectInput oi = new ObjectInputStream(input);

		Object product = oi.readObject();
		return (Product) product;
	}

	@Override
	public void writeObject(Product obj) throws IOException {
		ObjectOutput os = new ObjectOutputStream(output);

		os.writeObject(obj);

	}

	@Override
	public void close() throws IOException {
		input.close();
		output.close();

	}

	@Override
	public void open(InputStream input, OutputStream output) throws IOException {
		this.input=input;
		this.output=output;
	}

	@Override
	public String getFilename() {
		return "products.ser";
	}

	@Override
	public void giveValue(long value, long savedId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long giveId() {
		// TODO Auto-generated method stub
		return 0;
	}



}
