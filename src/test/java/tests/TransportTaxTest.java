package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;

public class TransportTaxTest {

    @BeforeEach
    void beforeEachTest() {

    }

    @Test
    void searchSelenideInGoogleTest() {
        open("https://google.com");

        $(By.name("q")).val("selenide").pressEnter();


        $("#res .g").shouldBe(visible).shouldHave(
                text("Selenide:"),
                text("selenide.org"));
    }



    @Test
    void checkMainTextTest() {
        Configuration.headless = true;
        open("https://asn.permkrai.ru/transport-tax/");

        //Проверяем наличие нужных текстов
        $("body").shouldHave(text("Калькулятор \"Расчет транспортного налога\""),
                                        text("Вид транспортного средства"),
                                        text("Год (налоговый период)"),
                                        text("Количество месяцев владения ТС в 2019 году"));


    }

    @Test
    void checkSuccessButtonAndTooltipTest() {
        open("https://asn.permkrai.ru/transport-tax/");

        $(byText("Рассчитать")).shouldBe(disabled).shouldHave(text("Рассчитать"));

        $("#tooltipSuccess").shouldNotHave(cssClass("show"));

        $(byText("Рассчитать")).hover();

        $("#tooltipSuccess").shouldHave(cssClass("show")).shouldHave(text("Не заполнено поле \"Вид транспортного средства\""));


        sleep(3000);
        //Todo Отвести курсор от кнопки
        //$(".success-control_button__1dySZ").;
        //Todo Проверяем,что тултип скрылся
    }

    @Test
    void checkSuccessBusTest() {
        open("https://asn.permkrai.ru/transport-tax/");

        $(".select-search_text__145KP").click();

        $("body li", 2).click();
        $(".type-of-vehicle_inputControl__3wuRp").setValue("5");
        $(byXpath("//body//label[1]")).click();
        $(".success-control_button__1dySZ").click();


        $(byXpath("//body//p[4]")).shouldHave(text("Автобусы"));
        $(byXpath("//body//p[6]")).shouldHave(text("5"));
        $(byXpath("//p[contains(text(),'250')]")).shouldBe(visible);

    }

    @Test
    void checkSuccessPageTextTest() {

        open("https://asn.permkrai.ru/transport-tax/");

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
        open("https://asn.permkrai.ru/transport-tax/");

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
