package ru.iteco.fmhandroid.tests.hospis;

import static ru.iteco.fmhandroid.testdata.CommonTestData.TODAY;
import static ru.iteco.fmhandroid.testdata.News.TODAY_NEWS;
import static ru.iteco.fmhandroid.testdata.News.TOMORROW_NEWS;
import static ru.iteco.fmhandroid.testdata.User.VALID_USER;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Story;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.tests.BaseTest;

@Feature(value = "Фильтрация новостей")
public class FilterNewsTest extends BaseTest {

    @Before
    public void loginAndCreateNews() {
        loginScreenSteps.login(VALID_USER);
        mainScreenSteps.checkScreenIsLoaded();
        mainScreenSteps.goToNews();
        newsScreenSteps.checkScreenIsLoaded();
        newsScreenSteps.goToControlPanel();
        controlPanelScreenSteps.checkScreenIsLoaded();
        controlPanelScreenSteps.goToCreateNews();
        createEditNewsScreenSteps.checkCreateScreenIsLoaded();
        createEditNewsScreenSteps.fillNewsFields(TODAY_NEWS);
        createEditNewsScreenSteps.clickSaveButton();
        controlPanelScreenSteps.checkScreenIsLoaded();
        controlPanelScreenSteps.goToCreateNews();
        createEditNewsScreenSteps.checkCreateScreenIsLoaded();
        createEditNewsScreenSteps.fillNewsFields(TOMORROW_NEWS);
        createEditNewsScreenSteps.clickSaveButton();
        controlPanelScreenSteps.checkScreenIsLoaded();
    }

    @After
    public void deleteCreatedNews() {
        controlPanelScreenSteps.checkScreenIsLoaded();
        controlPanelScreenSteps.clickDeleteNewsButton(TODAY_NEWS);
        controlPanelScreenSteps.checkExpandedNewsItemIsNotExists(TODAY_NEWS);
        controlPanelScreenSteps.checkScreenIsLoaded();
        controlPanelScreenSteps.clickDeleteNewsButton(TOMORROW_NEWS);
        controlPanelScreenSteps.checkExpandedNewsItemIsNotExists(TOMORROW_NEWS);
    }

    @Test
    @DisplayName("Вкладка «Новости» - фильтрация по дате")
    public void filterNewsByDateOnNewsScreenTest() {
        controlPanelScreenSteps.goToNewsFromMenu();
        newsScreenSteps.checkScreenIsLoaded();
        newsScreenSteps.goToFilters();
        filtersScreenSteps.checkNewsFilterScreenIsLoaded();
        filtersScreenSteps.fillDateFields(TODAY, TODAY);
        filtersScreenSteps.checkNewsFilterScreenIsLoaded();
        filtersScreenSteps.clickFilterButton();
        newsScreenSteps.checkScreenIsLoaded();
        newsScreenSteps.checkCollapsedNewsItemIsDisplayed(TODAY_NEWS);
        newsScreenSteps.checkCollapsedNewsItemIsNotExists(TOMORROW_NEWS);
        newsScreenSteps.goToControlPanel();
    }

    @Test
    @DisplayName("Вкладка «Панель управления» - фильтрация по дате")
    public void filterNewsByDateOnControlPanelScreenTest() {
        controlPanelScreenSteps.goToFilters();
        filtersScreenSteps.checkNewsFilterScreenIsLoaded();
        filtersScreenSteps.fillDateFields(TODAY, TODAY);
        filtersScreenSteps.clickFilterButton();
        controlPanelScreenSteps.checkScreenIsLoaded();
        controlPanelScreenSteps.checkCollapsedNewsItemIsDisplayed(TODAY_NEWS);
        controlPanelScreenSteps.checkCollapsedNewsItemIsNotExists(TOMORROW_NEWS);
        controlPanelScreenSteps.goToFilters();
        filtersScreenSteps.checkNewsFilterScreenIsLoaded();
        filtersScreenSteps.clearDateFields();
        filtersScreenSteps.clickFilterButton();
    }

    @Test
    @DisplayName("Вкладка «Панель управления» - фильтрация по параметру \"Активный\"")
    public void filterNewsByActiveOnControlPanelScreenTest() {
        controlPanelScreenSteps.clickEditNewsButton(TODAY_NEWS);
        createEditNewsScreenSteps.checkEditScreenIsLoaded();
        createEditNewsScreenSteps.clickSwitcher();
        createEditNewsScreenSteps.clickSaveButton();
        controlPanelScreenSteps.checkScreenIsLoaded();
        controlPanelScreenSteps.goToFilters();
        filtersScreenSteps.checkNewsFilterScreenIsLoaded();
        filtersScreenSteps.clickNotActiveCheckbox();
        filtersScreenSteps.clickFilterButton();
        controlPanelScreenSteps.checkScreenIsLoaded();
        controlPanelScreenSteps.checkCollapsedNewsItemIsNotExists(TODAY_NEWS);
        controlPanelScreenSteps.checkCollapsedNewsItemIsDisplayed(TOMORROW_NEWS);
        controlPanelScreenSteps.goToFilters();
        filtersScreenSteps.checkNewsFilterScreenIsLoaded();
        filtersScreenSteps.clearDateFields();
        filtersScreenSteps.clickFilterButton();
    }
}
