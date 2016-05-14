package serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import fpt.com.Product;
import fpt.com.SerializableStrategy;


public class BinaryStrategy implements SerializableStrategy {
	InputStream input;
	OutputStream output;

	@Override
	public Product readObject() throws IOException, ClassNotFoundException{
		ObjectInput oi = new ObjectInputStream(input);

		Object product = oi.readObject();
		return (Product) product;
	}

	@Override
	public void writeObject(Product obj) throws IOException {
		ObjectOutput os = new ObjectOutputStream(output);
		while(output != null){
			/* TODO Ich glaube nicht, dass product hier externalizable aufruft, macl sehen wie man das einstellen kann
			 * update: internet sagt das geht automatisch
			*/
			os.writeObject(obj);
		}

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

}
