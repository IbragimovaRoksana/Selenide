package ru.netology.web;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @Test
    void shouldRegisterCard(){
        open("http://localhost:9999");
        LocalDate today = LocalDate.now();
        LocalDate futureDay = today.plusDays(4);
        $("[data-test-id='city'] .input__control").setValue("Москва");
        $("[data-test-id='date'] .input__control").setValue(futureDay.toString());
        $("[data-test-id='name'] .input__control").setValue("Андрей Балахонцев");
        $("[data-test-id='phone'] .input__control").setValue("+78005553535");
        $("[data-test-id='agreement'] .checkbox__box").click();
        //$("[type='button'] .button__content").click();
        //$(".button").click();
        $$("[type='button']").find(exactText("Забронировать")).click();
        $(withText("Успешно")).shouldBe(appear, Duration.ofSeconds(11));
    }

    @Test
    void shouldRegisterCardWithChange(){
        open("http://localhost:9999");
        LocalDate today = LocalDate.now();
        LocalDate futureDay = today.plusDays(7);
        Integer day = futureDay.getDayOfMonth();

        $("[data-test-id='city'] .input__control").setValue("Ка");
        $$(".menu-item__control").find(exactText("Казань")).click();
        $(".icon_name_calendar").click();
        if (day<8)
            $(".calendar__arrow[data-step='1']").click();

        $$(".calendar__layout .calendar__row .calendar__day").find(exactText(day.toString())).click();


        $("[data-test-id='name'] .input__control").setValue("Андрей Балахонцев");
        $("[data-test-id='phone'] .input__control").setValue("+78005553535");
        $("[data-test-id='agreement'] .checkbox__box").click();
        //$("[type='button'] .button__content").click();
        //$(".button").click();
        $$("[type='button']").find(exactText("Забронировать")).click();
        $(withText("Успешно")).shouldBe(appear, Duration.ofSeconds(11));
    }
}
