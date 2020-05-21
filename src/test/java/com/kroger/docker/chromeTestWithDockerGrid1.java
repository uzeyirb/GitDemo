package com.kroger.docker;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class chromeTestWithDockerGrid1 {
	static Capabilities chromeCapabilities = DesiredCapabilities.chrome();
	static Capabilities firefoxCapabilities = DesiredCapabilities.firefox();
@BeforeTest
public void startDockerScale() throws IOException, InterruptedException {
	startDocker s = new startDocker();
	s.startDocker();
}

@AfterTest
public void stopDockerDeleteFile() throws IOException, InterruptedException {
	stopDocker s = new stopDocker();
	s.stopDocker();
}
	@Test
	public void test1() throws MalformedURLException {

		// RemoveWebDriver class because we are not running our test in our local. We
		// are running in cloud,
		// rvm or docker which are categorized as remotedrivers
		RemoteWebDriver chrome = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeCapabilities);
		RemoteWebDriver firefox = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxCapabilities);

		// run against chrome
		chrome.get("https://www.google.com");
		System.out.println(chrome.getTitle());

		// run against firefox
		firefox.get("https://github.com/");
		System.out.println(firefox.getTitle());

		chrome.quit();
		firefox.quit();
	}
}
