package org.hipeoplea.ui.sellenium;

import org.hipeoplea.ui.sellenium.core.BaseUiTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TS0402Test extends BaseUiTest {

    @Test
    @DisplayName("TS-04-02 Несколько промежуточных точек")
    void ts0402_shouldAddSeveralWaypoints() {
        twoGis.openDirectionsPage();
        twoGis.buildRoute("Кронверкский проспект, 49", "Белорусская улица, 6");

        String summaryBeforeFirstWaypoint = twoGis.waitForRouteSummary();
        twoGis.addWaypoint(1, "Pitas");
        String summaryAfterFirstWaypoint = twoGis.waitForRouteSummaryChange(summaryBeforeFirstWaypoint);

        twoGis.addWaypoint(2, "Площадь Восстания");
        String summaryAfterSecondWaypoint = twoGis.waitForRouteSummaryChange(summaryAfterFirstWaypoint);

        Assertions.assertFalse(twoGis.readWaypointValue(1).isBlank(),
                "Первая промежуточная точка должна быть заполнена.");
        Assertions.assertFalse(twoGis.readWaypointValue(2).isBlank(),
                "Вторая промежуточная точка должна быть заполнена.");
        Assertions.assertNotEquals(summaryAfterFirstWaypoint, summaryAfterSecondWaypoint,
                "После добавления второй промежуточной точки маршрут должен быть пересчитан.");
    }
}
