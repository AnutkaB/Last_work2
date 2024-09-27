package ru.iteco.fmhandroid.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.utils.CustomMatchers.waitForElement;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.screens.OurMissionScreen;
import ru.iteco.fmhandroid.testdata.Quote;

public class OurMissionScreenSteps extends NavigationBarSteps {

    private final OurMissionScreen ourMissionScreen = new OurMissionScreen();

    public void checkScreenIsLoaded() {
        Allure.step("Проверка отображения раздела \"О приложении\"");
        super.checkScreenIsLoaded();
        waitForElement(withId(R.id.our_mission_title_text_view), DEFAULT_TIMEOUT);
        ourMissionScreen.title.check(matches(isDisplayed()));
        ourMissionScreen.listOfItems.check(matches(isDisplayed()));
    }

    public void expandQuote(Quote quote) {
        expandQuote(quote.getTitle());
    }

    public void expandQuote(String title) {
        Allure.step("Развернуть цитату");
        ourMissionScreen.getCollapsedMissionItem(title).check(matches(isDisplayed()));
        ourMissionScreen.getCollapsedMissionItem(title).perform(click());
    }

    public void checkExpandedQuote(Quote quote) {
        checkExpandedQuote(quote.getTitle(), quote.getDescription());
    }

    public void checkExpandedQuote(String title, String description) {
        Allure.step("Проверить содержимое цитаты");
        ourMissionScreen.getExpandedMissionItem(title, description).check(matches(isDisplayed()));
    }

    public void collapseQuote(Quote quote) {
        collapseQuote(quote.getTitle(), quote.getDescription());
    }

    public void collapseQuote(String title, String description) {
        Allure.step("Свернуть цитату");
        ourMissionScreen.getExpandedMissionItem(title, description).perform(click());
    }

    public void checkQuoteDescriptionIsNotDisplayed(Quote quote) {
        checkQuoteDescriptionIsNotDisplayed(quote.getDescription());
    }

    public void checkQuoteDescriptionIsNotDisplayed(String description) {
        Allure.step("Проверить, что цитата свернута");
        ourMissionScreen.getMissionItemDescription(description).check(matches(not(isDisplayed())));
    }
}
