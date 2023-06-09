package org.selenide.pages.duck;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultsPage {

    private ElementsCollection results = $$("li[data-layout='organic']>article");


    public ElementsCollection getResults() {
        return results;
    }
    public SelenideElement getResult(int index) {
        return $("#r1-" + index);
    }
}