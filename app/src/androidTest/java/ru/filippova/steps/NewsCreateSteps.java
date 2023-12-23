package ru.filippova.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.filippova.helpers.Helpers.waitId;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;

import ru.filippova.helpers.Helpers;
import ru.filippova.module.ControlPanel;
import ru.filippova.module.NewsCreate;
import ru.iteco.fmhandroid.R;

public class NewsCreateSteps {

    private static String validCategoryFirstLetters = "Объя";
    private static String date = Helpers.currentDay();
    private static String time = Helpers.currentTime();
    private static String validTitle = "До Нового Года 13 дней";
    private static String emptyTitle = " ";
    private static String validDescription = "Пора запасаться мандаринками";
    private static String dateInFuture = "20.09.2025";
    private static String dateInPast = "20.09.2020";


    public static void goToNewsCreateScreen() {
        MainScreenSteps.mainScreenLoad();
        MainScreenSteps.isMainScreen();
        MainScreenSteps.allNewsLinkClick();
        NewsScreenSteps.clickOnControlPanelButton();
        ControlPanel.newsCreateButton.perform(click());
        waitForLoadNewsCreate();
    }

    public static void waitForLoadNewsCreate() {
        onView(isRoot()).perform(waitId(R.id.save_button, 7000));
    }

    public static void isNewsCreate() {
        NewsCreate.newsTitle.check(matches(isDisplayed()));
        NewsCreate.newsDescription.check(matches(isDisplayed()));
    }

    public static void isEditNews() {
        NewsCreate.newsTitle.check(matches(isDisplayed()));
        NewsCreate.newsDescription.check(matches(isDisplayed()));
    }

    public static void newsCreateElementCheck() {
        NewsCreate.screenTitle.check(matches(isDisplayed()));
        NewsCreate.screenTitle.check(matches(withText("Создание")));
        NewsCreate.categoryField.check(matches(isDisplayed()));
        NewsCreate.newsTitle.check(matches(isDisplayed()));
        NewsCreate.publicationDate.check(matches(isDisplayed()));
        NewsCreate.publicationTime.check(matches(isDisplayed()));
        NewsCreate.newsDescription.check(matches(isDisplayed()));
        NewsCreate.newsSwitcher.check(matches(isDisplayed()));
        NewsCreate.newsSwitcher.check(matches(withText("Активна")));
        NewsCreate.saveButton.check(matches(isDisplayed()));
        NewsCreate.saveButton.check(matches(withText("Сохранить")));
        NewsCreate.cancelButton.check(matches(isDisplayed()));
        NewsCreate.cancelButton.check(matches(withText("Отмена")));
    }

    public static void selectItemFromCategoryList() {
        /*NewsCreate.categoryField.check(matches(isDisplayed()));
        NewsCreate.categoryField.perform(replaceText(validCategoryFirstLetters));*/
        NewsCreate.categoryOpenListButton.perform(click());
        NewsCreate.categoryListItem.inRoot(Helpers.isPopupWindow()).check(matches(isDisplayed()));
        NewsCreate.categoryListItem.inRoot(Helpers.isPopupWindow()).perform(click());
    }

    public static void typeCategory(String category) {
        NewsCreate.categoryField.perform(click());
        NewsCreate.categoryField.perform(replaceText(category), closeSoftKeyboard());
    }

    public static void typeTitle(String title) {
        NewsCreate.newsTitle.perform(click());
        NewsCreate.newsTitle.perform(replaceText(title), closeSoftKeyboard());
    }

    public static void typeDate() {
        NewsCreate.publicationDate.perform(replaceText(date));
        NewsCreate.publicationDate.check(matches(withText(date)));
    }

    public static void typeRandomDate() {
        NewsCreate.publicationDate.perform(replaceText(dateInFuture));
    }

    public static void typeDateFromPast() {
        NewsCreate.publicationDate.perform(replaceText(dateInPast));
    }



    public static void typeTime() {
        NewsCreate.publicationTime.perform(replaceText(time));
        NewsCreate.publicationTime.check(matches(withText(time)));
    }

    public static void typeDescription(String description) {
        NewsCreate.newsDescription.perform(click());
        NewsCreate.newsDescription.perform(replaceText(description), closeSoftKeyboard());

    }

    public static void clickSaveButton() {
        NewsCreate.saveButton.perform(click());
    }

    public static void createNews() {
        selectItemFromCategoryList();
        typeTitle(validTitle);
        typeTime();;
        typeDescription(validDescription);
        Espresso.closeSoftKeyboard();
    }

    public static void createNewsWithPastDate() {
        selectItemFromCategoryList();
        typeTitle(validTitle);
        typeDateFromPast();
        typeTime();
        typeDescription(validDescription);
        Espresso.closeSoftKeyboard();
    }

    public static void createNewsWithEmptyField() {
        typeCategory(validTitle);
        typeDate();
        typeTime();
        typeDescription(validDescription);
    }

    public static void clickConfirmationForCancelButton() {
        NewsCreate.cancelButton.check(matches(isDisplayed()));
        NewsCreate.cancelButton.perform(click());
        NewsCreate.cancelButtonConfirmation.check(matches(isDisplayed()));
        NewsCreate.cancelButtonConfirmation.perform(click());
    }

    public static void clickCancelForCancelButton () {
        NewsCreate.cancelButton.check(matches(isDisplayed()));
        NewsCreate.cancelButton.perform(click());
        NewsCreate.cancelButtonForConfirmation.check(matches(isDisplayed()));
        NewsCreate.cancelButtonForConfirmation.perform(click());
    }

    public static void clickSwitcherButton() {
        NewsCreate.newsSwitcher.check(matches(isDisplayed()));
        NewsCreate.newsSwitcher.perform(click());
    }

    public static void makeEmptyFieldInEdit() {
        NewsCreateSteps.typeTitle(emptyTitle);
    }

    public static String getNewsCategory() {
        return Helpers.getText(onView(withId(R.id.news_item_category_text_auto_complete_text_view)));
    }

    public static String getNewsTitle() {
        return Helpers.getText(onView(withId(R.id.news_item_title_text_input_edit_text)));
    }

    public static String getNewsDate() {
        return Helpers.getText(onView(withId(R.id.news_item_publish_date_text_input_edit_text)));
    }

    public static String getNewsTime() {
        return Helpers.getText(onView(withId(R.id.news_item_publish_time_text_input_edit_text)));
    }

    public static String getNewsDescription() {
        return Helpers.getText(onView(withId(R.id.news_item_description_text_input_edit_text)));
    }

    public static String getNewsStatus() {
        return Helpers.getText(onView(withId(R.id.switcher)));
    }
}
