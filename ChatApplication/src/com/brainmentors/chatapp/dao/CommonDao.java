package com.brainmentors.chatapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static com.brainmentors.chatapp.utils.ConfigReader.getValue;
//Throw early and catch later
public interface CommonDao {

	public static Connection createConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName(getValue("DRIVER"));
		final String CONNECTION_STRING=getValue("CONNECTION_URL");
		final String USER_ID=getValue("USERID");
		final String PASSWORD=getValue("PASSWORD");
		Connection con=DriverManager.getConnection(CONNECTION_STRING, USER_ID, PASSWORD);  //Drivermanager is class ... Connection is an interface
		if(con!=null)
		{
			System.out.println("Connection Created...");
			
		}
		return con;
	}
	
}
