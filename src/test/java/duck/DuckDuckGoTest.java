package duck;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeOptions;
import org.selenide.pages.duck.DuckAndGoPage;
import org.selenide.pages.duck.SearchResultsPage;

import java.time.LocalDate;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DuckDuckGoTest {

    @Test
    public void userCanSearch() {
        open("https://duckduckgo.com");
        new DuckAndGoPage().searchFor("selenide java");

        SearchResultsPage results = new SearchResultsPage();
        results.getResults().shouldHave(sizeGreaterThan(1));
        results.getResult(0).shouldHave(text("Selenide: concise UI tests in Java"));
    }


}
