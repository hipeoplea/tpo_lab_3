package org.hipeoplea.ui.sellenium;

import org.hipeoplea.ui.sellenium.core.BaseUiTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UC08Test extends BaseUiTest {

    @Test
    @DisplayName("UC-08 Использование режима Гид")
    void uC08() {
        twoGis.openHomePage();
        twoGis.openGuide();
        twoGis.selectGuideDistrict();
        twoGis.openFirstGuidePlace();

        Assertions.assertTrue(!twoGis.readObjectCardTitle().isBlank() || twoGis.isObjectAddressVisible(),
                "После выбора района и места в режиме 'Гид' должна открываться карточка объекта.");
    }
}
