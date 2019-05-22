import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationClass extends BaseClass {

    @FindBy(xpath = "//p[contains(text(),'Регистрация')]")
    private WebElement registrationButton;

    public RegistrationClass(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void goToRegistrationPage(){
        registrationButton.click();
    }
}
