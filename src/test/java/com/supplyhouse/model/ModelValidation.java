package com.supplyhouse.model;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
public class ModelValidation {
	 static WebDriver driver;
		@BeforeTest
		public void browser() throws Exception{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get(com.supplyhouse.model.Util.url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(com.supplyhouse.model.Util.waittime, TimeUnit.SECONDS);	
		}
		@Test
		public void model() throws Exception {
			List<String> modelNumber = com.supplyhouse.model.Util.ExcelReader(com.supplyhouse.model.Util.FILE_PATH, "Data");
			System.out.println(modelNumber);
			for(int i=0;i<modelNumber.size();i++){	
				driver.findElement(By.id("model-number")).sendKeys(modelNumber.get(i));
				driver.findElement(By.xpath("//button[contains(@class,'search-btn')]")).click();
				Thread.sleep(1000);
				try{
				String value = driver.findElement(By.xpath("//div[@class='desc-sku']/strong")).getText();
				if(value.contentEquals(modelNumber.get(i))){
					System.out.println("Search Result displays model number entered:"+ modelNumber.get(i));
				}else{
					System.out.println("Sorry couldnt find the item: "+modelNumber.get(i) );
				}
				}catch(Exception e){	
					boolean flag=driver.findElement(By.xpath("//*[contains(text(),'Sorry, we ')]")).isDisplayed();
					if(flag){
						System.out.println("Sorry couldnt find the item:" +modelNumber.get(i));
					}
				}
				Thread.sleep(1000);
			}
		}
		@AfterTest
		public void teardown() {
			driver.quit();
		}
}
