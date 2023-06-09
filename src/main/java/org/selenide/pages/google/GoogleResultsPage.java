package org.selenide.pages.google;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.selenide.core.annotations.Url;
import org.selenide.pages.Page;
import org.selenide.pages.google.models.ResultsModel;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

@Url(pattern = ".*/search.*")
//@Url(pattern = ".*search?q=*")
//@Url(pattern = ".*/search?q=.*")
public class GoogleResultsPage extends Page {

    private ElementsCollection resultsWidget = $$("div[class='MjjYud']");

    public GoogleResultsPage() {
        waitPage();
    }

    private List<SelenideElement> getSearchResults() {
        return resultsWidget.stream()
                .filter(x -> x.$x("*//div[@class='g']").is(Condition.appear))
                .collect(Collectors.toList());
    }

    public List<ResultsModel> getSearchResultsAsModel() {
        return getSearchResults()
                .stream()
                .filter(x -> x.$x("*//div[@class='g']").is(Condition.appear))
                .map(a -> getSearchResultModel(a))
                .collect(Collectors.toList());
    }

    private ResultsModel getSearchResultModel(SelenideElement element) {
        return new ResultsModel(
                element.$("[class='VuuXrf']").getText(),
                element.$x("*//span[@class='VuuXrf']/../..//cite").getText(),
                element.$("[style='-webkit-line-clamp:2']").getText()
        );
    }

}
