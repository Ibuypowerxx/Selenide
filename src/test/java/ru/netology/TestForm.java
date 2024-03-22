package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.Keys.BACK_SPACE;

public class TestForm {


    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }
// Заполнение валидной формы
    @Test
    public void ValidForm() {
        String Date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Кемерово");
        $("[data-test-id='date'] input").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id='date'] input").setValue(Date);
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("+70000000000");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='notification']").shouldHave(Condition.exactText("Успешно! Встреча успешно забронирована на " + Date), Duration.ofSeconds(15));
    }
   // Заполнение поля Имя и Фамилия с буквой "Ё" (Баг)
    @Test
    public void nameYo() {
        String Date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Кемерово");
        $("[data-test-id='date'] input").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id='date'] input").setValue(Date);
        $("[data-test-id='name'] input").setValue("Пётр Иванов");
        $("[data-test-id='phone'] input").setValue("+70000000000");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='notification']").shouldHave(Condition.exactText("Успешно! Встреча успешно забронирована на " + Date), Duration.ofSeconds(15));
    }
    // Заполнение двойного имени
    @Test
    public void doubleName() {
        String Date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Кемерово");
        $("[data-test-id='date'] input").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id='date'] input").setValue(Date);
        $("[data-test-id='name'] input").setValue("Карина-Елена Иванова");
        $("[data-test-id='phone'] input").setValue("+70000000000");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='notification']").shouldHave(Condition.exactText("Успешно! Встреча успешно забронирована на " + Date), Duration.ofSeconds(15));
    }
    //Заполнение поля Фамилия и Имя с буквой "Й"
    @Test
    public void nameWithJj() {
        String Date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Кемерово");
        $("[data-test-id='date'] input").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id='date'] input").setValue(Date);
        $("[data-test-id='name'] input").setValue("Иванов Йода");
        $("[data-test-id='phone'] input").setValue("+70000000000");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='notification']").shouldHave(Condition.exactText("Успешно! Встреча успешно забронирована на " + Date), Duration.ofSeconds(15));
    }
    // Заполнение города с буквой "Й"
    @Test
    public void cityWithJj() {
        String Date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Майкоп");
        $("[data-test-id='date'] input").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id='date'] input").setValue(Date);
        $("[data-test-id='name'] input").setValue("Иванов Йода");
        $("[data-test-id='phone'] input").setValue("+70000000000");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='notification']").shouldHave(Condition.exactText("Успешно! Встреча успешно забронирована на " + Date), Duration.ofSeconds(15));
    }
    //Заполнение города через тире
    @Test
    public void cityWithTire() {
        String Date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Горно-Алтайск");
        $("[data-test-id='date'] input").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id='date'] input").setValue(Date);
        $("[data-test-id='name'] input").setValue("Иванов Йода");
        $("[data-test-id='phone'] input").setValue("+70000000000");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='notification']").shouldHave(Condition.exactText("Успешно! Встреча успешно забронирована на " + Date), Duration.ofSeconds(15));
    }
    // Заполнение даты больше года
    @Test
    public void dateOverYear() {
        String Date = LocalDate.now().plusDays(370).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Майкоп");
        $("[data-test-id='date'] input").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id='date'] input").setValue(Date);
        $("[data-test-id='name'] input").setValue("Иванов Йода");
        $("[data-test-id='phone'] input").setValue("+70000000000");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='notification']").shouldHave(Condition.exactText("Успешно! Встреча успешно забронирована на " + Date), Duration.ofSeconds(15));
    }

}