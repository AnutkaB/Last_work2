package ru.iteco.fmhandroid.tests.hospis;

import static ru.iteco.fmhandroid.testdata.User.VALID_USER;

import org.junit.Before;
import org.junit.Test;

import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.tests.BaseTest;

@Feature(value = "Вкладка «Главная»")
public class MainScreenTest extends BaseTest {

    @Before
    public void beforeEach() {
        loginScreenSteps.login(VALID_USER);
        mainScreenSteps.checkScreenIsLoaded();
    }

    @Test
    @DisplayName("Переход на вкладку «Новости» с главной страницы")
    public void goToNewsFromMainScreenTest() {
        mainScreenSteps.goToNews();
        newsScreenSteps.checkScreenIsLoaded();
    }

    @Test
    @DisplayName("Переход на вкладку «Новости» через меню навигации")
    public void goToNewsFromMenuTest() {
        mainScreenSteps.goToNewsFromMenu();
        newsScreenSteps.checkScreenIsLoaded();
    }

    @Test
    @DisplayName("Переход на вкладку «О приложении» через меню навигации")
    public void goToAboutFromMenuTest() {
        mainScreenSteps.goToAboutFromMenu();
        aboutScreenSteps.checkScreenIsLoaded();
    }

    @Test
    @DisplayName("Переход на вкладку «Цитаты»")
    public void goToOurMissionTest() {
        mainScreenSteps.goToOurMission();
        ourMissionScreenSteps.checkScreenIsLoaded();
    }
}
