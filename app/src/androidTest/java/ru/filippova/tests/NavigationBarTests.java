package ru.filippova.tests;

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

import ru.filippova.steps.AuthorizationSteps;
import ru.filippova.steps.ClaimScreenSteps;
import ru.filippova.steps.MainScreenSteps;
import ru.filippova.steps.NavigationBarSteps;
import ru.filippova.steps.NewsScreenSteps;
import ru.filippova.steps.OurMissionSteps;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NavigationBarTests {

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

   /*@Before
    public void login() throws InterruptedException {
       AuthorizationSteps.login();
       MainScreenSteps.mainScreenLoad();
    }

    @After
    public void logOut() {
        MainScreenSteps.logOut();
    }*/

   @Test
    public void a_setUp() {
        AuthorizationSteps.login();
    }

    @Test
    public void pickItemFromMainAndPressBack() {
        /** выбор раздела "Главная" в бургер-меню с экрана "Главная"*/
        MainScreenSteps.mainScreenLoad();
        NavigationBarSteps.clickMainMenu();
        NavigationBarSteps.clickMainItemInMenu();
        Espresso.pressBackUnconditionally(); //для закрытия бургер-меню
        MainScreenSteps.isMainScreen();

        /** выбор раздела "Новости" в бургер-меню с экрана "Главная"*/
        MainScreenSteps.mainScreenLoad();
        NavigationBarSteps.goOnNewsScreenFromMain();
        Espresso.pressBackUnconditionally();
        MainScreenSteps.isMainScreen();

        /** переход на экран "Заявки" с экрана "Главная" и возврат"*/
        NavigationBarSteps.goOnClaimScreenMain();
        Espresso.pressBackUnconditionally();
        MainScreenSteps.isMainScreen();

        /** переход на экран "О приложении" с экрана "Главная" и возврат"*/
        NavigationBarSteps.goOnAboutScreenFromMain();
        Espresso.pressBackUnconditionally();
        MainScreenSteps.isMainScreen();

        /** переход на экран "Цитаты" с экрана "Главная" и возврат"*/
        NavigationBarSteps.goOnOurMissionScreen();
        Espresso.pressBackUnconditionally();
        MainScreenSteps.isMainScreen();
    }

    @Test
    public void pickItemsFromClaimsScreen() {
        /** выбор раздела "Заявки" в бургер-меню с экрана "Заявки"*/
        MainScreenSteps.mainScreenLoad();
        NavigationBarSteps.goOnClaimScreenMain();
        NavigationBarSteps.clickMainMenu();
        NavigationBarSteps.clickClaimItemInMenu();
        Espresso.pressBackUnconditionally(); //для закрытия бургер-меню
        ClaimScreenSteps.isClaimScreen();

        /** переход на экран "Главная" с экрана "Заявки" и возврат"*/
        NavigationBarSteps.goOnAMainFromMain();
        Espresso.pressBackUnconditionally();
        ClaimScreenSteps.isClaimScreen();

        /** переход на экран "Новости" с экрана "Заявки" и возврат"*/
        NavigationBarSteps.goOnNewsScreenFromMain();
        Espresso.pressBackUnconditionally();
        ClaimScreenSteps.isClaimScreen();

        /** переход на экран "Цитаты" с экрана "Заявки" и возврат"*/
        NavigationBarSteps.goOnOurMissionScreen();
        Espresso.pressBackUnconditionally();
        ClaimScreenSteps.isClaimScreen();

        /** переход на экран "О приложении" с экрана "Заявки" и возврат"*/
        NavigationBarSteps.goOnAboutScreenFromMain();
        Espresso.pressBackUnconditionally();
        ClaimScreenSteps.isClaimScreen();
    }

    @Test
    public void pickItemsFromNewsScreen() {
        /** выбор раздела "Новости" в бургер-меню с экрана "Новости"*/
        MainScreenSteps.mainScreenLoad();
        NavigationBarSteps.goOnNewsScreenFromMain();
        NavigationBarSteps.clickMainMenu();
        NavigationBarSteps.clickNewsItemInMenu();
        Espresso.pressBackUnconditionally(); //для закрытия бургер-меню
        NewsScreenSteps.isNewsScreen();

        /** переход на экран "Главная" с экрана "Новости" и возврат"*/
        NavigationBarSteps.goOnAMainFromMain();
        Espresso.pressBackUnconditionally();
        NewsScreenSteps.isNewsScreen();

        /** переход на экран "Заявки" с экрана "Новости" и возврат"*/
        NavigationBarSteps.goOnClaimScreenMain();
        Espresso.pressBackUnconditionally();
        NewsScreenSteps.isNewsScreen();

        /** переход на экран "Цитаты" с экрана "Новости" и возврат"*/
        NavigationBarSteps.goOnOurMissionScreen();
        Espresso.pressBackUnconditionally();
        NewsScreenSteps.isNewsScreen();

        /** переход на экран "О приложении" с экрана "Новости" и возврат"*/
        NavigationBarSteps.goOnAboutScreenFromMain();
        Espresso.pressBackUnconditionally();
        NewsScreenSteps.isNewsScreen();
    }

    @Test
    public void pickItemsFromOurMissionScreen() {
        /** выбор раздела "Цитаты" с экрана "Цитаты"*/
        MainScreenSteps.mainScreenLoad();
        NavigationBarSteps.clickOurMissionButton();
        OurMissionSteps.isOurMissionScreen();

        /** переход на экран "Главная" с экрана "Цитаты" и возврат"*/
        NavigationBarSteps.goOnAMainFromMain();
        Espresso.pressBackUnconditionally();
        OurMissionSteps.isOurMissionScreen();

        /** переход на экран "Заявки" с экрана "Цитаты" и возврат"*/
        NavigationBarSteps.goOnClaimScreenMain();
        Espresso.pressBackUnconditionally();
        OurMissionSteps.isOurMissionScreen();

        /** переход на экран "Новости" с экрана "Цитаты" и возврат"*/
        NavigationBarSteps.goOnNewsScreenFromMain();
        Espresso.pressBackUnconditionally();
        OurMissionSteps.isOurMissionScreen();

        /** переход на экран "О приложении" с экрана "Цитаты" и возврат"*/
        NavigationBarSteps.goOnAboutScreenFromMain();
        Espresso.pressBackUnconditionally();
        OurMissionSteps.isOurMissionScreen();
    }

    @Test
    public void w_logOutAfterTests() {
        MainScreenSteps.mainScreenLoad();
        MainScreenSteps.logOut();
    }
}
