package application;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import fpt.com.Product;
import fpt.com.SerializableStrategy;

public class BinaryStrategy implements SerializableStrategy {
	InputStream input;
	OutputStream output;

	@Override
	public Product readObject() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeObject(Product obj) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void open(InputStream input, OutputStream output) throws IOException {
		;
		

	}

}
