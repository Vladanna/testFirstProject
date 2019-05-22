import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AuthenticationClass extends BaseClass{

    public AuthenticationClass(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
