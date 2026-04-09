package org.hipeoplea.ui.sellenium;

import org.hipeoplea.ui.sellenium.core.BaseUiTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UC09Test extends BaseUiTest {

    @Test
    @DisplayName("UC-09 Просмотр информации о погоде")
    void uC09() {
        twoGis.openHomePage();
        twoGis.openWeather();

        Assertions.assertTrue(twoGis.isWeatherPanelVisible(),
                "После нажатия на блок погоды должна отображаться погодная информация.");
    }
}
