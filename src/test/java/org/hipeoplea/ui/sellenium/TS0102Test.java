package org.hipeoplea.ui.sellenium;

import org.hipeoplea.ui.sellenium.core.BaseUiTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TS0102Test extends BaseUiTest {

    @Test
    @DisplayName("TS-01-02 По запросу ничего не найдено")
    void ts0102_shouldShowNoResultsMessage() {
        twoGis.openHomePage();
        twoGis.search("фывапролджфывапролдфывапролдфывапролд");

        String noResultsMessage = twoGis.readNoResultsMessage();

        Assertions.assertTrue(
                noResultsMessage.startsWith("Ничего не нашлось")
                        && noResultsMessage.contains("попробуйте уточнить запрос"),
                "При пустой выдаче должно отображаться сообщение с предложением уточнить запрос");
    }
}
