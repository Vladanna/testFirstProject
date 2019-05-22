import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public  abstract class BaseClass {

    private WebDriver driver;
    private Integer timeForWaiting = 15;

    @FindBy(xpath = "//input[@placeholder='Email']")
    private WebElement emailInput;
    @FindBy(xpath =  "//input[@placeholder='Пароль']")
    private WebElement passwordInput;
    @FindBy(xpath = "//div[contains(text(),'Личный кабинет')]/..//button[@type='submit']")
    private WebElement authButton;
    @FindBy(xpath = "//a[contains(text(),'Личный кабинет')]")
    private WebElement loginPage;
    @FindBy(xpath = "//div[@class='notice error']")
    private WebElement errorMessage;

    public BaseClass(WebDriver driver)
    {
        this.driver = driver;
    }
    public void goToLoginPage() {
        loginPage.click();
    }

    public void authenticate(String email, String password){
        if (!email.isEmpty()) {
            emailInput.sendKeys(email);
        }
        if (!password.isEmpty()) {
            passwordInput.sendKeys(password);
        }
    }

    public void waitErrorMessage() {
        new WebDriverWait(driver, timeForWaiting)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='notice error']")));
    }

    public void clickToLogin() {
        new WebDriverWait(driver, timeForWaiting)
                .until(ExpectedConditions.elementToBeClickable(authButton)).click();
        new WebDriverWait(driver, timeForWaiting)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Личный кабинет')]")));
    }

    public void checkLogin() {
        authButton.isDisplayed();
    }
}
