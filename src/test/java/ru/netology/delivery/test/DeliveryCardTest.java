package ru.netology.delivery.test;

import java.time.Duration;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;
import ru.netology.delivery.data.UserInfo;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCardTest {

    private UserInfo user;

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        user = DataGenerator.generateUser(4);
    }

    @Test
    void shouldRescheduleMeeting() {
        String secondDate = DataGenerator.generateDate(7);

        fillAndSubmit(user);

        $("[data-test-id=success-notification]")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Успешно!"))
                .shouldHave(Condition.text("Встреча успешно запланирована"));

        changeDateAndSubmit(secondDate);

        $("[data-test-id=replan-notification]")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Необходимо подтверждение"))
                .shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"));

        $$("button")
                .findBy(Condition.text("Перепланировать"))
                .shouldBe(Condition.visible)
                .click();

        $("[data-test-id=success-notification]")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Успешно!"))
                .shouldHave(Condition.text("Встреча успешно запланирована"));
    }

    private void fillAndSubmit(UserInfo user) {
        $("[data-test-id=city] input")
                .setValue(user.getCity());

        $("[data-test-id=date] input")
                .doubleClick();
        $("[data-test-id=date] input")
                .sendKeys(user.getDate());
        $("[data-test-id=date] input")
                .pressTab();

        $("[data-test-id=name] input")
                .setValue(user.getName());

        $("[data-test-id=phone] input")
                .setValue(user.getPhone());

        $("[data-test-id=agreement]").click();

        $$("button")
                .findBy(Condition.text("Запланировать"))
                .shouldBe(Condition.visible)
                .click();
    }

    private void changeDateAndSubmit(String date) {
        $("[data-test-id=date] input")
                .doubleClick();
        $("[data-test-id=date] input")
                .sendKeys(date);
        $("[data-test-id=date] input")
                .pressTab();

        $$("button")
                .findBy(Condition.text("Запланировать"))
                .shouldBe(Condition.visible)
                .click();
    }
}