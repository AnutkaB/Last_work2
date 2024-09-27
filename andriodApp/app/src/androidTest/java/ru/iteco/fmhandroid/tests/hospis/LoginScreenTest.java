package ru.iteco.fmhandroid.tests.hospis;

import static ru.iteco.fmhandroid.testdata.User.UNREGISTERED_USER;
import static ru.iteco.fmhandroid.testdata.User.USER_WITHOUT_LOGIN;
import static ru.iteco.fmhandroid.testdata.User.USER_WITHOUT_PASSWORD;
import static ru.iteco.fmhandroid.testdata.User.VALID_USER;

import org.junit.Test;

import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.tests.BaseTest;

@Feature(value = "Авторизация")
public class LoginScreenTest extends BaseTest {

    @Test
    @DisplayName("Авторизация в приложении под валидными учетными данными")
    public void loginWithValidUserTest() {
        loginScreenSteps.login(VALID_USER);
        mainScreenSteps.checkScreenIsLoaded();
    }

    @Test
    @DisplayName("Выход из учетной записи")
    public void logoutTest() {
        loginScreenSteps.login(VALID_USER);
        mainScreenSteps.checkScreenIsLoaded();
        mainScreenSteps.logOut();
    }

    @Test
    @DisplayName("Авторизация в мобильном приложении при пустом поле \"Логин\"")
    public void loginWithEmptyLoginTest() {
        loginScreenSteps.login(USER_WITHOUT_LOGIN);
        loginScreenSteps.checkEmptyUserDataToastIsDisplayed();
    }

    @Test
    @DisplayName("Авторизация в мобильном приложении при пустом поле \"Пароль\"")
    public void loginWithEmptyPasswordTest() {
        loginScreenSteps.login(USER_WITHOUT_PASSWORD);
        loginScreenSteps.checkEmptyUserDataToastIsDisplayed();
    }

    @Test
    @DisplayName("Авторизация в мобильном приложении незарегистрированного пользователя")
    public void loginWithUnregisteredUserTest() {
        loginScreenSteps.login(UNREGISTERED_USER);
        loginScreenSteps.checkWrongUserDataToastIsDisplayed();
    }
}
