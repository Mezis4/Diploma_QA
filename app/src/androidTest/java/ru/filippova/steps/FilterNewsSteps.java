package ru.filippova.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import static org.hamcrest.Matchers.not;

import static ru.filippova.helpers.Helpers.waitId;

import ru.filippova.helpers.Helpers;
import ru.filippova.module.ControlPanel;
import ru.filippova.module.FilterNews;
import ru.filippova.module.MainScreen;
import ru.iteco.fmhandroid.R;

public class FilterNewsSteps {

    public static String categoryFirstLetters = "пра";
    public static String invalidCategory = "Котики";
    public static String noNewsCategory = "Массаж";

    public static void isNewsFilter() {
        FilterNews.newsContainer.check(matches(isDisplayed()));
        FilterNews.newsContainer.check(matches(withText("Фильтровать новости")));
    }

    public static void goToFiltersFromControlPanel() {
        ControlPanelSteps.goToControlPanel();
        ControlPanelSteps.clickNewsFilterFromControlPanel();
    }

    public static void goToFiltersFromNews() {
        MainScreenSteps.mainScreenLoad();
        NewsScreenSteps.goToNews();
        NewsScreenSteps.clickOnNewsFilterButton();
    }
    public static void waitForLoadFilters() {
        onView(isRoot()).perform(waitId(R.id.filter_news_title_text_view, 7000));
    }

    public static void filterNewsElementsCheck() {
        FilterNews.newsContainer.check(matches(isDisplayed()));
        FilterNews.newsContainer.check(matches(withText("Фильтровать новости")));
        FilterNews.categoryInput.check(matches(isDisplayed()));
        FilterNews.dateInputFrom.check(matches(isDisplayed()));
        FilterNews.dateInputTo.check(matches(isDisplayed()));
        FilterNews.acceptFiltersButton.check(matches(isDisplayed()));
        FilterNews.acceptFiltersButton.check(matches(withText("Фильтровать")));
        FilterNews.cancelFiltersButton.check(matches(isDisplayed()));
        FilterNews.cancelFiltersButton.check(matches(withText("Отмена")));
    }

    public static void clickOnAcceptFiltersButton() {
        FilterNews.acceptFiltersButton.check(matches(isDisplayed()));
        FilterNews.acceptFiltersButton.perform(click());
    }

    public static void clickOnCancelButton() {
        FilterNews.cancelFiltersButton.check(matches(isDisplayed()));
        FilterNews.cancelFiltersButton.perform(click());
    }

    public static void selectCategoryFromList() {
        FilterNews.categoryInput.perform(click());
        FilterNews.categoryInput.perform(replaceText(categoryFirstLetters));
        FilterNews.categoryListItem.inRoot(Helpers.isPopupWindow()).check(matches(isDisplayed()));
        FilterNews.categoryListItem.inRoot(Helpers.isPopupWindow()).perform(click());
    }

    public static void selectCategoryWithNoNewsFromList() {
        FilterNews.categoryInput.perform(click());
        FilterNews.categoryInput.perform(replaceText(noNewsCategory));
        FilterNews.categoryListItemWithNoNews.inRoot(Helpers.isPopupWindow()).check(matches(isDisplayed()));
        FilterNews.categoryListItemWithNoNews.inRoot(Helpers.isPopupWindow()).perform(click());
    }

    public static void typeInvalidCategory() {
        FilterNews.categoryInput.perform(click());
        FilterNews.categoryInput.perform(replaceText(invalidCategory));
    }

    public static void typeCategoryWithNoNews() {
        FilterNews.categoryInput.perform(click());
        FilterNews.categoryInput.perform(replaceText(noNewsCategory), closeSoftKeyboard());
    }

    public static void errorScreenCheckOnNews() {
        FilterNews.errorIconOnNews.check(matches(isDisplayed()));
        FilterNews.errorMessageOnNews.check(matches(isDisplayed()));
        FilterNews.refreshButtonOnNews.check(matches(isDisplayed()));
        FilterNews.refreshButtonOnNews.check(matches(withText("Обновить")));
    }

    public static String getNewsDateOnNews() {
        return Helpers.getText(MainScreen.newsElementDate);
    }

    public static void chooseDateFromTo(String date) {
        FilterNews.dateInputFrom.check(matches(isDisplayed()));
        FilterNews.dateInputFrom.perform(replaceText(date));
        FilterNews.dateInputTo.perform(replaceText(date));
    }

    public static void clickActiveFilter() {
        FilterNews.activeFilter.check(matches(isDisplayed()));
        FilterNews.activeFilter.perform(click());
    }

    public static void clickNotActiveFilter() {
        FilterNews.notActiveFilter.check(matches(isDisplayed()));
        FilterNews.notActiveFilter.perform(click());
    }

    public static void clickRefreshButtonOnNews() {
        FilterNews.refreshButtonOnNews.check(matches(isDisplayed()));
        FilterNews.refreshButtonOnNews.perform(click());
    }
}
