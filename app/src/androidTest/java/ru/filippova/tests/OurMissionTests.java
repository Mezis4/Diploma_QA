package ru.filippova.tests;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.filippova.module.OurMission;
import ru.filippova.steps.AuthorizationSteps;
import ru.filippova.steps.MainScreenSteps;
import ru.filippova.steps.NavigationBarSteps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import static org.hamcrest.Matchers.not;

import ru.filippova.steps.OurMissionSteps;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AndroidJUnit4.class)
public class OurMissionTests {
    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    @Before
    public void login() {
        AuthorizationSteps.login();
        OurMissionSteps.goToOurMissionScree();
    }

    @After
    public void logOut() {
        MainScreenSteps.logOut();
    }

    @Test
    public void OurMissionTests() {
        NavigationBarSteps.navigationBarElementsCheck();
        OurMissionSteps.screenElementsCheck();

        OurMissionSteps.clickCardOpenButton();
        OurMission.cardDescription.check(matches(isDisplayed()));
        OurMissionSteps.closeCard();
        OurMission.cardDescriptionAfterClose.check(matches(not(isDisplayed())));
    }
}
