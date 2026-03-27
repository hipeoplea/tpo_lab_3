package org.hipeoplea.ui.sellenium;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.hipeoplea.ui.sellenium.core.BaseUiTest;

public class UC03Test extends BaseUiTest {

    @Test
    @DisplayName("UC-03 Построение маршрута")
    void uC03() {
        twoGis.openDirectionsPage();
        twoGis.buildRoute("Кронверкский проспект, 49", "Белорусская улица, 6");
        org.junit.jupiter.api.Assertions.assertTrue(twoGis.isRouteVisible(),
                "После построения маршрута должна отображаться его сводка.");
    }
}
