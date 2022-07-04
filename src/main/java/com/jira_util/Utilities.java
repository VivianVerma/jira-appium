package com.jira_util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.base.TestBase;

public class Utilities extends TestBase{

	public static void captureScreenshot() throws IOException {
		File scr = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scr, new File(System.getProperty("user.dir") + "/Screenshots/FailedTest.jpg"));
	}
}
