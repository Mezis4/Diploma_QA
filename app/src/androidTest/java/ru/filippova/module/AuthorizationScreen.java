package ru.filippova.module;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;


public class AuthorizationScreen {

    public static ViewInteraction titleView = onView(
            allOf(withText(R.string.authorization),
                    withParent(withParent(withId(R.id.nav_host_fragment)))));
    public static ViewInteraction loginField = onView(allOf(withHint(R.string.login),
            withParent(withParent(withId(R.id.login_text_input_layout)))));
    public static ViewInteraction passwordField = onView(
            allOf(withHint(R.string.password),
                    withParent(withParent(withId(R.id.password_text_input_layout)))));
    public static ViewInteraction signInButton = onView(withId(R.id.enter_button));
}
