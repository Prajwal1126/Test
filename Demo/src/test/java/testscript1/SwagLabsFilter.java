package testscript1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class SwagLabsFilter {
	WebDriver driver;
	Select db;
	
	@BeforeSuite
	public void browserInitialization() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\admin\\Downloads\\chromedriver_win32\\Chromedriver.exe" );
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/inventory.html");
		
		
	}
	
	@Test(priority = 1)
	public void dropdownAtoZ()
	{
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		ArrayList<String> itemNames = new ArrayList<String>();
		List<WebElement> items = driver.findElements(By.className("inventory_item_name"));
		for(int j=0; j<items.size(); j++) {
			itemNames.add(items.get(j).getText());
		}
		TreeSet<String> Sorteditems= new TreeSet<String>(itemNames);
		Assert.assertEquals(Sorteditems, itemNames);			
	}
	@Test(priority = 2)
	public void dropdownZtoA() {
		WebElement dropdown = driver.findElement(By.className("product_sort_container"));
		Select db = new Select(dropdown);
		db.selectByIndex(1);
		ArrayList<String> itemNames = new ArrayList<String>();
		List<WebElement> items = driver.findElements(By.className("inventory_item_name"));
		for(int j=0; j<items.size(); j++) {
			itemNames.add(items.get(j).getText());
		}
		TreeSet<String> Sorteditems = new TreeSet<String>(itemNames);
		Assert.assertEquals(Sorteditems.descendingSet(), itemNames);
		
	}
	@Test(priority = 3)
	public void pricelowtohigh()
	{
		WebElement dropdown = driver.findElement(By.className("product_sort_container"));
		Select db = new Select(dropdown);
		db.selectByIndex(2);
		ArrayList<Float> itemPrices = new ArrayList<Float>();
		List<WebElement> Prices = driver.findElements(By.className("inventory_item_price"));
		for(int j=0; j<Prices.size(); j++) {
			String price = Prices.get(j).getText().replace('$',' ').trim();
			itemPrices.add(Float.parseFloat(price));
		}
		ArrayList<Float> sortedPrices = new ArrayList<Float>(itemPrices);
		Collections.sort(sortedPrices);
		Assert.assertEquals(sortedPrices, itemPrices);
		
	}
	@Test(priority = 4)
	public void pricehightolow()
	{
		WebElement dropdown = driver.findElement(By.className("product_sort_container"));
		Select db = new Select(dropdown);
		db.selectByIndex(3);
		ArrayList<Float> itemPrices = new ArrayList<Float>();
		List<WebElement> Prices = driver.findElements(By.className("inventory_item_price"));
		for(int j=0; j<Prices.size(); j++) {
			String price = Prices.get(j).getText().replace('$',' ').trim();
			itemPrices.add(Float.parseFloat(price));
		}
		ArrayList<Float> sortedPrices = new ArrayList<Float>(itemPrices);
		Collections.sort(sortedPrices,Collections.reverseOrder());
		Assert.assertEquals(sortedPrices, itemPrices);
		
	}
	
	
}
