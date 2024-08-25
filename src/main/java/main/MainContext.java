package main;

import utils.UtilityClass;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class MainContext implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		UtilityClass.print("------------------------------------------>	Startup Web Application	<------------------------------------------");

		ServletContext context = sce.getServletContext();
		DataSource ds = null;

		try {
			Context inContext = new InitialContext();
			Context envContext = (Context) inContext.lookup("java:comp/env");

			ds = (DataSource) envContext.lookup("jdbc/freedb_hpblog_db");


			try {
				Connection con = null;
				try {
					con = ds.getConnection();
					DatabaseMetaData metaData = con.getMetaData();
					UtilityClass.print("---------->	DMS name: " + metaData.getDatabaseProductName() + "	<----------");
					UtilityClass.print("---------->	DMS version: " + metaData.getDatabaseProductVersion() + "	<----------");

				} finally {
					if (con != null) {
						con.close();
						UtilityClass.print("----------------------->	CONNESSIONE CHIUSA	<-----------------------");
					}
				}
			} catch (SQLException e) {
				UtilityClass.print(e);
			}

		} catch (NamingException e) {
			UtilityClass.print(e);
		}

		context.setAttribute("DataSource", ds);

		//set Navigator


		UtilityClass.print("Creazione DataSource: "+ ds.toString());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		
		context.removeAttribute("DataSource");
		
		UtilityClass.print("Shutdown Web Application");
	}

}
