package ru.filippova.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import static ru.filippova.helpers.Helpers.waitId;

import ru.filippova.module.NavigationBar;
import ru.iteco.fmhandroid.R;

public class NavigationBarSteps {

    public static void navigationBarElementsCheck() {
        NavigationBar.mainMenuButton.check(matches(isDisplayed()));
        NavigationBar.imageView.check(matches(isDisplayed()));
        NavigationBar.ourMissionButton.check(matches(isDisplayed()));
        NavigationBar.userButton.check(matches(isDisplayed()));
    }

    public static void clickMainMenu() {
        NavigationBar.mainMenuButton.check(matches(isDisplayed()));
        NavigationBar.mainMenuButton.perform(click());
    }

    public static void clickMainItemInMenu() {
        NavigationBar.mainItemOnMenu.check(matches(isDisplayed()));
        NavigationBar.mainItemOnMenu.perform(click());
    }

    public static void clickClaimItemInMenu() {
        NavigationBar.claimItemOnMain.check(matches(isDisplayed()));
        NavigationBar.claimItemOnMain.perform(click());
    }

    public static void clickNewsItemInMenu() {
        NavigationBar.newsItemOnMenu.check(matches(isDisplayed()));
        NavigationBar.newsItemOnMenu.perform(click());
    }

    public static void clickAbout() {
        NavigationBar.aboutItemOnMenu.check(matches(isDisplayed()));
        NavigationBar.aboutItemOnMenu.perform(click());
    }
    public static void clickAboutItemInMenu() {
        NavigationBar.mainMenuButton.check(matches(isDisplayed()));
        NavigationBar.mainMenuButton.perform(click());
        NavigationBar.aboutItemOnMenu.check(matches(isDisplayed()));
        NavigationBar.aboutItemOnMenu.perform(click());
    }

    public static void clickOurMissionButton() {
        NavigationBar.ourMissionButton.check(matches(isDisplayed()));
        NavigationBar.ourMissionButton.perform(click());
    }
    public static void goOnNewsScreenFromMain() {
        NavigationBarSteps.clickMainMenu();
        NavigationBarSteps.clickNewsItemInMenu();
        NewsScreenSteps.isNewsScreen();
    }

    public static void goOnClaimScreenMain() {
        NavigationBarSteps.clickMainMenu();
        NavigationBarSteps.clickClaimItemInMenu();
        ClaimScreenSteps.isClaimScreen();
    }

    public static void goOnAboutScreenFromMain() {
        NavigationBarSteps.clickMainMenu();
        NavigationBarSteps.clickAbout();
        AboutScreenSteps.isAboutScreen();
    }

    public static void goOnAMainFromMain() {
        NavigationBarSteps.clickMainMenu();
        NavigationBarSteps.clickMainItemInMenu();
        MainScreenSteps.isMainScreen();
    }

    public static void goOnOurMissionScreen() {
        NavigationBarSteps.clickOurMissionButton();
        OurMissionSteps.isOurMissionScreen();
    }
}
