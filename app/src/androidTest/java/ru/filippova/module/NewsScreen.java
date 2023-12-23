package ru.filippova.module;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import static ru.filippova.helpers.Helpers.withIndex;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class NewsScreen {

    public static ViewInteraction newsContainer = onView(withId(R.id.container_list_news_include));
    public static ViewInteraction newsContainerTitle = onView(allOf(withText(R.string.news),
            withParent(withParent(withId(R.id.container_list_news_include)))));
    public static ViewInteraction sortNewsButton = onView(withId(R.id.sort_news_material_button));
    public static ViewInteraction filterScreenButton = onView(withId(R.id.filter_news_material_button));
    public static ViewInteraction controlPanelScreenButton = onView(withId(R.id.edit_news_material_button));
    public static ViewInteraction lastAfterSortingNews = onView(withIndex(withId(R.id.news_item_date_text_view), 5));
    public static ViewInteraction lastNews = onView(withIndex(withId(R.id.news_item_title_text_view), 5));

    public static ViewInteraction newsElementTitle = onView(withIndex(withId(R.id.news_item_title_text_view), 0));
    public static ViewInteraction newsElementDate = onView(withIndex(withId(R.id.news_item_date_text_view), 0));

}
