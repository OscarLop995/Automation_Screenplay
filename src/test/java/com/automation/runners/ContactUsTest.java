package com.automation.runners;

import com.automation.questions.SuccessMessageIsVisible;
import com.automation.tasks.OpenHomePage;
import com.automation.tasks.SendContactForm;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.annotations.Managed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

@RunWith(SerenityRunner.class)
public class ContactUsTest {

    @Managed(driver = "chrome")
    WebDriver driver;

    private Actor user;

    @Before
    public void setUp() {
        user = Actor.named("Usuario")
                .whoCan(BrowseTheWeb.with(driver));
    }

    @Test
    public void ShowContactFormSuccessfully() {
        user.attemptsTo(
                OpenHomePage.open(),
                SendContactForm.withValidData()
        );

        user.should(
                seeThat(
                        "El mensaje de éxito debe ser visible",
                        SuccessMessageIsVisible.displayed(),
                        is(true)
                )
        );
    }
}