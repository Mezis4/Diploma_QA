package ru.filippova.module;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AboutScreen {
    public static ViewInteraction versionTitle = onView(withId(R.id.about_version_title_text_view));
    public static ViewInteraction versionNumber = onView(withId(R.id.about_version_value_text_view));
    public static ViewInteraction privacyTitle = onView(withId(R.id.about_privacy_policy_label_text_view));
    public static ViewInteraction privacyLink = onView(withId(R.id.about_privacy_policy_value_text_view));
    public static ViewInteraction termsTitle = onView(withId(R.id.about_terms_of_use_label_text_view));
    public static ViewInteraction termsLink = onView(withId(R.id.about_terms_of_use_value_text_view));
    public static ViewInteraction aboutCompany = onView(withId(R.id.about_company_info_label_text_view));
    public static ViewInteraction backButton = onView(withId(R.id.about_back_image_button));
}
