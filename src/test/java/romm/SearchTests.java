package romm;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import romm.components.BaseTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchTests extends BaseTest {
    @Test(groups = {"Links"})
    public void checkHeaderLeftBlockLinks() {
        List<WebElement> leftBlockLinks = mainPage.pageHeader.getLeftBlockLinkElements();
        Map<String, String> namesLinksMap = new HashMap<>();
        leftBlockLinks.forEach(link ->
                namesLinksMap.put(link.getText(), link.getAttribute("href"))
        );
        System.out.println();
        mainPage.pageHeader.openLinkByName("Jobs");
    }

    @Test(dependsOnMethods = {"checkHeaderLeftBlockLinks"})
    public void depend() {
        System.out.println();
    }
}