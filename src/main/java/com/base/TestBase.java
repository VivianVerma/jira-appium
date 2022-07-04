package com.base;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;


public class TestBase {

	private static ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();
	    
	    

		public static void initialization() throws InterruptedException, MalformedURLException {
	            // ************ Launch Locally on Simulator **********************
			  System.out.println("Launching App");
			  
		      File classpathRoot = new File(System.getProperty("user.dir"));
		      File appDir = new File(classpathRoot, "/apkfile");
		      File app = new File(appDir, "sample.apk");
			  
		      DesiredCapabilities capabilites = new DesiredCapabilities();
		      capabilites.setCapability("platformName", "Android");
		      capabilites.setCapability("deviceName", "Android Emulator");
		      capabilites.setCapability("app", app.getAbsolutePath());		      

			  driver.set(new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub/"), capabilites));    
			  driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	        } 
		
	    public static AndroidDriver getDriver() {
	        return driver.get();
	    }

	    public static void closeBrowser() {
	        driver.get().quit();
	        driver.remove();
	    }
}
