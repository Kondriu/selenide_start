package org.selenide.core;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class SelenideProvider {

    public static void init(String url) {
        Configuration.baseUrl = url;
        Configuration.browser = Browsers.CHROME;
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = PropertiesController.timeoutConfig().webdriverWaitTimeout();
        Configuration.reportsFolder = "target/selenide-screenshots";
        Configuration.browserCapabilities = getDefaultCapabilities();
    }


    private static ChromeOptions disabledDevicesChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>(4);
        prefs.put("profile.default_content_setting_values.media_stream_mic", 2);
        prefs.put("profile.default_content_setting_values.media_stream_camera", 2);
        options.setExperimentalOption("prefs", prefs);
        return options;
    }

    private static DesiredCapabilities getDefaultCapabilities(){
        DesiredCapabilities caps = new DesiredCapabilities();

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        caps.setCapability("goog:loggingPrefs", logPrefs);
        caps.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        caps.setCapability(ChromeOptions.CAPABILITY, disabledDevicesChromeOptions());

        return caps;
    }
}
