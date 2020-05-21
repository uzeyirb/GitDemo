package com.kroger.databasetesting;

import static org.testng.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.testng.annotations.Test;

public class DataBaseTestingAutomation {
	
	@Test 
	
	public void getFirstFiveEmployees() {
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://54.147.152.199", "service_dev", "IBecameACoder");
			PreparedStatement preparedStatement = conn.prepareStatement("select * From classicmodels.employees Limit 5");
			ResultSet resultSet = preparedStatement.executeQuery();
				
				resultSet.next();
				assertEquals(resultSet.getString(1), "1002");
				assertEquals(resultSet.getString(3), "Diane");
				
				
				resultSet.next();
				assertEquals(resultSet.getString(1), "1056");
				assertEquals(resultSet.getString(3), "Mary");
				
			

		} catch (SQLException e) {
			e.getMessage();
			e.getStackTrace();
			e.getSQLState();
			e.printStackTrace();
		}
		
		
	}

	
}
