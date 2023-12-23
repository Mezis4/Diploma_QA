package ru.filippova.module;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;

import static ru.filippova.helpers.Helpers.childAtPosition;
import static ru.filippova.helpers.Helpers.withIndex;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class NavigationBar {

    public static ViewInteraction mainMenuButton = onView(withId(R.id.main_menu_image_button));
    public static ViewInteraction imageView = onView(withId(R.id.trademark_image_view));
    public static ViewInteraction ourMissionButton = onView(withId(R.id.our_mission_image_button));
    public static ViewInteraction userButton = onView(withId(R.id.authorization_image_button));
    public static ViewInteraction logOutButton = onView(withId(android.R.id.title));
    public static ViewInteraction mainItemOnMenu = onView(withText(R.string.main));
    public static ViewInteraction claimItemOnMain = onView(withText(R.string.claims));
    public static ViewInteraction newsItemOnMenu = onView(withText(R.string.news));
    public static ViewInteraction aboutItemOnMenu = onView(withText(R.string.about));
}
