package org.selenide.pages.google;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.selenide.core.annotations.Url;
import org.selenide.pages.Page;

import java.time.LocalDate;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Url(pattern = ".*/")
public class GooglePage extends Page {

    private SelenideElement searchInput = $(By.name("q"));
    private SelenideElement searchButton = $(By.name("btnK"));
    private SelenideElement switchToEnglishLink = $("[id='SIvCob']>a");

    public GooglePage(){
        waitPage();
    }

    public static GooglePage open() {
        return Selenide.open(Configuration.baseUrl, GooglePage.class);
    }

    public GooglePage setCookiesConsent(){
        getWebDriver().manage().deleteCookieNamed ("CONSENT");
        getWebDriver().manage().addCookie(new Cookie("CONSENT","YES+shp.gws-"+ LocalDate.now().toString().replace("-","")+"-0-RC2.en+FX+374"));
        getWebDriver().navigate().refresh();
        return this;
    }

    public GooglePage switchToEnglish(){
        if (switchToEnglishLink.is(Condition.visible)){
            switchToEnglishLink.click();
        }
        return this;
    }

    public GoogleResultsPage typeSearchText(String text){
        searchInput.shouldBe(Condition.visible).click();
        searchInput.setValue(text);
        searchButton.shouldBe(Condition.visible).click();
        return new GoogleResultsPage();
    }
}
