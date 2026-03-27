package org.hipeoplea.ui.sellenium;

import org.hipeoplea.ui.sellenium.core.BaseUiTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TS0302Test extends BaseUiTest {

    @Test
    @DisplayName("TS-03-02 Смена типа маршрута")
    void ts0302_shouldRecalculateRouteAfterChangingType() {
        twoGis.openDirectionsPage();
        twoGis.buildRoute("Кронверкский проспект, 49", "Белорусская улица, 6");
        String summaryBeforeChange = twoGis.waitForRouteSummary();

        twoGis.selectWalkingRouteType();
        String summaryAfterChange = twoGis.waitForRouteSummaryChange(summaryBeforeChange);

        Assertions.assertNotEquals(summaryBeforeChange, summaryAfterChange,
                "После смены типа маршрута его сводка должна измениться.");
    }
}
