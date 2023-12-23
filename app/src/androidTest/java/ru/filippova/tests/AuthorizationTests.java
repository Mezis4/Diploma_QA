package ru.filippova.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.filippova.steps.AuthorizationSteps;
import ru.filippova.steps.MainScreenSteps;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AndroidJUnit4.class)
public class AuthorizationTests {

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    @Before public void waitForScreen() {
        AuthorizationSteps.waitForAuthScreen();
    }

    @Test
    public void authScreenElementsCheck() {
        AuthorizationSteps.authScreenElementsCheck();
    }

    @Test
    public void signInWithValidData() throws InterruptedException {
        AuthorizationSteps.isAuthScreen();
        AuthorizationSteps.fillValidLogin();
        AuthorizationSteps.fillValidPassword();
        AuthorizationSteps.clickSignInButtonField();

        Thread.sleep(1200);
        MainScreenSteps.isMainScreen();
        MainScreenSteps.logOut();
    }

    @Test
    public void signInWithInvalidData() throws InterruptedException {
        AuthorizationSteps.isAuthScreen();
        AuthorizationSteps.fillInvalidLogin();
        AuthorizationSteps.fillInvalidPassword();
        AuthorizationSteps.clickSignInButtonField();

        Thread.sleep(600);
        onView(withText(R.string.wrong_login_or_password))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow()
                        .getDecorView())))).check(matches(withText("Неверный логин или пароль")));
    }

    @Test
    public void signInWithEmptyFields() throws InterruptedException {
        AuthorizationSteps.isAuthScreen();
        AuthorizationSteps.clickSignInButtonField();

        Thread.sleep(600);
        onView(withText(R.string.empty_login_or_password))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow()
                        .getDecorView())))).check(matches(withText("Логин и пароль не могут быть пустыми")));
    }
}
