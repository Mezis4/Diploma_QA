package ru.filippova.module;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class SplashScreen {

    public static ViewInteraction imageView = onView(withId(R.id.splashscreen_image_view));
    public static ViewInteraction loaderView = onView(withId(R.id.splash_screen_circular_progress_indicator));
    public static ViewInteraction textView = onView(withId(R.id.splashscreen_text_view));
}
