package org.hipeoplea.ui.sellenium;

import org.hipeoplea.ui.sellenium.core.BaseUiTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TS0303Test extends BaseUiTest {

    @Test
    @DisplayName("TS-03-03 Невалидный маршрут")
    void ts0303_shouldExecuteInvalidRouteFlowWithEmptyFromPoint() {
        Assertions.assertDoesNotThrow(() -> {
            twoGis.openDirectionsPage();
            twoGis.buildRouteWithoutFrom("Белорусская улица, 6");
        }, "Сценарий с пустой точкой 'Откуда' должен выполняться без падения теста.");
    }
}
