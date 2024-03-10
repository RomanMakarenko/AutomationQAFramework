package romm.sections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import romm.AbstractComponent;

import java.util.List;

public class PageHeader extends AbstractComponent {
    public PageHeader(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "header-service__holder")
    public WebElement header;

    @FindBy(css = "div[class*=\"header-service__container--left\"]")
    public WebElement leftBlock;

    public List<WebElement> getLeftBlockLinkElements() {
        return leftBlock.findElements(By.cssSelector("a[role=\"link\"]"));
    }

    @FindBy(css = "div[class*=\"header-service__container--right\"]")
    public WebElement rightBlock;

    public List<WebElement> getRightBlockLinkElements() {
        return rightBlock.findElements(By.cssSelector("a[role=\"link\"]"));
    }

    public void openLinkByName(String linkName) {
        WebElement link = driver.findElement(By.xpath(String.format("//span[contains(text(), '%s')]", linkName)));
        link.click();
    }
}
