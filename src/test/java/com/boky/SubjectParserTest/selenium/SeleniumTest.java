package com.boky.SubjectParserTest.selenium;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@Ignore
public class SeleniumTest {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/subjectparser/fuggosegek/";
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}

	@Test
	public void testSelenium() throws Exception {
		driver.get(baseUrl);
		try {
			assertTrue(driver.findElement(By.cssSelector("h1.pretty")).getText().matches("^[\\s\\S]*Kötelező szakirány[\\s\\S]*$"));

		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.linkText("Szakirányok")).click();
		try {
			assertFalse(isElementPresent(By.linkText("Kötelező")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.linkText("Geotechnikai")).click();
		try {
			assertFalse(isElementPresent(By.linkText("Geotechnikai")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertTrue(driver.findElement(By.cssSelector("h1.pretty")).getText().matches("^[\\s\\S]*Geotechnikai szakirány[\\s\\S]*$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.linkText("Szakirányok")).click();
		driver.findElement(By.linkText("Kötelező")).click();
		try {
			assertFalse(isElementPresent(By.linkText("Kötelező")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertTrue(driver.findElement(By.cssSelector("h1.pretty")).getText().matches("^[\\s\\S]*Kötelező szakirány[\\s\\S]*$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.linkText("Szakirányok")).click();
		driver.findElement(By.linkText("Magasépítési")).click();
		try {
			assertFalse(isElementPresent(By.linkText("Magasépítési")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertTrue(driver.findElement(By.cssSelector("h1.pretty")).getText().matches("^[\\s\\S]*Magasépítési szakirány[\\s\\S]*$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.linkText("Szakirányok")).click();
		driver.findElement(By.linkText("Szabadon választható")).click();
		try {
			assertFalse(isElementPresent(By.linkText("Szabadon választható")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertTrue(driver.findElement(By.cssSelector("h1.pretty")).getText().matches("^[\\s\\S]*Szabadon választható szakirány[\\s\\S]*$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.linkText("Szakirányok")).click();
		driver.findElement(By.linkText("Települési")).click();
		try {
			assertFalse(isElementPresent(By.linkText("Települési")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertTrue(driver.findElement(By.cssSelector("h1.pretty")).getText().matches("^[\\s\\S]*Települési szakirány[\\s\\S]*$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.linkText("Szakirányok")).click();
		driver.findElement(By.linkText("Tűz- és katasztrófavédelmi")).click();
		try {
			assertFalse(isElementPresent(By.linkText("Tűz- és katasztrófavédelmi")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertTrue(driver.findElement(By.cssSelector("h1.pretty")).getText().matches("^[\\s\\S]*Tűz- és katasztrófavédelmi szakirány[\\s\\S]*$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.linkText("Kapcsolat")).click();
		// Warning: verifyTextPresent may require manual changes
		try {
			assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Kapcsolatok[\\s\\S]*$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.linkText("FÜGGŐSÉGEK")).click();
		try {
			assertFalse(isElementPresent(By.linkText("Kötelező")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertTrue(driver.findElement(By.cssSelector("h1.pretty")).getText().matches("^[\\s\\S]*Kötelező szakirány[\\s\\S]*$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertTrue(driver.findElement(By.cssSelector("#description p")).getText().matches("^[\\s\\S]*Kattints egy tárgyra[\\s\\S]*$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.id("SGYMMAT201XXX")).click();
		try {
			assertTrue(driver.findElement(By.cssSelector("#description p")).getText().matches("^[\\s\\S]*SGYMMAT201XXX[\\s\\S]*$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertTrue(isElementPresent(By.cssSelector("#SGYMMAT201XXX.dependencyBtn-forward0")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
