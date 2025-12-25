package com.automation.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.Alert;

public class AcceptAlert implements Interaction {

    public static AcceptAlert now() {
        return new AcceptAlert();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Alert alert = BrowseTheWeb.as(actor).getDriver().switchTo().alert();
        alert.accept();
    }
}
