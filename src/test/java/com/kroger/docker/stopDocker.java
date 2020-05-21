package com.kroger.docker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;




public class stopDocker {

	public void stopDocker() throws IOException, InterruptedException {
		boolean flag = false;
		Runtime runtime =  Runtime.getRuntime(); 
		runtime.exec("cmd /c start dockerDown.bat");
		String file = "output.txt";
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, 45);
		Long stopNow = cal.getTimeInMillis();
		
		while(System.currentTimeMillis() < stopNow) {
			if (flag) {
				break;
			}
			reader = new BufferedReader(new FileReader(file));
			String currentLine = reader.readLine();
			while(currentLine!=null && !flag)
		 {
			// ("Registered a node http://172.19.0.4:5555")
			if (currentLine.contains("selenium-hub exited")) {
				System.out.println("Shutdown completeed Successfully");
				flag = true;
				break;
			}
			currentLine = reader.readLine();

		}
			reader.close();
		}
		
		Assert.assertTrue(flag);
		File fi = new File("output.txt");
		if(fi.delete()) {
			System.out.println("File deleted successfully");
		}
	}
}
