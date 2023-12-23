package ru.filippova.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.filippova.helpers.Helpers.waitId;

import ru.filippova.module.OurMission;
import ru.iteco.fmhandroid.R;

public class OurMissionSteps {

    public static void waitForLoadOurMissionScreen() {
        onView(isRoot()).perform(waitId(R.id.our_mission_title_text_view, 7000));
    }

    public static void goToOurMissionScree() {
        MainScreenSteps.mainScreenLoad();
        NavigationBarSteps.clickOurMissionButton();
        waitForLoadOurMissionScreen();
        isOurMissionScreen();
    }

    public static void isOurMissionScreen() {
        OurMission.ourMissionTitle.check(matches(isDisplayed()));
        OurMission.ourMissionTitle.check(matches(withText(R.string.our_mission_title_text)));
    }

    public static void screenElementsCheck() {
        OurMission.ourMissionTitle.check(matches(isDisplayed()));
        OurMission.ourMissionTitle.check(matches(withText(R.string.our_mission_title_text)));
        OurMission.cardElement.check(matches(isDisplayed()));
        OurMission.cardIcon.check(matches(isDisplayed()));
        OurMission.cardOpenButton.check(matches(isDisplayed()));
        OurMission.cardTitle.check(matches(isDisplayed()));
    }

    public static void clickCardOpenButton() {
        OurMission.cardOpenButton.check(matches(isDisplayed()));
        OurMission.cardOpenButton.perform(click());
    }

    public static void closeCard() {
        OurMission.cardDescription.perform(click());
    }
}
