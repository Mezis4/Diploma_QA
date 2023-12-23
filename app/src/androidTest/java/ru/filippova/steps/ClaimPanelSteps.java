package ru.filippova.steps;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.filippova.helpers.Helpers.waitId;
import static ru.filippova.helpers.Helpers.withIndex;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;

import ru.filippova.helpers.Helpers;
import ru.filippova.module.ClaimCreate;
import ru.filippova.module.ClaimPanel;
import ru.filippova.module.MainScreen;
import ru.iteco.fmhandroid.R;

public class ClaimPanelSteps {

    public static void goToClaimPanelFromMain() {
        MainScreenSteps.mainScreenLoad();
        MainScreenSteps.allClaimsLinkClick();
        claimCreateScreenLoad();
    }
    public static void claimCreateScreenLoad() {
        onView(isRoot()).perform(waitId(R.id.add_new_claim_material_button, 7000));
    }

    public static void isClaimPanel() {
        ClaimPanel.filtersButton.check(matches(isDisplayed()));
    }

    public static void claimPanelElementsCheck() {
        ClaimPanel.claimPanelTitle.check(matches(withText("Заявки")));
        ClaimPanel.filtersButton.check(matches(isDisplayed()));
        ClaimPanel.addClaimButton.check(matches(isDisplayed()));
    }

    public static void openFilters() {
        ClaimPanel.filtersButton.check(matches(isDisplayed()));
        ClaimPanel.filtersButton.perform(click());
    }

    public static void filtersElementsCheck() {
        ClaimPanel.filtersTitle.check(matches(isDisplayed()));
        ClaimPanel.filtersTitle.check(matches(withText("Фильтрация")));
        ClaimPanel.filterOpenStatus.check(matches(isDisplayed()));
        ClaimPanel.filterOpenStatus.check(matches(withText("Открыта")));
        ClaimPanel.filterProgressStatus.check(matches(isDisplayed()));
        ClaimPanel.filterProgressStatus.check(matches(withText("В работе")));
        ClaimPanel.filterExecuteStatus.check(matches(isDisplayed()));
        ClaimPanel.filterExecuteStatus.check(matches(withText("Выполнена")));
        ClaimPanel.filterCancelledStatus.check(matches(isDisplayed()));
        ClaimPanel.filterCancelledStatus.check(matches(withText("Отмененные")));
        ClaimPanel.filterOkButton.check(matches(isDisplayed()));
        ClaimPanel.filterOkButton.check(matches(withText("ОК")));
        ClaimPanel.filterCancelButton.check(matches(isDisplayed()));
        ClaimPanel.filterCancelButton.check(matches(withText("Отмена")));
    }

    public static void chooseFilter(ViewInteraction status) {
        status.check(matches(isDisplayed()));
        status.perform(click());
    }

    public static void clickOkButton() {
        ClaimPanel.filterOkButton.check(matches(isDisplayed()));
        ClaimPanel.filterOkButton.perform(click());
    }

    public static void goToClaimCreate() {
        ClaimPanel.addClaimButton.check(matches(isDisplayed()));
        ClaimPanel.addClaimButton.perform(click());
    }

    public static void openClaimCard() {
        ClaimPanel.claimCard.check(matches(isDisplayed()));
        ClaimPanel.claimCard.perform(click());
    }

    public static void openClaimCardAgain() {
        onView(withIndex(withId(R.id.claim_list_card),0)).check(matches(isDisplayed()));
        onView(withIndex(withId(R.id.claim_list_card),0)).perform(click());
    }

    public static String getClaimTitle() {
        return Helpers.getText(onView(withIndex(withId(R.id.description_material_text_view),0)));
    }

    public static String getClaimDate() {
        return Helpers.getText(onView(withIndex(withId(R.id.plan_date_material_text_view),0)));
    }



}
