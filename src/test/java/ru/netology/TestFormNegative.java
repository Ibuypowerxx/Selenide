package ru.netology;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.Keys.BACK_SPACE;

public class TestFormNegative {


    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    // Отправка формы с пустым полем Город
    @Test
    public void cityNull() {
        String Date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("");
        $("[data-test-id='date'] input").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id='date'] input").setValue(Date);
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("+70000000000");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldBe(visible).
                shouldHave(exactText("Поле обязательно для заполнения"));
    }
    // Заполнение поля Город на латинице
    @Test
    public void cityEnglish() {
        String Date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Kemerovo");
        $("[data-test-id='date'] input").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id='date'] input").setValue(Date);
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("+70000000000");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldBe(visible).
                shouldHave(exactText("Доставка в выбранный город недоступна"));
    }
    // Заполнение поля Город. Городом не из административного центра субъектов РФ
    @Test
    public void cityNonAdministarative() {
        String Date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Новокузнецк");
        $("[data-test-id='date'] input").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id='date'] input").setValue(Date);
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("+70000000000");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldBe(visible).
                shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    //Отправка формы с пустым полем дата встречи
    @Test
    public void dataNull() {
        String Date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Кемерово");
        $("[data-test-id='date'] input").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("+70000000000");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldBe(visible).
                shouldHave(exactText("Неверно введена дата"));
    }

    // Отправка формы с пустым именем
    @Test
    public void nameNull() {
        String Date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Кемерово");
        $("[data-test-id='date'] input").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id='date'] input").setValue(Date);
        $("[data-test-id='name'] input").setValue("");
        $("[data-test-id='phone'] input").setValue("+70000000000");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible).
                shouldHave(exactText("Поле обязательно для заполнения"));
    }
    // Заполнение поля Имя и Фамилия цифрами
    @Test
    public void nameNumber() {
        String Date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Кемерово");
        $("[data-test-id='date'] input").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id='date'] input").setValue(Date);
        $("[data-test-id='name'] input").setValue("888");
        $("[data-test-id='phone'] input").setValue("+70000000000");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible).
                shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    // Заполнение поля Имя и Фамилия символами
    @Test
    public void nameSymbol() {
        String Date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Кемерово");
        $("[data-test-id='date'] input").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id='date'] input").setValue(Date);
        $("[data-test-id='name'] input").setValue("...");
        $("[data-test-id='phone'] input").setValue("+70000000000");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible).
                shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    // Заполнение поля Фамилия и Имя на латиницу
    @Test
    public void nameEnglish() {
        String Date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Кемерово");
        $("[data-test-id='date'] input").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id='date'] input").setValue(Date);
        $("[data-test-id='name'] input").setValue("Ivanov Ivan");
        $("[data-test-id='phone'] input").setValue("+70000000000");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible).
                shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    // Отправка формы с пустым полем номер телеофна
    @Test
    public void phoneNull() {
        String Date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Кемерово");
        $("[data-test-id='date'] input").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id='date'] input").setValue(Date);
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible).
                shouldHave(exactText("Поле обязательно для заполнения"));
    }
    // Ввести номер телефона  без "+7"
    @Test
    public void invalidPhone() {
        String Date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Кемерово");
        $("[data-test-id='date'] input").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id='date'] input").setValue(Date);
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("89039080000");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible).
                shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    // Заполнение поля номер телефона символами
    @Test
    public void phoneSymbol() {
        String Date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Кемерово");
        $("[data-test-id='date'] input").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id='date'] input").setValue(Date);
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("...");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible).
                shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    //Ввести номер телефона с 12 цифрами
    @Test
    public void phoneOverEleven() {
        String Date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Кемерово");
        $("[data-test-id='date'] input").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id='date'] input").setValue(Date);
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("+790390800000");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible).
                shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    // Ввесьти номер с 1 цифрой
    @Test
    public void phoneOne() {
        String Date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Кемерово");
        $("[data-test-id='date'] input").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id='date'] input").setValue(Date);
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("+7");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible).
                shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    // Ввести номер телефона буквами
    @Test
    public void phoneWord() {
        String Date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Кемерово");
        $("[data-test-id='date'] input").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id='date'] input").setValue(Date);
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("aaa");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible).
                shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    // Не ставить галочку
    @Test
    public void agreementNull() {
        String Date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Кемерово");
        $("[data-test-id='date'] input").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id='date'] input").setValue(Date);
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("+79039080000");
        $(byText("Забронировать")).click();
        $("[data-test-id='agreement'].input_invalid .checkbox__text").shouldBe(visible).
                shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

}
