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

}
