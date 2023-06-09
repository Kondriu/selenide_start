package org.selenide.pages.duck;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class DuckAndGoPage {
    public void searchFor(String text) {
        $(By.name("q")).val(text).pressEnter();
    }
}