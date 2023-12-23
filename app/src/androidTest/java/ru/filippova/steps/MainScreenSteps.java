package ru.filippova.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.not;

import static ru.filippova.helpers.Helpers.waitId;
import static ru.filippova.helpers.Helpers.withIndex;


import ru.filippova.module.ClaimCard;
import ru.filippova.module.MainScreen;
import ru.filippova.module.NavigationBar;
import ru.iteco.fmhandroid.R;

public class MainScreenSteps {
    public static void mainScreenLoad() {
        onView(isRoot()).perform(waitId(R.id.container_list_claim_include_on_fragment_main, 7000));
    }


    public static void isMainScreen() {
        MainScreen.newsListOnMain.check(matches(isDisplayed()));
        MainScreen.navigationBar.check(matches(isDisplayed()));
    }

    public static void logOut() {
        NavigationBar.userButton.perform(click());
        NavigationBar.logOutButton.perform(click());
    }

    public static void mainNewsBlockCheck() {
        MainScreen.newsListOnMain.check(matches(isDisplayed()));
        MainScreen.newsTitle.check(matches(isDisplayed()));
        MainScreen.newsTitle.check(matches(withText("Новости")));
        MainScreen.newsExpandButton.check(matches(isDisplayed()));
        MainScreen.allNewsButton.check(matches(isDisplayed()));
        MainScreen.allNewsButton.check(matches(withText("Все новости")));
        MainScreen.newsItems.check(matches(isDisplayed()));
    }
    public static void climBlockCheck() {
        MainScreen.claimsListOnMain.check(matches(isDisplayed()));
        MainScreen.claimTitle.check(matches(isDisplayed()));
        MainScreen.claimTitle.check(matches(withText("Заявки")));
        MainScreen.createClaim.check(matches(isDisplayed()));
        MainScreen.expandClaimBlockButton.check(matches(isDisplayed()));
        MainScreen.allClaimsButton.check(matches(isDisplayed()));
        MainScreen.allClaimsButton.check(matches(withText("Все заявки")));
    }

    public static void claimCardCheck() {
        MainScreen.claimCard.check(matches(isDisplayed()));
        MainScreen.claimTitleView.check(matches(isDisplayed()));
        MainScreen.claimTitleView.check(matches(withText("Тема")));
        MainScreen.claimCardTitle.check(matches(isDisplayed()));
        MainScreen.claimCardExecutor.check(matches(isDisplayed()));
        MainScreen.claimCardExecutor.check(matches(withText("Исполнитель")));
        MainScreen.claimDate.check(matches(isDisplayed()));
        MainScreen.claimTime.check(matches(isDisplayed()));
        MainScreen.claimCardExpandButton.check(matches(isDisplayed()));
    }

    public static void newsElementsCheck() {
        MainScreen.newsElement.check(matches(isDisplayed()));
        MainScreen.newsElementIcon.check(matches(isDisplayed()));
        MainScreen.newsElementTitle.check(matches(isDisplayed()));
        MainScreen.newsElementExpand.check(matches(isDisplayed()));
        MainScreen.newsElementDate.check(matches(isDisplayed()));
    }

    public static void newsListRollUp() {
        MainScreen.newsExpandButton.check(matches(isDisplayed()));
        MainScreen.allNewsButton.check(matches(isDisplayed()));
        MainScreen.newsExpandButton.perform(click());
        MainScreen.allNewsButton.check(matches(not(isDisplayed())));
    }

    public static void newsListExpand() {
        MainScreen.newsExpandButton.check(matches(isDisplayed()));
        MainScreen.allNewsButton.check(matches(not(isDisplayed())));
        MainScreen.newsExpandButton.perform(click());
        MainScreen.allNewsButton.check(matches(isDisplayed()));
    }

    public static void claimsListRollUp() {
        MainScreen.expandClaimBlockButton.check(matches(isDisplayed()));
        MainScreen.allClaimsButton.check(matches(isDisplayed()));
        MainScreen.expandClaimBlockButton.perform(click());
        MainScreen.allClaimsButton.check(matches(not(isDisplayed())));
    }

    public static void claimsListExpand() {
        MainScreen.expandClaimBlockButton.check(matches(isDisplayed()));
        MainScreen.allClaimsButton.check(matches(not(isDisplayed())));
        MainScreen.expandClaimBlockButton.perform(click());
        MainScreen.allClaimsButton.check(matches(isDisplayed()));
    }

    public static void newsElementRollUp() {
        MainScreen.newsElementIcon.check(matches(isDisplayed()));
        MainScreen.newsElementIcon.perform(click());
        MainScreen.newsElementDescriptionExpand.check(matches(not(isDisplayed())));
    }

    public static void newsElementExpand() {
        onView(withIndex(withId(R.id.news_item_material_card_view), 0)).check(matches(isDisplayed()));
        onView(withIndex(withId(R.id.view_news_item_image_view), 0)).perform(click());
        MainScreen.newsElementDescription.check(matches(isDisplayed()));
    }

    public static void claimsElementRollUp() {
        MainScreen.claimTitleView.check(matches(isDisplayed()));
        MainScreen.claimTitleView.perform(click());
        ClaimCard.claimDescription.check(matches(isDisplayed()));
    }

    public static void allNewsLinkClick() {
        MainScreen.allNewsButton.check(matches(isDisplayed()));
        MainScreen.allNewsButton.perform(click());
    }

    public static void allClaimsLinkClick() {
        MainScreen.allClaimsButton.check(matches(isDisplayed()));
        MainScreen.allClaimsButton.perform(click());
    }

}
