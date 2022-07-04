package com.jira_util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {
	public void onTestFailure(ITestResult result) {

//****************** Capture Screen Shot ******************************
		try {
			Utilities.captureScreenshot();
		} catch (IOException e) {
		}

		boolean islogIssue = result.getMethod().getConstructorOrMethod().getMethod()
				.getAnnotation(JiraCreateIssue.class).isCreateIssue();
		String priority = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(JiraCreateIssue.class).priority();
		
// ************** Create a New Issue on Jira ********************
		if (islogIssue) {
			Properties prop;
			prop = new Properties();
			FileInputStream fis;
			try {
				fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/config/config.properties");
				prop.load(fis);
			} catch (Exception ignored) {
			}
			
			String url = prop.getProperty("url");
			String email = prop.getProperty("email");
			String apiKey = prop.getProperty("key");
			String project = prop.getProperty("project");
			
			JiraUtil jiraCreateConnection = new JiraUtil(url, email, apiKey, project);

			String issueDescription = "Reason For Test Failure : \n\n" + result.getThrowable().getMessage() + "\n";

			issueDescription.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));

			String issueSummary = result.getMethod().getConstructorOrMethod().getMethod().getName()
					+ " Failed in Automation Testing";

			try {
				jiraCreateConnection.createJiraIssue("Bug", issueSummary, issueDescription, priority);
			} catch (Exception ignore) {

			}
		}

	}
}
