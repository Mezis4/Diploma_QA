package ru.filippova.steps;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static ru.filippova.helpers.Helpers.waitId;

import ru.filippova.module.ClaimScreen;
import ru.iteco.fmhandroid.R;

public class ClaimScreenSteps {

    public static void waitForLoadClaimsScreen() {
        onView(isRoot()).perform(waitId(R.id.add_new_claim_material_button, 7000));
    }

    public static void isClaimScreen() {
        ClaimScreen.claimTitle.check(matches(isDisplayed()));
        ClaimScreen.claimTitle.perform(click());
    }
}
