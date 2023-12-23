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
import static org.hamcrest.Matchers.allOf;
import static ru.filippova.helpers.Helpers.childAtPosition;
import static ru.filippova.helpers.Helpers.waitId;

import androidx.test.espresso.Espresso;

import ru.filippova.helpers.Helpers;
import ru.filippova.module.ClaimCreate;
import ru.filippova.module.ClaimPanel;
import ru.filippova.module.MainScreen;
import ru.filippova.module.NewsCreate;
import ru.iteco.fmhandroid.R;

public class ClaimCreateSteps {

    private static String date = Helpers.currentDay();
    private static String time = Helpers.currentTime();
    private static String firstLettersExecutor = "Ivano";
    private static String latinExecutor = "Ivanov Ivan";
    private static String cyrillicExecutor = "Иванов Иван";
    private static String validTitle = "Развесить все новогодние украшения";
    private static String emptyTitle = " ";
    private static String validDescription = "Пора запасаться мандаринками";
    private static String dateInFuture = "20.09.2025";
    private static String dateInPast = "20.09.2000";


    public static void goToClaimCreate() {
        MainScreenSteps.mainScreenLoad();
        MainScreen.createClaim.perform(click());
        claimCreateScreenLoad();
        isClaimCreate();
    }

    public static void goTOClaimCreateFromClaimPanel() {
        MainScreenSteps.mainScreenLoad();
        MainScreenSteps.allClaimsLinkClick();
        ClaimPanelSteps.isClaimPanel();
        ClaimPanelSteps.goToClaimCreate();
        ClaimCreateSteps.isClaimCreate();
    }

    public static void goToClaimCreateFromClaimPanel() {
        MainScreenSteps.mainScreenLoad();
        MainScreenSteps.allClaimsLinkClick();
        ClaimPanelSteps.goToClaimCreate();
    }

    public static void claimCreateScreenLoad() {
        onView(isRoot()).perform(waitId(R.id.executor_drop_menu_auto_complete_text_view, 7000));
    }

    public static void isClaimCreate() {
        ClaimCreate.executorField.check(matches(isDisplayed()));
    }

    public static void claimCreateElementsCheck() {
        ClaimCreate.screenTitle.check(matches(isDisplayed()));
        ClaimCreate.screenTitle.check(matches(withText("Создание")));
        ClaimCreate.claimTitle.check(matches(isDisplayed()));
        ClaimCreate.executorField.check(matches(isDisplayed()));
        ClaimCreate.claimDate.check(matches(isDisplayed()));
        ClaimCreate.claimTime.check(matches(isDisplayed()));
        ClaimCreate.newsDescription.check(matches(isDisplayed()));
        ClaimCreate.saveButton.check(matches(isDisplayed()));
        ClaimCreate.saveButton.check(matches(withText("Сохранить")));
        ClaimCreate.cancelButton.check(matches(isDisplayed()));
        ClaimCreate.cancelButton.check(matches(withText("Отмена")));
    }

    public static void typeTitle(String title) {
        ClaimCreate.claimTitle.perform(click());
        ClaimCreate.claimTitle.perform(replaceText(title), closeSoftKeyboard());
    }

    public static void selectExecutor() {
        ClaimCreate.executorField.perform(click());
        ClaimCreate.executorListItem.inRoot(Helpers.isPopupWindow()).check(matches(isDisplayed()));
        ClaimCreate.executorListItem.inRoot(Helpers.isPopupWindow()).perform(click());
    }

    public static void typeCyrillicExecutor() {
        ClaimCreate.executorField.perform(replaceText(cyrillicExecutor));
    }

    public static void typeLatinExecutor() {
        ClaimCreate.executorField.perform(replaceText(latinExecutor));
    }

    public static void typeDate() {
        ClaimCreate.claimDate.perform(replaceText(date));
        ClaimCreate.claimDate.check(matches(withText(date)));
    }

    public static void typeRandomDate() {
        ClaimCreate.claimDate.perform(replaceText(dateInFuture));
    }

    public static void typeDateFromPast() {
        ClaimCreate.claimDate.perform(replaceText(dateInPast));
    }

    public static void typeTime() {
        ClaimCreate.claimTime.perform(replaceText(time));
        ClaimCreate.claimTime.check(matches(withText(time)));
    }

    public static void typeDescription(String description) {
        ClaimCreate.newsDescription.perform(click());
        ClaimCreate.newsDescription.perform(replaceText(description), closeSoftKeyboard());

    }

    public static void clickSaveButton() {
        ClaimCreate.saveButton.perform(click());
    }

    public static void createClaim() {
        typeTitle(validTitle);
        typeTime();
        typeDescription(validDescription);
        Espresso.closeSoftKeyboard();
    }

    public static void createValidClaim() {
        selectExecutor();
        typeTitle(validTitle);
        typeDate();
        typeTime();
        typeDescription(validDescription);
        Espresso.closeSoftKeyboard();
    }

    public static void createNewsWithPastDate() {
        selectExecutor();
        typeTitle(validTitle);
        typeDateFromPast();
        typeTime();
        typeDescription(validDescription);
        Espresso.closeSoftKeyboard();
    }

    public static void createNewsWithEmptyField() {
        selectExecutor();
        typeDate();
        typeTime();
        typeDescription(validDescription);
    }

    public static void clickConfirmationForCancelButton() {
        ClaimCreate.cancelButton.check(matches(isDisplayed()));
        ClaimCreate.cancelButton.perform(click());
        ClaimCreate.cancelButtonConfirmation.check(matches(isDisplayed()));
        ClaimCreate.cancelButtonConfirmation.perform(click());
    }

    public static void clickCancelForCancelButton () {
        ClaimCreate.cancelButton.check(matches(isDisplayed()));
        ClaimCreate.cancelButton.perform(click());
        ClaimCreate.cancelButtonForConfirmation.check(matches(isDisplayed()));
        ClaimCreate.cancelButtonForConfirmation.perform(click());
    }

    public static void checkErrorWindow() {
        ClaimCreate.errorMessage.check(matches(isDisplayed()));
        ClaimCreate.errorMessage.check(matches(withText("Заполните пустые поля")));
        ClaimCreate.errorOk.check(matches(isDisplayed()));
        ClaimCreate.errorOk.check(matches(withText("OK")));

    }

    public static String getClaimTitle() {
        return Helpers.getText(onView(withId(R.id.title_edit_text)));
    }

    public static String getClaimDate() {
        return Helpers.getText(onView(withId(R.id.date_in_plan_text_input_edit_text)));
    }

    public static String getClaimTime() {
        return Helpers.getText(onView(withId(R.id.time_in_plan_text_input_edit_text)));
    }

    public static String getClaimDescription() {
        return Helpers.getText(onView(withId(R.id.description_edit_text)));
    }
}
