package romm.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import romm.AbstractComponent;
import romm.sections.BurgerMenu;
import romm.sections.PageHeader;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends AbstractComponent {
    public PageHeader pageHeader;
    public BurgerMenu burgerMenu;

    public SearchPage(WebDriver driver) {
        super(driver);
        pageHeader = new PageHeader(driver);
        burgerMenu = new BurgerMenu(driver);
    }

    @FindBy(css = "div[class*=\"pageelement--search\"]")
    public WebElement searchContainer;

    public WebElement searchInput() {
        return this.searchContainer.findElement(By.cssSelector("input[name=\"q\"]"));
    }

    public WebElement startSearchBtn() {
        return this.searchContainer.findElement(By.cssSelector("button[aria-label=\"Suche\"]"));
    }

    public WebElement cleanSearchBtn() {
        return this.searchContainer.findElement(By.cssSelector("button[class*=\"searchform__button-close\"]"));
    }

    public String getInputPlaceholder() {
        return this.searchInput().getAttribute("placeholder");
    }

    public void search(String searchText) {
        this.searchInput().click();
        this.searchInput().sendKeys(searchText);
        this.startSearchBtn().click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        waitForElementAppear(this.searchFeed());
    }

    public WebElement searchFeed() {
        return this.searchContainer.findElement(By.cssSelector("div[data-nzz-tid=\"search-content\"]"));
    }

    public List<WebElement> searchArticlesList() {
        return this.searchFeed().findElements(By.cssSelector("div[data-nzz-tid=\"teaser\"]"));
    }

    public List<String> getArticlesListFromSearchFeed() {
        List<String> articlesListFromSearchFeed = new ArrayList<>();
        this.searchArticlesList().forEach(article -> articlesListFromSearchFeed.add(article.getAttribute("id").split("_")[1]));
        return articlesListFromSearchFeed;
    }

    public void goTo() {
        driver.get("https://www.nzz.ch/suche");
    }
}
