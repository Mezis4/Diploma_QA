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

import ru.filippova.helpers.Helpers;
import ru.filippova.module.ControlPanel;
import ru.filippova.module.FilterNews;
import ru.filippova.module.NewsCreate;
import ru.filippova.module.NewsScreen;
import ru.iteco.fmhandroid.R;

public class ControlPanelSteps {


    public static void goToControlPanel() {
        MainScreenSteps.mainScreenLoad();
        MainScreenSteps.isMainScreen();
        MainScreenSteps.allNewsLinkClick();
        NewsScreenSteps.clickOnControlPanelButton();
        waitForLoadControlPanel();
        isControlPanel();
    }

    public static void waitForLoadControlPanel() {
        onView(isRoot()).perform(waitId(R.id.add_news_image_view, 7000));
    }

    public static void isControlPanel() {
        ControlPanel.controlPanelTitle.check(matches(isDisplayed()));
        ControlPanel.controlPanelTitle.check(matches(withText("Панель \n управления")));
    }

    public static void controlPanelBarCheck() {
        ControlPanel.controlPanelTitle.check(matches(isDisplayed()));
        ControlPanel.controlPanelTitle.check(matches(withText("Панель \n управления")));
        NewsScreen.sortNewsButton.check(matches(isDisplayed()));
        NewsScreen.filterScreenButton.check(matches(isDisplayed()));
        ControlPanel.newsCreateButton.check(matches(isDisplayed()));
    }

    public static void controlPanelNewsElementsCheck() {
        ControlPanel.newsCategory.check(matches(isDisplayed()));
        ControlPanel.newsItem.check(matches(isDisplayed()));
        ControlPanel.firstNewsTitle.check(matches(isDisplayed()));
        ControlPanel.newsPublicationDateTitle.check(matches(isDisplayed()));
        ControlPanel.newsPublicationDateTitle.check(matches(withText("Дата публикации")));
        ControlPanel.firstNewsDate.check(matches(isDisplayed()));
        ControlPanel.newsCreationDateTitle.check(matches(isDisplayed()));
        ControlPanel.newsCreationDateTitle.check(matches(withText("Дата создания")));
        ControlPanel.newsCreationDate.check(matches(isDisplayed()));
        ControlPanel.newsAuthorTitle.check(matches(isDisplayed()));
        ControlPanel.newsAuthorTitle.check(matches(withText("Автор")));
        ControlPanel.newsAuthor.check(matches(isDisplayed()));
        ControlPanel.newsStatus.check(matches(isDisplayed()));
        ControlPanel.deleteNewsButton.check(matches(isDisplayed()));
        ControlPanel.editNewsButton.check(matches(isDisplayed()));
        ControlPanel.expandNewsButton.check(matches(isDisplayed()));
    }

    public static void clickNewsFilterFromControlPanel() {
        NewsScreen.filterScreenButton.perform(click());
        FilterNewsSteps.isNewsFilter();
    }

    public static String getFirstNewsTitle() {
        return Helpers.getText(onView(withIndex(withId(R.id.news_item_title_text_view),0)));
    }

    public static String getNewsDate() {
        return Helpers.getText(onView(withIndex(withId(R.id.news_item_publication_date_text_view),0)));
    }

    public static String checkNewsStatus() {
        return Helpers.getText(onView(withIndex(withId(R.id.news_item_published_text_view), 0)));
    }

    public static void errorScreenCheckOnControlPanel() {
        FilterNews.errorIconOnControlPanel.check(matches(isDisplayed()));
        FilterNews.errorMessageOnControlPanel.check(matches(isDisplayed()));
        FilterNews.refreshButtonOnControlPanel.check(matches(isDisplayed()));
        FilterNews.refreshButtonOnControlPanel.check(matches(withText("Обновить")));
    }

    public static void clickRefreshButtonOnNews() {
        FilterNews.refreshButtonOnControlPanel.check(matches(isDisplayed()));
        FilterNews.refreshButtonOnControlPanel.perform(click());
    }

    public static void clickNewsCreationButton() {
        ControlPanel.newsCreateButton.check(matches(isDisplayed()));
        ControlPanel.newsCreateButton.perform(click());
    }

    public static void rollUpNewsButton() throws InterruptedException {
        onView(withIndex(withId(R.id.view_news_item_image_view),0)).check(matches(isDisplayed()));
        onView(withIndex(withId(R.id.view_news_item_image_view),0)).perform(click());
        Thread.sleep(1800);
        ControlPanel.newsDescriptionAfterExpand.check(matches(not(isDisplayed())));
    }

    public static void expandNewsButton() throws InterruptedException {
        onView(withIndex(withId(R.id.view_news_item_image_view),0)).check(matches(isDisplayed()));
        onView(withIndex(withId(R.id.view_news_item_image_view),0)).perform(click());
        Thread.sleep(1800);
        ControlPanel.newsDescription.check(matches(isDisplayed()));
    }

    public static void clickCancelAfterDeleteButton() {
        onView(withIndex(withId(R.id.delete_news_item_image_view),0)).check(matches(isDisplayed()));
        onView(withIndex(withId(R.id.delete_news_item_image_view),0)).perform(click());
        NewsCreate.cancelButtonForConfirmation.perform(click());
    }

    public static void deleteNews() {
        onView(withIndex(withId(R.id.delete_news_item_image_view),0)).check(matches(isDisplayed()));
        onView(withIndex(withId(R.id.delete_news_item_image_view),0)).perform(click());
        NewsCreate.cancelButtonConfirmation.perform(click());
    }

    public static void clickEditNewsButton() {
       onView(withIndex(withId(R.id.edit_news_item_image_view),0)).check(matches(isDisplayed()));
        onView(withIndex(withId(R.id.edit_news_item_image_view),0)).perform(click());
    }
}
