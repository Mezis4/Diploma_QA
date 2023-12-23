package ru.filippova.tests;

import static androidx.test.espresso.intent.Intents.intended;

import android.content.Intent;
import android.net.Uri;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import ru.filippova.steps.AboutScreenSteps;
import ru.filippova.steps.AuthorizationSteps;
import ru.filippova.steps.MainScreenSteps;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScreenAboutLinksTest {

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    /*@Before
    public void waitScreen() {
        AuthorizationSteps.login();
    }*/

    /*@After
    public void logOut() {
        MainScreenSteps.logOut();
    }*/

    @Test
    public void a_setUp() {
        AuthorizationSteps.login();
    }

    @Test
    public void B_TermsLink() {
        AboutScreenSteps.goToAboutScreen();
        Intents.init();
        AboutScreenSteps.clickTermsLink();
        intended(IntentMatchers.hasAction(Intent.ACTION_VIEW));
        intended(IntentMatchers.hasData(Uri.parse("https://vhospice.org/#/terms-of-use")));
        Intents.release();
    }

    @Test
    public void C_PrivacyLink() {
        AboutScreenSteps.goToAboutScreen();
        Intents.init();
        AboutScreenSteps.clickPrivacyLink();
        intended(IntentMatchers.hasAction(Intent.ACTION_VIEW));
        intended(IntentMatchers.hasData(Uri.parse("https://vhospice.org/#/privacy-policy/")));
        Intents.release();
    }

    @Test
    public void logOutAfterTests() {
        MainScreenSteps.mainScreenLoad();
        MainScreenSteps.logOut();
    }
}
