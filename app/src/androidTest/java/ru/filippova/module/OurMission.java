package ru.filippova.module;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;

import static org.hamcrest.Matchers.allOf;
import static ru.filippova.helpers.Helpers.withIndex;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class OurMission {

    public static ViewInteraction ourMissionTitle = onView(withId(R.id.our_mission_title_text_view));
    public static ViewInteraction cardElement = onView(allOf(withIndex(withId(R.id.our_mission_item_material_card_view), 0), withParent(withId(R.id.our_mission_item_list_recycler_view))));
    public static ViewInteraction cardIcon = onView(withIndex(withId(R.id.our_mission_item_image_view), 0));
    public static ViewInteraction cardOpenButton = onView(allOf(withIndex(withId(R.id.our_mission_item_open_card_image_button), 0), withParent(withParent(withId(R.id.our_mission_item_material_card_view)))));
    public static ViewInteraction cardTitle = onView(withIndex(withId(R.id.our_mission_item_title_text_view), 0));
    public static ViewInteraction cardDescription = onView(withIndex(withId(R.id.our_mission_item_description_text_view), 0));
    public static ViewInteraction cardDescriptionAfterClose = onView(allOf(withIndex(withId(R.id.our_mission_item_description_text_view), 0),
            withParent(withParent(withId(R.id.our_mission_item_material_card_view)))));
}
