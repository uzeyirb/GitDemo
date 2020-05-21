package com.kroger.docker;

import java.io.BufferedReader;
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




public class startDocker {

	
	public void startDocker() throws IOException, InterruptedException {
		boolean flag = false;
		Runtime runtime =  Runtime.getRuntime(); 
		runtime.exec("cmd /c start dockeroup.bat");
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
			if (currentLine.contains("Registered a node")) {
				System.out.println("Registered a node found in output");
				flag = true;
				break;
			}
			currentLine = reader.readLine();

		}
			reader.close();
		}
		
		Assert.assertTrue(flag);
		runtime.exec("cmd /c start scale.bat");
		Thread.sleep(20000);
	}
}
