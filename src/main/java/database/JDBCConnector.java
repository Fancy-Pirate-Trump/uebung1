package database;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class JDBCConnector {
	private DatabaseMetaData metaData;

	public String getConnectionURL(){
		try {
			return metaData.getURL();
		}

		catch(SQLException sqle) {
			sqle.printStackTrace();
			return "";
		}
	}

	public String getConnectionUsername(){
		try {
			return metaData.getUserName();
		}

		catch(SQLException sqle) {
			sqle.printStackTrace();
			return "";
		}
	}
}
