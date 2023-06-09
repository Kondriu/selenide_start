package org.selenide.core;

import org.selenide.core.properties.TestSiteConfig;
import org.selenide.core.properties.TimeoutConfig;
import org.aeonbits.owner.ConfigFactory;

import java.util.Optional;

public class PropertiesController {

    private static TimeoutConfig timeoutConfig;
    private static TestSiteConfig testSiteConfig;

    public static TimeoutConfig timeoutConfig() {
        return Optional.ofNullable(timeoutConfig).orElseGet(() ->
                timeoutConfig = ConfigFactory.create(TimeoutConfig.class));
    }

    public static TestSiteConfig testSiteConfig() {
        return Optional.ofNullable(testSiteConfig).orElseGet(() ->
                testSiteConfig = ConfigFactory.create(TestSiteConfig.class));
    }
}
