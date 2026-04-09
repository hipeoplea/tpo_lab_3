package org.hipeoplea.ui.sellenium;

import org.hipeoplea.ui.sellenium.core.BaseUiTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UC07Test extends BaseUiTest {

    @Test
    @DisplayName("UC-07 Выбор объекта с карты")
    void uC07() {
        twoGis.openHomePage();
        twoGis.search("аптека");
        twoGis.openObjectFromMapMarker();

        Assertions.assertTrue(!twoGis.readObjectCardTitle().isBlank() || twoGis.isObjectAddressVisible(),
                "После выбора маркера должна открываться карточка объекта.");
    }
}
