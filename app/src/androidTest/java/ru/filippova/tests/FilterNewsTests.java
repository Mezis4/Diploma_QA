package ru.filippova.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.filippova.helpers.Helpers.withIndex;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import ru.filippova.module.FilterNews;
import ru.filippova.steps.AuthorizationSteps;
import ru.filippova.steps.ControlPanelSteps;
import ru.filippova.steps.FilterNewsSteps;
import ru.filippova.steps.MainScreenSteps;
import ru.filippova.steps.NewsScreenSteps;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FilterNewsTests {

    private static String invalidDate = "01.10.1989";
    private static String categoryName = "Праздник";
    public static String invalidCategory = "Котики";

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    /*@Before
    public void login() throws InterruptedException {
        AuthorizationSteps.login();
        Thread.sleep(3000);
        MainScreenSteps.isMainScreen();
        NavigationBarSteps.goOnNewsScreenFromMain();
        NewsScreenSteps.isNewsScreen();
    }

    @After
    public void logOut() {
        MainScreenSteps.logOut();
    }*/

    @Test
    public void a_setUp() {
        AuthorizationSteps.login();
    }

    @Test
    public void newsFilterElementsCheck() {
        /** проверка экрана фильтров для фильтров с "Новости" и для фильтров с "Панель управлления" */
        FilterNewsSteps.goToFiltersFromNews();
        FilterNewsSteps.waitForLoadFilters();
        FilterNewsSteps.filterNewsElementsCheck();
    }

    @Test
    public void newsFilterFromNewsPositiveChecks() {
        FilterNewsSteps.goToFiltersFromNews();
        FilterNewsSteps.waitForLoadFilters();

        /** Тап на кнопку "Отмена" */
        FilterNewsSteps.clickOnCancelButton();
        NewsScreenSteps.isNewsScreen();

        /** Ввод с существующего названия категории в поле "Категория" и выбор из выпадающего меню */
        NewsScreenSteps.clickOnNewsFilterButton();
        FilterNewsSteps.selectCategoryFromList();
        Espresso.closeSoftKeyboard();
        FilterNews.categoryInput.check(matches(withText(categoryName)));

        /** Отображение заглушки при отсутствии новостей по существующей категории из фильтра */
        FilterNewsSteps.selectCategoryFromList();
        FilterNewsSteps.selectCategoryWithNoNewsFromList();
        FilterNewsSteps.clickOnAcceptFiltersButton();
        NewsScreenSteps.isNewsScreen();
        FilterNewsSteps.errorScreenCheckOnNews();

        /** Тап на кнопку "Фильтровать" без фильтров (сбрасываем фильтры) */
        NewsScreenSteps.clickOnNewsFilterButton();
        FilterNewsSteps.clickOnAcceptFiltersButton();
        NewsScreenSteps.isNewsScreen();
    }

    @Test
    public void newsFilterFromControlPanelPositiveChecks() {
        FilterNewsSteps.goToFiltersFromControlPanel();
        FilterNewsSteps.waitForLoadFilters();

        /** Тап на кнопку "Отмена" */
        FilterNewsSteps.clickOnCancelButton();
        ControlPanelSteps.isControlPanel();

        /** Ввод с существующего названия категории в поле "Категория" и выбор из выпадающего меню */
        ControlPanelSteps.clickNewsFilterFromControlPanel();
        FilterNewsSteps.selectCategoryFromList();
        Espresso.closeSoftKeyboard();
        FilterNews.categoryInput.check(matches(withText(categoryName)));

        /** Отображение заглушки при отсутствии новостей по существующей категории из фильтра */
        FilterNewsSteps.selectCategoryWithNoNewsFromList();
        FilterNewsSteps.clickOnAcceptFiltersButton();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.errorScreenCheckOnControlPanel();

        /** Тап на кнопку "Фильтровать" без фильтров (сбрасываем фильтры) */
        ControlPanelSteps.clickNewsFilterFromControlPanel();
        FilterNewsSteps.clickOnAcceptFiltersButton();
        ControlPanelSteps.isControlPanel();

        /** Фильтрация новостей по статусу "Активна" */
        ControlPanelSteps.clickNewsFilterFromControlPanel();
        FilterNewsSteps.clickNotActiveFilter();
        FilterNewsSteps.clickOnAcceptFiltersButton();
        ControlPanelSteps.isControlPanel();
        onView(withIndex(withText(R.string.news_item_active), 0)).check(matches(isDisplayed()));
        onView(withIndex(withText(R.string.news_item_not_active), 0)).check(doesNotExist());

        /** Тап на кнопку "Фильтровать" без фильтров (сбрасываем фильтры) */
        ControlPanelSteps.clickNewsFilterFromControlPanel();
        FilterNewsSteps.clickOnAcceptFiltersButton();
        ControlPanelSteps.isControlPanel();

        /** Фильтрация новостей по статусу "Неактивна" */
        ControlPanelSteps.clickNewsFilterFromControlPanel();
        FilterNewsSteps.clickActiveFilter();
        FilterNewsSteps.clickOnAcceptFiltersButton();
        ControlPanelSteps.isControlPanel();
        onView(withIndex(withText(R.string.news_item_active), 0)).check(doesNotExist());
    }

    @Test
    public void newsFilterFromNewsNegativeChecks() {
        FilterNewsSteps.goToFiltersFromNews();
        FilterNewsSteps.waitForLoadFilters();

        /** Отображение заглушки при отсутствии новостей за период времени из фильтра
         * Тап на кнопку обновить */
        FilterNewsSteps.chooseDateFromTo(invalidDate);
        FilterNewsSteps.clickOnAcceptFiltersButton();
        NewsScreenSteps.isNewsScreen();
        FilterNewsSteps.errorScreenCheckOnNews();

        FilterNewsSteps.clickRefreshButtonOnNews();
        FilterNewsSteps.errorScreenCheckOnNews();

        /** Ввод несуществующего названия категории в поле "Категория" */
        NewsScreenSteps.clickOnNewsFilterButton();
        FilterNewsSteps.typeInvalidCategory();
        FilterNews.categoryInput.check(matches(withText(invalidCategory)));
        FilterNews.categoryListItem.check(doesNotExist());
        FilterNewsSteps.clickOnAcceptFiltersButton();
    }

    @Test
    public void newsFilterFromControlPanelNegativeChecks() {
        FilterNewsSteps.goToFiltersFromControlPanel();
        FilterNewsSteps.waitForLoadFilters();

        /** Отображение заглушки при отсутствии новостей за период времени из фильтра
         * Тап на кнопку обновить */
        FilterNewsSteps.chooseDateFromTo(invalidDate);
        FilterNewsSteps.clickOnAcceptFiltersButton();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.errorScreenCheckOnControlPanel();

        ControlPanelSteps.clickRefreshButtonOnNews();
        ControlPanelSteps.errorScreenCheckOnControlPanel();

        /** Ввод несуществующего названия категории в поле "Категория" */
        ControlPanelSteps.clickNewsFilterFromControlPanel();
        FilterNewsSteps.typeInvalidCategory();
        FilterNews.categoryInput.check(matches(withText(invalidCategory)));
        FilterNews.categoryListItem.check(doesNotExist());
        FilterNewsSteps.clickOnAcceptFiltersButton();
    }

    @Test
    public void changeFiltersOnNews() {
        NewsScreenSteps.waitForLoadNews();
        NewsScreenSteps.goToNews();

        /** Фильтрация новостей по отрезку времени */
        String newsDate = FilterNewsSteps.getNewsDateOnNews();
        NewsScreenSteps.clickOnNewsFilterButton();
        FilterNewsSteps.selectCategoryFromList();
        FilterNewsSteps.chooseDateFromTo(newsDate);
        FilterNewsSteps.clickOnAcceptFiltersButton();
        NewsScreenSteps.isNewsScreen();
        onView(withIndex(withText(newsDate),0)).check(matches(isDisplayed()));

        /** смена валидных параметров фильтрации на невалидные параметры фильтрации */
        NewsScreenSteps.clickOnNewsFilterButton();
        FilterNewsSteps.chooseDateFromTo(invalidDate);
        FilterNewsSteps.clickOnAcceptFiltersButton();
        FilterNewsSteps.errorScreenCheckOnNews();

        /** смена невалидных параметров фильтрации на валидные параметры фильтрации */
        NewsScreenSteps.clickOnNewsFilterButton();
        FilterNewsSteps.selectCategoryFromList();
        FilterNewsSteps.chooseDateFromTo(newsDate);
        FilterNewsSteps.clickOnAcceptFiltersButton();
        NewsScreenSteps.isNewsScreen();
        onView(withIndex(withText(newsDate), 0)).check(matches(isDisplayed()));
    }

    @Test
    public void changeFiltersOnControlPanel() {
        ControlPanelSteps.goToControlPanel();

        /** Фильтрация новостей по отрезку времени */
        String newsDate = ControlPanelSteps.getNewsDate();
        ControlPanelSteps.clickNewsFilterFromControlPanel();
        FilterNewsSteps.selectCategoryFromList();
        FilterNewsSteps.chooseDateFromTo(newsDate);
        FilterNewsSteps.clickOnAcceptFiltersButton();
        ControlPanelSteps.isControlPanel();
        onView(withIndex(withText(newsDate),0)).check(matches(isDisplayed()));

        /** смена валидных параметров фильтрации на невалидные параметры фильтрации */
        ControlPanelSteps.clickNewsFilterFromControlPanel();
        FilterNewsSteps.chooseDateFromTo(invalidDate);
        FilterNewsSteps.clickOnAcceptFiltersButton();
        ControlPanelSteps.errorScreenCheckOnControlPanel();

        /** смена невалидных параметров фильтрации на валидные параметры фильтрации */
        ControlPanelSteps.clickNewsFilterFromControlPanel();
        FilterNewsSteps.selectCategoryFromList();
        FilterNewsSteps.chooseDateFromTo(newsDate);
        FilterNewsSteps.clickOnAcceptFiltersButton();
        ControlPanelSteps.isControlPanel();
        onView(withIndex(withText(newsDate), 0)).check(matches(isDisplayed()));
    }

    /** при написании названия категории без ее выбора в выпадающем списке не происходиn фильтрации.
     * тест падает */
    @Test
    public void noNewsErrorScreenForValidCategoryOnNews() {
        FilterNewsSteps.goToFiltersFromControlPanel();
        FilterNewsSteps.waitForLoadFilters();
        FilterNewsSteps.typeCategoryWithNoNews();
        FilterNewsSteps.clickOnAcceptFiltersButton();
        NewsScreenSteps.isNewsScreen();
        FilterNewsSteps.errorScreenCheckOnNews();
    }

    /** при написании названия категории без ее выбора в выпадающем списке не происходит фильтрации.
     * тест падает */
    @Test
    public void noNewsErrorScreenForValidCategoryOnControlPanel() {
        FilterNewsSteps.goToFiltersFromControlPanel();
        FilterNewsSteps.waitForLoadFilters();
        FilterNewsSteps.typeCategoryWithNoNews();
        FilterNewsSteps.clickOnAcceptFiltersButton();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.errorScreenCheckOnControlPanel();
    }

    /** при написании названия категории без ее выбора в выпадающем списке не происходит фильтрации.
     * тест падает */
    @Test
    public void noNewsErrorScreenForInvalidCategory() {
        FilterNewsSteps.goToFiltersFromControlPanel();
        FilterNewsSteps.waitForLoadFilters();
        FilterNewsSteps.typeInvalidCategory();
        FilterNewsSteps.clickOnAcceptFiltersButton();
        NewsScreenSteps.isNewsScreen();
        FilterNewsSteps.errorScreenCheckOnNews();
    }

    /** при написании названия категории без ее выбора в выпадающем списке не происходит фильтрации.
     * тест падает */
    @Test
    public void noNewsErrorScreenForInvalidCategoryOnControlPanel() {
        FilterNewsSteps.goToFiltersFromControlPanel();
        FilterNewsSteps.waitForLoadFilters();
        ControlPanelSteps.clickNewsFilterFromControlPanel();
        FilterNewsSteps.typeInvalidCategory();
        FilterNewsSteps.clickOnAcceptFiltersButton();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.errorScreenCheckOnControlPanel();
    }

    @Test
    public void w_logOutAfterTests() {
        MainScreenSteps.mainScreenLoad();
        MainScreenSteps.logOut();
    }
}
