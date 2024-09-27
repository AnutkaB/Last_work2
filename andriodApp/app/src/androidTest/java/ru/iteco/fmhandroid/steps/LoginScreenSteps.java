package ru.iteco.fmhandroid.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.utils.CustomMatchers.waitForElement;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.screens.LoginScreen;
import ru.iteco.fmhandroid.testdata.User;

public class LoginScreenSteps extends BaseSteps {

    private final LoginScreen loginScreen = new LoginScreen();

    public void checkScreenIsLoaded() {
        Allure.step("Проверка отображения экрана авторизации");
        waitForElement(withText(R.string.authorization), DEFAULT_TIMEOUT);
        loginScreen.title.check(matches(isDisplayed()));
        loginScreen.loginField.check(matches(isDisplayed()));
        loginScreen.passwordField.check(matches(isDisplayed()));
        loginScreen.signInButton.check(matches(isDisplayed()));
    }

    public void login(User user) {
        Allure.step("Авторизоваться по логину и паролю");
        enterText(loginScreen.loginField, user.getLogin());
        enterText(loginScreen.passwordField, user.getPassword());
        loginScreen.signInButton.perform(click());
    }

    public void checkEmptyUserDataToastIsDisplayed() {
        Allure.step("Проверка отображения уведомления \"Логин и Пароль не могут быть пустыми\"");
        loginScreen.emptyUserDataToast.check(matches(isDisplayed()));
    }

    public void checkWrongUserDataToastIsDisplayed() {
        Allure.step("Проверка отображения уведомления \"Неверный логин или пароль\"");
        loginScreen.wrongUserDataToast.check(matches(isDisplayed()));
    }

    private void enterText(ViewInteraction field, String text) {
        field.perform(click());
        field.perform(replaceText(text));
    }
}
