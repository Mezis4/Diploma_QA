package ru.filippova.steps;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static ru.filippova.helpers.Helpers.childAtPosition;
import static ru.filippova.helpers.Helpers.waitId;
import static ru.filippova.helpers.Helpers.withIndex;

import androidx.test.espresso.ViewInteraction;

import ru.filippova.helpers.Helpers;
import ru.filippova.module.ClaimCard;
import ru.filippova.module.MainScreen;
import ru.iteco.fmhandroid.R;

public class ClaimCardSteps {

    private static String commentText = "лдпфывплфры";
    public static void goToClaimCardFromMain() {
        MainScreenSteps.mainScreenLoad();
        MainScreen.claimCardExpandButton.perform(click());
        claimCardLoad();
    }

    public static void claimCardLoad() {
        onView(isRoot()).perform(waitId(R.id.description_material_card_view, 7000));
    }

    public static void isClaimCard() {
        ClaimCard.claimDescription.check(matches(isDisplayed()));
    }

    public static void openClaimCardElementsCheck() {
        ClaimCard.claimTitle.check(matches(isDisplayed()));
        ClaimCard.claimTitle.check(matches(withText("Тема")));
        ClaimCard.claimTitleText.check(matches(isDisplayed()));
        ClaimCard.claimExecutor.check(matches(isDisplayed()));
        ClaimCard.claimExecutor.check(matches(withText("Исполнитель")));
        ClaimCard.claimExecutorText.check(matches(isDisplayed()));
        ClaimCard.claimPlaneDate.check(matches(isDisplayed()));
        ClaimCard.claimPlaneDate.check(matches(withText("Плановая дата")));
        ClaimCard.claimPlaneDateText.check(matches(isDisplayed()));
        ClaimCard.claimPlaneTimeText.check(matches(isDisplayed()));
        ClaimCard.claimStatus.check(matches(isDisplayed()));
        ClaimCard.claimDescription.check(matches(isDisplayed()));
        ClaimCard.claimDescriptionText.check(matches(isDisplayed()));
        ClaimCard.claimAuthor.check(matches(isDisplayed()));
        ClaimCard.claimAuthor.check(matches(withText("Автор")));
        ClaimCard.claimAuthorText.check(matches(isDisplayed()));
        ClaimCard.claimCreatedDate.check(matches(isDisplayed()));
        ClaimCard.claimCreatedDate.check(matches(withText("Создана")));
        ClaimCard.claimCreatedDateText.check(matches(isDisplayed()));
        ClaimCard.claimCreatedTimeText.check(matches(isDisplayed()));
        ClaimCard.addCommentButton.check(matches(isDisplayed()));
        ClaimCard.backButton.check(matches(isDisplayed()));
        ClaimCard.statusProcessingButton.check(matches(isDisplayed()));
        ClaimCard.claimEditButton.check(matches(isDisplayed()));
        ClaimCard.commentText.check(matches(isDisplayed()));
        ClaimCard.commentAuthor.check(matches(isDisplayed()));
        ClaimCard.editCommentImage.check(matches(isDisplayed()));
        ClaimCard.commentDateText.check(matches(isDisplayed()));
        ClaimCard.commentTimeText.check(matches(isDisplayed()));
    }

    public static void checkClaimStatus(String status) {
        ClaimCard.claimStatus.check(matches(isDisplayed()));
        ClaimCard.claimStatus.check(matches(withText(status)));
    }

    public static void isCommentScree() {
        ClaimCard.saveCommentButton.check(matches(isDisplayed()));
    }

    public static void checkCommentElements() {
        ClaimCard.commentInput.check(matches(isDisplayed()));
        ClaimCard.saveCommentButton.check(matches(isDisplayed()));
        ClaimCard.saveCommentButton.check(matches(withText("Сохранить")));
        ClaimCard.cancelButton.check(matches(isDisplayed()));
        ClaimCard.cancelButton.check(matches(withText("Отмена")));
    }

    public static void commentAddButton() {
        ClaimCard.addCommentButton.check(matches(isDisplayed()));
        ClaimCard.addCommentButton.perform(click());
    }

    public static void commentEditButton() {
        ClaimCard.editCommentImage.check(matches(isDisplayed()));
        ClaimCard.editCommentImage.perform(click());
    }

    public static void editClaimButton() {
        ClaimCard.claimEditButton.check(matches(isDisplayed()));
        ClaimCard.claimEditButton.perform(click());
    }

    public static void typeComment(String comment) {
        ClaimCard.commentInput.check(matches(isDisplayed()));
        ClaimCard.commentInput.perform(replaceText(comment));
    }

    public static void clickSaveButton() {
        ClaimCard.saveCommentButton.check(matches(isDisplayed()));
        ClaimCard.saveCommentButton.perform(click());
    }

    public static void clickCancelButton() {
        ClaimCard.cancelButton.check(matches(isDisplayed()));
        ClaimCard.cancelButton.perform(click());
    }

    public static void clickBackButton() {
        ClaimCard.backButton.check(matches(isDisplayed()));
        ClaimCard.backButton.perform(click());
    }

    public static String getCommentTextOnCommentCreate() {
        return Helpers.getText(onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.comment_text_input_layout),
                                0),
                        1))));
    }

    public static String getCommentTextOnClaimCard() {
        return Helpers.getText(onView(withIndex(withId(R.id.comment_description_text_view),0)));
    }

    public static String getTitleOnClaimCard() {
        return Helpers.getText(onView(withId(R.id.title_text_view)));
    }

    public static String getTimeOnClaimCard() {
        return Helpers.getText(onView(withId(R.id.plan_time_text_view)));
    }
    public static String getDateOnClaimCard() {
        return Helpers.getText(onView(withId(R.id.plane_date_text_view)));
    }

    public static String getDescriptionOnClaimCard() {
        return Helpers.getText(onView(withId(R.id.description_text_view)));
    }

    public static String getClaimStatus() {
        return Helpers.getText(onView(withId(R.id.status_label_text_view)));
    }

    public static void clickEditStatus() {
        ClaimCard.statusProcessingButton.check(matches(isDisplayed()));
        ClaimCard.statusProcessingButton.perform(click());
    }

    public static void clickInProgress() {
        ClaimCard.inProgressStatus.check(matches(isDisplayed()));
        ClaimCard.inProgressStatus.perform(click());
    }

    public static void clickExecute() {
        ClaimCard.executeStatus.check(matches(isDisplayed()));
        ClaimCard.executeStatus.perform(click());
    }

    public static void clickThrowOff() {
        ClaimCard.throwOff.check(matches(isDisplayed()));
        ClaimCard.throwOff.perform(click());
    }

    public static void clickCancelStatus() {
        ClaimCard.cancelStatus.check(matches(isDisplayed()));
        ClaimCard.cancelStatus.perform(click());
    }

    public static void typeCommentInWindow() {
        ClaimCard.editTextInExecute.check(matches(isDisplayed()));
        ClaimCard.editTextInExecute.perform(replaceText(commentText));
        ClaimCard.okButton.perform(click());
    }
}
