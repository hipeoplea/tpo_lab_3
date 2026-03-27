package org.hipeoplea.ui.sellenium.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public abstract class BaseUiTest {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(20);

    protected WebDriver driver;
    protected TwoGisSteps twoGis;

    @BeforeEach
    void setUp() {
        FirefoxOptions options = new FirefoxOptions();
        if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
            options.addArguments("-headless");
        }

        options.addPreference("intl.accept_languages", "ru-RU,ru");
        options.addPreference("dom.webnotifications.enabled", false);
        options.addPreference("permissions.default.geo", 1);
        options.addPreference("geo.prompt.testing", true);
        options.addPreference("geo.prompt.testing.allow", true);

        driver = new FirefoxDriver(options);
        driver.manage().window().setSize(new Dimension(1512, 949));

        DriverActions actions = new DriverActions(driver, DEFAULT_TIMEOUT);
        twoGis = new TwoGisSteps(actions);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
