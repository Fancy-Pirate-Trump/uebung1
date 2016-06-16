package serialization;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import fpt.com.Product;

public class XMLStrategy extends SerializableStrategyClass {
	InputStream input;
	OutputStream output;
	XMLEncoder xmlEncoder;
	XMLDecoder xmlDecoder;

	@Override
	public Product readObject() throws IOException, ClassNotFoundException{

		Object product = xmlDecoder.readObject();
		return (Product) product;
	}

	@Override
	public void writeObject(Product obj) throws IOException {

			xmlEncoder.writeObject(obj);

	}

	@Override
	public void close() throws IOException {
		xmlEncoder.flush();

		if(input != null) input.close();
		if(output != null) output.close();

	}

	@Override
	public void open(InputStream input, OutputStream output) throws IOException {
		/* TODO Ich bin mir nicht sicher ob das so sein soll,
		 * Aufgabenstellung und Interface sind hier ziemlich vage.
		*/
		this.input = input;
		this.output = output;
		if(output != null) xmlEncoder = new XMLEncoder(output);
		if(input != null) xmlDecoder = new XMLDecoder(input);
	}

	@Override
	public String getFilename() {
		return "products.xml";
	}

	@Override
	public void giveValue(long value,long savedId) {
		// TODO Auto-generated method stub

	}

	@Override
	public long giveId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
