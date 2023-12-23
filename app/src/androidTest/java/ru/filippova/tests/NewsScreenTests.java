package ru.filippova.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


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

import ru.filippova.steps.AuthorizationSteps;
import ru.filippova.steps.ControlPanelSteps;
import ru.filippova.steps.FilterNewsSteps;
import ru.filippova.steps.MainScreenSteps;
import ru.filippova.steps.NavigationBarSteps;
import ru.filippova.steps.NewsScreenSteps;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NewsScreenTests {

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    /*@Before
    public void login() throws InterruptedException {
        AuthorizationSteps.login();
        MainScreenSteps.mainScreenLoad();
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
    public void newsElementsCheck() {
        MainScreenSteps.mainScreenLoad();
        NewsScreenSteps.goToNews();
        /** Проверка элементов экрана */
        NavigationBarSteps.navigationBarElementsCheck();
        NewsScreenSteps.instrumentPanelCheck();
        MainScreenSteps.newsElementsCheck();

        /** Перехрд на экран Панели управления и возврат */
        NewsScreenSteps.clickOnControlPanelButton();
        ControlPanelSteps.isControlPanel();
        Espresso.pressBackUnconditionally();
        NewsScreenSteps.isNewsScreen();

        /** Перехрд на экран Фильтроции новостей и возврат */
        NewsScreenSteps.clickOnNewsFilterButton();
        FilterNewsSteps.isNewsFilter();
        Espresso.pressBackUnconditionally();
        NewsScreenSteps.isNewsScreen();
    }

    @Test
    public void newsExpandButton() throws InterruptedException {
        MainScreenSteps.mainScreenLoad();
        NewsScreenSteps.goToNews();
        MainScreenSteps.newsElementExpand();
        MainScreenSteps.newsElementRollUp();
        Thread.sleep(600);
    }

    /** на 21.12.2023 сортировка новостей работает некорректно (последняя новость закрепляеется на первой позиции)
     * тест падает */
    @Test
    public void newsSortButton() throws InterruptedException {
        MainScreenSteps.mainScreenLoad();
        NewsScreenSteps.goToNews();
        Thread.sleep(900);
        String firstNewsElementTitle = NewsScreenSteps.getNewsTitle();
        NewsScreenSteps.clickOnNewsSortButton();
        Thread.sleep(900);
        String sortedLastNewsElementTitle = NewsScreenSteps.getNewsTitle();
        NewsScreenSteps.clickOnNewsSortButton();
        Thread.sleep(900);
        String lastNewsNewsElementTitle = NewsScreenSteps.getNewsTitle();
        assertEquals(firstNewsElementTitle, lastNewsNewsElementTitle);
        assertNotEquals(firstNewsElementTitle, sortedLastNewsElementTitle);
    }

    @Test
    public void w_logOutAfterTests() {
        MainScreenSteps.mainScreenLoad();
        MainScreenSteps.logOut();
    }
}
