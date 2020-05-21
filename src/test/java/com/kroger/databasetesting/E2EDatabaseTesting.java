package com.kroger.databasetesting;

import static org.testng.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class E2EDatabaseTesting {

	@Test(priority = 1)

	public void entToEndDatabaseTesting() {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.get("https://www.mysqltutorial.org/tryit/query/mysql-select/#1");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		Actions act = new Actions(driver);
		driver.findElement(By.xpath("//a[@id = 'clear']")).click();
		act.click(driver.findElement(By.xpath("//div[@class='CodeMirror-scroll']")))
				.sendKeys("select employeeNumber, lastName, firstName from employees where employeeNumber = 1102;")
				.build().perform();
		act.click(driver.findElement(By.xpath("//a[@id='execute']"))).build().perform();
		List<WebElement> tds = driver.findElements(By.xpath("//*[@id='output']//td"));
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://54.147.152.199", "service_dev",
				"IBecameACoder");
				PreparedStatement preparedStatement = conn
						.prepareStatement("select * From classicmodels.employees where employeeNumber= 1102;");
				ResultSet resultSet = preparedStatement.executeQuery();) {

			resultSet.next();
			int counter = 1;
			for (WebElement webElement : tds) {

				assertEquals(resultSet.getString(counter), webElement.getText());
				counter++;

			}

		} catch (SQLException e) {
			e.getMessage();
			e.getStackTrace();
			e.getSQLState();
			e.printStackTrace();
		}

	}

	@Test(priority = 2)

	public void getFirstFiveEmployees() {

		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://54.147.152.199", "service_dev",
					"IBecameACoder");
			PreparedStatement preparedStatement = conn
					.prepareStatement("select * From classicmodels.employees Limit 5");
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
