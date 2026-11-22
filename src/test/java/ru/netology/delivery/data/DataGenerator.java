package ru.netology.delivery.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public final class DataGenerator {

    private static final Faker faker = new Faker(new Locale("ru"));

    private DataGenerator() {
    }

    public static UserInfo generateUser(int meetingOffsetDays) {
        return new UserInfo(
                faker.name().lastName() + " " + faker.name().firstName(),
                generatePhone(),
                generateDate(meetingOffsetDays)
        );
    }

    public static String generatePhone() {
        int first = ThreadLocalRandom.current().nextInt(100, 1000);
        int second = ThreadLocalRandom.current().nextInt(100, 1000);
        int third = ThreadLocalRandom.current().nextInt(0, 10000);

        return String.format("+7 (%03d) %03d-%02d-%02d",
                first, second, third / 100, third % 100);
    }

    public static String generateDate(int daysToAdd) {
        return LocalDate.now().plusDays(daysToAdd)
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}