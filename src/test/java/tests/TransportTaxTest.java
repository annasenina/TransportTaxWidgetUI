package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;

public class TransportTaxTest {

    @BeforeEach
    void beforeEachTest() {
        open("https://asn.permkrai.ru/transport-tax/");
    }

    @Test
    void checkMainTextTest() {
        //Проверяем наличие нужных текстов
        $("body").shouldHave(text("Калькулятор \"Расчет транспортного налога\""));
        $("body").shouldHave(text("Вид транспортного средства"));
        $("body").shouldHave(text("Год (налоговый период)"));
        $("body").shouldHave(text("Количество месяцев владения ТС в 2019 году"));
    }

    @Test
    void checkSuccessButtonAndTooltipTest() {
        $(".success-control_button__1dySZ").shouldHave(text("Рассчитать"));

        $(".success-control_button__1dySZ").is(disabled);

        $(".success-control_button__1dySZ").hover();

        $(".success-control_tooltip__3yK-5").getAttribute("data-tip").compareTo("Не заполнено поле \"Вид транспортного средства\"");

        //ToDo Проверяем,что тултип показался
        //Todo Отвести курсор от кнопки
        //$(".success-control_button__1dySZ").;
        //Todo Проверяем,что тултип скрылся
    }

    @Test
    void checkSuccessBusTest() {
        $(".select-search_text__145KP").click();

        $(byXpath("//body//li[3]")).click();
        $(".type-of-vehicle_inputControl__3wuRp").setValue("5");
        $(byXpath("//body//label[1]")).click();
        $(".success-control_button__1dySZ").click();


        $(byXpath("//body//p[4]")).shouldHave(text("Автобусы"));
        $(byXpath("//body//p[6]")).shouldHave(text("5"));
        $(byXpath("//p[contains(text(),'250')]")).shouldHave(text("250"));

    }

    @Test
    void checkSuccessPageTextTest() {
        $(".select-search_text__145KP").click();

        $(byXpath("//body//li[3]")).click();
        $(".type-of-vehicle_inputControl__3wuRp").setValue("5");
        $(byXpath("//body//label[1]")).click();
        $(".success-control_button__1dySZ").click();

        $(".result_container__3JJSQ").shouldHave(text("Налог рассчитан за период"));
        $(".result_container__3JJSQ").shouldHave(text("Вид транспортного средства"));
        $(".result_container__3JJSQ").shouldHave(text("Мощность двигателя, л.с."));
        $(".result_container__3JJSQ").shouldHave(text("Ставка, руб."));
        $(".result_container__3JJSQ").shouldHave(text("Сумма налога, руб."));
        $(".result_container__3JJSQ").shouldHave(text("Уважаемые пользователи!"));
        $(".result_container__3JJSQ").shouldHave(text("Расчет транспортного налога с помощью данного сервиса носит ознакомительный характер.\n" +
                "\n" +
                "Рекомендуем Вам осуществлять оплату транспортного налога после получения налогового уведомления. Налоговое уведомление направляется не позднее 30 дней до наступления срока уплаты.\n" +
                "\n" +
                "Вы можете получить уведомление в личном кабинете налогоплательщика на сайте Федеральной налоговой службы РФ (https://www.nalog.ru)."));

    }

    @Test
    void checkSuccessPageBackButtonTest() {
        $(".select-search_text__145KP").click();

        $(byXpath("//body//li[3]")).click();
        $(".type-of-vehicle_inputControl__3wuRp").setValue("5");
        $(byXpath("//body//label[1]")).click();
        $(".success-control_button__1dySZ").click();

        $(".result_button__H2X_k").shouldHave(text("Назад"));
        $(".result_button__H2X_k").click();

        //ToDo Проверить, что вернулись на нужную страницу

    }
}
