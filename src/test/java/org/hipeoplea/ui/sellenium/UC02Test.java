package org.hipeoplea.ui.sellenium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.hipeoplea.ui.sellenium.core.BaseUiTest;

public class UC02Test extends BaseUiTest {

    @Test
    @DisplayName("UC-02 Просмотр карточки объекта")
    void uC02() {
        twoGis.openHomePage();
        twoGis.search("аптека");
        twoGis.openFirstResult();

        Assertions.assertTrue(!twoGis.readObjectCardTitle().isBlank() || twoGis.isObjectAddressVisible(),
                "После открытия результата должна отображаться карточка объекта.");
    }
}
