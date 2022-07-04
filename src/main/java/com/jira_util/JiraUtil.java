package com.jira_util;



import java.io.File;
import net.rcarz.jiraclient.*;
import net.rcarz.jiraclient.Issue.FluentCreate;


public class JiraUtil {

  private JiraClient Jira;

  private String project;

  private String JiraUrl;

  public JiraUtil(String JiraUrl, String username, String password, String project) {

      this.JiraUrl=JiraUrl;
      BasicCredentials creds = new BasicCredentials(username, password);
      Jira = new JiraClient(JiraUrl, creds);
      this.project = project;

  }


 public void createJiraIssue(String issueType, String summary, String description, String priority ) throws InterruptedException {
     try {
         //Avoid Creating Duplicate Issue
         Issue.SearchResult sr = Jira.searchIssues("summary ~ \""+summary+"\"");
         if(sr.total!=0) {
             System.out.println("Same Issue Already Exists on Jira");
             return;
         }         

         //Create issue if not exists
         FluentCreate fleuntCreate = Jira.createIssue(project, issueType);
         fleuntCreate.field(Field.SUMMARY, summary);
         fleuntCreate.field(Field.DESCRIPTION, description);
         fleuntCreate.field(Field.PRIORITY, "Minor");
         Issue newIssue = fleuntCreate.execute();
         System.out.println("********************************************");
         System.out.println("New issue created in Jira with ID: " + newIssue);
         System.out.println("New issue URL is :"+JiraUrl+"/browse/"+newIssue);
         System.out.println("*******************************************");
         
         Thread.sleep(5000);
         Issue issue = Jira.getIssue(newIssue.toString());
         File file = new File(System.getProperty("user.dir") + "/Screenshots/FailedTest.jpg");
         issue.addAttachment(file);

     } catch (JiraException e) {

         e.printStackTrace();

     }

 }

}