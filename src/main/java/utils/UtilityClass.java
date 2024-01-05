package utils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class UtilityClass {
	
	public static Logger logger = Logger.getLogger("global");
	
	public static void print(String... messages) {
		String message = "";
		for(String s : messages) {
			message += s+"\n";
		}
		logger.info(message);
	}
	
	public static void print(Exception exception) {
		UtilityClass.print("Exception: " + exception.getMessage());
	}

	/********************************************************/
	/*	FUNZIONE AD HOC PER IL RESET DI AUTO_INCREMENT		*/
	/********************************************************/
	public static void resetAuto_increment(String tabella, DataSource ds) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null, ps = null;
		String sql = "SELECT max(id) as max FROM " + tabella;
		String sql1 = " ALTER TABLE " + tabella + " AUTO_INCREMENT = ?";

		try {

			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			ps = connection.prepareStatement(sql1);

			ResultSet rs = preparedStatement.executeQuery();
			int val=0;

			while(rs.next()) {
				val = rs.getInt("max");
				utils.UtilityClass.print("Id massimo: " + val + " della tabella:" + tabella + "\n");
			}

			ps.setInt(1, val+1);
			ps.executeUpdate();

		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
			if(ps != null)
				ps.close();
			if(connection != null)
				connection.close();
		}

	}
}
