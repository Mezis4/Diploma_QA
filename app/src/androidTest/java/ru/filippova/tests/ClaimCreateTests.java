package ru.filippova.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import ru.filippova.steps.AuthorizationSteps;
import ru.filippova.steps.ClaimCreateSteps;
import ru.filippova.steps.ClaimPanelSteps;
import ru.filippova.steps.MainScreenSteps;
import ru.filippova.steps.NewsCreateSteps;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClaimCreateTests {

    private static String cyrillicTitle = "Больше мандаринок";
    private static String latinLetters = "Blob";
    private static String cyrillicWIthNumbers = "До Нового Года 9 дней";
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
        ClaimCreateSteps.goToClaimCreate();
        ClaimCreateSteps.claimCreateElementsCheck();
    }

    @Test
    public void checkValidDataInFieldsAndCreateClaim() {
        /** создание заявки с главного экрана */
        ClaimCreateSteps.goToClaimCreate();

        /** ввод кириллицы и чисел в "тему" */
        ClaimCreateSteps.typeTitle(cyrillicWIthNumbers);
        String withNumbersText = ClaimCreateSteps.getClaimTitle();
        assertEquals(cyrillicWIthNumbers, withNumbersText);

        /** ввод латиницы в "тему" */
        ClaimCreateSteps.typeTitle(latinLetters);
        String latinText = ClaimCreateSteps.getClaimTitle();
        assertEquals(latinLetters, latinText);

        /** ввод кириллицы в "тему" */
        ClaimCreateSteps.typeTitle(cyrillicTitle);
        String cyrillicText = ClaimCreateSteps.getClaimTitle();
        assertEquals(cyrillicTitle, cyrillicText);

        /** выбор исполнителя из меню */
        ClaimCreateSteps.selectExecutor();

        /** ввод даты в "дата" */
        ClaimCreateSteps.typeDate();

        /** ввод времени в "время" */
        ClaimCreateSteps.typeTime();

        /** ввод кириллицы в "описание" */
        ClaimCreateSteps.typeDescription(cyrillicDescription);
        String cyrillic = ClaimCreateSteps.getClaimDescription();
        assertEquals(cyrillicDescription, cyrillic);

        /** ввод латиницы в "описание" */
        ClaimCreateSteps.typeDescription(latinDescription);
        String latin = ClaimCreateSteps.getClaimDescription();
        assertEquals(latinDescription, latin);

        /** ввод кириллицы и чисел в "описание" */
        ClaimCreateSteps.typeDescription(cyrillicWIthNumbers);
        String numbersCyrillic = ClaimCreateSteps.getClaimDescription();
        assertEquals(cyrillicWIthNumbers, numbersCyrillic);

        /** ввод кириллицы и спец.символов в "описание" */
        ClaimCreateSteps.typeDescription(symbolsDescription);
        String symbols = ClaimCreateSteps.getClaimDescription();
        assertEquals(symbolsDescription, symbols);

        /** создание и публикация заявки с валидными данными */
        NewsCreateSteps.clickSaveButton();
        MainScreenSteps.isMainScreen();
    }

    @Test
    public void createClaimWithFutureDateAndCheckExecutorField() {
        /** создание заявки с экрана Панель заявок */
        ClaimCreateSteps.goToClaimCreateFromClaimPanel();

        /** ввод кириллицы в поле "Исполнитель" */
        ClaimCreateSteps.typeCyrillicExecutor();

        /** ввод латиницы в поле "Исполнитель" */
        ClaimCreateSteps.typeLatinExecutor();

        /** создани заявки на будущую дату */
        ClaimCreateSteps.createClaim();
        ClaimCreateSteps.typeRandomDate();
        NewsCreateSteps.clickSaveButton();
        ClaimPanelSteps.isClaimPanel();
    }

    @Test
    public void claimCreteWithOneEmptyField() throws InterruptedException {
        ClaimCreateSteps.goToClaimCreate();

        /** сохранение заявки с одним пустым полем */
        ClaimCreateSteps.createNewsWithEmptyField();
        ClaimCreateSteps.clickSaveButton();
        Thread.sleep(300);
        ClaimCreateSteps.checkErrorWindow();
    }

    /** новость создается - тест падает,т.к. нет ошибки о вводе неккореткной даты */
    @Test
    public void createClaimInPast() {
        ClaimCreateSteps.goToClaimCreate();

        /** не создается новость с датой из прошлого */
        ClaimCreateSteps.createNewsWithPastDate();
        ClaimCreateSteps.clickSaveButton();
        ClaimCreateSteps.isClaimCreate();
        onView(withText(R.string.empty_fields))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow()
                        .getDecorView())))).check(matches(withText("Неверная дата")));
    }

    @Test
    public void cancelClaimCreation() {
        ClaimCreateSteps.goToClaimCreate();

        /** отмена закрытия */
        ClaimCreateSteps.clickCancelForCancelButton();
        ClaimCreateSteps.isClaimCreate();
        /** подтверждение закрытия */
        ClaimCreateSteps.clickConfirmationForCancelButton();
        MainScreenSteps.isMainScreen();
    }

    @Test
    public void w_logOutAfterTests() {
        MainScreenSteps.mainScreenLoad();
        MainScreenSteps.logOut();
    }


}
