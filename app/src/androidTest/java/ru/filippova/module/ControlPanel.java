package ru.filippova.module;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static ru.filippova.helpers.Helpers.withIndex;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class ControlPanel {

    public static ViewInteraction controlPanelTitle = onView(withText(R.string.news_control_panel));
    public static ViewInteraction newsCreateButton = onView(withId(R.id.add_news_image_view));
    public static ViewInteraction newsItem = onView(withIndex(withId(R.id.news_item_material_card_view),0));
    public static ViewInteraction newsCategory = onView(withIndex(withId(R.id.category_icon_image_view),0));
    public static ViewInteraction newsPublicationDateTitle = onView(withIndex(withId(R.id.news_item_publication_text_view),0));
    public static ViewInteraction newsCreationDateTitle = onView(withIndex(withId(R.id.news_item_creation_text_view),0));
    public static ViewInteraction newsCreationDate = onView(withIndex(withId(R.id.news_item_create_date_text_view),0));
    public static ViewInteraction newsAuthorTitle = onView(withIndex(withId(R.id.news_item_author_text_view),0));
    public static ViewInteraction newsAuthor = onView(withIndex(withId(R.id.news_item_author_name_text_view),0));
    public static ViewInteraction newsStatus = onView(withIndex(withId(R.id.news_item_published_text_view), 0));
    public static ViewInteraction firstNewsTitle = onView(withIndex(withId(R.id.news_item_title_text_view),0));
    public static ViewInteraction firstNewsDate = onView(withIndex(withId(R.id.news_item_publication_date_text_view),0));
    public static ViewInteraction deleteNewsButton = onView(withIndex(withId(R.id.delete_news_item_image_view),0));
    public static ViewInteraction editNewsButton = onView(withIndex(withId(R.id.edit_news_item_image_view),0));
    public static ViewInteraction expandNewsButton = onView(withIndex(withId(R.id.view_news_item_image_view),0));
    public static ViewInteraction newsDescription = onView(withIndex(withId(R.id.news_item_description_text_view),0));
    public static ViewInteraction newsDescriptionAfterExpand = onView(withIndex(withId(R.id.news_item_description_text_view),0));

}
