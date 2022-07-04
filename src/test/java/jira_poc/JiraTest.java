package jira_poc;


import java.net.MalformedURLException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.jira_util.JiraCreateIssue;

@Listeners(com.jira_util.Listener.class)

public class JiraTest extends TestBase
{

	public JiraTest() {
		super();
	}
	
	@BeforeTest
	public void setup() throws MalformedURLException, InterruptedException {
			initialization();
	}
	
	@JiraCreateIssue(isCreateIssue=true, priority = "Minor")
    @Test
    public void jiraTest13() throws MalformedURLException
    {
        Assert.assertTrue(false,"Assert Failed");
    }
	
	@AfterTest
	public void teardown() {
		getDriver().quit();
	}
}
