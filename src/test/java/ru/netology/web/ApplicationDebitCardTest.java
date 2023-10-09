package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class ApplicationDebitCardTest {
    @Test
    void shouldTestValidValues() throws InterruptedException {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Анна Павлова");
        form.$("[data-test-id=phone] input").setValue("+79990000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
        Thread.sleep(5000);
    }

    @Test
    void shouldTestValidValuesNameWithDash() throws InterruptedException {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Анна-Мария");
        form.$("[data-test-id=phone] input").setValue("+12345678912");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
        Thread.sleep(5000);
    }

    @Test
    void shouldTestInvalidName() throws InterruptedException {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Elena");
        form.$(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        Thread.sleep(5000);
    }

    @Test
    void shouldTestInvalidPhone() throws InterruptedException {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Иван Петров");
        form.$("[data-test-id=phone] input").setValue("+799900000");
        form.$(".button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        Thread.sleep(5000);
    }

    @Test
    void shouldTestSendBlankFieldName() throws InterruptedException {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=phone] input").setValue("+79990000000");
        form.$(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
        Thread.sleep(5000);
    }

    @Test
    void shouldTestSendBlankFieldPhone() throws InterruptedException {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Иван Петров");
        form.$(".button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
        Thread.sleep(5000);
    }
}