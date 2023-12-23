package ru.filippova.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;

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

import ru.filippova.module.NewsCreate;
import ru.filippova.steps.AuthorizationSteps;
import ru.filippova.steps.ControlPanelSteps;
import ru.filippova.steps.MainScreenSteps;
import ru.filippova.steps.NewsCreateSteps;
import ru.filippova.steps.NewsScreenSteps;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NewsCreateScreenTest {

    private static String invalidCategory = "Блоб";
    private static String latinLetters = "Blob";
    private static String cyrillicWIthNumbers = "До Нового Года 10 дней";
    private static String cyrillicDescription = "Больше мандаринок";
    private static String latinDescription = "It is what it is";
    private static String symbolsDescription = "Что-то на клингонском: (),.";


    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    /*@Before
    public void login() throws InterruptedException {
        *//*AuthorizationSteps.login();*//*
        Thread.sleep(5000);
        NewsCreateSteps.goToNewsCreateScreen();
        NewsCreateSteps.isNewsCreate();
    }*/

    /*@After
    public void logOut() {
        MainScreenSteps.logOut();
    }*/

    @Test
    public void a_setUp() {
        AuthorizationSteps.login();
    }

    @Test
    public void newsCreateScreenElementsCheckOnNewsCreate() {
        NewsCreateSteps.goToNewsCreateScreen();
        NewsCreateSteps.newsCreateElementCheck();
        Espresso.pressBackUnconditionally();
    }

    @Test
    public void checkValidDataInFieldsAndCreateNews() throws InterruptedException {
        NewsCreateSteps.goToNewsCreateScreen();

        /** выбор категории из списка */
        NewsCreateSteps.selectItemFromCategoryList();

        /** ввод кириллицы в "название" */
        NewsCreateSteps.typeTitle(invalidCategory);
        String cyrillicText = NewsCreateSteps.getNewsTitle();
        assertEquals(invalidCategory, cyrillicText);

        /** ввод латиницы в "название" */
        NewsCreateSteps.typeTitle(latinLetters);
        String latinText = NewsCreateSteps.getNewsTitle();
        assertEquals(latinLetters, latinText);

        /** ввод кириллицы и чисел в "название" */
        NewsCreateSteps.typeTitle(cyrillicWIthNumbers);
        String withNumbersText = NewsCreateSteps.getNewsTitle();
        assertEquals(cyrillicWIthNumbers, withNumbersText);

        /** ввод даты в "дата" */
        NewsCreateSteps.typeDate();

        /** ввод времени в "время" */
        NewsCreateSteps.typeTime();

        /** ввод кириллицы в "описание" */
        NewsCreateSteps.typeDescription(cyrillicDescription);
        String cyrillic = NewsCreateSteps.getNewsDescription();
        assertEquals(cyrillicDescription, cyrillic);

        /** ввод латиницы в "описание" */
        NewsCreateSteps.typeDescription(latinDescription);
        String latin = NewsCreateSteps.getNewsDescription();
        assertEquals(latinDescription, latin);

        /** ввод кириллицы и чисел в "описание" */
        NewsCreateSteps.typeDescription(cyrillicWIthNumbers);
        String numbersCyrillic = NewsCreateSteps.getNewsDescription();
        assertEquals(cyrillicWIthNumbers, numbersCyrillic);

        /** ввод кириллицы и спец.символов в "описание" */
        NewsCreateSteps.typeDescription(symbolsDescription);
        String symbols = NewsCreateSteps.getNewsDescription();
        assertEquals(symbolsDescription, symbols);

        /** проверка лока статуса */
        NewsCreateSteps.clickSwitcherButton();
        NewsCreate.newsSwitcher.check(matches(withText("Активна")));

        /** создание и публикация новости с валидными данными */
        String titleField = NewsCreateSteps.getNewsTitle();
        String dateField = NewsCreateSteps.getNewsDate();
        NewsCreateSteps.clickSaveButton();
        Espresso.pressBack();
        Thread.sleep(1200);
        NewsScreenSteps.isNewsScreen();
        String newsTitle = NewsScreenSteps.getNewsTitle();
        String newsDate = NewsScreenSteps.getNewsDate();
        assertEquals(titleField, newsTitle);
        assertEquals(dateField, newsDate);
    }

    @Test
    public void createNewsWithFutureDate() throws InterruptedException {
        /** проверка создания отложенной новости */
        NewsCreateSteps.goToNewsCreateScreen();
        NewsCreateSteps.createNews();
        NewsCreateSteps.typeRandomDate();
        String dateInFuture = NewsCreateSteps.getNewsDate();
        NewsCreateSteps.clickSaveButton();
        String newsDateOnPanel = ControlPanelSteps.getNewsDate();
        Espresso.pressBack();
        Thread.sleep(1200);
        NewsScreenSteps.isNewsScreen();
        onView(withText(dateInFuture)).check(doesNotExist());
        assertEquals(dateInFuture, newsDateOnPanel);
    }

    @Test
    public void negativeChecks() throws InterruptedException {
        NewsCreateSteps.goToNewsCreateScreen();

        /** ввод несущестующей категории */
        NewsCreateSteps.typeCategory(invalidCategory);
        NewsCreate.categoryListItem.check(doesNotExist());
        String fieldText = NewsCreateSteps.getNewsCategory();
        assertEquals(invalidCategory, fieldText);

        /** сохранение новости с одним пустым полем */
        NewsCreateSteps.createNewsWithEmptyField();
        NewsCreateSteps.clickSaveButton();
        NewsCreateSteps.isNewsCreate();
        Thread.sleep(300);
        onView(withText(R.string.empty_fields))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow()
                        .getDecorView())))).check(matches(withText("Заполните пустые поля")));
    }

    /** новость создается - тест падает */
    @Test
    public void createNewsInPast() {
        NewsCreateSteps.goToNewsCreateScreen();
        /** не создается новость с датой из прошлого */
        NewsCreateSteps.createNewsWithPastDate();
        NewsCreateSteps.clickSaveButton();
        NewsCreateSteps.isNewsCreate();
        onView(withText(R.string.empty_fields))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow()
                        .getDecorView())))).check(matches(withText("Неверная дата")));
    }

    @Test
    public void cancelNewsCreation() {
        NewsCreateSteps.goToNewsCreateScreen();

        /** подтверждение закрытия */
        NewsCreateSteps.clickConfirmationForCancelButton();
        ControlPanelSteps.isControlPanel();

        /** отмена закрытия */
        ControlPanelSteps.clickNewsCreationButton();
        NewsCreateSteps.clickCancelForCancelButton();
        NewsCreateSteps.isNewsCreate();
    }

    @Test
    public void w_logOutAfterTests() {
        MainScreenSteps.mainScreenLoad();
        MainScreenSteps.logOut();
    }
}
