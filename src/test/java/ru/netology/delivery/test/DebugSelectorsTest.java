package ru.netology.delivery.test;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class DebugSelectorsTest {

    @Test
    void printPageStructure() {
        open("http://localhost:9999");

        System.out.println("=== DATA-TEST-ID ===");
        $$("[data-test-id]").forEach(element ->
                System.out.println(
                        element.attr("data-test-id") + " | " +
                        element.getTagName() + " | " +
                        element.getText().replace("\n", " ")
                )
        );

        System.out.println("=== INPUTS ===");
        $$("input").forEach(element ->
                System.out.println(
                        "type=" + element.attr("type") +
                        " | name=" + element.attr("name") +
                        " | placeholder=" + element.attr("placeholder") +
                        " | data-test-id=" + element.attr("data-test-id")
                )
        );

        System.out.println("=== BUTTONS ===");
        $$("button").forEach(element ->
                System.out.println(
                        "text=" + element.getText() +
                        " | type=" + element.attr("type") +
                        " | data-test-id=" + element.attr("data-test-id")
                )
        );
    }
}