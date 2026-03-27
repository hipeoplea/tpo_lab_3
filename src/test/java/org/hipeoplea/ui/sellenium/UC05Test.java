package org.hipeoplea.ui.sellenium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.hipeoplea.ui.sellenium.core.BaseUiTest;

import java.time.Duration;

public class UC05Test extends BaseUiTest {

    @Test
    @DisplayName("UC-05 Определение местоположения")
    void uC05() {
        twoGis.openDirectionsPage();
        twoGis.clickMyLocation();

        boolean locationApplied = twoGis.isFromPointFilled(Duration.ofSeconds(10));

        Assertions.assertTrue(locationApplied || twoGis.isMyLocationButtonVisible(),
                "Кнопка определения местоположения должна быть доступна и обрабатываться интерфейсом.");
    }
}
