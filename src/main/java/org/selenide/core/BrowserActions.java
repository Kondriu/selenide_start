package org.selenide.core;

import java.util.concurrent.TimeUnit;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.selenide.core.annotations.Url;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.assertj.core.api.Assertions.assertThat;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BrowserActions {
    public static void refreshPage() {
        getWebDriver().navigate().refresh();
    }

    public static void back() {
        getWebDriver().navigate().back();
    }

    public static void navigateToUrl(String url) {
        open(url);
    }

    public static String getCurrentPageUrl() {
        return getWebDriver().getCurrentUrl();
    }

    public static boolean checkCurrentPageAt(Class<? extends Object> pageClass) {
        if (!pageClass.isAnnotationPresent(Url.class)) {
            throw new IllegalArgumentException(String.format("'%s' class has no URL annotation", pageClass.getName()));
        }
        WaitUtil.doWait().untilAsserted(() -> assertThat(getCurrentPageUrl())
                .as("Page %s was not opened", pageClass.getSimpleName())
                .matches(pageClass.getAnnotation(Url.class).pattern()));
        return true;
    }

    public static void waitPage(Class<? extends Object> pageClass) {
        long timeout = PropertiesController.timeoutConfig().pageWaitTimeout();
        WaitUtil.doWaitWithTimeout(timeout, TimeUnit.MILLISECONDS).ignoreExceptions()
                .untilAsserted(() -> assertThat(getCurrentPageUrl())
                        .as("Page is not loaded yet")
                        .matches(pageClass.getAnnotation(Url.class).pattern()));
    }
}
