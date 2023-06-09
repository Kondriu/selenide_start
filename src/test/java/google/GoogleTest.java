package google;

import com.codeborne.selenide.SelenideElement;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.selenide.pages.google.GooglePage;
import org.selenide.pages.google.GoogleResultsPage;
import org.selenide.pages.google.models.ResultsModel;

import java.util.List;

public class GoogleTest extends UITest {


    @Test
    public void userCanPerformSearch(){
        GoogleResultsPage javaSearchResults = GooglePage
                .open()
                .setCookiesConsent()
                .switchToEnglish()
                .typeSearchText("java");

        List<ResultsModel> searchResultsAsModel = javaSearchResults.getSearchResultsAsModel();
        /** поправить локаторы для результатов поиска, ато только три вместо 8. */

        SoftAssertions.assertSoftly(softAssertions -> {
            Assertions.assertThat(searchResultsAsModel.size()).isGreaterThan(0);
            Assertions.assertThat(searchResultsAsModel.get(0).getTitle()).contains("Java");
            Assertions.assertThat(searchResultsAsModel.get(0).getUrl()).contains("https://www.java.com");
        });

    }

}
