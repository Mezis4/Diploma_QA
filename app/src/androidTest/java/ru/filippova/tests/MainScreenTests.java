package ru.filippova.tests;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.assertFalse;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import ru.filippova.module.MainScreen;
import ru.filippova.steps.AuthorizationSteps;
import ru.filippova.steps.ClaimCardSteps;
import ru.filippova.steps.ClaimScreenSteps;
import ru.filippova.steps.MainScreenSteps;
import ru.filippova.steps.NavigationBarSteps;
import ru.filippova.steps.NewsScreenSteps;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainScreenTests {

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    /*@Before public void login() {
        AuthorizationSteps.login();
        MainScreenSteps.isMainScreen();
    }

    @After public void logOut() {
        MainScreenSteps.logOut();
    }*/

    @Test
    public void A_setUp() {
        AuthorizationSteps.login();
    }

    @Test
    public void MainScreenElements() {
        MainScreenSteps.mainScreenLoad();

        /** проверка элементов "Главное" */
        NavigationBarSteps.navigationBarElementsCheck();
        MainScreenSteps.mainNewsBlockCheck();
        MainScreenSteps.newsElementsCheck();
        MainScreenSteps.climBlockCheck();
        MainScreenSteps.claimCardCheck();

        /** возвращение на экран девайса */
        Espresso.pressBackUnconditionally();
        assertFalse(activityTestRule.getActivity().hasWindowFocus());
    }

    @Test
    public void expandElementsCheck() {
        MainScreenSteps.mainScreenLoad();

        /** проверка разворачивания/сворачивание блока "Новости" */
        MainScreenSteps.newsListRollUp();
        MainScreenSteps.newsListExpand();
        /** проверка разворачивания/сворачивание новости */
        MainScreenSteps.newsElementExpand();
        MainScreenSteps.newsElementRollUp();

        /** проверка разворачивания/сворачивание блока "Заявки" */
        MainScreenSteps.claimsListRollUp();
        MainScreenSteps.claimsListExpand();
        /** проверка разворачивания/сворачивание заявки */
        MainScreenSteps.claimsElementRollUp();
        ClaimCardSteps.claimCardLoad();
        ClaimCardSteps.clickBackButton();
    }

    @Test
    public void linksAndBackButtonChecks() {
        MainScreenSteps.mainScreenLoad();

        /** Переход по ссылке Все новости и возвращение назад */
        MainScreenSteps.allNewsLinkClick();
        NewsScreenSteps.isNewsScreen();
        Espresso.pressBackUnconditionally();
        MainScreen.allNewsButton.check(matches(isDisplayed()));
        MainScreen.allNewsButton.check(matches(withText("Все новости")));

        /** Переход по ссылке Все заявки и возвращение назад */
        MainScreenSteps.allClaimsLinkClick();
        ClaimScreenSteps.isClaimScreen();
        Espresso.pressBackUnconditionally();
        MainScreen.allNewsButton.check(matches(isDisplayed()));
        MainScreen.allNewsButton.check(matches(withText("Все новости")));
    }


    @Test
    public void w_logOutAfterTests() {
        MainScreenSteps.mainScreenLoad();
        MainScreenSteps.logOut();
    }
}
