package org.selenide.core.properties;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:properties/testsite.properties")
public interface TestSiteConfig extends Config{

    @Config.Key("site.url")
    String getUrl();
}
