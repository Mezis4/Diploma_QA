package ru.filippova.module;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static ru.filippova.helpers.Helpers.childAtPosition;
import static ru.filippova.helpers.Helpers.withIndex;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class ClaimCard {
    public static ViewInteraction claimTitle = onView(withId(R.id.title_label_text_view));
    public static ViewInteraction claimTitleText = onView(withId(R.id.title_text_view));
    public static ViewInteraction claimExecutor = onView(withId(R.id.executor_name_label_text_view));
    public static ViewInteraction claimExecutorText = onView(withId(R.id.executor_name_text_view));
    public static ViewInteraction claimPlaneDate = onView(withId(R.id.plane_date_label_text_view));
    public static ViewInteraction claimPlaneDateText = onView(withId(R.id.plane_date_text_view));
    public static ViewInteraction claimPlaneTimeText = onView(withId(R.id.plan_time_text_view));
    public static ViewInteraction claimDescriptionText = onView(withId(R.id.description_text_view));
    public static ViewInteraction claimAuthor = onView(withId(R.id.author_label_text_view));
    public static ViewInteraction claimAuthorText = onView(withId(R.id.author_name_text_view));
    public static ViewInteraction claimDescription = onView(withId(R.id.description_material_card_view));
    public static ViewInteraction claimStatus = onView(withId(R.id.status_label_text_view));
    public static ViewInteraction claimEditButton = onView(withId(R.id.edit_processing_image_button));
    public static ViewInteraction claimCreatedDate = onView(withId(R.id.create_data_label_text_view));
    public static ViewInteraction claimCreatedDateText = onView(withId(R.id.create_data_text_view));
    public static ViewInteraction claimCreatedTimeText = onView(withId(R.id.create_time_text_view));
    public static ViewInteraction commentText = onView(withIndex(withId(R.id.comment_description_text_view),0));
    public static ViewInteraction editCommentImage = onView(withIndex(withId(R.id.edit_comment_image_button),0));
    public static ViewInteraction commentAuthor = onView(withIndex(withId(R.id.commentator_name_text_view),0));
    public static ViewInteraction commentDateText = onView(withIndex(withId(R.id.comment_date_text_view),0));
    public static ViewInteraction commentTimeText = onView(withIndex(withId(R.id.comment_time_text_view),0));
    public static ViewInteraction addCommentButton = onView(withId(R.id.add_comment_image_button));
    public static ViewInteraction commentInput = onView(
            allOf(childAtPosition(
                    childAtPosition(
                            withId(R.id.comment_text_input_layout),
                            0),
                    1)));
    public static ViewInteraction saveCommentButton = onView(withId(R.id.save_button));
    public static ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
    public static ViewInteraction backButton = onView(withId(R.id.close_image_button));
    public static ViewInteraction statusProcessingButton = onView(withId(R.id.status_processing_image_button));
    public static ViewInteraction inProgressStatus = onView(allOf(withId(android.R.id.title), withText(R.string.take_to_work)));
    public static ViewInteraction executeStatus = onView(allOf(withId(android.R.id.title),withText(R.string.to_execute)));
    public static ViewInteraction editTextInExecute = onView(withId(R.id.editText));
    public static ViewInteraction throwOff = onView(allOf(withId(android.R.id.title),withText(R.string.throw_off)));
    public static ViewInteraction cancelStatus = onView(allOf(withId(android.R.id.title),withText(R.string.cancel_claim)));
    public static ViewInteraction okButton = onView(withId(android.R.id.button1));
    public static ViewInteraction cancelCommentButton = onView(withId(android.R.id.button2));

}
