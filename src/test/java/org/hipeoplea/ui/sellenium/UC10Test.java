package org.hipeoplea.ui.sellenium;

import org.hipeoplea.ui.sellenium.core.BaseUiTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class UC10Test extends BaseUiTest {

    @Test
    @DisplayName("UC-10 Изменение масштаба карты")
    void uC10() {
        twoGis.openHomePage();
        String initialUrl = twoGis.readCurrentUrl();

        twoGis.clickZoomOut();
        boolean urlChanged = twoGis.waitForUrlChange(initialUrl, Duration.ofSeconds(10));
        String updatedUrl = twoGis.readCurrentUrl();

        Assertions.assertTrue(urlChanged,
                "После уменьшения масштаба URL карты должен измениться. Был: "
                        + initialUrl + ", стал: " + updatedUrl);
    }
}
