package romm.pageobjects;

import org.openqa.selenium.WebDriver;
import romm.AbstractComponent;
import romm.sections.BurgerMenu;
import romm.sections.PageHeader;

public class MainPage extends AbstractComponent {
    public PageHeader pageHeader;
    public BurgerMenu burgerMenu;

    public MainPage(WebDriver driver) {
        super(driver);
        pageHeader = new PageHeader(driver);
        burgerMenu = new BurgerMenu(driver);
    }

    public void goTo() {
        driver.get("https://www.nzz.ch/");
    }
}
