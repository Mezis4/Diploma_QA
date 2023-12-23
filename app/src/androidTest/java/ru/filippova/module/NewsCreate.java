package ru.filippova.module;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;


import ru.filippova.helpers.Helpers;
import ru.iteco.fmhandroid.R;

public class NewsCreate {

    public static String categoryName = "Объявление";

    public static ViewInteraction screenTitle = onView(withId(R.id.custom_app_bar_title_text_view));
    public static ViewInteraction categoryField = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public static ViewInteraction newsTitle = onView(withId(R.id.news_item_title_text_input_edit_text));
    public static ViewInteraction publicationDate = onView(withId(R.id.news_item_publish_date_text_input_edit_text));

    public static ViewInteraction publicationTime = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    public static ViewInteraction newsDescription = onView(withId(R.id.news_item_description_text_input_edit_text));
    public static ViewInteraction newsSwitcher = onView(withId(R.id.switcher));
    public static ViewInteraction saveButton = onView(withId(R.id.save_button));
    public static ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
    public static ViewInteraction categoryListItem = onView(withText(categoryName));
    public static ViewInteraction categoryOpenListButton = onView(Helpers.withIndex(withId(R.id.text_input_end_icon), 0));
    public static ViewInteraction cancelButtonConfirmation = onView(allOf(withId(android.R.id.button1), withText("OK")));

    public static ViewInteraction cancelButtonForConfirmation = onView(allOf(withId(android.R.id.button2), withText("Отмена")));

}
