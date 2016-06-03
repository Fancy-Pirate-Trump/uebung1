package serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import database.JDBCConnector;
import fpt.com.Product;
	//Für eine Begrenzung der Ergebnisse: Statement.setMaxRows(int maxRows);
public class DataBaseStrategy extends SerializableStrategyClass{
	private JDBCConnector jcon;
	
	@Override
	public Product readObject() throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("hier im readobject");
		return jcon.read(1);
	}

	@Override
	public void writeObject(Product obj) {
		// TODO Auto-generated method stub
		
		jcon.insert((application.Product) obj);
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void open(InputStream input, OutputStream output) {
		// TODO Auto-generated method stub
		jcon = new JDBCConnector();
	}

	@Override
	public String getFilename() {
		// TODO Auto-generated method stub
		return "database";
	}

}
