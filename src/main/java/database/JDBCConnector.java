package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Product;

public class JDBCConnector {
	private DatabaseMetaData metaData;
	private Connection connection;

	public String getConnectionURL() {
		try {
			return metaData.getURL();
		}

		catch (SQLException sqle) {
			sqle.printStackTrace();
			return "https://www.google.de/";
		}
	}

	public String getConnectionUserName() {
		try {
			return metaData.getUserName();
		}

		catch (SQLException sqle) {
			sqle.printStackTrace();
			return "";
		}
	}

	public String getConnectionTableNames() {

		String tableNameQuery = "SELECT table_name FROM information_schema.tables WHERE table_schema='public' AND table_type='BASE TABLE'; ";
		String names = "";
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(tableNameQuery);) {

			while (resultSet.next()) {
				String nameVal = resultSet.getString("table_name");
				names += nameVal + "\n";
			}
			return names;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";

	}


	public long insert(String name, double price, int quantity){
		long id;
		return id;
	}
//	Achten Sie beim erstellen des Statements auf das Vorhandensein des Parameters
//	Statement.RETURN_GENERATED_KEYS.
//	INSERT INTO products(name,price,quantity) VALUES (?,?,?)
	public void insert(Product product){

	}
//	Nach dem erfolgreichen Einfügen in die Datenbank soll das Produkt die von der Datenbank
//	generierte Id übernehmen
	public Product read(long productId){
		try(PreparedStatement statement = connection.createStatement())
		return 	product;
	}
//	SELECT id,name,price,quantity FROM products WHERE id=?

}
