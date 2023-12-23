package ru.filippova.module;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.hasLinks;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import static ru.filippova.helpers.Helpers.childAtPosition;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class FilterNews {
    public static String categoryName = "Праздник";
    public static String noNewsCategory = "Массаж";

    public static ViewInteraction newsContainer = onView(withId(R.id.filter_news_title_text_view));
    public static ViewInteraction categoryInput = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public static ViewInteraction dateInputFrom = onView(withId(R.id.news_item_publish_date_start_text_input_edit_text));
    public static ViewInteraction dateInputTo = onView(withId(R.id.news_item_publish_date_end_text_input_edit_text));
    public static ViewInteraction activeFilter = onView(allOf(withId(R.id.filter_news_active_material_check_box), withText(R.string.news_item_active)));
    public static ViewInteraction notActiveFilter = onView(allOf(withId(R.id.filter_news_inactive_material_check_box), withText(R.string.news_item_not_active)));
    public static ViewInteraction acceptFiltersButton = onView(withId(R.id.filter_button));
    public static ViewInteraction cancelFiltersButton = onView(withId(R.id.cancel_button));
    public static ViewInteraction categoryListItem = onView(withText(categoryName));
    public static ViewInteraction categoryListItemWithNoNews = onView(withText(noNewsCategory));
    public static ViewInteraction errorIconOnNews = onView(withId(R.id.empty_news_list_image_view));
    public static ViewInteraction errorMessageOnNews = onView(withId(R.id.empty_news_list_text_view));
    public static ViewInteraction refreshButtonOnNews = onView(withId(R.id.news_retry_material_button));
    public static ViewInteraction errorIconOnControlPanel = onView(withId(R.id.control_panel_empty_news_list_image_view));
    public static ViewInteraction errorMessageOnControlPanel = onView(withId(R.id.control_panel_empty_news_list_text_view));
    public static ViewInteraction refreshButtonOnControlPanel = onView(withId(R.id.control_panel_news_retry_material_button));

}
