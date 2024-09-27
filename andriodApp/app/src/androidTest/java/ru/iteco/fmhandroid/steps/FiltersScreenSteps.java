package ru.iteco.fmhandroid.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.utils.CustomMatchers.waitForElement;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.screens.FiltersScreen;

public class FiltersScreenSteps extends BaseSteps {

    private final FiltersScreen filtersScreen = new FiltersScreen();

    public void checkNewsFilterScreenIsLoaded() {
        Allure.step("Проверка отображения раздела \"Фильтровать новости\" на экране \"Новости\"");
        checkScreenIsLoaded();
    }

    public void clearDateFields() {
        Allure.step("Очистить поля с датами");
        filtersScreen.startDateField.perform(replaceText(""));
        filtersScreen.endDateField.perform(replaceText(""));
    }

    public void fillDateFields(String startDate, String endDate) {
        Allure.step("Заполнить поля с датами");
        filtersScreen.startDateField.perform(replaceText(startDate));
        filtersScreen.endDateField.perform(replaceText(endDate));
    }

    public void clickFilterButton() {
        Allure.step("Нажать \"Фильтровать\"");
        filtersScreen.filterButton.perform(click());
    }

    public void clickActiveCheckbox() {
        Allure.step("Нажать на чекбокс \"Активный\"");
        filtersScreen.checkBoxActive.perform(click());
    }

    public void clickNotActiveCheckbox() {
        Allure.step("Нажать на чекбокс \"Неактивный\"");
        filtersScreen.checkBoxNotActive.perform(click());
    }

    private void checkScreenIsLoaded() {
        waitForElement(withText(R.string.filter_news), DEFAULT_TIMEOUT);
        filtersScreen.title.check(matches(isDisplayed()));
        filtersScreen.categoryField.check(matches(isDisplayed()));
        filtersScreen.startDateField.check(matches(isDisplayed()));
        filtersScreen.endDateField.check(matches(isDisplayed()));
        filtersScreen.filterButton.check(matches(isDisplayed()));
        filtersScreen.cancelButton.check(matches(isDisplayed()));
    }
}
