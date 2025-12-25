package com.automation.api.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.rest.SerenityRest;

public class UserWasCreated implements Question<Boolean> {

    public static UserWasCreated successfully() {
        return new UserWasCreated();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        int statusCode = SerenityRest.lastResponse().statusCode();
        String responseBody = SerenityRest.lastResponse().getBody().asString();

        // Log para debug
        System.out.println("=== CREATE USER RESPONSE ===");
        System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + responseBody);
        System.out.println("===========================");

        String responseMessage = SerenityRest.lastResponse().jsonPath().getString("message");

        return statusCode == 200 && "User created!".equals(responseMessage);
    }
}