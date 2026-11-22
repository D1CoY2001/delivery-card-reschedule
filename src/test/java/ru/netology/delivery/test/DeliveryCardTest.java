package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

        $("[data-test-id=notification]")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Успешно!"))
                .shouldHave(Condition.text("Встреча успешно забронирована на " + user.getDate()));

        UserInfo updatedUser = new UserInfo(
                user.getName(),
                user.getPhone(),
                secondDate
        );

        fillAndSubmit(updatedUser);

        $("[data-test-id=notification]")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Перепланировать"));

        $$("button")
                .findBy(Condition.text("Перепланировать"))
                .shouldBe(Condition.visible)
                .click();

        $("[data-test-id=notification]")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Успешно!"))
                .shouldHave(Condition.text("Встреча успешно забронирована на " + secondDate));
    }

    private void fillAndSubmit(UserInfo user) {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").setValue(user.getDate());
        $("[data-test-id=name] input").setValue(user.getName());
        $("[data-test-id=phone] input").setValue(user.getPhone());
        $("[data-test-id=agreement]").click();

        $$("button")
                .findBy(Condition.text("Забронировать"))
                .shouldBe(Condition.visible)
                .click();
    }
}