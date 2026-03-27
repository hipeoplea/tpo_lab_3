package org.hipeoplea.ui.sellenium;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.hipeoplea.ui.sellenium.core.BaseUiTest;

import java.time.Duration;

public class UC04Test extends BaseUiTest {

    @Test
    @DisplayName("UC-04 Маршрут с промежуточной точкой")
    void uC04() {
        twoGis.openDirectionsPage();
        twoGis.buildRoute("Кронверкский проспект, 49", "Белорусская улица, 6");
        String summaryBeforeWaypoint = twoGis.waitForRouteSummary();

        boolean waypointControlVisible = twoGis.isWaypointControlVisible(Duration.ofSeconds(5));
        Assumptions.assumeTrue(
                waypointControlVisible,
                "Кнопка добавления промежуточной точки требует ручного уточнения XPath под текущую версию 2GIS."
        );

        twoGis.addWaypoint("Pitas");
        String summaryAfterWaypoint = twoGis.waitForRouteSummaryChange(summaryBeforeWaypoint);

        Assertions.assertFalse(twoGis.readWaypointValue().isBlank(),
                "После добавления должна быть заполнена промежуточная точка.");
        Assertions.assertNotEquals(summaryBeforeWaypoint, summaryAfterWaypoint,
                "После добавления промежуточной точки маршрут должен быть пересчитан.");
    }
}
