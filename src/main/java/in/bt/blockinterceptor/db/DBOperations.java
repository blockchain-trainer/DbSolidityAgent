package in.bt.blockinterceptor.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import in.bt.blockinterceptor.utils.CommonConstants;

/**
 * Blockchain Trainer (www.blockchaintrainer.in)
 * 
 * This is start blockchain spring boot class
 * it adds four blocks after setting the mining difficulty level 
 */


@Component
public class DBOperations {

	public Connection connectMySql(String url, String user, String password) throws ClassNotFoundException, SQLException {
		Class.forName(CommonConstants.MYSQL_DRIVER_CLASS);	
		Connection connection = DriverManager.getConnection(url, user, password);
		return connection;

	}
	
	public List<String> getTables(String schema, Connection connection) throws SQLException {
		List<String> tables = new ArrayList<String>();
		DatabaseMetaData md = connection.getMetaData();
		ResultSet rs = md.getTables(schema, null, "%", null);
		while (rs.next()) {
			tables.add(rs.getString(3));
		}
		return tables;
	}
	
	public Map<String, String> getColumns(Connection connection, String schema, String tableName) throws SQLException {
		Map<String, String> columns = new HashMap<String, String>();
		
		DatabaseMetaData meta = connection.getMetaData();
		ResultSet rsColumns = meta.getColumns(null, null, tableName, null);
	    while (rsColumns.next()) {
	    	columns.put(rsColumns.getString(CommonConstants.COLUMN_NAME), rsColumns.getString(CommonConstants.TYPE_NAME));
	    }
	    rsColumns = meta.getPrimaryKeys(schema, null, tableName);
	    while (rsColumns.next()) {
	    	String cName = rsColumns.getString(CommonConstants.COLUMN_NAME);
	    	columns.put(CommonConstants.UNDERSCORE+cName, columns.get(cName));
	    	columns.remove(cName);
	    	
	    }
		return columns;
	}
	
	

}
