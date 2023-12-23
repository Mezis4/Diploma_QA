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

import ru.filippova.helpers.Helpers;
import ru.filippova.module.ControlPanel;
import ru.filippova.module.MainScreen;
import ru.filippova.module.NavigationBar;
import ru.filippova.module.NewsCreate;
import ru.filippova.module.NewsScreen;
import ru.iteco.fmhandroid.R;

public class NewsScreenSteps {

    public static void waitForLoadNews() {
        onView(isRoot()).perform(waitId(R.id.edit_news_material_button, 7000));
    }

    public static void goToNews() {
        NavigationBarSteps.goOnNewsScreenFromMain();
        waitForLoadNews();
    }

    public static void isNewsScreen() {
        NewsScreen.newsContainer.check(matches(isDisplayed()));
        NewsScreen.newsContainerTitle.check(matches(withText("Новости")));
    }

    public static void instrumentPanelCheck() {
        NewsScreen.newsContainerTitle.check(matches(withText("Новости")));
        NewsScreen.sortNewsButton.check(matches(isDisplayed()));
        NewsScreen.filterScreenButton.check(matches(isDisplayed()));
        NewsScreen.controlPanelScreenButton.check(matches(isDisplayed()));
    }

    public static void clickOnNewsSortButton() {
        NewsScreen.sortNewsButton.check(matches(isDisplayed()));
        NewsScreen.sortNewsButton.perform(click());
    }

    public static void clickOnNewsFilterButton() {
        NewsScreen.filterScreenButton.check(matches(isDisplayed()));
        NewsScreen.filterScreenButton.perform(click());
    }

    public static void clickOnControlPanelButton() {
        NewsScreen.controlPanelScreenButton.check(matches(isDisplayed()));
        NewsScreen.controlPanelScreenButton.perform(click());
    }

    public static String getNewsTitle() {
        return Helpers.getText(onView(withIndex(withId(R.id.news_item_title_text_view), 0)));
    }

    public static String getNewsDate() {
        return Helpers.getText(onView(withIndex(withId(R.id.news_item_date_text_view), 0)));
    }
}
