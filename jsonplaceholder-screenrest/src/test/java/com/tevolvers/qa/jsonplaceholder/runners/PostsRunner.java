package com.tevolvers.qa.jsonplaceholder.runners;


import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/posts.feature",
        glue = "com.tevolvers.qa.jsonplaceholder.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE)
public class PostsRunner {
}
