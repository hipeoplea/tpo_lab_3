package org.hipeoplea.ui.sellenium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.hipeoplea.ui.sellenium.core.BaseUiTest;

public class UC01Test extends BaseUiTest {

    @Test
    @DisplayName("UC-01 Поиск объекта")
    void uC01() {
        twoGis.openHomePage();
        twoGis.search("аптека");

        Assertions.assertTrue(twoGis.hasSearchResults(),
                "После поиска должна отображаться поисковая выдача.");
    }
}
