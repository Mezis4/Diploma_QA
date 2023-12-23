package ru.filippova.module;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import static ru.filippova.helpers.Helpers.withIndex;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class MainScreen {
    public static ViewInteraction navigationBar = onView(withId(
            R.id.container_custom_app_bar_include_on_fragment_main));
    public static ViewInteraction newsListOnMain = onView(withId(R.id.container_list_news_include_on_fragment_main));
    public static ViewInteraction claimsListOnMain = onView(withId(R.id.container_list_claim_include_on_fragment_main));
    public static ViewInteraction claimTitle = onView((withText(R.string.claims)));
    public static ViewInteraction createClaim = onView(withId(R.id.add_new_claim_material_button));
    public static ViewInteraction expandClaimBlockButton = onView(allOf(withId(R.id.expand_material_button),
            withParent(withParent(withId(R.id.container_list_claim_include_on_fragment_main)))));
    public static ViewInteraction allClaimsButton = onView(withId(R.id.all_claims_text_view));
    public static ViewInteraction claimCard = onView(withIndex(withId(R.id.claim_list_card),0));
    public static ViewInteraction claimTitleView = onView(withIndex(withId(R.id.title_material_text_view),0));
    public static ViewInteraction claimCardTitle = onView(withIndex(withId(R.id.description_material_text_view),0));
    public static ViewInteraction claimCardExecutor = onView(withIndex(withId(R.id.executor_name_label_material_text_view),0));
    public static ViewInteraction claimDate = onView(withIndex(withId(R.id.plan_date_material_text_view),0));
    public static ViewInteraction claimTime = onView(withIndex(withId(R.id.plan_time_material_text_view),0));
    public static ViewInteraction claimCardExpandButton = onView(withIndex(withId(R.id.claim_list_card),0));
    public static ViewInteraction newsTitle = onView(
            allOf(withText(R.string.news),
                    withParent(withParent(withId(R.id.container_list_news_include_on_fragment_main)))));
    public static ViewInteraction newsExpandButton = onView(allOf(withId(R.id.expand_material_button),
            withParent(withParent(withId(R.id.container_list_news_include_on_fragment_main)))));
    public static ViewInteraction allNewsButton = onView(withId(R.id.all_news_text_view));
    public static ViewInteraction newsItems = onView(withId(R.id.news_list_recycler_view));
    public static ViewInteraction newsElement = onView(withIndex(withId(R.id.news_item_material_card_view), 0));
    public static ViewInteraction newsElementIcon = onView(withIndex(withId(R.id.category_icon_image_view), 0));
    public static ViewInteraction newsElementTitle = onView(withIndex(withId(R.id.news_item_title_text_view), 0));
    public static ViewInteraction newsElementExpand = onView(withIndex(withId(R.id.view_news_item_image_view), 0));
    public static ViewInteraction newsElementDate = onView(withIndex(withId(R.id.news_item_date_text_view), 0));
    public static ViewInteraction newsElementDescription = onView(withIndex(withId(R.id.news_item_description_text_view), 0));
    public static ViewInteraction newsElementDescriptionExpand = onView(withIndex(withId(R.id.news_item_description_text_view), 0));
}
