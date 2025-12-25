package com.automation.api.questions;

import com.automation.api.models.User;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.rest.SerenityRest;

public class UserDetailsAreCorrect implements Question<Boolean> {

    private final User expectedUser;

    public UserDetailsAreCorrect(User expectedUser) {
        this.expectedUser = expectedUser;
    }

    public static UserDetailsAreCorrect forUser(User user) {
        return new UserDetailsAreCorrect(user);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        int statusCode = SerenityRest.lastResponse().statusCode();

        if (statusCode != 200) {
            return false;
        }

        String actualName = SerenityRest.lastResponse().jsonPath().getString("user.name");
        String actualEmail = SerenityRest.lastResponse().jsonPath().getString("user.email");
        String actualCity = SerenityRest.lastResponse().jsonPath().getString("user.city");
        String actualCountry = SerenityRest.lastResponse().jsonPath().getString("user.country");

        return expectedUser.getName().equals(actualName) &&
                expectedUser.getEmail().equals(actualEmail) &&
                expectedUser.getCity().equals(actualCity) &&
                expectedUser.getCountry().equals(actualCountry);
    }
}