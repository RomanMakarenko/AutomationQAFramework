package romm.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import romm.components.BaseTest;
import romm.pageobjects.SearchPage;

import java.util.List;

public class StepDefinition extends BaseTest {
    public SearchPage searchPage;

    @Given("Open search page")
    public void open_search_page() {
        launchApp();
        searchPage = new SearchPage(driver);
        searchPage.goTo();
    }

    @When("^Search by (.+)$")
    public void search_by_request_text(String searchText) {
        searchPage.search(searchText);
    }

    @Then("Response should contains article id {int}")
    public void response_should_contains_article_id(int articleId) {
        List<String> articlesListFromSearchFeed = searchPage.getArticlesListFromSearchFeed();
        Assert.assertTrue(articlesListFromSearchFeed.contains(articleId));
        tearDown();
    }
}
