package org.selenide.core.properties;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:properties/timeout.properties")
public interface TimeoutConfig extends Config {

    @Config.Key("webdriver.wait.timeout.mills")
    int webdriverWaitTimeout();
    @Config.Key("page.wait.timeout.millis")
    int pageWaitTimeout();
}
