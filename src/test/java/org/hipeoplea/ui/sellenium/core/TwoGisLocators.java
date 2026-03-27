package org.hipeoplea.ui.sellenium.core;

import org.openqa.selenium.By;

final class TwoGisLocators {

    static final String HOME_URL = "https://2gis.ru/spb";
    static final String DIRECTIONS_URL = "https://2gis.ru/spb/directions";

    static final By[] SEARCH_INPUT_LOCATORS = {
            By.xpath("//input[contains(@placeholder,'Поиск') or contains(@aria-label,'Поиск') or contains(@placeholder,'Что') or contains(@aria-label,'Что')]"),
            By.xpath("//input[contains(@placeholder,'Найти') or contains(@aria-label,'Найти')]"),
            By.xpath("(//input[@type='text' and not(@disabled)])[1]")
    };

    static final By[] SEARCH_RESULT_LOCATORS = {
            By.xpath("//a[contains(@href,'/firm/') and normalize-space(.)!='']"),
            By.xpath("//a[contains(@href,'/geo/') and normalize-space(.)!='']"),
            By.xpath("//article[normalize-space(.)!='']"),
            By.xpath("(//div[contains(@class,'search') or contains(@class,'result')]//*[self::a or self::article][normalize-space(.)!=''])[1]")
    };

    static final By[] OBJECT_CARD_TITLE_LOCATORS = {
            By.xpath("//h1[normalize-space()]"),
            By.xpath("//h2[normalize-space()]"),
            By.xpath("//div[contains(@class,'card') or contains(@class,'details')]//*[self::h1 or self::h2][normalize-space()]")
    };

    static final By[] OBJECT_CARD_ADDRESS_LOCATORS = {
            By.xpath("//*[contains(normalize-space(.),'Адрес')]/following::*[self::div or self::span][normalize-space()][1]"),
            By.xpath("//a[contains(@href,'/geo/')]/following::*[self::div or self::span][normalize-space()][1]"),
            By.xpath("//div[contains(@class,'card')]//*[contains(@class,'address') and normalize-space()]")
    };

    static final By[] DIRECTIONS_TAB_LOCATORS = {
            By.xpath("//a[normalize-space()='Маршруты']"),
            By.xpath("//button[normalize-space()='Маршруты']"),
            By.xpath("//div[@role='button'][normalize-space()='Маршруты']")
    };

    static final By[] FROM_INPUT_LOCATORS = {
            By.xpath("//input[contains(@placeholder,'Откуда') or contains(@aria-label,'Откуда')]"),
            By.xpath("//input[contains(@aria-label,'A') or contains(@placeholder,'A')]"),
            By.xpath("(//input[@type='text' and not(@disabled)])[1]")
    };

    static final By[] TO_INPUT_LOCATORS = {
            By.xpath("//input[contains(@placeholder,'Куда') or contains(@aria-label,'Куда')]"),
            By.xpath("//input[contains(@aria-label,'B') or contains(@placeholder,'B')]"),
            By.xpath("(//input[@type='text' and not(@disabled)])[2]")
    };

    static final By[] ADD_WAYPOINT_BUTTON_LOCATORS = {
            By.xpath("//*[@id='root']/div/div/div[1]/div[1]/div[3]/div/div/div/div/div/div[1]/div/div[2]/div/div[4]"),
            By.xpath("//button[contains(.,'Добавить точку') or contains(.,'Промежуточ')]"),
            By.xpath("//button[contains(@aria-label,'Добавить точку') or contains(@aria-label,'Промежуточ')]"),
            By.xpath("//div[@role='button'][contains(.,'Добавить точку') or contains(.,'Промежуточ')]")
    };

    static final By[] WAYPOINT_INPUT_LOCATORS = {
            By.xpath("//*[@id='root']/div/div/div[1]/div[1]/div[3]/div/div/div/div/div/div[1]/div/div[2]/div/div[1]/div/div[3]/div[1]/div/div[2]/div/input"),
            By.xpath("//input[contains(@placeholder,'Через') or contains(@aria-label,'Через')]"),
            By.xpath("(//input[@type='text' and not(@disabled)])[3]")
    };

    static final By[] AUTOCOMPLETE_OPTION_LOCATORS = {
            By.xpath("(//*[@role='option'][normalize-space(.)!=''])[1]"),
            By.xpath("(//li[@role='option' and normalize-space(.)!=''])[1]"),
            By.xpath("(//div[contains(@class,'suggest') or contains(@class,'autocomplete') or contains(@class,'dropdown')]//*[self::a or self::button or self::div][normalize-space(.)!=''])[1]")
    };

    static final By[] BUILD_ROUTE_BUTTON_LOCATORS = {
            By.xpath("//button[contains(.,'Построить') or contains(@aria-label,'Построить')]"),
            By.xpath("//div[@role='button'][contains(.,'Построить')]")
    };

    static final By[] WALK_ROUTE_BUTTON_LOCATORS = {
            By.xpath("//*[@id='root']/div/div/div[1]/div[1]/div[3]/div/div/div/div/div/div[1]/div/div[1]/div/div/div[1]/button[2]"),
            By.xpath("//button[contains(.,'Пешком')]"),
            By.xpath("//button[contains(@aria-label,'Пешком')]")
    };

    static final By[] ROUTE_SUMMARY_LOCATORS = {
            By.xpath("(//*[self::div or self::span][contains(normalize-space(.),'мин')])[1]"),
            By.xpath("(//*[self::div or self::span][contains(normalize-space(.),'мин') and contains(normalize-space(.),'км')])[1]"),
            By.xpath("(//*[self::div or self::span][contains(normalize-space(.),' ч ') and contains(normalize-space(.),'км')])[1]"),
            By.xpath("(//div[contains(@class,'route')]//*[contains(normalize-space(.),'мин') or contains(normalize-space(.),'км')])[1]")
    };

    static final By[] MY_LOCATION_BUTTON_LOCATORS = {
            By.xpath("//*[contains(normalize-space(.),'Моё местоположение')]/ancestor::*[self::button or @role='button'][1]"),
            By.xpath("//*[contains(normalize-space(.),'Мое местоположение')]/ancestor::*[self::button or @role='button'][1]"),
            By.xpath("//button[contains(.,'Моё местоположение') or contains(.,'Мое местоположение') or contains(@aria-label,'местоположение')]"),
            By.xpath("//div[@role='button'][contains(.,'Моё местоположение') or contains(.,'Мое местоположение')]"),
            By.xpath("//*[contains(normalize-space(.),'Моё местоположение') or contains(normalize-space(.),'Мое местоположение')]")
    };

    static final By[] SHARE_BUTTON_LOCATORS = {
            By.xpath("//button[contains(.,'Поделиться') or contains(@aria-label,'Поделиться')]"),
            By.xpath("//div[@role='button'][contains(.,'Поделиться')]"),
            By.xpath("(//button[contains(@aria-label,'share') or contains(@title,'share')])[1]")
    };

    static final By[] SHARE_PANEL_LOCATORS = {
            By.xpath("//div[@role='dialog']//input[contains(@value,'2gis.ru')]"),
            By.xpath("//div[@role='dialog']//a[contains(@href,'2gis.ru')]"),
            By.xpath("//*[contains(.,'Скопировать ссылку') or contains(.,'Копировать ссылку')]")
    };

    static final By[] OVERLAY_CLOSE_LOCATORS = {
            By.xpath("//button[contains(@aria-label,'Закрыть')]"),
            By.xpath("//button[normalize-space()='Закрыть']"),
            By.xpath("//button[normalize-space()='Понятно']"),
            By.xpath("//button[contains(.,'Принять')]")
    };

    private TwoGisLocators() {
    }

    static By[] waypointInputLocators(int waypointNumber) {
        if (waypointNumber <= 1) {
            return WAYPOINT_INPUT_LOCATORS;
        }

        int inputIndex = waypointNumber + 2;
        return new By[]{By.xpath("(//input[@type='text' and not(@disabled)])[" + inputIndex + "]")};
    }
}
