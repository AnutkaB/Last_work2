package ru.iteco.fmhandroid.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.utils.CustomMatchers.waitForElement;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.screens.NewsScreen;
import ru.iteco.fmhandroid.testdata.News;

public class NewsScreenSteps extends NavigationBarSteps {

    private final NewsScreen newsScreen = new NewsScreen();

    public void checkScreenIsLoaded() {
        Allure.step("Проверка отображения раздела \"Новости\"");
        super.checkScreenIsLoaded();
        waitForElement(withText(R.string.news), DEFAULT_TIMEOUT);
        newsScreen.title.check(matches(isDisplayed()));
        newsScreen.filterButton.check(matches(isDisplayed()));
        newsScreen.sortButton.check(matches(isDisplayed()));
        newsScreen.editButton.check(matches(isDisplayed()));
        newsScreen.listOfNews.check(matches(isDisplayed()));
    }

    public void checkCollapsedNewsItemIsDisplayed(News news) {
        checkCollapsedNewsItemIsDisplayed(news.getTitle(), news.getPublicationDate());
    }

    public void checkCollapsedNewsItemIsDisplayed(String title, String date) {
        Allure.step("Проверить наличие свернутой новости");
        newsScreen.getCollapsedNewsItem(title, date).check(matches(isDisplayed()));
    }

    public void checkCollapsedNewsItemIsNotExists(News news) {
        checkCollapsedNewsItemIsNotExists(news.getTitle(), news.getPublicationDate());
    }

    public void checkCollapsedNewsItemIsNotExists(String title, String date) {
        Allure.step("Проверить отсутствие свернутой новости");
        newsScreen.getCollapsedNewsItem(title, date).check(doesNotExist());
    }

    public void expandNewsItem(News news) {
        expandNewsItem(news.getTitle(), news.getPublicationDate());
    }

    public void expandNewsItem(String title, String date) {
        Allure.step("Развернуть новость");
        checkCollapsedNewsItemIsDisplayed(title, date);
        newsScreen.getCollapsedNewsItem(title, date).perform(click());
    }

    public void checkExpandedNewsItemIsDisplayed(News news) {
        checkExpandedNewsItemIsDisplayed(news.getTitle(), news.getPublicationDate(), news.getDescription());
    }

    public void checkExpandedNewsItemIsDisplayed(String title, String date, String description) {
        Allure.step("Проверить наличие развернутой новости");
        newsScreen.getExpandedNewsItem(title, date, description).check(matches(isDisplayed()));
    }

    public void checkExpandedNewsItemIsNotExists(News news) {
        checkExpandedNewsItemIsNotExists(news.getTitle(), news.getPublicationDate(), news.getDescription());
    }

    public void checkExpandedNewsItemIsNotExists(String title, String publicationDate, String description) {
        Allure.step("Проверить отсутствие развернутой новости");
        newsScreen.getExpandedNewsItem(title, publicationDate, description).check(doesNotExist());
    }

    public void goToFilters() {
        Allure.step("Перейти в \"Фильтры\"");
        newsScreen.filterButton.perform(click());
    }

    public void goToControlPanel() {
        Allure.step("Перейти в \"Панель управления\"");
        newsScreen.editButton.perform(click());
    }
}
