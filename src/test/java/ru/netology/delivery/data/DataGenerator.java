package ru.netology.delivery.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {

    private DataGenerator() {
    }

    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days)
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static UserInfo generateUser(int days) {
        Faker faker = new Faker(new Locale("ru"));

        return new UserInfo(
                faker.options().option("\u041C\u043E\u0441\u043A\u0432\u0430"),
                faker.name().fullName(),
                faker.phoneNumber().cellPhone(),
                generateDate(days)
        );
    }
}