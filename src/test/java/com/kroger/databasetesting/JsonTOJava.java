package com.kroger.databasetesting;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gherkin.deps.com.google.gson.Gson;

public class JsonTOJava {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, JsonGenerationException, JsonMappingException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		CustomerDetails c = new CustomerDetails();
		JSONArray js = new JSONArray();
		//Create arrayList with the objects of CustomerDetails then add object inside while loop
		ArrayList<CustomerDetails> a = new ArrayList<CustomerDetails>();
		conn = DriverManager.getConnection("jdbc:mysql://54.147.152.199/Business", "service_dev", "IBecameACoder");

		// Object of statement class will help us to execute queries
		Statement st = conn.createStatement();
		ResultSet rs = st
				.executeQuery("select * from CustomerInfo where location = 45424 and purchaseDate = curdate();");

		while (rs.next()) {
		
//		System.out.println(rs.getInt(1));
			c.setProductID(rs.getInt(1));
//		System.out.println(rs.getString(2));
			c.setPurchaseDate(rs.getString(2));
//		System.out.println(rs.getInt(3));
			c.setCustomerID(rs.getInt(3));
//		System.out.println(rs.getInt(4));
			c.setAmount(rs.getInt(4));
//		System.out.println(rs.getInt(5));
			c.setLocation(rs.getInt(5));

//Above code takes value from DB and store using set methods
// Below we will simply retrieve information that has been already stored in the method
//			System.out.println(c.getProductID());
//			System.out.println(c.getPurchaseDate());
//			System.out.println(c.getCustomerID());
//			System.out.println(c.getAmount());
//			System.out.println(c.getLocation());
					
			
//Add object into the arrayList 
		a.add(c);
		}
		
		for (int i = 0; i < a.size(); i++) {
			ObjectMapper o = new ObjectMapper();
			o.writeValue(new File
					("C:\\Users\\uzeyir\\Desktop\\PortalFramework\\KrogerBDDFramework\\customerInfo" + i + ".json"), a.get(i));
			Gson g = new Gson();
			String jsonString = g.toJson(a.get(i));
			js.add(jsonString);
		}
		
		
		//Multiple Json in one file
		
		
		JSONObject jo = new JSONObject();
		jo.put("data", js);
		System.out.println(jo.toJSONString());
		
		
		conn.close();

	}
}
