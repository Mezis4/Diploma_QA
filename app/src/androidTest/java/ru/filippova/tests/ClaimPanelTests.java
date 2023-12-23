package ru.filippova.tests;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import ru.filippova.module.ClaimCard;
import ru.filippova.module.ClaimPanel;
import ru.filippova.steps.AuthorizationSteps;
import ru.filippova.steps.ClaimCardSteps;
import ru.filippova.steps.ClaimCreateSteps;
import ru.filippova.steps.ClaimPanelSteps;
import ru.filippova.steps.MainScreenSteps;
import ru.filippova.steps.NavigationBarSteps;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClaimPanelTests {

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);


    @Test
    public void a_setUp() {
        AuthorizationSteps.login();
    }

    @Test
    public void claimPanelElementsCheck() {
        ClaimPanelSteps.goToClaimPanelFromMain();

        /** проверка элементов экран "панель заявок" */
        NavigationBarSteps.navigationBarElementsCheck();
        ClaimPanelSteps.claimPanelElementsCheck();
        MainScreenSteps.claimCardCheck();

        /** Переход на экран создания заявки и возврат */
        ClaimPanelSteps.goToClaimCreate();
        ClaimCreateSteps.isClaimCreate();
        Espresso.pressBackUnconditionally();
        ClaimPanelSteps.isClaimPanel();

        /** Открытие фильтров заявок и закрытие через системную кнопку "Назад"
         * проверка элементов окна фильтров */
        ClaimPanelSteps.openFilters();
        ClaimPanelSteps.filtersElementsCheck();
        Espresso.pressBackUnconditionally();
        ClaimPanelSteps.isClaimPanel();

        /** переход на карточку заявки и возврат */
        ClaimPanelSteps.openClaimCard();
        ClaimCardSteps.isClaimCard();
        Espresso.pressBackUnconditionally();
        ClaimPanelSteps.isClaimPanel();
    }

    @Test
    public void claimFilters() throws InterruptedException {
        ClaimPanelSteps.goToClaimPanelFromMain();

        /** фильтрация заявки по статусу Открыта
         * по умолчанию всегда активны чекбоксы "Открыта" и "В работе" */
        ClaimPanelSteps.openFilters();
        ClaimPanelSteps.chooseFilter(ClaimPanel.filterProgressStatus);
        ClaimPanelSteps.clickOkButton();
        Thread.sleep(900);
        ClaimPanelSteps.openClaimCard();
        ClaimCardSteps.checkClaimStatus("Открыта");
        ClaimCardSteps.clickBackButton();
        ClaimPanelSteps.isClaimPanel();

        /** фильтрация заявки по статусу Открыта */
        ClaimPanelSteps.openFilters();
        ClaimPanelSteps.chooseFilter(ClaimPanel.filterOpenStatus);
        ClaimPanelSteps.chooseFilter(ClaimPanel.filterProgressStatus);
        ClaimPanelSteps.clickOkButton();
        Thread.sleep(900);
        ClaimPanelSteps.openClaimCardAgain();
        ClaimCardSteps.checkClaimStatus("В работе");
        ClaimCardSteps.clickBackButton();
        ClaimPanelSteps.isClaimPanel();

        /** фильтрация заявки по статусу Выполнена */
        ClaimPanelSteps.openFilters();
        ClaimPanelSteps.chooseFilter(ClaimPanel.filterProgressStatus);
        ClaimPanelSteps.chooseFilter(ClaimPanel.filterExecuteStatus);
        ClaimPanelSteps.clickOkButton();
        Thread.sleep(900);
        ClaimPanelSteps.openClaimCardAgain();
        ClaimCardSteps.checkClaimStatus("Выполнена");
        ClaimCardSteps.clickBackButton();
        ClaimPanelSteps.isClaimPanel();

        /** фильтрация заявки по статусу Отмененные */
        ClaimPanelSteps.openFilters();
        ClaimPanelSteps.chooseFilter(ClaimPanel.filterExecuteStatus);
        ClaimPanelSteps.chooseFilter(ClaimPanel.filterCancelledStatus);
        ClaimPanelSteps.clickOkButton();
        Thread.sleep(900);
        ClaimPanelSteps.openClaimCardAgain();
        ClaimCardSteps.checkClaimStatus("Отменена");
    }

}
