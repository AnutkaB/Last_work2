package ru.iteco.fmhandroid.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.screens.MainScreen;

public class MainScreenSteps extends NavigationBarSteps {

    private final MainScreen mainScreen = new MainScreen();

    public void checkScreenIsLoaded() {
        Allure.step("Проверка отображения раздела \"Главная\"");
        super.checkScreenIsLoaded();
        mainScreen.news.check(matches(isDisplayed()));
        mainScreen.allNewsButton.check(matches(isDisplayed()));
    }

    public void goToNews() {
        Allure.step("Перейти в раздел \"Новости\" по кнопке \"Все новости\"");
        mainScreen.allNewsButton.perform(click());
    }
}
