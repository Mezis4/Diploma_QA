package ru.filippova.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.not;

import static ru.filippova.helpers.Helpers.waitId;

import ru.filippova.module.AboutScreen;
import ru.iteco.fmhandroid.R;

public class AboutScreenSteps {

    public static void waitForLoadAboutScreen() {
        onView(isRoot()).perform(waitId(R.id.about_privacy_policy_value_text_view, 7000));
    }

    public static void goToAboutScreen() {
        MainScreenSteps.mainScreenLoad();
        NavigationBarSteps.clickAboutItemInMenu();
        waitForLoadAboutScreen();
        AboutScreenSteps.isAboutScreen();
    }

    public static void isAboutScreen() {
        AboutScreen.versionTitle.check(matches(isDisplayed()));
        AboutScreen.versionTitle.check(matches(withText(R.string.version)));
    }

    public static void notAboutScreen() {
        AboutScreen.versionTitle.check(doesNotExist());
        AboutScreen.versionTitle.check(doesNotExist());
    }
    public static void aboutScreenElementsCheck() {
        AboutScreen.backButton.check(matches(isDisplayed()));
        AboutScreen.versionTitle.check(matches(isDisplayed()));
        AboutScreen.versionTitle.check(matches(withText(R.string.version)));
        AboutScreen.versionNumber.check(matches(isDisplayed()));
        AboutScreen.versionNumber.check(matches(withText("1.0.0")));
        AboutScreen.privacyTitle.check(matches(isDisplayed()));
        AboutScreen.privacyTitle.check(matches(withText(R.string.privacy_policy)));
        AboutScreen.privacyLink.check(matches(isDisplayed()));
        AboutScreen.privacyLink.check(matches(withText(R.string.privacy_policy_url)));
        AboutScreen.termsTitle.check(matches(isDisplayed()));
        AboutScreen.termsTitle.check(matches(withText(R.string.terms_of_use)));
        AboutScreen.termsLink.check(matches(isDisplayed()));
        AboutScreen.termsLink.check(matches(withText(R.string.terms_of_use_url)));
        AboutScreen.aboutCompany.check(matches(isDisplayed()));
        AboutScreen.aboutCompany.check(matches(withText(R.string.company_info)));
    }

    public static void clickPrivacyLink() {
        AboutScreen.privacyLink.check(matches(isDisplayed()));
        AboutScreen.privacyLink.perform(click());
    }

    public static void clickTermsLink() {
        AboutScreen.termsLink.check(matches(isDisplayed()));
        AboutScreen.termsLink.perform(click());
    }

    public static void clickBackButton() {
        AboutScreen.backButton.check(matches(isDisplayed()));
        AboutScreen.backButton.perform(click());
    }
}
