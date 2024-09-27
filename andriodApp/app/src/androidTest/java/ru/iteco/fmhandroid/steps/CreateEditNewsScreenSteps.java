package ru.iteco.fmhandroid.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.utils.CustomMatchers.waitForElement;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.screens.CreateEditNewsScreen;
import ru.iteco.fmhandroid.testdata.News;

public class CreateEditNewsScreenSteps extends BaseSteps {

    private final CreateEditNewsScreen createEditNewsScreen = new CreateEditNewsScreen();

    public void checkCreateScreenIsLoaded() {
        Allure.step("Проверка отображения раздела \"Создание новости\"");
        checkScreenIsLoaded();
        createEditNewsScreen.creatingTitle.check(matches(isDisplayed()));
    }

    public void checkEditScreenIsLoaded() {
        Allure.step("Проверка отображения раздела \"Редактирование новости\"");
        checkScreenIsLoaded();
        createEditNewsScreen.editingTitle.check(matches(isDisplayed()));
    }

    public void fillNewsFields(News news) {
        fillNewsFields(news.getCategory(), news.getTitle(), news.getPublicationDate(), news.getTime(), news.getDescription());
    }

    public void fillNewsFields(String category, String title, String publicationDate, String time, String description) {
        Allure.step("Заполнить поля новости");
        createEditNewsScreen.categoryField.perform(replaceText(category));
        createEditNewsScreen.titleField.perform(replaceText(title));
        createEditNewsScreen.publicationDateField.perform(replaceText(publicationDate));
        createEditNewsScreen.timeField.perform(replaceText(time));
        createEditNewsScreen.descriptionField.perform(replaceText(description));
    }

    public void clickSaveButton() {
        Allure.step("Сохранить изменения");
        createEditNewsScreen.saveButton.perform(click());
    }

    public void clickCancelButton() {
        Allure.step("Отменить изменения");
        createEditNewsScreen.cancelButton.perform(click());
        createEditNewsScreen.okButton.check(matches(isDisplayed()));
        createEditNewsScreen.okButton.perform(click());
    }

    public void clickSwitcher() {
        Allure.step("Переключить свитчер активации новости");
        createEditNewsScreen.switcher.perform(click());
    }

    public void checkEmptyFieldsToastIsDisplayed() {
        Allure.step("Проверка отображения уведомления \"Заполните пустые поля\"");
        createEditNewsScreen.emptyFieldsToast.check(matches(isDisplayed()));
    }

    private void checkScreenIsLoaded() {
        waitForElement(withText(R.string.news), DEFAULT_TIMEOUT);
        createEditNewsScreen.title.check(matches(isDisplayed()));
        createEditNewsScreen.categoryField.check(matches(isDisplayed()));
        createEditNewsScreen.titleField.check(matches(isDisplayed()));
        createEditNewsScreen.publicationDateField.check(matches(isDisplayed()));
        createEditNewsScreen.timeField.check(matches(isDisplayed()));
        createEditNewsScreen.descriptionField.check(matches(isDisplayed()));
        createEditNewsScreen.switcher.check(matches(isDisplayed()));
        createEditNewsScreen.saveButton.check(matches(isDisplayed()));
        createEditNewsScreen.cancelButton.check(matches(isDisplayed()));
    }
}
