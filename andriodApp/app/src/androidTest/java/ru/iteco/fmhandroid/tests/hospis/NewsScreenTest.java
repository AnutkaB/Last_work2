package ru.iteco.fmhandroid.tests.hospis;

import static ru.iteco.fmhandroid.testdata.User.VALID_USER;

import org.junit.Before;
import org.junit.Test;

import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.tests.BaseTest;

@Feature(value = "Вкладка «Новости»")
public class NewsScreenTest extends BaseTest {

    @Before
    public void beforeEach() {
        loginScreenSteps.login(VALID_USER);
        mainScreenSteps.checkScreenIsLoaded();
        mainScreenSteps.goToNews();
        newsScreenSteps.checkScreenIsLoaded();
    }

    @Test
    @DisplayName("Переход на вкладку «Главная» через меню навигации")
    public void goToNewsFromMenuTest() {
        newsScreenSteps.goToMainFromMenu();
        mainScreenSteps.checkScreenIsLoaded();
    }

    @Test
    @DisplayName("Переход на вкладку «О приложении» через меню навигации")
    public void goToAboutFromMenuTest() {
        newsScreenSteps.goToAboutFromMenu();
        aboutScreenSteps.checkScreenIsLoaded();
    }

    @Test
    @DisplayName("Переход на вкладку «Цитаты»")
    public void goToOurMissionTest() {
        newsScreenSteps.goToOurMission();
        ourMissionScreenSteps.checkScreenIsLoaded();
    }
}
