package ru.filippova.tests;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import ru.filippova.module.ClaimCard;
import ru.filippova.steps.AuthorizationSteps;
import ru.filippova.steps.ClaimCardSteps;
import ru.filippova.steps.ClaimCreateSteps;
import ru.filippova.steps.ClaimPanelSteps;
import ru.filippova.steps.MainScreenSteps;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClaimCardTests {

    private static String cyrillicWithNumbers = "До Нового Года 7 дней";
    private static String latinComment = "Merry Christmas";
    private static String cyrillicComment = "Пора запасаться мандаринками";
    private static String cyrillicWithSymbols = "Блоб *()-:";
    private static String openStatus = "Открыта";
    private static String inProgressStatus = "В работе";
    private static String executeStatus = "Выполнена";
    private static String cancelStatus = "Отменена";

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);


    @Test
    public void a_setUp() {
        AuthorizationSteps.login();
    }

    @Test
    public void claimCardElementsCheck() {
        ClaimCardSteps.goToClaimCardFromMain();

        /** проверка элементов экрана */
        ClaimCardSteps.openClaimCardElementsCheck();

        /** переход к редактированию комментария и возвращение назад через системную кнопку */
        ClaimCardSteps.commentEditButton();
        Espresso.pressBackUnconditionally();
        ClaimCardSteps.claimCardLoad();

        /** переход к созданию комментария и возвращение назад через системную кнопку */
        ClaimCardSteps.commentAddButton();
        Espresso.pressBackUnconditionally();
        ClaimCardSteps.claimCardLoad();

        /** переход к редактированию заявки и возвращение назад через системную кнопку */
        ClaimCardSteps.editClaimButton();
        Espresso.pressBackUnconditionally();
        ClaimCardSteps.claimCardLoad();
    }

    @Test
    public void commentItemChecks() {
        ClaimCardSteps.goToClaimCardFromMain();
        ClaimCardSteps.commentAddButton();

        /** проверка элементов на экране комментарий */
        ClaimCardSteps.checkCommentElements();

        /** ввод кириллицы в "описание" */
        ClaimCardSteps.typeComment(cyrillicComment);
        String cyrillic = ClaimCardSteps.getCommentTextOnCommentCreate();
        assertEquals(cyrillicComment, cyrillic);

        /** ввод латиницы в "описание" */
        ClaimCardSteps.typeComment(latinComment);
        String latin = ClaimCardSteps.getCommentTextOnCommentCreate();
        assertEquals(latinComment, latin);

        /** ввод кириллицы и чисел в "описание" */
        ClaimCardSteps.typeComment(cyrillicWithNumbers);
        String numbersCyrillic = ClaimCardSteps.getCommentTextOnCommentCreate();
        assertEquals(cyrillicWithNumbers, numbersCyrillic);

        /** ввод кириллицы и спец.символов в "описание" */
        ClaimCardSteps.typeComment(cyrillicWithSymbols);
        String symbols = ClaimCardSteps.getCommentTextOnCommentCreate();
        assertEquals(cyrillicWithSymbols, symbols);

        /** создание коммнентария */
        ClaimCardSteps.clickSaveButton();
        onView(withText(symbols)).check(matches(isDisplayed()));

        /** редактирование комментария */
        ClaimCardSteps.commentEditButton();
        String originalComment = ClaimCardSteps.getCommentTextOnCommentCreate();
        ClaimCardSteps.typeComment(cyrillicWithNumbers);
        ClaimCardSteps.clickSaveButton();
        String changedComment = ClaimCardSteps.getCommentTextOnClaimCard();
        assertNotEquals(originalComment, changedComment);
    }

    @Test
    public void buttonsCheckOnCommentScreen() {
        ClaimCardSteps.goToClaimCardFromMain();
        ClaimCardSteps.commentAddButton();

        /** Создание комментария с пустым полем */
        ClaimCardSteps.clickSaveButton();
        ClaimCardSteps.isCommentScree();

        /** Отмена создания комментария */
        ClaimCardSteps.clickCancelButton();
        ClaimCardSteps.isClaimCard();
    }

    @Test
    public void claimEditChecks() {
        ClaimCardSteps.goToClaimCardFromMain();

        /** Изменение данных в заявке */
        String originalTitle = ClaimCardSteps.getTitleOnClaimCard();
        String originalDescription = ClaimCardSteps.getDescriptionOnClaimCard();
        ClaimCardSteps.editClaimButton();
        ClaimCreateSteps.createClaim();
        ClaimCreateSteps.clickSaveButton();
        String changedTitle = ClaimCardSteps.getTitleOnClaimCard();
        String changedDescription = ClaimCardSteps.getDescriptionOnClaimCard();

        assertNotEquals(originalTitle, changedTitle);
        assertNotEquals(originalDescription, changedDescription);
    }

    @Test
    public void claimDoneStatusSteps() {
        /** создаем карточку со статусом "Открыта */
        ClaimCreateSteps.goTOClaimCreateFromClaimPanel();
        ClaimCreateSteps.createValidClaim();
        ClaimCreateSteps.clickSaveButton();

        /** Меняем статус "Открыта" на статус "В работу" */
        ClaimPanelSteps.openClaimCard();
        ClaimCardSteps.isClaimCard();
        String originClaimStatus = ClaimCardSteps.getClaimStatus();
        assertEquals(openStatus, originClaimStatus);
        ClaimCardSteps.clickEditStatus();
        ClaimCardSteps.clickInProgress();
        String claimStatusInProgress = ClaimCardSteps.getClaimStatus();
        assertEquals(inProgressStatus, claimStatusInProgress);

        /** Отменяем ввод комментария */
        ClaimCardSteps.clickEditStatus();
        ClaimCardSteps.clickExecute();
        ClaimCard.cancelCommentButton.perform(click());
        String claimStatusAfterCancel = ClaimCardSteps.getClaimStatus();
        assertEquals(inProgressStatus, claimStatusAfterCancel);

        /** Меняем статус "В работу" на статус "Выполнена" */
        ClaimCardSteps.clickEditStatus();
        ClaimCardSteps.clickExecute();
        ClaimCardSteps.typeCommentInWindow();
        String claimStatusExecute = ClaimCardSteps.getClaimStatus();
        assertEquals(executeStatus, claimStatusExecute);
    }

    @Test
    public void claimCancelStatusSteps() {
        /** создаем карточку со статусом "Открыта */
        ClaimCreateSteps.goTOClaimCreateFromClaimPanel();
        ClaimCreateSteps.createValidClaim();
        ClaimCreateSteps.clickSaveButton();

        /** Меняем статус "Открыта" на статус "В работу" */
        ClaimPanelSteps.openClaimCard();
        ClaimCardSteps.isClaimCard();
        String originClaimStatus = ClaimCardSteps.getClaimStatus();
        assertEquals(openStatus, originClaimStatus);
        ClaimCardSteps.clickEditStatus();
        ClaimCardSteps.clickInProgress();
        String claimStatusInProgress = ClaimCardSteps.getClaimStatus();
        assertEquals(inProgressStatus, claimStatusInProgress);

        /** Меняем статус "В работу" на статус "Сбросить" */
        ClaimCardSteps.clickEditStatus();
        ClaimCardSteps.clickThrowOff();
        ClaimCardSteps.typeCommentInWindow();
        String claimStatusThrowOff = ClaimCardSteps.getClaimStatus();
        assertEquals(openStatus, claimStatusThrowOff);

        /** Меняем статус "Открыта" на статус "Отменена" */
        ClaimCardSteps.clickEditStatus();
        ClaimCardSteps.clickCancelStatus();
        String claimStatusCancel = ClaimCardSteps.getClaimStatus();
        assertEquals(cancelStatus, claimStatusCancel);
    }

    @Test
    public void w_logOutAfterTests() {
        MainScreenSteps.mainScreenLoad();
        MainScreenSteps.logOut();
    }
}
