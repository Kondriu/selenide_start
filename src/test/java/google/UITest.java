package google;

import com.codeborne.selenide.WebDriverRunner;
import org.selenide.core.PropertiesController;
import org.selenide.core.SelenideProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@ExtendWith({ReportPortalExtension.class, TestClassMetaDataExtension.class, ScreenshotExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class UITest {

    @BeforeAll
    public void setup() {
        SelenideProvider.init(PropertiesController.testSiteConfig().getUrl());
    }

    @AfterAll
    void tearDown() {
        WebDriverRunner.clearBrowserCache();
        WebDriverRunner.closeWebDriver();
    }
}
