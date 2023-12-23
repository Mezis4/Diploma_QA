package ru.filippova.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import static ru.filippova.helpers.Helpers.withIndex;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.filippova.helpers.Helpers;
import ru.filippova.module.ControlPanel;
import ru.filippova.module.NewsCreate;
import ru.filippova.steps.AuthorizationSteps;
import ru.filippova.steps.ControlPanelSteps;
import ru.filippova.steps.FilterNewsSteps;
import ru.filippova.steps.MainScreenSteps;
import ru.filippova.steps.NavigationBarSteps;
import ru.filippova.steps.NewsCreateSteps;
import ru.filippova.steps.NewsScreenSteps;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ControlPanelTests {

    private String newNewsCategory = "Праздник";
    private String newNewsTitle = "Блоб";
    private String newNewsDescription = "Блоб";

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    /*@Before
    public void login() {
        AuthorizationSteps.login();
        ControlPanelSteps.goToControlPanel();
    }

    @After
    public void logOut() {
        MainScreenSteps.logOut();
    }*/

    @Test
    public void A_setUp() {
        AuthorizationSteps.login();
    }

    @Test
    public void controlPanelElementsAndGoTOScreenCheck() {
        /** Проверка элементов экрана Панель управления */
        ControlPanelSteps.goToControlPanel();
        NavigationBarSteps.navigationBarElementsCheck();
        ControlPanelSteps.controlPanelBarCheck();
        ControlPanelSteps.controlPanelNewsElementsCheck();

        /** Переход на экран Фильтровать Новости и возвращение назад */
        ControlPanelSteps.clickNewsFilterFromControlPanel();
        FilterNewsSteps.isNewsFilter();
        Espresso.pressBackUnconditionally();
        ControlPanelSteps.isControlPanel();

        /** Переход на экран Создания новости и возвращение назад */
        ControlPanelSteps.clickNewsCreationButton();
        NewsCreateSteps.isNewsCreate();
        Espresso.pressBackUnconditionally();
        ControlPanelSteps.isControlPanel();
    }

    /** баг с измением стартовой позиции пользователя после нажатия на кнопку сортировки. Тест может падать*/
    @Test
    public void sortButtonNewsCheck() throws InterruptedException {
        ControlPanelSteps.goToControlPanel();
        ControlPanelSteps.waitForLoadControlPanel();
        String firstNewsTitle = ControlPanelSteps.getFirstNewsTitle();
        NewsScreenSteps.clickOnNewsSortButton();
        Thread.sleep(900);
        String sortedFirsNewsTitle = ControlPanelSteps.getFirstNewsTitle();;
        NewsScreenSteps.clickOnNewsSortButton();
        Thread.sleep(900);
        String originFirstNewsNewsTitle = ControlPanelSteps.getFirstNewsTitle();;
        assertEquals(firstNewsTitle, originFirstNewsNewsTitle);
        assertNotEquals(firstNewsTitle, sortedFirsNewsTitle);
    }

    @Test public void newsExpandAndRollUp() throws InterruptedException {
        ControlPanelSteps.goToControlPanel();
        ControlPanelSteps.waitForLoadControlPanel();
        ControlPanelSteps.expandNewsButton();
        Thread.sleep(900);
        ControlPanelSteps.rollUpNewsButton();
    }

    @Test
    public void newsDelete() throws InterruptedException {
        /** Отмена удаления новости */
        ControlPanelSteps.goToControlPanel();
        String firstNewsTitle = ControlPanelSteps.getFirstNewsTitle();
        ControlPanelSteps.clickCancelAfterDeleteButton();
        ControlPanelSteps.waitForLoadControlPanel();
        String newsTitleAfterCancel = ControlPanelSteps.getFirstNewsTitle();
        assertEquals(firstNewsTitle, newsTitleAfterCancel);

        /** Удаление новости */
        ControlPanelSteps.deleteNews();
        Thread.sleep(1200);
        String newsTitleAfterDelete = ControlPanelSteps.getFirstNewsTitle();
        assertNotEquals(firstNewsTitle, newsTitleAfterDelete);
    }

    @Test
    public void newsEdit() throws InterruptedException {
        /** сбор данных из новостных элементов */
        ControlPanelSteps.goToControlPanel();
        ControlPanelSteps.waitForLoadControlPanel();
        ControlPanelSteps.clickEditNewsButton();
        NewsCreateSteps.isEditNews();
        Thread.sleep(900);
        String firstNewsTitle = NewsCreateSteps.getNewsTitle();
        String firstNewsDate = NewsCreateSteps.getNewsDate();
        String firstNewsTime = NewsCreateSteps.getNewsTime();
        String firstNewsCategory = NewsCreateSteps.getNewsCategory();
        String firstNewsDescription = NewsCreateSteps.getNewsDescription();
        String firstNewsStatus = NewsCreateSteps.getNewsStatus();

        /** изменение данных */
        NewsCreateSteps.typeCategory(newNewsCategory);
        NewsCreateSteps.typeTitle(newNewsTitle);
        NewsCreateSteps.typeRandomDate();
        NewsCreateSteps.typeTime();
        NewsCreateSteps.typeDescription(newNewsDescription);
        NewsCreateSteps.clickSwitcherButton();
        NewsCreateSteps.clickSaveButton();

        /** сбор измененных данных */
        ControlPanelSteps.waitForLoadControlPanel();
        String changedNewsTitle = ControlPanelSteps.getFirstNewsTitle();
        String changedNewsDate = ControlPanelSteps.getNewsDate();
        String changedNewsStatus = ControlPanelSteps.checkNewsStatus();

        ControlPanelSteps.clickEditNewsButton();
        String changedNewsCategory = NewsCreateSteps.getNewsCategory();
        String changedNewsTime = NewsCreateSteps.getNewsTime();
        String changedNewsDescription = NewsCreateSteps.getNewsDescription();

        /** проверка данных */
        Espresso.pressBackUnconditionally();
        assertNotEquals(firstNewsCategory, changedNewsCategory);
        assertNotEquals(firstNewsTitle, changedNewsTitle);
        assertNotEquals(firstNewsDate, changedNewsDate);
        assertNotEquals(firstNewsTime, changedNewsTime);
        assertNotEquals(firstNewsDescription, changedNewsDescription);
        assertNotEquals(firstNewsStatus, changedNewsStatus);
    }

    @Test
    public void saveEditNewsWithOneEmptyFieldAndCancelEdit() throws InterruptedException {
        /** сохранение изменений с одним пустым полем */
        ControlPanelSteps.goToControlPanel();
        ControlPanelSteps.waitForLoadControlPanel();
        ControlPanelSteps.clickEditNewsButton();
        NewsCreateSteps.makeEmptyFieldInEdit();
        NewsCreateSteps.clickSaveButton();
        Thread.sleep(600);
        NewsCreateSteps.isNewsCreate();
        Espresso.pressBackUnconditionally();

        ControlPanelSteps.waitForLoadControlPanel();
        String firstNewsTitle = ControlPanelSteps.getFirstNewsTitle();
        /** Отмена подтверждения выхода из редактирования новости */
        ControlPanelSteps.waitForLoadControlPanel();
        ControlPanelSteps.clickEditNewsButton();
        NewsCreateSteps.clickCancelForCancelButton();

        /** Подтверждение выхода из редактирования новости */
        NewsCreateSteps.clickConfirmationForCancelButton();
        ControlPanelSteps.waitForLoadControlPanel();
        String newsTitleAfterCancelEdit = ControlPanelSteps.getFirstNewsTitle();
        assertEquals(firstNewsTitle, newsTitleAfterCancelEdit);
    }

    @Test
    public void w_logOutAfterTests() {
        MainScreenSteps.mainScreenLoad();
        MainScreenSteps.logOut();
    }
}
