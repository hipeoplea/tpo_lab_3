package org.hipeoplea.ui.sellenium.core;

import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class TwoGisSteps {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(20);
    private static final Duration SHORT_TIMEOUT = Duration.ofSeconds(5);

    private final DriverActions actions;

    TwoGisSteps(DriverActions actions) {
        this.actions = actions;
    }

    public void openHomePage() {
        openUrl(TwoGisLocators.HOME_URL);
    }

    public void openDirectionsPage() {
        openHomePage();
        try {
            actions.safeClick(actions.waitForAnyClickable(SHORT_TIMEOUT, TwoGisLocators.DIRECTIONS_TAB_LOCATORS));
            actions.waitUntil(
                    currentDriver -> currentDriver.getCurrentUrl().contains("/directions")
                            || actions.isAnyElementVisible(TwoGisLocators.FROM_INPUT_LOCATORS),
                    DEFAULT_TIMEOUT
            );
        } catch (TimeoutException exception) {
            openUrl(TwoGisLocators.DIRECTIONS_URL);
        }
        dismissCommonOverlays();
    }

    public void search(String query) {
        actions.clearAndType(DEFAULT_TIMEOUT, query, TwoGisLocators.SEARCH_INPUT_LOCATORS);
        actions.waitForAnyVisible(DEFAULT_TIMEOUT, TwoGisLocators.SEARCH_INPUT_LOCATORS).sendKeys(Keys.ENTER);
        actions.waitUntil(
                currentDriver -> currentDriver.getCurrentUrl().contains("/search/")
                        || actions.isAnyElementVisible(TwoGisLocators.SEARCH_RESULT_LOCATORS),
                DEFAULT_TIMEOUT
        );
        dismissCommonOverlays();
    }

    public boolean hasSearchResults() {
        return actions.isAnyElementVisible(TwoGisLocators.SEARCH_RESULT_LOCATORS);
    }

    public String readNoResultsMessage() {
        return actions.waitForNonBlankText(DEFAULT_TIMEOUT, TwoGisLocators.NO_RESULTS_MESSAGE_LOCATORS);
    }

    public void openFirstResult() {
        actions.safeClick(actions.waitForAnyClickable(DEFAULT_TIMEOUT, TwoGisLocators.SEARCH_RESULT_LOCATORS));
        waitForObjectCard();
    }

    public void openObjectFromMapMarker() {
        actions.safeClick(actions.waitForAnyClickable(DEFAULT_TIMEOUT, TwoGisLocators.MAP_MARKER_LOCATORS));
        waitForObjectCard();
    }

    public void openGuide() {
        try {
            actions.safeClick(actions.waitForAnyVisible(DEFAULT_TIMEOUT, TwoGisLocators.GUIDE_BUTTON_LOCATORS));
            boolean opened = actions.waitUntil(
                    currentDriver -> currentDriver.getCurrentUrl().contains("/guide")
                            || actions.isAnyElementVisible(TwoGisLocators.GUIDE_DISTRICT_LOCATORS),
                    DEFAULT_TIMEOUT
            );
            if (!opened) {
                openUrl(TwoGisLocators.GUIDE_URL);
            }
        } catch (TimeoutException exception) {
            openUrl(TwoGisLocators.GUIDE_URL);
        }
        dismissCommonOverlays();
    }

    public void selectGuideDistrict() {
        try {
            actions.safeClick(actions.waitForAnyVisible(Duration.ofSeconds(40), TwoGisLocators.GUIDE_DISTRICT_LOCATORS));
            actions.waitUntil(
                    currentDriver -> actions.isAnyElementVisible(TwoGisLocators.SEARCH_RESULT_LOCATORS)
                            || actions.isAnyElementVisible(TwoGisLocators.OBJECT_CARD_TITLE_LOCATORS),
                    DEFAULT_TIMEOUT
            );
        } catch (TimeoutException exception) {
            actions.safeClick(actions.waitForAnyVisible(DEFAULT_TIMEOUT, TwoGisLocators.GUIDE_COLLECTION_LOCATORS));
            actions.waitUntil(
                    currentDriver -> currentDriver.getCurrentUrl().contains("/collection/")
                            || actions.isAnyElementVisible(TwoGisLocators.SEARCH_RESULT_LOCATORS),
                    DEFAULT_TIMEOUT
            );
        }
    }

    public void openFirstGuidePlace() {
        openFirstResult();
    }

    public String readObjectCardTitle() {
        return actions.readFirstVisibleText(TwoGisLocators.OBJECT_CARD_TITLE_LOCATORS);
    }

    public boolean isObjectAddressVisible() {
        return actions.isAnyElementVisible(TwoGisLocators.OBJECT_CARD_ADDRESS_LOCATORS);
    }

    public void buildRoute(String from, String to) {
        fillRoutePoint(TwoGisLocators.FROM_INPUT_LOCATORS, from);
        fillRoutePoint(TwoGisLocators.TO_INPUT_LOCATORS, to);
        submitRouteBuild();
        waitForRouteSummary();
    }

    public boolean isRouteVisible() {
        return !waitForRouteSummary().isBlank();
    }

    public String waitForRouteSummary() {
        return actions.waitForNonBlankText(DEFAULT_TIMEOUT, TwoGisLocators.ROUTE_SUMMARY_LOCATORS);
    }

    public boolean isWaypointControlVisible(Duration timeout) {
        return actions.waitUntil(
                currentDriver -> actions.isAnyElementVisible(TwoGisLocators.ADD_WAYPOINT_BUTTON_LOCATORS),
                timeout
        );
    }

    public void addWaypoint(String waypoint) {
        addWaypoint(1, waypoint);
    }

    public String readWaypointValue() {
        return readWaypointValue(1);
    }

    public String waitForRouteSummaryChange(String previousSummary) {
        return actions.waitForTextChange(DEFAULT_TIMEOUT, previousSummary, TwoGisLocators.ROUTE_SUMMARY_LOCATORS);
    }

    public void selectWalkingRouteType() {
        actions.safeClick(actions.waitForAnyClickable(DEFAULT_TIMEOUT, TwoGisLocators.WALK_ROUTE_BUTTON_LOCATORS));
    }

    public void buildRouteWithoutFrom(String to) {
        fillRoutePoint(TwoGisLocators.TO_INPUT_LOCATORS, to);
        submitRouteBuild();
    }

    public void addWaypoint(int waypointNumber, String waypoint) {
        actions.safeClick(actions.waitForAnyClickable(DEFAULT_TIMEOUT, TwoGisLocators.ADD_WAYPOINT_BUTTON_LOCATORS));
        fillRoutePoint(TwoGisLocators.waypointInputLocators(waypointNumber), waypoint);
        submitRouteBuild();
    }

    public boolean tryAddWaypoint(int waypointNumber, String waypoint) {
        try {
            addWaypoint(waypointNumber, waypoint);
            return true;
        } catch (TimeoutException exception) {
            return false;
        }
    }

    public String readWaypointValue(int waypointNumber) {
        return actions.readInputValue(TwoGisLocators.waypointInputLocators(waypointNumber));
    }

    public void clickMyLocation() {
        actions.safeClick(actions.waitForAnyClickable(DEFAULT_TIMEOUT, TwoGisLocators.MY_LOCATION_BUTTON_LOCATORS));
    }

    public boolean isFromPointFilled(Duration timeout) {
        return actions.waitUntil(
                currentDriver -> !actions.readInputValue(TwoGisLocators.FROM_INPUT_LOCATORS).isBlank(),
                timeout
        );
    }

    public boolean isMyLocationButtonVisible() {
        return actions.isAnyElementVisible(TwoGisLocators.MY_LOCATION_BUTTON_LOCATORS);
    }

    public void clickShare() {
        actions.safeClick(actions.waitForAnyClickable(DEFAULT_TIMEOUT, TwoGisLocators.SHARE_BUTTON_LOCATORS));
    }

    public boolean isSharePanelVisible() {
        return actions.waitForAnyVisible(DEFAULT_TIMEOUT, TwoGisLocators.SHARE_PANEL_LOCATORS).isDisplayed();
    }

    public void openWeather() {
        actions.safeClick(actions.waitForAnyClickable(DEFAULT_TIMEOUT, TwoGisLocators.WEATHER_BUTTON_LOCATORS));
    }

    public boolean isWeatherPanelVisible() {
        return actions.waitForAnyVisible(DEFAULT_TIMEOUT, TwoGisLocators.WEATHER_PANEL_LOCATORS).isDisplayed();
    }

    public void clickZoomIn() {
        actions.safeClick(actions.waitForAnyVisible(DEFAULT_TIMEOUT, TwoGisLocators.ZOOM_IN_BUTTON_LOCATORS));
    }

    public void clickZoomOut() {
        actions.safeClick(actions.waitForAnyVisible(DEFAULT_TIMEOUT, TwoGisLocators.ZOOM_OUT_BUTTON_LOCATORS));
    }

    public String readCurrentUrl() {
        return actions.currentUrl();
    }

    public boolean waitForUrlChange(String previousUrl, Duration timeout) {
        return actions.waitUntil(
                currentDriver -> !currentDriver.getCurrentUrl().equals(previousUrl),
                timeout
        );
    }

    private void openUrl(String url) {
        actions.openUrl(url);
        dismissCommonOverlays();
    }

    private void fillRoutePoint(org.openqa.selenium.By[] locators, String value) {
        actions.clearAndType(DEFAULT_TIMEOUT, value, locators);
        try {
            actions.safeClick(actions.waitForAnyClickable(SHORT_TIMEOUT, TwoGisLocators.AUTOCOMPLETE_OPTION_LOCATORS));
        } catch (TimeoutException | StaleElementReferenceException ignored) {
            WebElement refreshedInput = actions.waitForAnyVisible(DEFAULT_TIMEOUT, locators);
            refreshedInput.sendKeys(Keys.ARROW_DOWN);
            refreshedInput.sendKeys(Keys.ENTER);
        }
    }

    private void submitRouteBuild() {
        try {
            actions.safeClick(actions.waitForAnyClickable(SHORT_TIMEOUT, TwoGisLocators.BUILD_ROUTE_BUTTON_LOCATORS));
        } catch (TimeoutException ignored) {
            actions.waitForAnyVisible(DEFAULT_TIMEOUT, TwoGisLocators.TO_INPUT_LOCATORS).sendKeys(Keys.ENTER);
        }
    }

    private void dismissCommonOverlays() {
        try {
            actions.safeClick(actions.waitForAnyClickable(SHORT_TIMEOUT, TwoGisLocators.OVERLAY_CLOSE_LOCATORS));
        } catch (TimeoutException ignored) {
            // Всплывающего окна может не быть.
        }
    }

    private void waitForObjectCard() {
        actions.waitUntil(
                currentDriver -> currentDriver.getCurrentUrl().contains("/firm/")
                        || currentDriver.getCurrentUrl().contains("/geo/")
                        || actions.isAnyElementVisible(TwoGisLocators.OBJECT_CARD_TITLE_LOCATORS),
                DEFAULT_TIMEOUT
        );
    }
}
