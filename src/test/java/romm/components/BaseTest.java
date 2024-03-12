package romm.components;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import romm.pageobjects.MainPage;
import romm.utils.JSONParser;

import java.io.*;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public MainPage mainPage;
    public JSONParser jsonParser = new JSONParser();

    public WebDriver initializeDriver() {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/global.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String browserName = System.getProperty("browser") == null ? properties.getProperty("browser") : System.getProperty("browser");
        String browserWidth = properties.getProperty("browserWidth");
        String browserHeight = properties.getProperty("browserHeight");
        String implicitlyWaitInSeconds = properties.getProperty("implicitlyWaitInSeconds");
        WebDriver driver;
        switch (browserName) {
            case "firefox":
                //TODO if issue with driver manager System.setProperty("webdriver.firefox.driver", "")
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                //TODO if issue with driver manager System.setProperty("webdriver.edge.driver", "")
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "chrome_headless":
                ChromeOptions options = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                options.addArguments("headless");
                driver = new ChromeDriver(options);
                break;
            default:
                //TODO if issue with driver manager System.setProperty("webdriver.chrome.driver", "")
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(implicitlyWaitInSeconds)));
        Dimension dimension = new Dimension(Integer.parseInt(browserWidth), Integer.parseInt(browserHeight));
        driver.manage().window().setSize(dimension);

        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public MainPage launchApp() {
        driver = initializeDriver();
        mainPage = new MainPage(driver);
        mainPage.goTo();
        return mainPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }

    public String getScreeShoot(String testCaseName, WebDriver driver) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        StringBuilder pathBuilder = new StringBuilder("reports/");
        pathBuilder.append(testCaseName);
        pathBuilder.append(".png");
        File file = new File(pathBuilder.toString());
        try {
            FileUtils.copyFile(source, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return testCaseName + ".png";
    }
}
