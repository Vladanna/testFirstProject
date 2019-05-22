import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TsumTestSuite {
    private WebDriver driver;
    private String email;
    private String password;

    private Properties getProperties() {
        File file = new File("src/dataFile.properties");
        FileInputStream fileInput = null;

        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Properties prop = new Properties();

        try {
            prop.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    @Before
    public void setUp() {
        Properties prop = getProperties();

        String driverPath = prop.getProperty("driverPath");
        String driverName = prop.getProperty("driverName");
        String url = prop.getProperty("url");
        email = prop.getProperty("email");
        password = prop.getProperty("password");

        System.setProperty(driverName, driverPath);

        if (driverName.equals("webdriver.gecko.driver")) {
            driver = new FirefoxDriver();
        } else if (driverName.equals("webdriver.chrome.driver")) {
            driver = new ChromeDriver();
        } else if (driverName.equals("webdriver.ie.driver")) {
            driver = new InternetExplorerDriver();
        } else {
            throw new IllegalArgumentException("Browser " + driverName + " not found!");
        }
        driver.get(url);
    }

    @Test
    public void registrationPassedTest() {
        RegistrationClass registration = PageFactory.initElements(driver, RegistrationClass.class);
        registration.goToLoginPage();
        registration.goToRegistrationPage();
        registration.authenticate("test1@email.ru", "abCd12341");
        registration.clickToLogin();
    }

    @Test
    public void authenticationPassedTest() {
        AuthenticationClass authentication = PageFactory.initElements(driver, AuthenticationClass.class);
        authentication.goToLoginPage();
        authentication.authenticate(email, password);
        authentication.clickToLogin();
        authentication.waitErrorMessage();
    }

    @Test
    public void authenticationFailedTest() {
        AuthenticationClass authentication = PageFactory.initElements(driver, AuthenticationClass.class);
        authentication.goToLoginPage();
        authentication.authenticate("test", "");
        authentication.checkLogin();
    }
    @Test
    public void registrationWrongPasswordTest() {
        RegistrationClass registry = PageFactory.initElements(driver, RegistrationClass.class);
        registry.goToLoginPage();
        registry.authenticate("", "lalalalals");
        registry.checkLogin();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
