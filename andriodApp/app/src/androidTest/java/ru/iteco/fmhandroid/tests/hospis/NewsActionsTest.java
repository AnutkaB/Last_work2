package ru.iteco.fmhandroid.tests.hospis;

import static ru.iteco.fmhandroid.testdata.News.EMPTY_NEWS;
import static ru.iteco.fmhandroid.testdata.News.TODAY_NEWS;
import static ru.iteco.fmhandroid.testdata.News.TOMORROW_NEWS;
import static ru.iteco.fmhandroid.testdata.User.VALID_USER;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.testdata.News;
import ru.iteco.fmhandroid.tests.BaseTest;

@Feature(value = "Создание и изменение новостей")
public class NewsActionsTest extends BaseTest {

    private static News currentNews;

    @Before
    public void loginAndGoToControlPanelScreen() {
        loginScreenSteps.login(VALID_USER);
        mainScreenSteps.checkScreenIsLoaded();
        mainScreenSteps.goToNews();
        newsScreenSteps.checkScreenIsLoaded();
        newsScreenSteps.goToControlPanel();
        controlPanelScreenSteps.checkScreenIsLoaded();
    }

    @After
    public void deleteCreatedNews() {
        controlPanelScreenSteps.checkScreenIsLoaded();
        controlPanelScreenSteps.clickDeleteNewsButton(currentNews);
        controlPanelScreenSteps.checkExpandedNewsItemIsNotExists(currentNews);
    }

    @Test
    @DisplayName("Вкладка «Новости» - Развернуть новость созданную на сегодняшний день")
    public void createdTodayNewsDisplayedTest() {
        currentNews = TODAY_NEWS;
        controlPanelScreenSteps.goToCreateNews();
        createEditNewsScreenSteps.checkCreateScreenIsLoaded();
        createEditNewsScreenSteps.fillNewsFields(currentNews);
        createEditNewsScreenSteps.clickSaveButton();
        controlPanelScreenSteps.checkScreenIsLoaded();
        controlPanelScreenSteps.goToNewsFromMenu();
        newsScreenSteps.checkScreenIsLoaded();
        newsScreenSteps.expandNewsItem(currentNews);
        newsScreenSteps.checkExpandedNewsItemIsDisplayed(currentNews);
        newsScreenSteps.goToControlPanel();
    }

    @Test
    @DisplayName("Вкладка «Новости» - Проверить отсутствие новости созданной на завтрашний день")
    public void createdTomorrowNewsIsNotDisplayedTest() {
        currentNews = TOMORROW_NEWS;
        controlPanelScreenSteps.goToCreateNews();
        createEditNewsScreenSteps.checkCreateScreenIsLoaded();
        createEditNewsScreenSteps.fillNewsFields(currentNews);
        createEditNewsScreenSteps.clickSaveButton();
        controlPanelScreenSteps.checkScreenIsLoaded();
        controlPanelScreenSteps.goToNewsFromMenu();
        newsScreenSteps.checkScreenIsLoaded();
        newsScreenSteps.checkExpandedNewsItemIsNotExists(currentNews);
        newsScreenSteps.goToControlPanel();
    }

    @Test
    @DisplayName("Вкладка «Панель управления» - Создание новости с заполненными полями")
    public void createNewsTest() {
        currentNews = TODAY_NEWS;
        controlPanelScreenSteps.goToCreateNews();
        createEditNewsScreenSteps.checkCreateScreenIsLoaded();
        createEditNewsScreenSteps.fillNewsFields(currentNews);
        createEditNewsScreenSteps.clickSaveButton();
        controlPanelScreenSteps.checkScreenIsLoaded();
        controlPanelScreenSteps.expandNewsItem(currentNews);
        controlPanelScreenSteps.checkExpandedNewsItemIsDisplayed(currentNews);
        controlPanelScreenSteps.checkNewsIsActive(currentNews);
    }

    @Test
    @DisplayName("Вкладка «Панель управления» - Редактирование новости с заполненными полями")
    public void editNewsTest() {
        currentNews = TODAY_NEWS;
        controlPanelScreenSteps.goToCreateNews();
        createEditNewsScreenSteps.checkCreateScreenIsLoaded();
        createEditNewsScreenSteps.fillNewsFields(currentNews);
        createEditNewsScreenSteps.clickSaveButton();
        controlPanelScreenSteps.checkScreenIsLoaded();
        controlPanelScreenSteps.clickEditNewsButton(currentNews);
        createEditNewsScreenSteps.checkEditScreenIsLoaded();

        currentNews = TOMORROW_NEWS;
        createEditNewsScreenSteps.fillNewsFields(currentNews);
        createEditNewsScreenSteps.clickSwitcher();
        createEditNewsScreenSteps.clickSaveButton();
        controlPanelScreenSteps.checkScreenIsLoaded();
        controlPanelScreenSteps.checkExpandedNewsItemIsNotExists(TODAY_NEWS);
        controlPanelScreenSteps.expandNewsItem(currentNews);
        controlPanelScreenSteps.checkExpandedNewsItemIsDisplayed(currentNews);
        controlPanelScreenSteps.checkNewsIsNotActive(currentNews);
    }

    @Test
    @DisplayName("Вкладка «Панель управления» - Редактирование новости с пустыми полями")
    public void editNewsWithEmptyFieldsTest() {
        currentNews = TODAY_NEWS;
        controlPanelScreenSteps.goToCreateNews();
        createEditNewsScreenSteps.checkCreateScreenIsLoaded();
        createEditNewsScreenSteps.fillNewsFields(currentNews);
        createEditNewsScreenSteps.clickSaveButton();
        controlPanelScreenSteps.checkScreenIsLoaded();
        controlPanelScreenSteps.clickEditNewsButton(currentNews);
        createEditNewsScreenSteps.checkEditScreenIsLoaded();
        createEditNewsScreenSteps.fillNewsFields(EMPTY_NEWS);
        createEditNewsScreenSteps.clickSaveButton();
        createEditNewsScreenSteps.checkEmptyFieldsToastIsDisplayed();
        createEditNewsScreenSteps.clickCancelButton();
        controlPanelScreenSteps.checkScreenIsLoaded();
        controlPanelScreenSteps.expandNewsItem(currentNews);
        controlPanelScreenSteps.checkExpandedNewsItemIsDisplayed(currentNews);
    }
}
