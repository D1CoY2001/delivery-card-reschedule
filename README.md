[![Java CI](https://github.com/D1CoY2001/delivery-card-reschedule/actions/workflows/gradle.yml/badge.svg)](https://github.com/D1CoY2001/delivery-card-reschedule/actions/workflows/gradle.yml)

# Автотесты: заказ доставки карты

Автоматизированный UI-тест на Selenide для проверки перепланирования встречи при повторной отправке формы.

## Запуск

```bash
java -jar artifacts/app-replan-delivery.jar
./gradlew test
```

## Обнаруженный дефект

При повторной отправке формы с изменённой датой приложение не предлагает перепланировать встречу.

Issue: <вставьте сюда ссылку на ваш issue>
