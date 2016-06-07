package serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import database.JDBCConnector;
import fpt.com.Product;
import gui.ModelShop;
	//Für eine Begrenzung der Ergebnisse: Statement.setMaxRows(int maxRows);
public class DataBaseStrategy extends SerializableStrategyClass{
	private JDBCConnector jcon;
	private long id;
	private long count;

	public DataBaseStrategy(){

		jcon = new JDBCConnector();

	}

	/* wenn die höchste Id noch nicht erreicht ist wird ein neues
	 * Produkt hinzugefügt und der Zähler einen höher gesetzt
	 * sonst wird null ausgegeben, dann stoppt das Hinzufügen
	 */

	@Override
	public Product readObject() throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		if(count <= id){
			return jcon.read(count++);
		}else
			return null;
	}

	@Override
	public void writeObject(Product obj) {
		// TODO Auto-generated method stub

		jcon.insert((application.Product) obj);
	}

	@Override
	public void close() throws IOException {
		jcon.close();

	}

	@Override
	public void open(InputStream input, OutputStream output) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getFilename() {
		// TODO Auto-generated method stub
		return "database";
	}

	/* Übergabe der höchsten Id und der Anzahl der Produkte
	 * somit kann der Zähler laufen, der die Produkte einfügt
	 */

	@Override
	public void giveValue(long value, long savedId) {
		// TODO Auto-generated method stub
		this.id = savedId;
		this.count = (savedId-value)+1;
	}

	//übergabe der höchsten Id vom JDBC Connector

	@Override
	public long giveId() {
		// TODO Auto-generated method stub
		return jcon.getHighestId();

	}

}
