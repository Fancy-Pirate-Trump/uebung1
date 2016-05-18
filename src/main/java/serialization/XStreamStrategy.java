package serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.OutputStream;

import com.thoughtworks.xstream.XStream;

import fpt.com.Product;
import fpt.com.SerializableStrategy;

public class XStreamStrategy extends SerializableStrategyClass {
	XStream xStream;
	InputStream input;
	OutputStream output;

	@Override
	public Product readObject() throws IOException, ClassNotFoundException {
		xStream = this.createXStream(null);
		Object product = xStream.fromXML(input);
		return (Product) product;
	}

	@Override
	public void writeObject(Product obj) throws IOException {
		xStream = this.createXStream(null);
		xStream.toXML(obj,output);
	}


	@Override
	public void close() throws IOException {
		input.close();
		output.close();

	}

	@Override
	public void open(InputStream input, OutputStream output) throws IOException {
		/* TODO Ich bin mir nicht sicher ob das so sein soll,
		 * Aufgabenstellung und Interface sind hier ziemlich vage.
		*/
		this.input = input;
		this.output = output;
	}

	@Override
	public String getFilename() {
		return "Xstream.xml";
	}

}
