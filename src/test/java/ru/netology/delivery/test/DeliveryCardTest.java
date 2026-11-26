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
                .shouldHave(Condition.text("\u0423\u0441\u043F\u0435\u0448\u043D\u043E!"))
                .shouldHave(Condition.text("\u0412\u0441\u0442\u0440\u0435\u0447\u0430 \u0443\u0441\u043F\u0435\u0448\u043D\u043E \u0437\u0430\u043F\u043B\u0430\u043D\u0438\u0440\u043E\u0432\u0430\u043D\u0430"));

        changeDateAndSubmit(secondDate);

        $("[data-test-id=replan-notification]")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("\u041D\u0435\u043E\u0431\u0445\u043E\u0434\u0438\u043C\u043E \u043F\u043E\u0434\u0442\u0432\u0435\u0440\u0436\u0434\u0435\u043D\u0438\u0435"))
                .shouldHave(Condition.text("\u0423 \u0432\u0430\u0441 \u0443\u0436\u0435 \u0437\u0430\u043F\u043B\u0430\u043D\u0438\u0440\u043E\u0432\u0430\u043D\u0430 \u0432\u0441\u0442\u0440\u0435\u0447\u0430 \u043D\u0430 \u0434\u0440\u0443\u0433\u0443\u044E \u0434\u0430\u0442\u0443. \u041F\u0435\u0440\u0435\u043F\u043B\u0430\u043D\u0438\u0440\u043E\u0432\u0430\u0442\u044C?"));

        $$("button")
                .findBy(Condition.text("\u041F\u0435\u0440\u0435\u043F\u043B\u0430\u043D\u0438\u0440\u043E\u0432\u0430\u0442\u044C"))
                .shouldBe(Condition.visible)
                .click();

        $("[data-test-id=success-notification]")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("\u0423\u0441\u043F\u0435\u0448\u043D\u043E!"))
                .shouldHave(Condition.text("\u0412\u0441\u0442\u0440\u0435\u0447\u0430 \u0443\u0441\u043F\u0435\u0448\u043D\u043E \u0437\u0430\u043F\u043B\u0430\u043D\u0438\u0440\u043E\u0432\u0430\u043D\u0430"));
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
                .findBy(Condition.text("\u0417\u0430\u043F\u043B\u0430\u043D\u0438\u0440\u043E\u0432\u0430\u0442\u044C"))
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
                .findBy(Condition.text("\u0417\u0430\u043F\u043B\u0430\u043D\u0438\u0440\u043E\u0432\u0430\u0442\u044C"))
                .shouldBe(Condition.visible)
                .click();
    }
}