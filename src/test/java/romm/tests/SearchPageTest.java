package romm.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import romm.components.BaseTest;
import romm.components.Retry;
import romm.pageobjects.SearchPage;
import romm.utils.XLSXReader;

import java.util.List;


public class SearchPageTest extends BaseTest {
    @Test
    public void searchPageDefaultState() {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.goTo();
        System.out.println(searchPage.getInputPlaceholder());
    }

    @Test(dataProvider = "getDataFromXLSS", retryAnalyzer = Retry.class)
    public void searchResult(String requestText, String articleId) {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.goTo();
        searchPage.search(requestText);
        List<String> articlesListFromSearchFeed = searchPage.getArticlesListFromSearchFeed();
        Assert.assertTrue(articlesListFromSearchFeed.contains(articleId));
    }

    @DataProvider
    public Object[][] getRequestResult() {
        return new Object[][] {
                {"Shevchenko", "9861"},
                {"Lviv", "1773916"},
               // {"Lviv", "3333"}
        };
    }

    @DataProvider
    public Object[][] getDataFromXLSS() {
        return new XLSXReader("src/test/java/romm/data/TestData.xlsx").getDataForDataProvider("SearchPage");
    }

//    @DataProvider
//    public Object[][] getRequestResult() {
//        List<Map<String, String>> searchData = jsonParser.parseJsonFile("src/test/java/romm/data/search.json");
//        System.out.println(searchData.get(0));
//        return new Object[][] {{searchData.get(0)}, {searchData.get(1)}};
//    }
}
