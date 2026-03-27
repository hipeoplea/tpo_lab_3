package org.hipeoplea.ui.sellenium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.hipeoplea.ui.sellenium.core.BaseUiTest;

public class UC06Test extends BaseUiTest {

    @Test
    @DisplayName("UC-06 Отправка ссылки на объект")
    void uC06() {
        twoGis.openHomePage();
        twoGis.search("Саблинская улица 13-15");
        twoGis.openFirstResult();

        twoGis.clickShare();

        Assertions.assertTrue(twoGis.isSharePanelVisible(),
                "После нажатия на 'Поделиться' должен открываться блок со ссылкой.");
    }
}
