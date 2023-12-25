package ru.filippova.tests;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.filippova.steps.AboutScreenSteps;
import ru.filippova.steps.AuthorizationSteps;
import ru.filippova.steps.MainScreenSteps;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AndroidJUnit4.class)
public class AboutScreenTests {

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    @Before
    public void waitScreen() {
        AuthorizationSteps.login();
        AboutScreenSteps.goToAboutScreen();
    }

    @After
    public void logOut() {
        MainScreenSteps.logOut();
    }

    @Test
    public void aboutScreenElementsAndBackButtonCheck() {
        AboutScreenSteps.aboutScreenElementsCheck();
        AboutScreenSteps.clickBackButton();
        AboutScreenSteps.notAboutScreen();
    }
}
