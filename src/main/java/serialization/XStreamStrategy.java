package serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.thoughtworks.xstream.XStream;

import fpt.com.Product;

public class XStreamStrategy extends SerializableStrategyClass {
	XStream xStream;
	InputStream input;
	OutputStream output;

	@Override
	public Product readObject() throws IOException, ClassNotFoundException {
		xStream = this.createXStream(Product.class);
		Object product = xStream.fromXML(input);
		return (Product) product;
	}

	@Override
	public void writeObject(Product obj) throws IOException {
		xStream = this.createXStream(Product.class);
		xStream.toXML(obj,output);
	}


	@Override
	public void close() throws IOException {
		input.close();
		output.close();

	}

	@Override
	public void open(InputStream input, OutputStream output) throws IOException {

		this.input = input;
		this.output = output;
	}

	@Override
	public String getFilename() {
		return "Xstream.xml";
	}

	@Override
	public void setCount(long value, long savedId) {
		// TODO Auto-generated method stub

	}

	@Override
	public long getHighestId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
