package ru.filippova.module;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;

import ru.filippova.helpers.Helpers;
import ru.iteco.fmhandroid.R;

public class ClaimCreate {

    public static String categoryName = "Ivanov Ivan Ivanovich";

    public static ViewInteraction screenTitle = onView(withId(R.id.custom_app_bar_title_text_view));
    public static ViewInteraction claimTitle = onView(withId(R.id.title_edit_text));
    public static ViewInteraction executorField = onView(withId(R.id.executor_drop_menu_auto_complete_text_view));
    public static ViewInteraction claimDate = onView(withId(R.id.date_in_plan_text_input_edit_text));
    public static ViewInteraction claimTime = onView(withId(R.id.time_in_plan_text_input_edit_text));
    public static ViewInteraction newsDescription = onView(withId(R.id.description_edit_text));
    public static ViewInteraction saveButton = onView(withId(R.id.save_button));
    public static ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
    public static ViewInteraction executorListItem = onView(withText(categoryName));
    public static ViewInteraction errorMessage = onView(withId(android.R.id.message));
    public static ViewInteraction errorOk = onView(withId(android.R.id.button1));
    public static ViewInteraction cancelButtonConfirmation = onView(allOf(withId(android.R.id.button1), withText("OK")));

    public static ViewInteraction cancelButtonForConfirmation = onView(allOf(withId(android.R.id.button2), withText("Отмена")));
}
