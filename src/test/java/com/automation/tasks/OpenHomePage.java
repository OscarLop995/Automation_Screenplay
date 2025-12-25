package com.automation.tasks;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class OpenHomePage {

    public static Task open() {
        return Task.where(
                "{0} abre la página Automation Exercise",
                Open.url("https://automationexercise.com")
        );
    }
}
