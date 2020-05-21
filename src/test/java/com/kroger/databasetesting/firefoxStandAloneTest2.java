package com.kroger.databasetesting;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;



public class firefoxStandAloneTest2 {
public static void main(String[] args) throws MalformedURLException {
	
	
	
	//RemoveWebDriver class because we are not running our test in our local. We are running in cloud,
	// rvm or docker which are categorized as remotedrivers
	DesiredCapabilities cap = DesiredCapabilities.firefox();
	URL u = new URL("http://localhost:4444/wd/hub");
	RemoteWebDriver driver = new RemoteWebDriver(u, cap);
	driver.get("https://www.google.com/");
	System.out.println(driver.getTitle());
}
}
