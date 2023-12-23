package ru.filippova.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.filippova.helpers.Helpers.waitId;

import ru.filippova.module.AuthorizationScreen;
import ru.iteco.fmhandroid.R;
public class AuthorizationSteps {

    private static String validLogin = "login2";
    private static String validPassword = "password2";
    private static String invalidLogin = "login";
    private static String invalidPassword = "password";

    public static void isAuthScreen() {
        AuthorizationScreen.titleView.check(matches(isDisplayed()));
    }

    public static void waitForAuthScreen() {
        onView(isRoot()).perform(waitId(R.id.enter_button, 7000));
    }

    public static void authScreenElementsCheck() {
        AuthorizationScreen.titleView.check(matches(isDisplayed()));
        AuthorizationScreen.titleView.check(matches(withText("Авторизация")));
        AuthorizationScreen.loginField.check(matches(isDisplayed()));
        AuthorizationScreen.passwordField.check(matches(isDisplayed()));
        AuthorizationScreen.signInButton.check(matches(isDisplayed()));
    }

    public static void fillValidLogin() {
        AuthorizationScreen.loginField.check(matches(isDisplayed()));
        AuthorizationScreen.loginField.perform(click(), typeText(validLogin), closeSoftKeyboard());
    }

    public static void fillValidPassword() {
        AuthorizationScreen.passwordField.check(matches(isDisplayed()));
        AuthorizationScreen.passwordField.perform(click(), typeText(validPassword), closeSoftKeyboard());
    }

    public static void fillInvalidLogin() {
        AuthorizationScreen.loginField.check(matches(isDisplayed()));
        AuthorizationScreen.loginField.perform(click(), typeText(invalidLogin), closeSoftKeyboard());
    }

    public static void fillInvalidPassword() {
        AuthorizationScreen.passwordField.check(matches(isDisplayed()));
        AuthorizationScreen.passwordField.perform(click(), typeText(invalidPassword), closeSoftKeyboard());
    }

    public static void clickSignInButtonField() {
        AuthorizationScreen.signInButton.check(matches(isDisplayed()));
        AuthorizationScreen.signInButton.perform(click());
    }

    public static void login() {
        try {
            Thread.sleep(5000);
            /*AuthorizationSteps.isAuthScreen();*/
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        AuthorizationSteps.fillValidLogin();
        AuthorizationSteps.fillValidPassword();
        AuthorizationSteps.clickSignInButtonField();
    }
}
