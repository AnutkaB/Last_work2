package ru.iteco.fmhandroid.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.utils.CustomMatchers.waitForElement;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.screens.ControlPanelScreen;
import ru.iteco.fmhandroid.testdata.News;

public class ControlPanelScreenSteps extends NavigationBarSteps {

    private final ControlPanelScreen controlPanelScreen = new ControlPanelScreen();

    public void checkScreenIsLoaded() {
        Allure.step("Проверка отображения раздела \"Панель управления\"");
        super.checkScreenIsLoaded();
        waitForElement(withText(R.string.news_control_panel), DEFAULT_TIMEOUT);
        controlPanelScreen.title.check(matches(isDisplayed()));
        controlPanelScreen.filterButton.check(matches(isDisplayed()));
        controlPanelScreen.sortButton.check(matches(isDisplayed()));
        controlPanelScreen.addButton.check(matches(isDisplayed()));
        controlPanelScreen.listOfNews.check(matches(isDisplayed()));
    }

    public void checkCollapsedNewsItemIsDisplayed(News news) {
        checkCollapsedNewsItemIsDisplayed(news.getTitle(), news.getPublicationDate(), news.getAuthor());
    }

    public void checkCollapsedNewsItemIsDisplayed(String title, String publicationDate, String author) {
        Allure.step("Проверить наличие свернутой новости");
        controlPanelScreen.getCollapsedNewsItem(title, publicationDate, author).check(matches(isDisplayed()));
    }

    public void checkCollapsedNewsItemIsNotExists(News news) {
        checkCollapsedNewsItemIsNotExists(news.getTitle(), news.getPublicationDate(), news.getAuthor());
    }

    public void checkCollapsedNewsItemIsNotExists(String title, String publicationDate, String author) {
        Allure.step("Проверить отсутствие свернутой новости");
        controlPanelScreen.getCollapsedNewsItem(title, publicationDate, author).check(doesNotExist());
    }

    public void expandNewsItem(News news) {
        expandNewsItem(news.getTitle(), news.getPublicationDate(), news.getAuthor());
    }

    public void expandNewsItem(String title, String publicationDate, String author) {
        Allure.step("Развернуть новость");
        checkCollapsedNewsItemIsDisplayed(title, publicationDate, author);
        controlPanelScreen.getCollapsedNewsItem(title, publicationDate, author).perform(click());
    }

    public void checkExpandedNewsItemIsDisplayed(News news) {
        checkExpandedNewsItemIsDisplayed(news.getTitle(), news.getPublicationDate(), news.getAuthor(), news.getDescription());
    }

    public void checkExpandedNewsItemIsDisplayed(String title, String publicationDate, String author, String description) {
        Allure.step("Проверить наличие развернутой новости");
        controlPanelScreen.getExpandedNewsItem(title, publicationDate, author, description).check(matches(isDisplayed()));
    }

    public void checkExpandedNewsItemIsNotExists(News news) {
        checkExpandedNewsItemIsNotExists(news.getTitle(), news.getPublicationDate(), news.getAuthor(), news.getDescription());
    }

    public void checkExpandedNewsItemIsNotExists(String title, String publicationDate, String author, String description) {
        Allure.step("Проверить отсутствие развернутой новости");
        controlPanelScreen.getExpandedNewsItem(title, publicationDate, author, description).check(doesNotExist());
    }

    public void goToFilters() {
        Allure.step("Перейти в \"Фильтры\"");
        controlPanelScreen.filterButton.perform(click());
    }

    public void goToCreateNews() {
        Allure.step("Перейти в \"Создание новости\"");
        controlPanelScreen.addButton.perform(click());
    }

    public void clickDeleteNewsButton(News news) {
        clickDeleteNewsButton(news.getTitle());
    }

    public void clickDeleteNewsButton(String title) {
        Allure.step("Удалить новость");
        controlPanelScreen.getNewsDeleteButton(title).check(matches(isDisplayed()));
        controlPanelScreen.getNewsDeleteButton(title).perform(click());
        controlPanelScreen.okButton.perform(click());
    }

    public void clickEditNewsButton(News news) {
        clickEditNewsButton(news.getTitle());
    }

    public void clickEditNewsButton(String title) {
        Allure.step("Открыть \"Редактирование новости\"");
        controlPanelScreen.getNewsEditButton(title).check(matches(isDisplayed()));
        controlPanelScreen.getNewsEditButton(title).perform(click());
    }

    public void checkNewsIsActive(News news) {
        checkNewsIsActive(news.getTitle());
    }

    public void checkNewsIsActive(String title) {
        Allure.step("Проверить, что новость активна");
        controlPanelScreen.getNewsStatus(title).check(matches(isDisplayed()));
        controlPanelScreen.getNewsStatus(title).check(matches(withText(R.string.news_control_panel_active)));
    }

    public void checkNewsIsNotActive(News news) {
        checkNewsIsNotActive(news.getTitle());
    }

    public void checkNewsIsNotActive(String title) {
        Allure.step("Проверить, что новость неактивна");
        controlPanelScreen.getNewsStatus(title).check(matches(isDisplayed()));
        controlPanelScreen.getNewsStatus(title).check(matches(withText(R.string.news_control_panel_not_active)));
    }
}
