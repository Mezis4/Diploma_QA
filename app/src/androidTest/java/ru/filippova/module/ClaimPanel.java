package ru.filippova.module;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static ru.filippova.helpers.Helpers.childAtPosition;
import static ru.filippova.helpers.Helpers.withIndex;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class ClaimPanel {

    public static ViewInteraction claimPanelTitle = onView(withText(R.string.claims));
    public static ViewInteraction filtersButton = onView(withId(R.id.filters_material_button));
    public static ViewInteraction filtersTitle = onView(withId(R.id.claim_filter_dialog_title));
    public static ViewInteraction filterOpenStatus = onView(withId(R.id.item_filter_open));
    public static ViewInteraction filterProgressStatus = onView(withId(R.id.item_filter_in_progress));
    public static ViewInteraction filterExecuteStatus = onView(withId(R.id.item_filter_executed));
    public static ViewInteraction filterCancelledStatus = onView(withId(R.id.item_filter_cancelled));
    public static ViewInteraction filterOkButton = onView(withId(R.id.claim_list_filter_ok_material_button));
    public static ViewInteraction filterCancelButton = onView(withId(R.id.claim_filter_cancel_material_button));
    public static ViewInteraction addClaimButton = onView(allOf(withId(R.id.add_new_claim_material_button), withContentDescription(R.string.add_claim_button),
            childAtPosition(
                    childAtPosition(
                            withId(R.id.container_list_claim_include),
                            0),
                    2)));
    public static ViewInteraction claimCard = onView(withIndex(withId(R.id.claim_list_card),0));
    public static ViewInteraction claimCardTitle = onView(withIndex(withId(R.id.description_material_text_view),0));
    public static ViewInteraction claimDate = onView(withIndex(withId(R.id.plan_date_material_text_view),0));
}
